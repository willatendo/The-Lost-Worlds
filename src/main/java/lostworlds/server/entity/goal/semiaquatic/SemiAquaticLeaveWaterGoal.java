package lostworlds.server.entity.goal.semiaquatic;

import java.util.EnumSet;

import lostworlds.server.entity.utils.ISemiAquatic;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.Vec3;

public class SemiAquaticLeaveWaterGoal extends Goal {
	private final PathfinderMob creature;
	private BlockPos targetPos;
	private int executionChance = 30;

	public SemiAquaticLeaveWaterGoal(PathfinderMob creature) {
		this.creature = creature;
		this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		if (this.creature.level.getFluidState(this.creature.blockPosition()).is(FluidTags.WATER) && (this.creature.getTarget() != null || this.creature.getRandom().nextInt(executionChance) == 0)) {
			if (this.creature instanceof ISemiAquatic && ((ISemiAquatic) this.creature).shouldLeaveWater()) {
				targetPos = generateTarget();
				return targetPos != null;
			}
		}
		return false;
	}

	@Override
	public void start() {
		if (targetPos != null) {
			this.creature.getNavigation().moveTo(targetPos.getX(), targetPos.getY(), targetPos.getZ(), 1D);
		}
	}

	@Override
	public void tick() {
		if (targetPos != null) {
			this.creature.getNavigation().moveTo(targetPos.getX(), targetPos.getY(), targetPos.getZ(), 1D);
		}
		if (this.creature.horizontalCollision && this.creature.isInWater()) {
			float f1 = creature.yRot * ((float) Math.PI / 180F);
			creature.setDeltaMovement(creature.getDeltaMovement().add((double) (-Mth.sin(f1) * 0.2F), 0.1D, (double) (Mth.cos(f1) * 0.2F)));

		}
	}

	@Override
	public boolean canContinueToUse() {
		if (this.creature instanceof ISemiAquatic && !((ISemiAquatic) this.creature).shouldLeaveWater()) {
			this.creature.getNavigation().stop();
			return false;
		}
		return !this.creature.getNavigation().isDone() && targetPos != null && !this.creature.level.getFluidState(targetPos).is(FluidTags.WATER);
	}

	public BlockPos generateTarget() {
		Vec3 vector3d = DefaultRandomPos.getPos(this.creature, 23, 7);
		int tries = 0;
		while (vector3d != null && tries < 8) {
			boolean waterDetected = false;
			for (BlockPos blockpos1 : BlockPos.betweenClosed(Mth.floor(vector3d.x - 2.0D), Mth.floor(vector3d.y - 1.0D), Mth.floor(vector3d.z - 2.0D), Mth.floor(vector3d.x + 2.0D), Mth.floor(vector3d.y), Mth.floor(vector3d.z + 2.0D))) {
				if (this.creature.level.getFluidState(blockpos1).is(FluidTags.WATER)) {
					waterDetected = true;
					break;
				}
			}
			if (waterDetected) {
				vector3d = DefaultRandomPos.getPos(this.creature, 23, 7);
			} else {
				return new BlockPos(vector3d);
			}
			tries++;
		}
		return null;
	}
}
