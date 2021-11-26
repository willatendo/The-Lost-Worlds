package lostworlds.library.entity.goal.terrestrial.herbivore;

import java.util.Random;

import lostworlds.content.ModUtils;
import lostworlds.content.server.init.BlockInit;
import lostworlds.library.block.LargeEggBlock;
import lostworlds.library.block.MediumEggBlock;
import lostworlds.library.block.SmallEggBlock;
import lostworlds.library.block.TinyEggBlock;
import lostworlds.library.entity.DinoTypes;
import lostworlds.library.entity.terrestrial.HerbivoreEggLayingEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class HerbivoreLayEggGoal extends MoveToBlockGoal 
{
	private final HerbivoreEggLayingEntity entity;
	private final DinoTypes dino;

	public HerbivoreLayEggGoal(HerbivoreEggLayingEntity entity, double speedModifer, DinoTypes dino) 
	{
		super(entity, speedModifer, 16);
		this.entity = entity;
		this.dino = dino;
	}
	
	@Override
	public void start() 
	{
		super.start();
		ModUtils.LOGGER.debug("Attemping to lay egg");
	}

	@Override
	public boolean canUse() 
	{
		return this.entity.hasEgg() && this.entity.getHomePos().closerThan(this.entity.position(), 9.0D) && !this.entity.isPanicked() ? super.canUse() : false;
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
				Block egg = this.dino.getEgg();
				World world = this.entity.level;
				world.playSound((PlayerEntity) null, blockpos, SoundEvents.TURTLE_LAY_EGG, SoundCategory.BLOCKS, 0.3F, 0.9F + world.random.nextFloat() * 0.2F);
				if(egg instanceof LargeEggBlock)
				{
					LargeEggBlock block = (LargeEggBlock) egg.defaultBlockState().setValue(LargeEggBlock.EGGS, Integer.valueOf(new Random().nextInt(3) + 1)).getBlock();
					world.setBlock(this.blockPos.above(), block.defaultBlockState(), 3);
				}
				else if(egg.getBlock() instanceof MediumEggBlock)
				{
					MediumEggBlock block = (MediumEggBlock) egg.defaultBlockState().setValue(MediumEggBlock.EGGS, Integer.valueOf(new Random().nextInt(6) + 1)).getBlock();
					world.setBlock(this.blockPos.above(), block.defaultBlockState(), 3);
				}
				else if(egg.getBlock() instanceof SmallEggBlock)
				{
					SmallEggBlock block = (SmallEggBlock) egg.defaultBlockState().setValue(SmallEggBlock.EGGS, Integer.valueOf(new Random().nextInt(10) + 1)).getBlock();
					world.setBlock(this.blockPos.above(), block.defaultBlockState(), 3);
				}
				else if(egg.getBlock() instanceof TinyEggBlock)
				{
					TinyEggBlock block = (TinyEggBlock) egg.defaultBlockState().setValue(TinyEggBlock.EGGS, Integer.valueOf(new Random().nextInt(19) + 1)).getBlock();
					world.setBlock(this.blockPos.above(), block.defaultBlockState(), 3);
				}
				this.entity.setHasEgg(false);
				this.entity.setLayingEgg(false);
				this.entity.setInLoveTime(600);
				this.entity.setInNaturalLoveTime(600);
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
		return !reader.isEmptyBlock(pos.above()) ? false : isNest(reader, pos);
	}
	
	public static boolean isNest(IBlockReader blockReader, BlockPos pos) 
	{
		return blockReader.getBlockState(pos).is(BlockInit.NESTING_BLOCK);
	}
}
