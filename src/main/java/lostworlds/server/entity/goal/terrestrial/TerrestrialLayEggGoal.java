package lostworlds.server.entity.goal.terrestrial;

import java.util.Random;

import lostworlds.server.block.LargeEggBlock;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.block.MediumEggBlock;
import lostworlds.server.block.SmallEggBlock;
import lostworlds.server.block.TinyEggBlock;
import lostworlds.server.entity.terrestrial.EggLayingEntity;
import lostworlds.server.entity.utils.enums.DinoTypes;
import net.minecraft.block.Block;
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

public class TerrestrialLayEggGoal extends MoveToBlockGoal {
	private final EggLayingEntity entity;
	private final DinoTypes dino;

	public TerrestrialLayEggGoal(EggLayingEntity entity, double speedModifer, DinoTypes dino) {
		super(entity, speedModifer, 16);
		this.entity = entity;
		this.dino = dino;
	}

	@Override
	public boolean canUse() {
		return this.entity.hasEgg() && this.entity.getHomePos().closerThan(this.entity.position(), 9.0D) ? super.canUse() : false;
	}

	@Override
	public void stop() {
		super.stop();
		this.entity.setGoingHome(false);
	}

	@Override
	public boolean canContinueToUse() {
		return super.canContinueToUse() && this.entity.hasEgg() && this.entity.getHomePos().closerThan(this.entity.position(), 9.0D);
	}

	@Override
	public void tick() {
		super.tick();
		BlockPos blockpos = this.entity.blockPosition();
		if (!this.entity.isInWater() && this.isReachedTarget()) {
			Block egg = this.dino.getEgg().get();
			World world = this.entity.level;
			world.playSound((PlayerEntity) null, blockpos, SoundEvents.TURTLE_LAY_EGG, SoundCategory.BLOCKS, 0.3F, 0.9F + world.random.nextFloat() * 0.2F);
			if (egg instanceof LargeEggBlock) {
				LargeEggBlock block = (LargeEggBlock) egg.defaultBlockState().setValue(LargeEggBlock.EGGS, Integer.valueOf(new Random().nextInt(3) + 1)).getBlock();
				world.setBlock(this.blockPos.above(), block.defaultBlockState(), 3);
			} else if (egg instanceof MediumEggBlock) {
				MediumEggBlock block = (MediumEggBlock) egg.defaultBlockState().setValue(MediumEggBlock.EGGS, Integer.valueOf(new Random().nextInt(6) + 1)).getBlock();
				world.setBlock(this.blockPos.above(), block.defaultBlockState(), 3);
			} else if (egg instanceof SmallEggBlock) {
				SmallEggBlock block = (SmallEggBlock) egg.defaultBlockState().setValue(SmallEggBlock.EGGS, Integer.valueOf(new Random().nextInt(10) + 1)).getBlock();
				world.setBlock(this.blockPos.above(), block.defaultBlockState(), 3);
			} else if (egg instanceof TinyEggBlock) {
				TinyEggBlock block = (TinyEggBlock) egg.defaultBlockState().setValue(TinyEggBlock.EGGS, Integer.valueOf(new Random().nextInt(19) + 1)).getBlock();
				world.setBlock(this.blockPos.above(), block.defaultBlockState(), 3);
			}
			world.setBlock(this.blockPos, LostWorldsBlocks.NESTING_BLOCK.getDefaultState(), 3);
			this.entity.setHasEgg(false);
			this.entity.setInLoveTime(600);
		}
	}

	@Override
	protected boolean isValidTarget(IWorldReader reader, BlockPos pos) {
		return isNatural(reader, pos) && reader.getBlockState(pos.above()).isAir();
	}

	public static boolean isNatural(IBlockReader reader, BlockPos pos) {
		return reader.getBlockState(pos).is(BlockTags.SAND) || reader.getBlockState(pos).is(Tags.Blocks.DIRT) || reader.getBlockState(pos).is(LostWorldsBlocks.NESTING_BLOCK.get());
	}
}
