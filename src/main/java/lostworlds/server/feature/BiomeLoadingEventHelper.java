package lostworlds.server.feature;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class BiomeLoadingEventHelper {
	public static void addFeature(BiomeLoadingEvent event, GenerationStep.Decoration decoration, Holder<PlacedFeature> feature) {
		BiomeGenerationSettingsBuilder generation = event.getGeneration();
		generation.addFeature(decoration, feature);
	}

	public static void addOre(BiomeLoadingEvent event, Holder<PlacedFeature> ore) {
		addFeature(event, GenerationStep.Decoration.UNDERGROUND_ORES, ore);
	}

	public static void addSpawn(BiomeLoadingEvent event, MobCategory classification, EntityType toSpawn, int spawnWeight, int spawnGroupMinimum, int spawnGroupMaximum) {
		event.getSpawns().addSpawn(classification, new SpawnerData(toSpawn, spawnWeight, spawnGroupMinimum, spawnGroupMaximum));
	}
}
