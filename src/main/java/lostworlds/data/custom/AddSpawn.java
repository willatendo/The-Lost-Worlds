package lostworlds.data.custom;

import net.minecraft.world.entity.EntityType;

public record AddSpawn(EntityType entityType, int spawnWeight, int spawnGroupMinimum, int spawnGroupMaximum) {

}
