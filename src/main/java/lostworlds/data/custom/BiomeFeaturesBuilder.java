package lostworlds.data.custom;

import java.util.List;

import com.google.gson.JsonArray;

import net.minecraft.world.level.levelgen.feature.Feature;

public record BiomeFeaturesBuilder(List<Feature<?>> step0, List<Feature<?>> step1, List<Feature<?>> step2, List<Feature<?>> step3, List<Feature<?>> step4, List<Feature<?>> step5, List<Feature<?>> step6, List<Feature<?>> step7, List<Feature<?>> step8, List<Feature<?>> step9, List<Feature<?>> step10) {
	public JsonArray serializeFeatures() {
		JsonArray features = new JsonArray();
		JsonArray step0 = new JsonArray();
		for (Feature<?> feature : this.step0) {
			step0.add(feature.getRegistryName().toString());
		}
		features.add(step0);
		JsonArray step1 = new JsonArray();
		for (Feature<?> feature : this.step1) {
			step1.add(feature.getRegistryName().toString());
		}
		features.add(step1);
		JsonArray step2 = new JsonArray();
		for (Feature<?> feature : this.step2) {
			step2.add(feature.getRegistryName().toString());
		}
		features.add(step2);
		JsonArray step3 = new JsonArray();
		for (Feature<?> feature : this.step3) {
			step3.add(feature.getRegistryName().toString());
		}
		features.add(step3);
		JsonArray step4 = new JsonArray();
		for (Feature<?> feature : this.step4) {
			step4.add(feature.getRegistryName().toString());
		}
		features.add(step4);
		JsonArray step5 = new JsonArray();
		for (Feature<?> feature : this.step5) {
			step5.add(feature.getRegistryName().toString());
		}
		features.add(step5);
		JsonArray step6 = new JsonArray();
		for (Feature<?> feature : this.step6) {
			step6.add(feature.getRegistryName().toString());
		}
		features.add(step6);
		JsonArray step7 = new JsonArray();
		for (Feature<?> feature : this.step7) {
			step7.add(feature.getRegistryName().toString());
		}
		features.add(step7);
		JsonArray step8 = new JsonArray();
		for (Feature<?> feature : this.step8) {
			step8.add(feature.getRegistryName().toString());
		}
		features.add(step8);
		JsonArray step9 = new JsonArray();
		for (Feature<?> feature : this.step9) {
			step9.add(feature.getRegistryName().toString());
		}
		features.add(step9);
		JsonArray step10 = new JsonArray();
		for (Feature<?> feature : this.step10) {
			step10.add(feature.getRegistryName().toString());
		}
		features.add(step10);

		return features;
	}
}
