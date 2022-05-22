package lostworlds.server.entity.navigators;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.Level;

public class TerrestrialPathNavigator extends GroundPathNavigation {
	private float distancemodifier = 0.75F;

	public TerrestrialPathNavigator(Mob mob, Level world) {
		super(mob, world);
	}

	public TerrestrialPathNavigator(Mob mob, Level world, float distancemodifier) {
		super(mob, world);
		this.distancemodifier = distancemodifier;
	}

	protected void pathFollow() {
		Vec3 vector3d = this.getTempMobPos();
		this.maxDistanceToWaypoint = this.mob.getBbWidth() * distancemodifier;
		Vec3i vector3i = this.path.getNextNodePos();
		double d0 = Math.abs(this.mob.getX() - ((double) vector3i.getX() + 0.5D));
		double d1 = Math.abs(this.mob.getY() - (double) vector3i.getY());
		double d2 = Math.abs(this.mob.getZ() - ((double) vector3i.getZ() + 0.5D));
		boolean flag = d0 < (double) this.maxDistanceToWaypoint && d2 < (double) this.maxDistanceToWaypoint && d1 < 1.0D;
		if (flag || this.mob.canCutCorner(this.path.getNextNode().type) && this.shouldTargetNextNodeInDirection(vector3d)) {
			this.path.advance();
		}

		this.doStuckDetection(vector3d);
	}
}
