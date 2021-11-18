package lostworlds.library.entity.goal.herbivore;

import lostworlds.library.block.LargeEggBlock;
import lostworlds.library.entity.terrestrial.HerbivoreEggLayingEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class HerbivoreLayEggGoal extends MoveToBlockGoal 
{
	private final HerbivoreEggLayingEntity entity;
	private final BlockState egg;

	public HerbivoreLayEggGoal(HerbivoreEggLayingEntity entity, double speedModifer, BlockState egg) 
	{
		super(entity, speedModifer, 16);
		this.entity = entity;
		this.egg = egg;
	}

	@Override
	public boolean canUse() 
	{
		return this.entity.hasEgg() && this.entity.getHomePos().closerThan(this.entity.position(), 9.0D) ? super.canUse() : false;
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
			if(this.entity.layEggCounter < 1) 
			{
				this.entity.setLayingEgg(true);
			}
			else if(this.entity.layEggCounter > 200) 
			{
				World world = this.entity.level;
				world.playSound((PlayerEntity) null, blockpos, SoundEvents.TURTLE_LAY_EGG, SoundCategory.BLOCKS, 0.3F, 0.9F + world.random.nextFloat() * 0.2F);
				world.setBlock(this.blockPos.above(), egg, 3);
				this.entity.setHasEgg(false);
				this.entity.setLayingEgg(false);
				this.entity.setInLoveTime(600);
			}

			if(this.entity.isLayingEgg()) 
			{
				this.entity.layEggCounter++;
			}
		}

	}

	@Override
	protected boolean isValidTarget(IWorldReader reader, BlockPos pos) 
	{
		return !reader.isEmptyBlock(pos.above()) ? false : LargeEggBlock.isNest(reader, pos);
	}
}