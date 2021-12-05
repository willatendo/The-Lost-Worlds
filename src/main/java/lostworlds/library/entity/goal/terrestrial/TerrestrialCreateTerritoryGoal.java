package lostworlds.library.entity.goal.terrestrial;

import lostworlds.library.entity.terrestrial.EggLayingEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;

public class TerrestrialCreateTerritoryGoal extends MoveToBlockGoal 
{
	private final EggLayingEntity entity;
	private final World level;

	public TerrestrialCreateTerritoryGoal(EggLayingEntity entity, double speedModifier) 
	{
		super(entity, speedModifier, 16);
		this.entity = entity;
		this.level = entity.level;
	}

	@Override
	public boolean canUse() 
	{
		return !this.entity.isInWaterOrBubble() && entity.canMakeTerritory() && this.level.isDay() && this.level.getBlockState(this.entity.blockPosition().below()) != Blocks.AIR.defaultBlockState();
	}

	@Override
	public boolean canContinueToUse() 
	{
		return !this.entity.hasTerritory();
	}

	public static boolean isNatural(IBlockReader reader, BlockPos pos) 
	{
		return reader.getBlockState(pos).is(BlockTags.SAND) || reader.getBlockState(pos).is(Tags.Blocks.DIRT);
	}
	
	@Override
	public void start() 
	{
		super.start();
		this.entity.getNavigation().stop();
	}
	
	@Override
	public void stop() 
	{
		BlockPos blockpos = this.entity.blockPosition();
		this.entity.setHomePos(blockpos);
		this.entity.setHasTerritory(true);
	}

	@Override
	protected boolean isValidTarget(IWorldReader reader, BlockPos pos) 
	{
		return !reader.isEmptyBlock(pos.above()) ? false : isNatural(reader, pos);
	}
}
