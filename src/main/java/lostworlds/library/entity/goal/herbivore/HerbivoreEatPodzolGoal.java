package lostworlds.library.entity.goal.herbivore;

import java.util.EnumSet;
import java.util.function.Predicate;

import lostworlds.library.entity.terrestrial.HerbivoreEggLayingEntity;
import lostworlds.library.entity.terrestrial.HerbivoreEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockStateMatcher;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class HerbivoreEatPodzolGoal extends Goal 
{
	private static final Predicate<BlockState> IS_TALL_GRASS = BlockStateMatcher.forBlock(Blocks.PODZOL);
	private final HerbivoreEntity entity;
	private final World level;

	public HerbivoreEatPodzolGoal(HerbivoreEntity entity) 
	{
		this.entity = entity;
		this.level = entity.level;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
	}

	@Override
	public boolean canUse() 
	{
		if(!this.entity.isHungry() || this.entity.isSleeping()) 
		{
			return false;
		} 
		else 
		{
			BlockPos blockpos = this.entity.blockPosition();
			if(IS_TALL_GRASS.test(this.level.getBlockState(blockpos))) 
			{
				return true;
			} 
			else 
			{
				return this.level.getBlockState(blockpos.below()).is(Blocks.PODZOL);
			}
		}
	}

	@Override
	public void start() 
	{
		this.level.broadcastEntityEvent(this.entity, (byte) 10);
		this.entity.getNavigation().stop();
	}

	@Override
	public void stop() 
	{
		this.entity.setHunger(21000);
		this.entity.setEating(false);
	}

	@Override
	public boolean canContinueToUse() 
	{
		return this.entity.isHungry();
	}

	@Override
	public void tick() 
	{
		if(this.entity.isHungry()) 
		{
			this.entity.setEating(true);
			BlockPos blockpos = this.entity.blockPosition();
			if(IS_TALL_GRASS.test(this.level.getBlockState(blockpos))) 
			{
				if(ForgeEventFactory.getMobGriefingEvent(this.level, this.entity)) 
				{
					this.level.destroyBlock(blockpos, false);
				}
				this.entity.ate();
			} 
			else 
			{
				BlockPos blockpos1 = blockpos.below();
				if(this.level.getBlockState(blockpos1).is(Blocks.PODZOL)) 
				{
					if(ForgeEventFactory.getMobGriefingEvent(this.level, this.entity)) 
					{
						this.level.levelEvent(2001, blockpos1, Block.getId(Blocks.PODZOL.defaultBlockState()));
						this.level.setBlock(blockpos1, Blocks.DIRT.defaultBlockState(), 2);
					}

					this.entity.ate();
				}
			}
		}
	}
	
	public static class Egg extends HerbivoreEatPodzolGoal
	{
		private final HerbivoreEggLayingEntity entity;
		
		public Egg(HerbivoreEggLayingEntity entity) 
		{
			super(entity);
			this.entity = entity;
		}
		
		@Override
		public boolean canUse() 
		{
			return !this.entity.isGoingHome() && !this.entity.hasEgg() ? super.canUse() : false;
		}
	}
}
