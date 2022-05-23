package lostworlds.server.entity.goal.terrestrial;

import lostworlds.server.entity.terrestrial.EggLayingMob;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;

public class TerrestrialCreateTerritoryGoal extends MoveToBlockGoal {
	private final EggLayingMob entity;
	private final Level level;

	public TerrestrialCreateTerritoryGoal(EggLayingMob entity, double speedModifier) {
		super(entity, speedModifier, 16);
		this.entity = entity;
		this.level = entity.level;
	}

	@Override
	public boolean canUse() {
		return !this.entity.isInWaterOrBubble() && entity.canMakeTerritory() && !this.entity.isSleeping() && this.level.getBlockState(this.entity.blockPosition().below()) != Blocks.AIR.defaultBlockState();
	}

	@Override
	public boolean canContinueToUse() {
		return !this.entity.hasTerritory();
	}

	public static boolean isNatural(BlockGetter reader, BlockPos pos) {
		return reader.getBlockState(pos).is(BlockTags.SAND) || reader.getBlockState(pos).is(BlockTags.DIRT);
	}

	@Override
	public void start() {
		super.start();
		this.entity.getNavigation().stop();
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
