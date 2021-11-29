package lostworlds.library.entity.goal.semiaquatic;

import javax.annotation.Nullable;

import lostworlds.library.entity.semiaquatic.BreedingSemiAquaticEntity;
import lostworlds.library.entity.semiaquatic.ISemiAquatic;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.pathfinding.PathType;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

public class SemiAquaticSwimGoal extends RandomWalkingGoal 
{
	public SemiAquaticSwimGoal(BreedingSemiAquaticEntity creature, double speed, int chance) 
	{
		super(creature, speed, chance, false);
	}
	
	@Override
	public boolean canUse() 
	{
		if(mob.getTarget() != null || !this.mob.isInWater() && !this.mob.isInLava() && this.mob instanceof ISemiAquatic && !((ISemiAquatic) this.mob).shouldEnterWater()) 
		{
			return false;
		} 
		else 
		{
			if(!this.forceTrigger) 
			{
				if(this.mob.getRandom().nextInt(this.interval) != 0) 
				{
					return false;
				}
			}
			Vector3d vector3d = this.getPosition();
			if(vector3d == null) 
			{
				return false;
			} 
			else 
			{
				this.wantedX = vector3d.x;
				this.wantedY = vector3d.y;
				this.wantedZ = vector3d.z;
				this.forceTrigger = false;
				return true;
			}
		}
	}

	@Nullable
	@Override
	protected Vector3d getPosition() 
	{
		if(this.mob.hasRestriction() && this.mob.distanceToSqr(Vector3d.atCenterOf(this.mob.getRestrictCenter())) > this.mob.getRestrictRadius() * this.mob.getRestrictRadius()) 
		{
			return RandomPositionGenerator.getPosTowards(this.mob, 7, 3, Vector3d.atCenterOf(this.mob.getRestrictCenter()));
		}
		if(this.mob.getRandom().nextFloat() < 0.3F) 
		{
			Vector3d vector3d = findSurfaceTarget(this.mob, 15, 7);
			if(vector3d != null) 
			{
				return vector3d;
			}
		}
		Vector3d vector3d = RandomPositionGenerator.getPos(this.mob, 7, 3);

		for(int i = 0; vector3d != null && !this.mob.level.getFluidState(new BlockPos(vector3d)).is(FluidTags.LAVA) && !this.mob.level.getBlockState(new BlockPos(vector3d)).isPathfindable(this.mob.level, new BlockPos(vector3d), PathType.WATER) && i++ < 15; vector3d = RandomPositionGenerator.getPos(this.mob, 10, 7)) { }

		return vector3d;
	}
	
	private boolean canJumpTo(BlockPos pos, int dx, int dz, int scale) 
	{
		BlockPos blockpos = pos.offset(dx * scale, 0, dz * scale);
		return this.mob.level.getFluidState(blockpos).is(FluidTags.LAVA) || this.mob.level.getFluidState(blockpos).is(FluidTags.WATER) && !this.mob.level.getBlockState(blockpos).getMaterial().blocksMotion();
	}

	private boolean isAirAbove(BlockPos pos, int dx, int dz, int scale) 
	{
		return this.mob.level.getBlockState(pos.offset(dx * scale, 1, dz * scale)).isAir() && this.mob.level.getBlockState(pos.offset(dx * scale, 2, dz * scale)).isAir();
	}

	private Vector3d findSurfaceTarget(CreatureEntity creature, int i, int i1) 
	{
		BlockPos upPos = creature.blockPosition();
		while(creature.level.getFluidState(upPos).is(FluidTags.WATER) || creature.level.getFluidState(upPos).is(FluidTags.LAVA)) 
		{
			upPos = upPos.above();
		}
		if(isAirAbove(upPos.below(), 0, 0, 0) && canJumpTo(upPos.below(), 0, 0, 0)) 
		{
			return new Vector3d(upPos.getX() + 0.5F, upPos.getY() - 1F, upPos.getZ() + 0.5F);
		}
		return null;
	}
}
