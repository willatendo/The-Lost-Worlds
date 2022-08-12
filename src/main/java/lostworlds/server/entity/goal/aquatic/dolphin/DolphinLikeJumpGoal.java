package lostworlds.server.entity.goal.aquatic.dolphin;

import lostworlds.server.entity.aquatic.DolphinLike;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.JumpGoal;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;

public class DolphinLikeJumpGoal extends JumpGoal {
	private static final int[] STEPS_TO_CHECK = new int[] { 0, 1, 4, 5, 6, 7 };
	private final DolphinLike entity;
	private final int interval;
	private boolean breached;

	public DolphinLikeJumpGoal(DolphinLike entity, int interval) {
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
			BlockPos blockpos = this.entity.blockPosition().offset(0, 2, 0);

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

		Vec3 vec3 = this.entity.getDeltaMovement();
		if (vec3.y * vec3.y < (double) 0.03F && this.entity.getXRot() != 0.0F) {
			this.entity.setXRot(Mth.rotlerp(this.entity.getXRot(), 0.0F, 0.2F));
		} else if (vec3.length() > (double) 1.0E-5F) {
			double d0 = vec3.horizontalDistance();
			double d1 = Math.atan2(-vec3.y, d0) * (double) (180F / (float) Math.PI);
			this.entity.setXRot((float) d1);
		}
	}
}
