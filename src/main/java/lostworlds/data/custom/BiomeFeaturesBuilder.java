package lostworlds.data.custom;

import java.util.List;

import com.google.gson.JsonArray;

import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public record BiomeFeaturesBuilder(List<Holder<PlacedFeature>> step0, List<Holder<PlacedFeature>> step1, List<Holder<PlacedFeature>> step2, List<Holder<PlacedFeature>> step3, List<Holder<PlacedFeature>> step4, List<Holder<PlacedFeature>> step5, List<Holder<PlacedFeature>> step6, List<Holder<PlacedFeature>> step7, List<Holder<PlacedFeature>> step8, List<Holder<PlacedFeature>> step9, List<Holder<PlacedFeature>> step10) {
	public JsonArray serializeFeatures() {
		JsonArray features = new JsonArray();
		JsonArray step0 = new JsonArray();
		for (Holder<PlacedFeature> placedFeature : this.step0) {
			ResourceLocation name = BuiltinRegistries.PLACED_FEATURE.getKey(placedFeature.value());
			step0.add(name.toString());
		}
		features.add(step0);
		JsonArray step1 = new JsonArray();
		for (Holder<PlacedFeature> placedFeature : this.step1) {
			ResourceLocation name = BuiltinRegistries.PLACED_FEATURE.getKey(placedFeature.value());
			step1.add(name.toString());
		}
		features.add(step1);
		JsonArray step2 = new JsonArray();
		for (Holder<PlacedFeature> placedFeature : this.step2) {
			ResourceLocation name = BuiltinRegistries.PLACED_FEATURE.getKey(placedFeature.value());
			step2.add(name.toString());
		}
		features.add(step2);
		JsonArray step3 = new JsonArray();
		for (Holder<PlacedFeature> placedFeature : this.step3) {
			ResourceLocation name = BuiltinRegistries.PLACED_FEATURE.getKey(placedFeature.value());
			step3.add(name.toString());
		}
		features.add(step3);
		JsonArray step4 = new JsonArray();
		for (Holder<PlacedFeature> placedFeature : this.step4) {
			ResourceLocation name = BuiltinRegistries.PLACED_FEATURE.getKey(placedFeature.value());
			step4.add(name.toString());
		}
		features.add(step4);
		JsonArray step5 = new JsonArray();
		for (Holder<PlacedFeature> placedFeature : this.step5) {
			ResourceLocation name = BuiltinRegistries.PLACED_FEATURE.getKey(placedFeature.value());
			step5.add(name.toString());
		}
		features.add(step5);
		JsonArray step6 = new JsonArray();
		for (Holder<PlacedFeature> placedFeature : this.step6) {
			ResourceLocation name = BuiltinRegistries.PLACED_FEATURE.getKey(placedFeature.value());
			step6.add(name.toString());
		}
		features.add(step6);
		JsonArray step7 = new JsonArray();
		for (Holder<PlacedFeature> placedFeature : this.step7) {
			ResourceLocation name = BuiltinRegistries.PLACED_FEATURE.getKey(placedFeature.value());
			step7.add(name.toString());
		}
		features.add(step7);
		JsonArray step8 = new JsonArray();
		for (Holder<PlacedFeature> placedFeature : this.step8) {
			ResourceLocation name = BuiltinRegistries.PLACED_FEATURE.getKey(placedFeature.value());
			step8.add(name.toString());
		}
		features.add(step8);
		JsonArray step9 = new JsonArray();
		for (Holder<PlacedFeature> placedFeature : this.step9) {
			ResourceLocation name = BuiltinRegistries.PLACED_FEATURE.getKey(placedFeature.value());
			step9.add(name.toString());
		}
		features.add(step9);
		JsonArray step10 = new JsonArray();
		for (Holder<PlacedFeature> placedFeature : this.step10) {
			ResourceLocation name = BuiltinRegistries.PLACED_FEATURE.getKey(placedFeature.value());
			step10.add(name.toString());
		}
		features.add(step10);

		return features;
	}
}
