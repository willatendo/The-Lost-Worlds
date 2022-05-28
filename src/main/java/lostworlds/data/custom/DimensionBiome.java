package lostworlds.data.custom;

import com.google.gson.JsonObject;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;

public record DimensionBiome(ResourceKey<Biome> biome, Climate.ParameterPoint parameters) {
	public JsonObject writeBiome() {
		JsonObject biome = new JsonObject();
		biome.addProperty("biome", this.biome.location().toString());
		JsonObject parameters = new JsonObject();
		biome.add("parameters", parameters);
		parameters.addProperty("temperature", this.parameters.temperature().min());
		parameters.addProperty("humidity", this.parameters.humidity().min());
		parameters.addProperty("depth", this.parameters.depth().min());
		parameters.addProperty("weirdness", this.parameters.weirdness().min());
		parameters.addProperty("continentalness", this.parameters.continentalness().min());
		parameters.addProperty("erosion", this.parameters.erosion().min());
		parameters.addProperty("offset", this.parameters.offset());
		return biome;
	}
}
