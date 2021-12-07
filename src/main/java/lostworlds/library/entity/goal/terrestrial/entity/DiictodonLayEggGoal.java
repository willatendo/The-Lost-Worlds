package lostworlds.library.entity.goal.terrestrial.entity;

import java.util.Random;

import lostworlds.content.server.init.BlockInit;
import lostworlds.library.block.DiictodonBurrowBlock;
import lostworlds.library.entity.terrestrial.EggLayingEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class DiictodonLayEggGoal extends MoveToBlockGoal 
{
	private final EggLayingEntity entity;

	public DiictodonLayEggGoal(EggLayingEntity entity, double speedModifer) 
	{
		super(entity, speedModifer, 16);
		this.entity = entity;
	}

	@Override
	public boolean canUse() 
	{
		return this.entity.hasEgg() && this.entity.getHomePos().closerThan(this.entity.position(), 9.0D) ? super.canUse() : false;
	}
	
	@Override
	public void stop() 
	{
		super.stop();
		this.entity.setGoingHome(false);
	}

	@Override
	public boolean canContinueToUse() 
	{
		return super.canContinueToUse() && this.entity.hasEgg()	&& this.entity.getHomePos().closerThan(this.entity.position(), 9.0D);
	}

	@Override
	public void tick() 
	{
		super.tick();
		BlockPos blockpos = this.entity.blockPosition();
		if(!this.entity.isInWater() && this.isReachedTarget()) 
		{
			World world = this.entity.level;
			world.playSound((PlayerEntity) null, blockpos, SoundEvents.TURTLE_LAY_EGG, SoundCategory.BLOCKS, 0.3F, 0.9F + world.random.nextFloat() * 0.2F);
			Block block = BlockInit.DIICTODON_BURROW.defaultBlockState().setValue(DiictodonBurrowBlock.EGGS, Integer.valueOf(new Random().nextInt(19) + 1)).getBlock();
			world.setBlock(this.blockPos.below(), block.defaultBlockState(), 3);
			this.entity.setHasEgg(false);
			this.entity.setInLoveTime(600);
			this.entity.setInNaturalLoveTime(600);
		}
	}

	@Override
	protected boolean isValidTarget(IWorldReader reader, BlockPos pos) 
	{
		return isNatural(reader, pos);
	}

	public static boolean isNatural(IBlockReader reader, BlockPos pos) 
	{
		return reader.getBlockState(pos).is(BlockInit.PERMIAN_SAND) || reader.getBlockState(pos).is(BlockInit.DIICTODON_BURROW);
	}
}
