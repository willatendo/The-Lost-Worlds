package lostworlds.server.entity.controller;

import lostworlds.server.entity.semiaquatic.CarnivoreSemiAquaticEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;

public class SemiAquaticMoveController extends MoveControl {
	private final CarnivoreSemiAquaticEntity entity;

	public SemiAquaticMoveController(CarnivoreSemiAquaticEntity entity) {
		super(entity);
		this.entity = entity;
	}

	@Override
	public void tick() {
		if (this.entity.isEyeInFluid(FluidTags.WATER)) {
			this.entity.setDeltaMovement(this.entity.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
		}

		if (this.operation == MoveControl.Operation.MOVE_TO && !this.entity.getNavigation().isDone()) {
			float f = (float) (this.speedModifier * this.entity.getAttributeValue(Attributes.MOVEMENT_SPEED));
			this.entity.setSpeed(Mth.lerp(0.125F, this.entity.getSpeed(), f));
			double d0 = this.wantedX - this.entity.getX();
			double d1 = this.wantedY - this.entity.getY();
			double d2 = this.wantedZ - this.entity.getZ();
			if (d1 != 0.0D) {
				double d3 = (double) Mth.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
				this.entity.setDeltaMovement(this.entity.getDeltaMovement().add(0.0D, (double) this.entity.getSpeed() * (d1 / d3) * 0.1D, 0.0D));
			}

			if (d0 != 0.0D || d2 != 0.0D) {
				float f1 = (float) (Mth.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
				this.entity.yRot = this.rotlerp(this.entity.yRot, f1, 90.0F);
				this.entity.yBodyRot = this.entity.yRot;
			}
		} else {
			this.entity.setSpeed(0.0F);
		}
	}
}
