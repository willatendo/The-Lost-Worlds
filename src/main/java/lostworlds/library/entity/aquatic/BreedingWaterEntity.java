package lostworlds.library.entity.aquatic;

import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public abstract class BreedingWaterEntity extends AnimalEntity {
	public BreedingWaterEntity(EntityType<? extends BreedingWaterEntity> entity, World world) {
		super(entity, world);
		this.setPathfindingMalus(PathNodeType.WATER, 0.0F);
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	public CreatureAttribute getMobType() {
		return CreatureAttribute.WATER;
	}

	@Override
	public boolean checkSpawnObstruction(IWorldReader reader) {
		return reader.isUnobstructed(this);
	}

	@Override
	public int getAmbientSoundInterval() {
		return 120;
	}

	@Override
	protected int getExperienceReward(PlayerEntity entity) {
		return 1 + this.level.random.nextInt(3);
	}

	protected void handleAirSupply(int air) {
		if (this.isAlive() && !this.isInWaterOrBubble()) {
			this.setAirSupply(air - 1);
			if (this.getAirSupply() == -20) {
				this.setAirSupply(0);
				this.hurt(DamageSource.DROWN, 2.0F);
			}
		} else {
			this.setAirSupply(300);
		}

	}

	@Override
	public void baseTick() {
		int i = this.getAirSupply();
		super.baseTick();
		this.handleAirSupply(i);
	}

	@Override
	public boolean isPushedByFluid() {
		return false;
	}

	@Override
	public boolean canBeLeashed(PlayerEntity entity) {
		return false;
	}
}
