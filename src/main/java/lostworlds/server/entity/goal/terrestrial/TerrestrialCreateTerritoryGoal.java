package lostworlds.server.entity.goal.terrestrial;

import lostworlds.server.entity.terrestrial.EggLayingEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.tags.BlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;

public class TerrestrialCreateTerritoryGoal extends MoveToBlockGoal {
	private final EggLayingEntity entity;
	private final Level level;

	public TerrestrialCreateTerritoryGoal(EggLayingEntity entity, double speedModifier) {
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
		return reader.getBlockState(pos).is(BlockTags.SAND) || reader.getBlockState(pos).is(Tags.Blocks.DIRT);
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
