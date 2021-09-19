package lostworlds.library.entity.goal;

import java.util.EnumSet;
import java.util.function.Predicate;

import lostworlds.library.entity.prehistoric.HerbivourEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockStateMatcher;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class HerbivourEatGrassGoal extends Goal 
{
	private static final Predicate<BlockState> IS_TALL_GRASS = BlockStateMatcher.forBlock(Blocks.GRASS);
	private final HerbivourEntity entity;
	private final World level;
	private int eatAnimationTick;

	public HerbivourEatGrassGoal(HerbivourEntity entity) 
	{
		this.entity = entity;
		this.level = entity.level;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
	}

	@Override
	public boolean canUse() 
	{
		if(this.entity.getRandom().nextInt(this.entity.isBaby() ? 50 : 1000) != 0 && !this.entity.isHungry()) 
		{
			return false;
		} 
		else 
		{
			BlockPos blockpos = this.entity.blockPosition();
			if (IS_TALL_GRASS.test(this.level.getBlockState(blockpos))) {
				return true;
			} else {
				return this.level.getBlockState(blockpos.below()).is(Blocks.GRASS_BLOCK);
			}
		}
	}

	@Override
	public void start() 
	{
		this.eatAnimationTick = 40;
		this.level.broadcastEntityEvent(this.entity, (byte) 10);
		this.entity.getNavigation().stop();
	}

	@Override
	public void stop() 
	{
		this.eatAnimationTick = 0;
	}

	@Override
	public boolean canContinueToUse() 
	{
		return this.eatAnimationTick > 0;
	}

	public int getEatAnimationTick() 
	{
		return this.eatAnimationTick;
	}

	@Override
	public void tick() 
	{
		this.eatAnimationTick = Math.max(0, this.eatAnimationTick - 1);
		if(this.eatAnimationTick == 4) 
		{
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
				if(this.level.getBlockState(blockpos1).is(Blocks.GRASS_BLOCK)) 
				{
					if(ForgeEventFactory.getMobGriefingEvent(this.level, this.entity)) 
					{
						this.level.levelEvent(2001, blockpos1, Block.getId(Blocks.GRASS_BLOCK.defaultBlockState()));
						this.level.setBlock(blockpos1, Blocks.DIRT.defaultBlockState(), 2);
					}

					this.entity.ate();
				}
			}
		}
	}
}
