package lostworlds.library.biome;

import lostworlds.content.config.LostWorldsConfig;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import tyrannotitanlib.library.base.biome.generation.TyrannoWorld;

public class OreGeneration 
{
	public static void addOresToOverworld(BiomeLoadingEvent event) 
	{		
		if(LostWorldsConfig.COMMON_CONFIG.amberOre.get())
		{
			TyrannoWorld.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_AMBER_ORE);
		}
		
		if(LostWorldsConfig.COMMON_CONFIG.plantFossilsInOverworld.get())
		{
			TyrannoWorld.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_PLANT_FOSSIL_ALETHOPTERIS);
			TyrannoWorld.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_PLANT_FOSSIL_BRAZILEA);
			TyrannoWorld.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_PLANT_FOSSIL_CALAMITES_SUCKOWII);
			TyrannoWorld.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_PLANT_FOSSIL_CEPHALOTAXUS);
			TyrannoWorld.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_PLANT_FOSSIL_DILLHOFFIA);
			TyrannoWorld.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_PLANT_FOSSIL_DUISBERGIA);
			TyrannoWorld.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_PLANT_FOSSIL_OSMUNDA);
			TyrannoWorld.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_PLANT_FOSSIL_WILLIAMSONIA);
			TyrannoWorld.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_PLANT_FOSSIL_ZAMITES);
		}
		
		if(LostWorldsConfig.COMMON_CONFIG.siltPatchGeneration.get())
		{
			TyrannoWorld.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.SILT_PATCH);
		}
	}
}
