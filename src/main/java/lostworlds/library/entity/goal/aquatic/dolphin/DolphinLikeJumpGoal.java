package lostworlds.library.entity.goal.aquatic.dolphin;

import lostworlds.library.entity.aquatic.DolphinLikeEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.JumpGoal;
import net.minecraft.fluid.FluidState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;

public class DolphinLikeJumpGoal extends JumpGoal {
	private static final int[] STEPS_TO_CHECK = new int[] { 0, 1, 4, 5, 6, 7 };
	private final DolphinLikeEntity entity;
	private final int interval;
	private boolean breached;

	public DolphinLikeJumpGoal(DolphinLikeEntity entity, int interval) {
		this.entity = entity;
		this.interval = interval;
	}

	@Override
	public boolean canUse() {
		if (this.entity.getRandom().nextInt(this.interval) != 0) {
			return false;
		} else {
			Direction direction = this.entity.getMotionDirection();
			int i = direction.getStepX();
			int j = direction.getStepZ();
			BlockPos blockpos = this.entity.blockPosition();

			for (int k : STEPS_TO_CHECK) {
				if (!this.waterIsClear(blockpos, i, j, k) || !this.surfaceIsClear(blockpos, i, j, k)) {
					return false;
				}
			}

			return true;
		}
	}

	private boolean waterIsClear(BlockPos pos, int x, int y, int z) {
		BlockPos blockpos = pos.offset(x * z, 0, y * z);
		return this.entity.level.getFluidState(blockpos).is(FluidTags.WATER) && !this.entity.level.getBlockState(blockpos).getMaterial().blocksMotion();
	}

	private boolean surfaceIsClear(BlockPos pos, int x, int y, int z) {
		return this.entity.level.getBlockState(pos.offset(x * z, 1, y * z)).isAir() && this.entity.level.getBlockState(pos.offset(x * z, 2, y * z)).isAir();
	}

	@Override
	public boolean canContinueToUse() {
		double d0 = this.entity.getDeltaMovement().y;
		return (!(d0 * d0 < (double) 0.03F) || this.entity.xRot == 0.0F || !(Math.abs(this.entity.xRot) < 10.0F) || !this.entity.isInWater()) && !this.entity.isOnGround();
	}

	@Override
	public boolean isInterruptable() {
		return false;
	}

	@Override
	public void start() {
		Direction direction = this.entity.getMotionDirection();
		this.entity.setDeltaMovement(this.entity.getDeltaMovement().add((double) direction.getStepX() * 0.6D, 0.7D, (double) direction.getStepZ() * 0.6D));
		this.entity.getNavigation().stop();
	}

	@Override
	public void stop() {
		this.entity.xRot = 0.0F;
	}

	@Override
	public void tick() {
		boolean flag = this.breached;
		if (!flag) {
			FluidState fluidstate = this.entity.level.getFluidState(this.entity.blockPosition());
			this.breached = fluidstate.is(FluidTags.WATER);
		}

		if (this.breached && !flag) {
			this.entity.playSound(SoundEvents.DOLPHIN_JUMP, 1.0F, 1.0F);
		}

		Vector3d vector3d = this.entity.getDeltaMovement();
		if (vector3d.y * vector3d.y < (double) 0.03F && this.entity.xRot != 0.0F) {
			this.entity.xRot = MathHelper.rotlerp(this.entity.xRot, 0.0F, 0.2F);
		} else {
			double d0 = Math.sqrt(Entity.getHorizontalDistanceSqr(vector3d));
			double d1 = Math.signum(-vector3d.y) * Math.acos(d0 / vector3d.length()) * (double) (180F / (float) Math.PI);
			this.entity.xRot = (float) d1;
		}

	}
}
