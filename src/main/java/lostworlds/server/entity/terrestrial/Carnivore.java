package lostworlds.server.entity.terrestrial;

import lostworlds.server.entity.utils.enums.CreatureDiet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

public abstract class Carnivore extends EggLayingMob {
	public Carnivore(EntityType<? extends Carnivore> entity, Level world) {
		super(entity, world);
	}

	@Override
	public CreatureDiet diet() {
		return CreatureDiet.CARNIVORE;
	}

	@Override
	public void killed(ServerLevel world, LivingEntity entity) {
		super.killed(world, entity);
		this.increaseHunger(this.maxHunger());
		this.heal(3.0F);
		this.setAnimation(ANIMATION_EAT);
	}
}
