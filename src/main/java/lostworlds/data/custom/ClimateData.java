package lostworlds.data.custom;

import com.google.gson.JsonObject;

public record ClimateData(float temperature, float humidity, float continentalness, float erosion, float depth, float weirdness, float offset) {
	public JsonObject write() {
		JsonObject climateParameters = new JsonObject();
		climateParameters.addProperty("temperature", this.temperature);
		climateParameters.addProperty("humidity", this.humidity);
		climateParameters.addProperty("continentalness", this.continentalness);
		climateParameters.addProperty("erosion", this.erosion);
		climateParameters.addProperty("depth", this.depth);
		climateParameters.addProperty("weirdness", this.weirdness);
		climateParameters.addProperty("offset", this.offset);
		return climateParameters;
	}
}
