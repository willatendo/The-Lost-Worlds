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
	}
}
