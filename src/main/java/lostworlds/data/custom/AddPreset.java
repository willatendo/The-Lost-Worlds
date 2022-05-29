package lostworlds.data.custom;

import com.google.gson.JsonObject;

import net.minecraft.world.level.biome.MultiNoiseBiomeSource;

public record AddPreset(MultiNoiseBiomeSource.Preset preset) implements BiomeSource {
	@Override
	public JsonObject writeBiome() {
		return new JsonObject();
	}
}
