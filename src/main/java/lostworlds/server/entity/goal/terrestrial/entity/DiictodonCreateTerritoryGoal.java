package lostworlds.server.entity.goal.terrestrial.entity;

import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.entity.terrestrial.EggLayingEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class DiictodonCreateTerritoryGoal extends MoveToBlockGoal {
	private final EggLayingEntity entity;
	private final World level;

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

	public static boolean isNatural(IBlockReader reader, BlockPos pos) {
		return reader.getBlockState(pos).is(LostWorldsBlocks.PERMIAN_SAND);
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
			this.level.playSound((PlayerEntity) null, blockpos, SoundEvents.TURTLE_LAY_EGG, SoundCategory.BLOCKS, 0.3F, 0.9F + this.level.random.nextFloat() * 0.2F);
		}
	}

	@Override
	public void stop() {
		BlockPos blockpos = this.entity.blockPosition();
		this.entity.setHomePos(blockpos);
		this.entity.setHasTerritory(true);
	}

	@Override
	protected boolean isValidTarget(IWorldReader reader, BlockPos pos) {
		return !reader.isEmptyBlock(pos.above()) ? false : isNatural(reader, pos);
	}
}
