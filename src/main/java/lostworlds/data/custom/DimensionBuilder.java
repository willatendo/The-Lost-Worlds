package lostworlds.data.custom;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class DimensionBuilder {
	private final String id;
	private final boolean ultrawarm;
	private final boolean natural;
	private final float coordinateScale;
	private final boolean hasSkylight;
	private final boolean hasCeiling;
	private final int ambientLight;
	private final boolean piglinSafe;
	private final boolean bedWorks;
	private final boolean respawnAnchorWorks;
	private final boolean hasRaids;
	private final int logicalHeight;
	private final int height;
	private final int minY;
	private final TagKey<Block> infiniburn;
	private final String effects;
	private final DimensionBiome[] dimensionBiomes;

	public DimensionBuilder(String id, boolean ultrawarm, boolean natural, float coordinateScale, boolean hasSkylight, boolean hasCeiling, int ambientLight, boolean piglinSafe, boolean bedWorks, boolean respawnAnchorWorks, boolean hasRaids, int logicalHeight, int height, int minY, TagKey<Block> infiniburn, String effects, DimensionBiome... dimensionBiomes) {
		this.id = id;
		this.ultrawarm = ultrawarm;
		this.natural = natural;
		this.coordinateScale = coordinateScale;
		this.hasSkylight = hasSkylight;
		this.hasCeiling = hasCeiling;
		this.ambientLight = ambientLight;
		this.piglinSafe = piglinSafe;
		this.bedWorks = bedWorks;
		this.respawnAnchorWorks = respawnAnchorWorks;
		this.hasRaids = hasRaids;
		this.logicalHeight = logicalHeight;
		this.height = height;
		this.minY = minY;
		this.infiniburn = infiniburn;
		this.effects = effects;
		this.dimensionBiomes = dimensionBiomes;
	}

	public String getId() {
		return this.id;
	}

	public JsonObject serializeDimensionType(String modid) {
		JsonObject dimensionTypeJson = new JsonObject();
		dimensionTypeJson.addProperty("name", modid + ":" + this.id);
		dimensionTypeJson.addProperty("ultrawarm", this.ultrawarm);
		dimensionTypeJson.addProperty("natural", this.natural);
		dimensionTypeJson.addProperty("coordinate_scale", this.coordinateScale);
		dimensionTypeJson.addProperty("has_skylight", this.hasSkylight);
		dimensionTypeJson.addProperty("has_ceiling", this.hasCeiling);
		dimensionTypeJson.addProperty("ambient_light", this.ambientLight);
		dimensionTypeJson.addProperty("piglin_safe", this.piglinSafe);
		dimensionTypeJson.addProperty("bed_works", this.bedWorks);
		dimensionTypeJson.addProperty("respawn_anchor_works", this.respawnAnchorWorks);
		dimensionTypeJson.addProperty("has_raids", this.hasRaids);
		dimensionTypeJson.addProperty("logical_height", this.logicalHeight);
		dimensionTypeJson.addProperty("height", this.height);
		dimensionTypeJson.addProperty("min_y", this.minY);
		dimensionTypeJson.addProperty("infiniburn", "#" + this.infiniburn.location().toString());
		dimensionTypeJson.addProperty("effects", this.effects);
		return dimensionTypeJson;
	}

	public JsonObject serializeDimension(String modid) {
		JsonObject dimensionJson = new JsonObject();
		dimensionJson.addProperty("type", modid + ":" + this.id);
		dimensionJson.addProperty("forge:use_server_seed", true);
		JsonObject generator = new JsonObject();
		dimensionJson.add("generator", generator);
		generator.addProperty("type", "minecraft:noise");
		generator.addProperty("seed", 0);
		generator.addProperty("settings", modid + ":" + this.id + "_noise");
		JsonObject biomeSource = new JsonObject();
		generator.add("biome_source", biomeSource);
		biomeSource.addProperty("type", "minecraft:multi_noise");
		JsonArray biomes = new JsonArray();
		biomeSource.add("biomes", biomes);
		for (DimensionBiome biome : this.dimensionBiomes) {
			biomes.add(biome.writeBiome());
		}
		return dimensionJson;
	}
}
