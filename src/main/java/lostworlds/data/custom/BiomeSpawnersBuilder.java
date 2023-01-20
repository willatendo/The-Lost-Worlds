package lostworlds.data.custom;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public record BiomeSpawnersBuilder(List<AddSpawn> monsters, List<AddSpawn> creature, List<AddSpawn> ambient, List<AddSpawn> axolotls, List<AddSpawn> undergroundWaterCreature, List<AddSpawn> waterCreature, List<AddSpawn> waterAmbient, List<AddSpawn> misc) {
	public JsonObject serializeSpawns() {
		JsonObject spawners = new JsonObject();
		JsonArray monsters = new JsonArray();
		for (AddSpawn spawn : this.monsters) {
			JsonObject newSpawn = new JsonObject();
			newSpawn.addProperty("type", spawn.entityType().getRegistryName().toString());
			newSpawn.addProperty("weight", spawn.spawnWeight());
			newSpawn.addProperty("minCount", spawn.spawnGroupMinimum());
			newSpawn.addProperty("maxCount", spawn.spawnGroupMaximum());
			monsters.add(newSpawn);
		}
		spawners.add("monsters", monsters);
		JsonArray creature = new JsonArray();
		for (AddSpawn spawn : this.creature) {
			JsonObject newSpawn = new JsonObject();
			newSpawn.addProperty("type", spawn.entityType().getRegistryName().toString());
			newSpawn.addProperty("weight", spawn.spawnWeight());
			newSpawn.addProperty("minCount", spawn.spawnGroupMinimum());
			newSpawn.addProperty("maxCount", spawn.spawnGroupMaximum());
			creature.add(newSpawn);
		}
		spawners.add("creature", creature);
		JsonArray ambient = new JsonArray();
		for (AddSpawn spawn : this.ambient) {
			JsonObject newSpawn = new JsonObject();
			newSpawn.addProperty("type", spawn.entityType().getRegistryName().toString());
			newSpawn.addProperty("weight", spawn.spawnWeight());
			newSpawn.addProperty("minCount", spawn.spawnGroupMinimum());
			newSpawn.addProperty("maxCount", spawn.spawnGroupMaximum());
			ambient.add(newSpawn);
		}
		spawners.add("ambient", ambient);
		JsonArray axolotls = new JsonArray();
		for (AddSpawn spawn : this.axolotls) {
			JsonObject newSpawn = new JsonObject();
			newSpawn.addProperty("type", spawn.entityType().getRegistryName().toString());
			newSpawn.addProperty("weight", spawn.spawnWeight());
			newSpawn.addProperty("minCount", spawn.spawnGroupMinimum());
			newSpawn.addProperty("maxCount", spawn.spawnGroupMaximum());
			axolotls.add(newSpawn);
		}
		spawners.add("axolotls", axolotls);
		JsonArray undergroundWaterCreature = new JsonArray();
		for (AddSpawn spawn : this.undergroundWaterCreature) {
			JsonObject newSpawn = new JsonObject();
			newSpawn.addProperty("type", spawn.entityType().getRegistryName().toString());
			newSpawn.addProperty("weight", spawn.spawnWeight());
			newSpawn.addProperty("minCount", spawn.spawnGroupMinimum());
			newSpawn.addProperty("maxCount", spawn.spawnGroupMaximum());
			undergroundWaterCreature.add(newSpawn);
		}
		spawners.add("underground_water_creature", undergroundWaterCreature);
		JsonArray waterCreature = new JsonArray();
		for (AddSpawn spawn : this.waterCreature) {
			JsonObject newSpawn = new JsonObject();
			newSpawn.addProperty("type", spawn.entityType().getRegistryName().toString());
			newSpawn.addProperty("weight", spawn.spawnWeight());
			newSpawn.addProperty("minCount", spawn.spawnGroupMinimum());
			newSpawn.addProperty("maxCount", spawn.spawnGroupMaximum());
			waterCreature.add(newSpawn);
		}
		spawners.add("water_creature", waterCreature);
		JsonArray waterAmbient = new JsonArray();
		for (AddSpawn spawn : this.waterAmbient) {
			JsonObject newSpawn = new JsonObject();
			newSpawn.addProperty("type", spawn.entityType().getRegistryName().toString());
			newSpawn.addProperty("weight", spawn.spawnWeight());
			newSpawn.addProperty("minCount", spawn.spawnGroupMinimum());
			newSpawn.addProperty("maxCount", spawn.spawnGroupMaximum());
			waterAmbient.add(newSpawn);
		}
		spawners.add("water_ambient", waterAmbient);
		JsonArray misc = new JsonArray();
		for (AddSpawn spawn : this.misc) {
			JsonObject newSpawn = new JsonObject();
			newSpawn.addProperty("type", spawn.entityType().getRegistryName().toString());
			newSpawn.addProperty("weight", spawn.spawnWeight());
			newSpawn.addProperty("minCount", spawn.spawnGroupMinimum());
			newSpawn.addProperty("maxCount", spawn.spawnGroupMaximum());
			misc.add(newSpawn);
		}
		spawners.add("misc", misc);
		return spawners;
	}
}
