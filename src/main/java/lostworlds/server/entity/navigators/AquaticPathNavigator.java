package lostworlds.server.entity.navigators;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.AmphibiousNodeEvaluator;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class AquaticPathNavigator extends WaterBoundPathNavigation {
	public AquaticPathNavigator(Mob entity, Level world) {
		super(entity, world);
	}

	protected PathFinder getPathFinder(int maxVisitedNodes) {
		this.nodeEvaluator = new AmphibiousNodeEvaluator(true);
		return new PathFinder(this.nodeEvaluator, maxVisitedNodes);
	}

	protected boolean canNavigate() {
		return true;
	}

	protected Vec3 getEntityPosition() {
		return new Vec3(this.mob.getX(), this.mob.getY(0.5D), this.mob.getZ());
	}

	protected boolean isDirectPathBetweenPoints(Vec3 posVec31, Vec3 posVec32, int sizeX, int sizeY, int sizeZ) {
		Vec3 vector3d = new Vec3(posVec32.x, posVec32.y + (double) this.mob.getBbHeight() * 0.5D, posVec32.z);
		return this.level.clip(new ClipContext(posVec31, vector3d, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this.mob)).getType() == HitResult.Type.MISS;
	}

	public boolean canEntityStandOnPos(BlockPos pos) {
		return !this.level.getBlockState(pos.below()).isAir();
	}

	public void setCanSwim(boolean canSwim) {
	}
}
