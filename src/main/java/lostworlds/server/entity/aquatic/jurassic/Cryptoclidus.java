package lostworlds.server.entity.aquatic.jurassic;

import lostworlds.server.entity.aquatic.DolphinLike;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class Cryptoclidus extends DolphinLike {
	public Cryptoclidus(EntityType<? extends Cryptoclidus> entity, Level level) {
		super(entity, level);
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
		return null;
	}
}
