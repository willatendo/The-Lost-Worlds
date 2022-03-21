package lostworlds.server.entity.navigators;

import net.minecraft.entity.MobEntity;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.World;

public class TerrestrialPathNavigator extends GroundPathNavigator {
	private float distancemodifier = 0.75F;

	public TerrestrialPathNavigator(MobEntity mob, World world) {
		super(mob, world);
	}

	public TerrestrialPathNavigator(MobEntity mob, World world, float distancemodifier) {
		super(mob, world);
		this.distancemodifier = distancemodifier;
	}

	protected void pathFollow() {
		Vector3d vector3d = this.getTempMobPos();
		this.maxDistanceToWaypoint = this.mob.getBbWidth() * distancemodifier;
		Vector3i vector3i = this.path.getNextNodePos();
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
