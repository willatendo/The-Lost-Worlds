package lostworlds.data.custom;

import java.util.Optional;

import com.google.gson.JsonObject;

import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.biome.Biome.TemperatureModifier;

public record BiomeBuilder(String id, float temperature, float downfall, Precipitation precipitation, Optional<TemperatureModifier> temperatureModifier, BiomeCategory biomeCategory, BiomeEffectsBuilder biomeEffectsBuilder, BiomeSpawnersBuilder biomeSpawnersBuilder, Optional<BiomeCavesBuilder> biomeCavesBuilder, BiomeFeaturesBuilder biomeFeaturesBuilder) {
	public JsonObject build() {
		JsonObject biome = new JsonObject();
		biome.addProperty("temperature", this.temperature);
		biome.addProperty("downfall", this.downfall);
		biome.addProperty("precipitation", this.precipitation.getName());
		if (this.temperatureModifier.isPresent()) {
			biome.addProperty("temperature_modifier", this.temperatureModifier.get().getName());
		}
		biome.addProperty("category", this.biomeCategory.getName());
		biome.add("effects", this.biomeEffectsBuilder.serializeBiomeEffects(this.temperature));
		biome.add("spawners", this.biomeSpawnersBuilder.serializeSpawns());
		if (this.biomeCavesBuilder.isPresent()) {
			biome.add("carvers", this.biomeCavesBuilder.get().serializeCaves());
		}
		biome.add("features", this.biomeFeaturesBuilder.serializeFeatures());
		return biome;
	}
}
