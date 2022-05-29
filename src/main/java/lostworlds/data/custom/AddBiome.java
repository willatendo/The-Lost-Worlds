package lostworlds.data.custom;

import com.google.gson.JsonObject;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public record AddBiome(ResourceKey<Biome> biome, ClimateData parameters) {
	public JsonObject writeBiome() {
		JsonObject biome = new JsonObject();
		biome.addProperty("biome", this.biome.location().toString());
		biome.add("parameters", this.parameters.write());
		return biome;
	}
}
