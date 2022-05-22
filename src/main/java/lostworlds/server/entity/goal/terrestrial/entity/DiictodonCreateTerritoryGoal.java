package lostworlds.server.entity.goal.terrestrial.entity;

import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.entity.terrestrial.EggLayingEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;

public class DiictodonCreateTerritoryGoal extends MoveToBlockGoal {
	private final EggLayingEntity entity;
	private final Level level;

	public DiictodonCreateTerritoryGoal(EggLayingEntity entity, double speedModifier) {
		super(entity, speedModifier, 16);
		this.entity = entity;
		this.level = entity.level;
	}

	@Override
	public boolean canUse() {
		return !this.entity.isInWaterOrBubble() && entity.canMakeTerritory() && this.level.getBlockState(this.entity.blockPosition().below()) != Blocks.AIR.defaultBlockState();
	}

	@Override
	public boolean canContinueToUse() {
		return !this.entity.hasTerritory();
	}

	public static boolean isNatural(BlockGetter reader, BlockPos pos) {
		return reader.getBlockState(pos).is(LostWorldsBlocks.PERMIAN_SAND.get());
	}

	@Override
	public void start() {
		super.start();
		this.entity.getNavigation().stop();
	}

	@Override
	public void tick() {
		super.tick();
		BlockPos blockpos = this.entity.blockPosition();
		if (!this.entity.isInWater()) {
			this.level.playSound((Player) null, blockpos, SoundEvents.TURTLE_LAY_EGG, SoundSource.BLOCKS, 0.3F, 0.9F + this.level.random.nextFloat() * 0.2F);
		}
	}

	@Override
	public void stop() {
		BlockPos blockpos = this.entity.blockPosition();
		this.entity.setHomePos(blockpos);
		this.entity.setHasTerritory(true);
	}

	@Override
	protected boolean isValidTarget(LevelReader reader, BlockPos pos) {
		return !reader.isEmptyBlock(pos.above()) ? false : isNatural(reader, pos);
	}
}
