package lostworlds.data.custom;

import java.util.Optional;

import com.google.gson.JsonObject;

import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.AmbientAdditionsSettings;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.AmbientParticleSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;

public record BiomeEffectsBuilder(Optional<Integer> skyColour, int fogColour, int waterColour, int waterFogColour, Optional<Integer> grassColourOverride, Optional<Integer> foliageColourOverride, Optional<BiomeSpecialEffects.GrassColorModifier> grassColourModifier, Optional<SoundEvent> ambientLoopSoundEvent, AmbientMoodSettings ambientMoodSettings, Optional<AmbientAdditionsSettings> ambientAdditionsSettings, Optional<Music> backgroundMusic, Optional<AmbientParticleSettings> ambientParticleSettings) {
	public JsonObject serializeBiomeEffects(float temperature) {
		JsonObject biomeEffects = new JsonObject();
		int finalSkyColour;
		if (this.skyColour.isPresent()) {
			finalSkyColour = skyColour.get();
		} else {
			finalSkyColour = calculateSkyColor(temperature);
		}
		biomeEffects.addProperty("sky_color", finalSkyColour);
		biomeEffects.addProperty("fog_color", this.fogColour);
		biomeEffects.addProperty("water_color", this.waterColour);
		biomeEffects.addProperty("water_fog_color", this.waterFogColour);
		if (this.grassColourOverride.isPresent()) {
			biomeEffects.addProperty("grass_color", this.grassColourOverride.get());
		}
		if (this.foliageColourOverride.isPresent()) {
			biomeEffects.addProperty("foliage_color", this.foliageColourOverride.get());
		}
		if (this.grassColourModifier.isPresent()) {
			biomeEffects.addProperty("grass_color_modifier", this.grassColourModifier.get().getName());
		}
		if (this.ambientLoopSoundEvent.isPresent()) {
			biomeEffects.addProperty("ambient_sound", this.ambientLoopSoundEvent.get().getLocation().toString());
		}
		JsonObject moodSound = new JsonObject();
		moodSound.addProperty("sound", this.ambientMoodSettings.getSoundEvent().getLocation().toString());
		moodSound.addProperty("tick_delay", this.ambientMoodSettings.getTickDelay());
		moodSound.addProperty("block_search_extent", this.ambientMoodSettings.getBlockSearchExtent());
		moodSound.addProperty("offset", this.ambientMoodSettings.getSoundPositionOffset());
		biomeEffects.add("mood_sound", moodSound);
		if (this.ambientAdditionsSettings.isPresent()) {
			JsonObject additionsSound = new JsonObject();
			additionsSound.addProperty("sound", this.ambientAdditionsSettings.get().getSoundEvent().getLocation().toString());
			additionsSound.addProperty("tick_chance", this.ambientAdditionsSettings.get().getTickChance());
			biomeEffects.add("additions_sound", additionsSound);
		}
		if (this.backgroundMusic.isPresent()) {
			JsonObject music = new JsonObject();
			music.addProperty("sound", this.backgroundMusic.get().getEvent().getLocation().toString());
			music.addProperty("min_delay", this.backgroundMusic.get().getMinDelay());
			music.addProperty("max_delay", this.backgroundMusic.get().getMaxDelay());
			music.addProperty("replace_current_music", this.backgroundMusic.get().replaceCurrentMusic());
			biomeEffects.add("music", music);
		}
		if (this.ambientParticleSettings.isPresent()) {
			JsonObject particle = new JsonObject();
			JsonObject options = new JsonObject();
			options.addProperty("type", this.ambientParticleSettings.get().getOptions().getType().getRegistryName().toString());
			particle.add("options", options);
			particle.addProperty("probability", 0.15F);
			biomeEffects.add("particle", particle);
		}
		return biomeEffects;
	}

	public static int calculateSkyColor(float temperature) {
		float temp = temperature / 3.0F;
		temp = Mth.clamp(temp, -1.0F, 1.0F);
		return Mth.hsvToRgb(0.62222224F - temp * 0.05F, 0.5F + temp * 0.1F, 1.0F);
	}
}
