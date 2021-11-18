package lostworlds.library.entity.goal.herbivore;

import lostworlds.content.ModUtils;
import lostworlds.content.server.init.BlockInit;
import lostworlds.library.entity.terrestrial.HerbivoreEggLayingEntity;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;

public class HerbivoreCreateTerritoryGoal extends MoveToBlockGoal 
{
	private final HerbivoreEggLayingEntity entity;
	private final World level;

	public HerbivoreCreateTerritoryGoal(HerbivoreEggLayingEntity entity, double speedModifier) 
	{
		super(entity, speedModifier, 16);
		this.entity = entity;
		this.level = entity.level;
	}

	@Override
	public boolean canUse() 
	{
		return !this.entity.isInWaterOrBubble() && entity.canMakeTerritory();
	}

	@Override
	public void start() 
	{
		ModUtils.LOGGER.debug("Attempting Territory");
		this.entity.setMakingTerritory(true);
	}

	@Override
	public boolean canContinueToUse() 
	{
		return !this.entity.hasTerritory() && !this.entity.isMakingTerritory();
	}

	public static boolean isNatural(IBlockReader reader, BlockPos pos) 
	{
		return reader.getBlockState(pos).is(BlockTags.SAND) || reader.getBlockState(pos).is(Tags.Blocks.DIRT);
	}

	@Override
	public void tick() 
	{
		super.tick();
		BlockPos blockpos = this.entity.blockPosition();
		if(!this.entity.isInWater()) 
		{
			this.level.playSound((PlayerEntity) null, blockpos, SoundEvents.TURTLE_LAY_EGG, SoundCategory.BLOCKS, 0.3F, 0.9F + this.level.random.nextFloat() * 0.2F);
			this.level.setBlock(blockpos.below(), BlockInit.NESTING_BLOCK.defaultBlockState(), 3);
		}
	}
	
	@Override
	public void stop() 
	{
		BlockPos blockpos = this.entity.blockPosition();
		this.entity.setHomePos(blockpos);
		this.entity.setHasTerritory(true);
		this.entity.setMakingTerritory(false);
	}

	@Override
	protected boolean isValidTarget(IWorldReader reader, BlockPos pos) 
	{
		return !reader.isEmptyBlock(pos.above()) ? false : isNatural(reader, pos);
	}
}
