package lostworlds.library.biome;

import lostworlds.content.config.LostWorldsConfig;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class OreGeneration 
{
	public static void addOresToOverworld(BiomeLoadingEvent event) 
	{
		BiomeGenerationSettingsBuilder generation = event.getGeneration();
		
		if(LostWorldsConfig.COMMON_CONFIG.copperOreGeneration.get())
		{
			generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.COPPER_ORE);
		}
		
		if(LostWorldsConfig.COMMON_CONFIG.plantFossilsInOverworld.get())
		{
			generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_PLANT_FOSSIL_ALETHOPTERIS);
			generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_PLANT_FOSSIL_BRAZILEA);
			generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_PLANT_FOSSIL_CALAMITES_SUCKOWII);
			generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_PLANT_FOSSIL_CEPHALOTAXUS);
			generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_PLANT_FOSSIL_DILLHOFFIA);
			generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_PLANT_FOSSIL_DUISBERGIA);
			generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_PLANT_FOSSIL_OSMUNDA);
			generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_PLANT_FOSSIL_WILLIAMSONIA);
			generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_PLANT_FOSSIL_ZAMITES);
		}
		
		if(LostWorldsConfig.COMMON_CONFIG.siltPatchGeneration.get())
		{
			generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.SILT_PATCH);
		}
	}
}
