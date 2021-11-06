package lostworlds.content.server.setup.mappings;

import java.util.HashMap;
import java.util.Map;

import lostworlds.content.ModUtils;
import lostworlds.content.server.init.BiomeInit;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = ModUtils.ID, bus = Bus.MOD)
public class BiomeMappings 
{
	private static final Map<ResourceLocation, Biome> biomeRemappings = new HashMap<ResourceLocation, Biome>()
	{
		private static final long serialVersionUID = 2729763913422843325L;
		{
			put(ModUtils.rL("overworld_araucaria_forest"), BiomeInit.ARAUCARIA_FOREST);
			put(ModUtils.rL("overworld_araucaria_forest_hills"), BiomeInit.ARAUCARIA_FOREST);
			put(ModUtils.rL("overworld_conifer_forest"), BiomeInit.CONIFER_FOREST);
			put(ModUtils.rL("overworld_conifer_forest_hills"), BiomeInit.CONIFER_FOREST_HILLS);
			put(ModUtils.rL("overworld_ginkgo_forest"), BiomeInit.GINKGO_FOREST);
			put(ModUtils.rL("overworld_ginkgo_forest_hills"), BiomeInit.GINKGO_FOREST_HILLS);
			put(ModUtils.rL("permian_flood_basalt_plains"), BiomeInit.PERMIAN_FLOOD_BASALTS);
		}
	};
	
	@SubscribeEvent
	public void updateBiomeMappings(RegistryEvent.MissingMappings<Biome> event) 
	{
		if(event.getAllMappings().stream().filter(mapping -> mapping.key.getNamespace().equals(ModUtils.ID)).findAny().isPresent()) 
		{
			event.getAllMappings().stream().filter(mapping -> mapping.key.getNamespace().equals(ModUtils.ID)).forEach(mapping ->
			{
				if(biomeRemappings.containsKey(mapping.key))
				{
					remap(mapping, biomeRemappings);
				}
			});
		}
	}
	
	private <T extends IForgeRegistryEntry<T>> void remap(RegistryEvent.MissingMappings.Mapping<T> mapping, Map<ResourceLocation, T> remappings)
	{
		ResourceLocation key = mapping.key;
		if(remappings.containsKey(key))
		{
			mapping.remap(remappings.get(key));
			ModUtils.LOGGER.warn("Replaced " + key + " with " + remappings.get(key).getRegistryName());
		}
		else
		{
			mapping.ignore();
			ModUtils.LOGGER.warn("Could not find a mapping replacement for " + key + ". It was likely intentionally removed in an update.");
		}
	}

}
