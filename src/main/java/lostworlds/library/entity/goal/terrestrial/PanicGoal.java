package lostworlds.library.entity.goal.terrestrial;

import java.util.EnumSet;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import lostworlds.library.entity.utils.IHerdPanic;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;

public class PanicGoal extends Goal {
	protected final CreatureEntity creature;
	protected final double speedModifer;
	protected final Predicate<? super CreatureEntity> targetEntitySelector;
	protected double randPosX;
	protected double randPosY;
	protected double randPosZ;
	protected boolean running;

	public PanicGoal(CreatureEntity creature, double speedModifer) {
		this.creature = creature;
		this.speedModifer = speedModifer;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		this.targetEntitySelector = new Predicate<CreatureEntity>() {
			@Override
			public boolean apply(@Nullable CreatureEntity animal) {
				if (animal instanceof IHerdPanic && animal.getType() == creature.getType()) {
					return ((IHerdPanic) animal).canPanic();
				}
				return false;
			}
		};
	}

	@Override
	public boolean canUse() {
		if (this.creature.getLastHurtByMob() == null && !this.creature.isOnFire()) {
			return false;
		} else {
			if (this.creature.isOnFire()) {
				BlockPos blockpos = this.getRandPos(this.creature.level, this.creature, 5, 4);
				if (blockpos != null) {
					this.randPosX = blockpos.getX();
					this.randPosY = blockpos.getY();
					this.randPosZ = blockpos.getZ();
					return true;
				}
			}
			if (this.creature.getLastHurtByMob() != null && this.creature instanceof IHerdPanic && ((IHerdPanic) this.creature).canPanic()) {

				List<CreatureEntity> list = this.creature.level.getEntitiesOfClass(this.creature.getClass(), this.getTargetableArea(), this.targetEntitySelector);
				for (CreatureEntity creatureEntity : list) {
					creatureEntity.setLastHurtByMob(this.creature.getLastHurtByMob());
				}
				return this.findRandomPositionFrom(this.creature.getLastHurtByMob());
			}
			return this.findRandomPosition();
		}
	}

	private boolean findRandomPositionFrom(LivingEntity revengeTarget) {
		Vector3d vector3d = RandomPositionGenerator.getPosAvoid(this.creature, 16, 7, revengeTarget.position());
		if (vector3d == null) {
			return false;
		} else {
			this.randPosX = vector3d.x;
			this.randPosY = vector3d.y;
			this.randPosZ = vector3d.z;
			return true;
		}
	}

	protected AxisAlignedBB getTargetableArea() {
		Vector3d renderCenter = new Vector3d(this.creature.getX() + 0.5, this.creature.getY() + 0.5D, this.creature.getZ() + 0.5D);
		double searchRadius = 15;
		AxisAlignedBB aabb = new AxisAlignedBB(-searchRadius, -searchRadius, -searchRadius, searchRadius, searchRadius, searchRadius);
		return aabb.move(renderCenter);
	}

	protected boolean findRandomPosition() {
		Vector3d vector3d = RandomPositionGenerator.getPos(this.creature, 5, 4);
		if (vector3d == null) {
			return false;
		} else {
			this.randPosX = vector3d.x;
			this.randPosY = vector3d.y;
			this.randPosZ = vector3d.z;
			return true;
		}
	}

	public boolean isRunning() {
		return this.running;
	}

	@Override
	public void start() {
		if (this.creature instanceof IHerdPanic) {
			((IHerdPanic) this.creature).onPanic();
		}
		this.creature.getNavigation().moveTo(this.randPosX, this.randPosY, this.randPosZ, this.speedModifer);
		this.running = true;
	}

	@Override
	public void stop() {
		this.running = false;
	}

	@Override
	public boolean canContinueToUse() {
		return !this.creature.getNavigation().isDone();
	}

	@Nullable
	protected BlockPos getRandPos(IBlockReader world, Entity entity, int horizontalRange, int verticalRange) {
		BlockPos blockpos = entity.blockPosition();
		int i = blockpos.getX();
		int j = blockpos.getY();
		int k = blockpos.getZ();
		float f = (float) (horizontalRange * horizontalRange * verticalRange * 2);
		BlockPos blockpos1 = null;
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

		for (int l = i - horizontalRange; l <= i + horizontalRange; ++l) {
			for (int i1 = j - verticalRange; i1 <= j + verticalRange; ++i1) {
				for (int j1 = k - horizontalRange; j1 <= k + horizontalRange; ++j1) {
					blockpos$mutable.set(l, i1, j1);
					if (world.getFluidState(blockpos$mutable).is(FluidTags.WATER)) {
						float f1 = (float) ((l - i) * (l - i) + (i1 - j) * (i1 - j) + (j1 - k) * (j1 - k));
						if (f1 < f) {
							f = f1;
							blockpos1 = new BlockPos(blockpos$mutable);
						}
					}
				}
			}
		}

		return blockpos1;
	}
}
