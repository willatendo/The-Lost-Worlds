package lostworlds.server.entity.terrestrial;

import lostworlds.server.entity.utils.enums.CreatureDiet;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public abstract class CarnivoreEntity extends EggLayingEntity {
	public CarnivoreEntity(EntityType<? extends CarnivoreEntity> entity, World world) {
		super(entity, world);
	}

	@Override
	public CreatureDiet diet() {
		return CreatureDiet.CARNIVORE;
	}

	@Override
	public void killed(ServerWorld world, LivingEntity entity) {
		super.killed(world, entity);
		this.increaseHunger(this.maxHunger());
		this.heal(3.0F);
		this.setAnimation(ANIMATION_EAT);
	}
}
