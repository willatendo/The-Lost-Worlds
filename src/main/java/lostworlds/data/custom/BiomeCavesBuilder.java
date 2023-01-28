package lostworlds.data.custom;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;

public record BiomeCavesBuilder<T extends CarverConfiguration> (List<Holder<ConfiguredWorldCarver<T>>> caves) {
	public JsonObject serializeCaves() {
		JsonObject caves = new JsonObject();
		JsonArray air = new JsonArray();
		for (Holder<ConfiguredWorldCarver<T>> carver : this.caves) {
			ResourceLocation name = BuiltinRegistries.CONFIGURED_CARVER.getKey(carver.value());
			air.add(name.toString());
		}
		caves.add("air", air);
		return caves;
	}
}
