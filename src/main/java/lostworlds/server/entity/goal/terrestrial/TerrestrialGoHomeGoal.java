package lostworlds.server.entity.goal.terrestrial;

import lostworlds.server.entity.terrestrial.EggLayingEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.ai.util.RandomPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;

public class TerrestrialGoHomeGoal extends Goal {
	private final EggLayingEntity entity;
	private final double speedModifier;
	private boolean stuck;
	private int closeToHomeTryTicks;

	public TerrestrialGoHomeGoal(EggLayingEntity entity, double speedModifier) {
		this.entity = entity;
		this.speedModifier = speedModifier;
	}

	@Override
	public boolean canUse() {
		if (this.entity.hasEgg()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void start() {
		this.entity.setGoingHome(true);
		this.stuck = false;
		this.closeToHomeTryTicks = 0;
	}

	@Override
	public void stop() {
		this.entity.setGoingHome(false);
	}

	@Override
	public boolean canContinueToUse() {
		return !this.entity.getHomePos().closerThan(this.entity.position(), 9.0D) && !this.stuck && this.closeToHomeTryTicks <= 600;
	}

	@Override
	public void tick() {
		BlockPos blockpos = this.entity.getHomePos();
		boolean flag = blockpos.closerThan(this.entity.position(), 16.0D);
		if (flag) {
			++this.closeToHomeTryTicks;
		}

		if (this.entity.getNavigation().isDone()) {
			Vec3 vector3d = Vec3.atBottomCenterOf(blockpos);
			Vec3 vector3d1 = RandomPos.getPosTowards(this.entity, 16, 3, vector3d, (double) ((float) Math.PI / 10F));
			if (vector3d1 == null) {
				vector3d1 = RandomPos.getPosTowards(this.entity, 8, 7, vector3d);
			}

			if (vector3d1 != null && !flag && !this.entity.level.getBlockState(new BlockPos(vector3d1)).is(Blocks.WATER)) {
				vector3d1 = RandomPos.getPosTowards(this.entity, 16, 5, vector3d);
			}

			if (vector3d1 == null) {
				this.stuck = true;
				return;
			}

			this.entity.getNavigation().moveTo(vector3d1.x, vector3d1.y, vector3d1.z, this.speedModifier);
		}
	}
}
