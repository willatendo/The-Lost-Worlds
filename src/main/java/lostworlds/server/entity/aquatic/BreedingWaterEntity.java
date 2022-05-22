package lostworlds.server.entity.aquatic;

import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;

public abstract class BreedingWaterEntity extends Animal {
	public BreedingWaterEntity(EntityType<? extends BreedingWaterEntity> entity, Level world) {
		super(entity, world);
		this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	public MobType getMobType() {
		return MobType.WATER;
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader reader) {
		return reader.isUnobstructed(this);
	}

	@Override
	public int getAmbientSoundInterval() {
		return 120;
	}

	@Override
	protected int getExperienceReward(Player entity) {
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
	public boolean canBeLeashed(Player entity) {
		return false;
	}
}
