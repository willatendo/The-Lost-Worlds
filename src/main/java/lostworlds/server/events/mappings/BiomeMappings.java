package lostworlds.server.events.mappings;

import java.util.HashMap;
import java.util.Map;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.biome.LostWorldsBiomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = LostWorldsUtils.ID, bus = Bus.MOD)
public class BiomeMappings {
	private static final Map<ResourceLocation, Biome> biomeRemappings = new HashMap<ResourceLocation, Biome>() {
		private static final long serialVersionUID = 2729763913422843325L;
		{
			put(LostWorldsUtils.rL("overworld_araucaria_forest"), LostWorldsBiomes.ARAUCARIA_FOREST);
			put(LostWorldsUtils.rL("overworld_araucaria_forest_hills"), LostWorldsBiomes.ARAUCARIA_FOREST);
			put(LostWorldsUtils.rL("overworld_conifer_forest"), LostWorldsBiomes.CONIFER_FOREST);
			put(LostWorldsUtils.rL("overworld_conifer_forest_hills"), LostWorldsBiomes.CONIFER_FOREST_HILLS);
			put(LostWorldsUtils.rL("overworld_ginkgo_forest"), LostWorldsBiomes.GINKGO_FOREST);
			put(LostWorldsUtils.rL("overworld_ginkgo_forest_hills"), LostWorldsBiomes.GINKGO_FOREST_HILLS);
			put(LostWorldsUtils.rL("permian_flood_basalt_plains"), LostWorldsBiomes.PERMIAN_FLOOD_BASALTS);
		}
	};

	@SubscribeEvent
	public void updateBiomeMappings(RegistryEvent.MissingMappings<Biome> event) {
		if (event.getAllMappings().stream().filter(mapping -> mapping.key.getNamespace().equals(LostWorldsUtils.ID)).findAny().isPresent()) {
			event.getAllMappings().stream().filter(mapping -> mapping.key.getNamespace().equals(LostWorldsUtils.ID)).forEach(mapping -> {
				if (biomeRemappings.containsKey(mapping.key)) {
					remap(mapping, biomeRemappings);
				}
			});
		}
	}

	private <T extends IForgeRegistryEntry<T>> void remap(RegistryEvent.MissingMappings.Mapping<T> mapping, Map<ResourceLocation, T> remappings) {
		ResourceLocation key = mapping.key;
		if (remappings.containsKey(key)) {
			mapping.remap(remappings.get(key));
			LostWorldsUtils.LOGGER.warn("Replaced " + key + " with " + remappings.get(key).getRegistryName());
		} else {
			mapping.ignore();
			LostWorldsUtils.LOGGER.warn("Could not find a mapping replacement for " + key + ". It was likely intentionally removed in an update.");
		}
	}

}
