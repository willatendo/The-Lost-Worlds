package lostworlds.server.entity.goal.terrestrial;

import java.util.Random;

import lostworlds.server.block.LargeEggBlock;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.block.MediumEggBlock;
import lostworlds.server.block.SmallEggBlock;
import lostworlds.server.block.TinyEggBlock;
import lostworlds.server.entity.terrestrial.EggLayingMob;
import lostworlds.server.entity.utils.enums.AncientCreatures;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;

public class TerrestrialLayEggGoal extends MoveToBlockGoal {
	private final EggLayingMob entity;
	private final AncientCreatures dino;

	public TerrestrialLayEggGoal(EggLayingMob entity, double speedModifer, AncientCreatures dino) {
		super(entity, speedModifer, 16);
		this.entity = entity;
		this.dino = dino;
	}

	@Override
	public boolean canUse() {
		return this.entity.hasEgg() && this.entity.getHomePos().closerToCenterThan(this.entity.position(), 9.0D) ? super.canUse() : false;
	}

	@Override
	public void stop() {
		super.stop();
		this.entity.setGoingHome(false);
	}

	@Override
	public boolean canContinueToUse() {
		return super.canContinueToUse() && this.entity.hasEgg() && this.entity.getHomePos().closerToCenterThan(this.entity.position(), 9.0D);
	}

	@Override
	public void tick() {
		super.tick();
		BlockPos blockpos = this.entity.blockPosition();
		if (!this.entity.isInWater() && this.isReachedTarget()) {
			Block egg = this.dino.getEgg().get();
			Level world = this.entity.level;
			world.playSound((Player) null, blockpos, SoundEvents.TURTLE_LAY_EGG, SoundSource.BLOCKS, 0.3F, 0.9F + world.random.nextFloat() * 0.2F);
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
	protected boolean isValidTarget(LevelReader reader, BlockPos pos) {
		return isNatural(reader, pos) && reader.getBlockState(pos.above()).isAir();
	}

	public static boolean isNatural(BlockGetter reader, BlockPos pos) {
		return reader.getBlockState(pos).is(BlockTags.SAND) || reader.getBlockState(pos).is(BlockTags.DIRT) || reader.getBlockState(pos).is(LostWorldsBlocks.NESTING_BLOCK.get());
	}
}
