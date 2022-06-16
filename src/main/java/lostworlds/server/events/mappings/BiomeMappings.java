package lostworlds.server.events.mappings;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.biome.LostWorldsBiomes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = LostWorldsUtils.ID, bus = Bus.MOD)
public class BiomeMappings {
	private static final Map<ResourceLocation, Supplier<Biome>> biomeRemappings = new HashMap<ResourceLocation, Supplier<Biome>>() {
		private static final long serialVersionUID = 2729763913422843325L;
		{
			// Alpha 10 -> Alpha 11
			put(LostWorldsUtils.rL("overworld_araucaria_forest"), () -> LostWorldsBiomes.ARAUCARIA_FOREST.get());
			put(LostWorldsUtils.rL("overworld_araucaria_forest_hills"), () -> LostWorldsBiomes.ARAUCARIA_FOREST.get());
			put(LostWorldsUtils.rL("overworld_conifer_forest"), () -> LostWorldsBiomes.CONIFER_FOREST.get());
			put(LostWorldsUtils.rL("overworld_conifer_forest_hills"), () -> LostWorldsBiomes.CONIFER_FOREST.get());
			put(LostWorldsUtils.rL("overworld_ginkgo_forest"), () -> LostWorldsBiomes.GINKGO_FOREST.get());
			put(LostWorldsUtils.rL("overworld_ginkgo_forest_hills"), () -> LostWorldsBiomes.GINKGO_FOREST.get());
			put(LostWorldsUtils.rL("ginkgo_forest_hills"), () -> LostWorldsBiomes.GINKGO_FOREST.get());
			put(LostWorldsUtils.rL("permian_flood_basalt_plains"), () -> LostWorldsBiomes.PERMIAN_FLOOD_BASALTS.get());
			// Alpha 11 -> Alpha 11.1
			put(LostWorldsUtils.rL("permian_conifer_forest_hills"), () -> LostWorldsBiomes.PERMIAN_CONIFER_FOREST.get());
			put(LostWorldsUtils.rL("permian_ginkgo_forest_hills"), () -> LostWorldsBiomes.PERMIAN_GINKGO_FOREST.get());
			put(LostWorldsUtils.rL("permian_plains_hills"), () -> LostWorldsBiomes.PERMIAN_PLAINS.get());
			put(LostWorldsUtils.rL("permian_dried_plains_hills"), () -> LostWorldsBiomes.PERMIAN_DRIED_PLAINS.get());
			put(LostWorldsUtils.rL("permian_desert_hills"), () -> LostWorldsBiomes.PERMIAN_DESERT.get());
			put(LostWorldsUtils.rL("permian_mountains"), () -> LostWorldsBiomes.PERMIAN_WINDSWEPT_HILLS.get());
			put(LostWorldsUtils.rL("warm_deep_permian_ocean"), () -> LostWorldsBiomes.DEEP_WARM_PERMIAN_OCEAN.get());
			put(LostWorldsUtils.rL("jurassic_araucaria_forest_hills"), () -> LostWorldsBiomes.JURASSIC_ARAUCARIA_FOREST.get());
			put(LostWorldsUtils.rL("jurassic_conifer_forest_hills"), () -> LostWorldsBiomes.JURASSIC_CONIFER_FOREST.get());
			put(LostWorldsUtils.rL("jurassic_ginkgo_forest_hills"), () -> LostWorldsBiomes.JURASSIC_GINKGO_FOREST.get());
			put(LostWorldsUtils.rL("jurassic_redwoods_forest_hills"), () -> LostWorldsBiomes.JURASSIC_REDWOODS_FOREST.get());
			put(LostWorldsUtils.rL("jurassic_plains_hills"), () -> LostWorldsBiomes.JURASSIC_PLAINS.get());
			put(LostWorldsUtils.rL("jurassic_mountains"), () -> LostWorldsBiomes.JURASSIC_WINDSWEPT_HILLS.get());
			put(LostWorldsUtils.rL("jurassic_erroded_mountains"), () -> LostWorldsBiomes.JURASSIC_ERRODED_WINDSWEPT_HILLS.get());
			put(LostWorldsUtils.rL("jurassic_desert_hills"), () -> LostWorldsBiomes.JURASSIC_DESERT.get());
			put(LostWorldsUtils.rL("warm_deep_jurassic_ocean"), () -> LostWorldsBiomes.DEEP_WARM_JURASSIC_OCEAN.get());
			put(LostWorldsUtils.rL("cretaceous_araucaria_forest_hills"), () -> LostWorldsBiomes.CRETACEOUS_ARAUCARIA_FOREST.get());
			put(LostWorldsUtils.rL("cretaceous_conifer_forest_hills"), () -> LostWorldsBiomes.CRETACEOUS_CONIFER_FOREST.get());
			put(LostWorldsUtils.rL("cretaceous_ginkgo_forest_hills"), () -> LostWorldsBiomes.CRETACEOUS_GINKGO_FOREST.get());
			put(LostWorldsUtils.rL("cretaceous_redwoods_forest_hills"), () -> LostWorldsBiomes.CRETACEOUS_REDWOODS_FOREST.get());
			put(LostWorldsUtils.rL("cretaceous_plains_hills"), () -> LostWorldsBiomes.CRETACEOUS_PLAINS.get());
			put(LostWorldsUtils.rL("cretaceous_arctic_hills"), () -> LostWorldsBiomes.CRETACEOUS_ARCTIC.get());
			put(LostWorldsUtils.rL("cretaceous_frozen_forest_hills"), () -> LostWorldsBiomes.CRETACEOUS_FROZEN_FOREST.get());
			put(LostWorldsUtils.rL("cretaceous_mountains"), () -> LostWorldsBiomes.CRETACEOUS_WINDSWEPT_HILLS.get());
			put(LostWorldsUtils.rL("cretaceous_erroded_mountains"), () -> LostWorldsBiomes.CRETACEOUS_ERRODED_WINDSWEPT_HILLS.get());
			put(LostWorldsUtils.rL("cretaceous_desert_hills"), () -> LostWorldsBiomes.CRETACEOUS_DESERT.get());
			put(LostWorldsUtils.rL("cretaceous_red_desert_hills"), () -> LostWorldsBiomes.CRETACEOUS_RED_DESERT.get());
			put(LostWorldsUtils.rL("cold_deep_cretaceous_ocean"), () -> LostWorldsBiomes.DEEP_COLD_CRETACEOUS_OCEAN.get());
			put(LostWorldsUtils.rL("warm_deep_cretaceous_ocean"), () -> LostWorldsBiomes.DEEP_WARM_CRETACEOUS_OCEAN.get());
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

	private <T extends IForgeRegistryEntry<T>> void remap(RegistryEvent.MissingMappings.Mapping<T> mapping, Map<ResourceLocation, Supplier<T>> remappings) {
		ResourceLocation key = mapping.key;
		if (remappings.containsKey(key)) {
			mapping.remap(remappings.get(key).get());
			LostWorldsUtils.LOGGER.warn("Replaced " + key + " with " + remappings.get(key).get().getRegistryName());
		} else {
			mapping.ignore();
			LostWorldsUtils.LOGGER.warn("Could not find a mapping replacement for " + key + ". It was likely intentionally removed in an update.");
		}
	}
}
