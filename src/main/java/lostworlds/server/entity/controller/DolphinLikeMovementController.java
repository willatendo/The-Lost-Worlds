package lostworlds.server.entity.controller;

import lostworlds.server.entity.aquatic.DolphinLikeEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.util.Mth;

public class DolphinLikeMovementController extends MoveControl {
	private final DolphinLikeEntity entity;

	public DolphinLikeMovementController(DolphinLikeEntity entity) {
		super(entity);
		this.entity = entity;
	}

	@Override
	public void tick() {
		if (this.entity.isInWater()) {
			this.entity.setDeltaMovement(this.entity.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
		}

		if (this.operation == MoveControl.Operation.MOVE_TO && !this.entity.getNavigation().isDone()) {
			double x = this.wantedX - this.entity.getX();
			double y = this.wantedY - this.entity.getY();
			double z = this.wantedZ - this.entity.getZ();
			double result = x * x + y * y + z * z;
			if (result < (double) 2.5000003E-7F) {
				this.mob.setZza(0.0F);
			} else {
				float f = (float) (Mth.atan2(z, x) * (double) (180F / (float) Math.PI)) - 90.0F;
				this.entity.yRot = this.rotlerp(this.entity.yRot, f, 10.0F);
				this.entity.yBodyRot = this.entity.yRot;
				this.entity.yHeadRot = this.entity.yRot;
				float f1 = (float) (this.speedModifier * this.entity.getAttributeValue(Attributes.MOVEMENT_SPEED));
				if (this.entity.isInWater()) {
					this.entity.setSpeed(f1 * 0.02F);
					float f2 = -((float) (Mth.atan2(y, (double) Mth.sqrt(x * x + z * z)) * (double) (180F / (float) Math.PI)));
					f2 = Mth.clamp(Mth.wrapDegrees(f2), -85.0F, 85.0F);
					this.entity.xRot = this.rotlerp(this.entity.xRot, f2, 5.0F);
					float f3 = Mth.cos(this.entity.xRot * ((float) Math.PI / 180F));
					float f4 = Mth.sin(this.entity.xRot * ((float) Math.PI / 180F));
					this.entity.zza = f3 * f1;
					this.entity.yya = -f4 * f1;
				} else {
					this.entity.setSpeed(f1 * 0.1F);
				}

			}
		} else {
			this.entity.setSpeed(0.0F);
			this.entity.setXxa(0.0F);
			this.entity.setYya(0.0F);
			this.entity.setZza(0.0F);
		}
	}
}
