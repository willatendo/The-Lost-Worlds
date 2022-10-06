package lostworlds.server.world;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import lostworlds.client.LostWorldsConfig;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.biome.LostWorldsBiomes;
import lostworlds.server.entity.LostWorldsEntities;
import lostworlds.server.feature.FeatureAdder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class EntityGen {
	public static void init(BiomeLoadingEvent event) {
		if (LostWorldsUtils.SERVER_CONFIG.livingFossils.get()) {
			List<? extends String> nautilusBiomes = Lists.newArrayList("minecraft:warm_ocean");
			if (nautilusBiomes.contains(event.getName().toString())) {
				FeatureAdder.addSpawn(event, MobCategory.WATER_CREATURE, LostWorldsEntities.NAUTILUS.get(), LostWorldsUtils.SERVER_CONFIG.nautilusSpawnWeight.get(), LostWorldsUtils.SERVER_CONFIG.nautilusSpawnGroupMinimum.get(), LostWorldsUtils.SERVER_CONFIG.nautilusSpawnGroupMaximum.get());
			}
		}

		// Cretaceous

		// Terrestrial
		ArrayList<ResourceLocation> carnotaurusBiomes = Lists.newArrayList(LostWorldsBiomes.CRETACEOUS_ARAUCARIA_FOREST.getRegistryName(), LostWorldsBiomes.CRETACEOUS_CONIFER_FOREST.getRegistryName(), LostWorldsBiomes.CRETACEOUS_GINKGO_FOREST.getRegistryName());
		if (carnotaurusBiomes.contains(event.getName())) {
			FeatureAdder.addSpawn(event, MobCategory.CREATURE, LostWorldsEntities.CARNOTAURUS.get(), LostWorldsConfig.COMMON_CONFIG.carnotaurusSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.carnotaurusSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.carnotaurusSpawnGroupMaximum.get());
		}

		ArrayList<ResourceLocation> fukuivenatorBiomes = Lists.newArrayList(LostWorldsBiomes.CRETACEOUS_ARAUCARIA_FOREST.getRegistryName(), LostWorldsBiomes.CRETACEOUS_CONIFER_FOREST.getRegistryName(), LostWorldsBiomes.CRETACEOUS_WINDSWEPT_HILLS.getRegistryName(), LostWorldsBiomes.CRETACEOUS_ERRODED_WINDSWEPT_HILLS.getRegistryName());
		if (fukuivenatorBiomes.contains(event.getName())) {
			FeatureAdder.addSpawn(event, MobCategory.CREATURE, LostWorldsEntities.FUKUIVENATOR.get(), LostWorldsConfig.COMMON_CONFIG.fukuivenatorSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.fukuivenatorSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.fukuivenatorSpawnGroupMaximum.get());
		}

		ArrayList<ResourceLocation> giganotosaurusBiomes = Lists.newArrayList(LostWorldsBiomes.CRETACEOUS_PLAINS.getRegistryName(), LostWorldsBiomes.CRETACEOUS_RED_DESERT.getRegistryName(), LostWorldsBiomes.CRETACEOUS_DESERT.getRegistryName());
		if (giganotosaurusBiomes.contains(event.getName())) {
			FeatureAdder.addSpawn(event, MobCategory.CREATURE, LostWorldsEntities.GIGANOTOSAURUS.get(), LostWorldsConfig.COMMON_CONFIG.giganotosaurusSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.giganotosaurusSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.giganotosaurusSpawnGroupMaximum.get());
		}

		ArrayList<ResourceLocation> ouranosaurusBiomes = Lists.newArrayList(LostWorldsBiomes.CRETACEOUS_PLAINS.getRegistryName(), LostWorldsBiomes.CRETACEOUS_RED_DESERT.getRegistryName(), LostWorldsBiomes.CRETACEOUS_SWAMP.getRegistryName(), LostWorldsBiomes.CRETACEOUS_BOG.getRegistryName());
		if (ouranosaurusBiomes.contains(event.getName())) {
			FeatureAdder.addSpawn(event, MobCategory.CREATURE, LostWorldsEntities.OURANOSAURUS.get(), LostWorldsConfig.COMMON_CONFIG.ouranosaurusSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.ouranosaurusSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.ouranosaurusSpawnGroupMaximum.get());
		}

		ArrayList<ResourceLocation> parksosaurusBiomes = Lists.newArrayList(LostWorldsBiomes.CRETACEOUS_PLAINS.getRegistryName(), LostWorldsBiomes.CRETACEOUS_ARAUCARIA_FOREST.getRegistryName(), LostWorldsBiomes.CRETACEOUS_SWAMP.getRegistryName(), LostWorldsBiomes.CRETACEOUS_BOG.getRegistryName());
		if (parksosaurusBiomes.contains(event.getName())) {
			FeatureAdder.addSpawn(event, MobCategory.CREATURE, LostWorldsEntities.PARKSOSAURUS.get(), LostWorldsConfig.COMMON_CONFIG.parksosaurusSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.parksosaurusSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.parksosaurusSpawnGroupMaximum.get());
		}

		ArrayList<ResourceLocation> psittacosaurusBiomes = Lists.newArrayList(LostWorldsBiomes.CRETACEOUS_PLAINS.getRegistryName(), LostWorldsBiomes.CRETACEOUS_ARAUCARIA_FOREST.getRegistryName(), LostWorldsBiomes.CRETACEOUS_CONIFER_FOREST.getRegistryName());
		if (psittacosaurusBiomes.contains(event.getName())) {
			FeatureAdder.addSpawn(event, MobCategory.CREATURE, LostWorldsEntities.PSITTACOSAURUS.get(), LostWorldsConfig.COMMON_CONFIG.psittacosaurusSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.psittacosaurusSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.psittacosaurusSpawnGroupMaximum.get());
		}

		ArrayList<ResourceLocation> suchomimusBiomes = Lists.newArrayList(LostWorldsBiomes.CRETACEOUS_DESERT.getRegistryName(), LostWorldsBiomes.CRETACEOUS_SWAMP.getRegistryName());
		if (suchomimusBiomes.contains(event.getName())) {
			FeatureAdder.addSpawn(event, MobCategory.CREATURE, LostWorldsEntities.SUCHOMIMUS.get(), LostWorldsConfig.COMMON_CONFIG.suchomimusSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.suchomimusSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.suchomimusSpawnGroupMaximum.get());
		}

		ArrayList<ResourceLocation> thanosBiomes = Lists.newArrayList(LostWorldsBiomes.CRETACEOUS_DESERT.getRegistryName(), LostWorldsBiomes.CRETACEOUS_GAME_TRAIL.getRegistryName(), LostWorldsBiomes.CRETACEOUS_MEDOW.getRegistryName());
		if (thanosBiomes.contains(event.getName())) {
			FeatureAdder.addSpawn(event, MobCategory.CREATURE, LostWorldsEntities.THANOS.get(), LostWorldsConfig.COMMON_CONFIG.thanosSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.thanosSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.thanosSpawnGroupMaximum.get());
		}

		ArrayList<ResourceLocation> tyrannosaurusBiomes = Lists.newArrayList(LostWorldsBiomes.CRETACEOUS_ARCTIC.getRegistryName(), LostWorldsBiomes.CRETACEOUS_ARCTIC_SPIRES.getRegistryName(), LostWorldsBiomes.CRETACEOUS_GAME_TRAIL.getRegistryName(), LostWorldsBiomes.CRETACEOUS_MEDOW.getRegistryName(), LostWorldsBiomes.CRETACEOUS_PLAINS.getRegistryName());
		if (tyrannosaurusBiomes.contains(event.getName())) {
			FeatureAdder.addSpawn(event, MobCategory.CREATURE, LostWorldsEntities.TYRANNOSAURUS.get(), LostWorldsConfig.COMMON_CONFIG.tyrannosaurusSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.tyrannosaurusSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.tyrannosaurusSpawnGroupMaximum.get());
		}

		ArrayList<ResourceLocation> utahraptorBiomes = Lists.newArrayList(LostWorldsBiomes.CRETACEOUS_ARCTIC.getRegistryName(), LostWorldsBiomes.CRETACEOUS_ARCTIC_SPIRES.getRegistryName(), LostWorldsBiomes.CRETACEOUS_FROZEN_FOREST.getRegistryName(), LostWorldsBiomes.CRETACEOUS_GINKGO_FOREST.getRegistryName());
		if (utahraptorBiomes.contains(event.getName())) {
			FeatureAdder.addSpawn(event, MobCategory.CREATURE, LostWorldsEntities.UTAHRAPTOR.get(), LostWorldsConfig.COMMON_CONFIG.utahraptorSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.utahraptorSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.utahraptorSpawnGroupMaximum.get());
		}

		ArrayList<ResourceLocation> zephyrosaurusBiomes = Lists.newArrayList(LostWorldsBiomes.CRETACEOUS_PLAINS.getRegistryName(), LostWorldsBiomes.CRETACEOUS_MARSH.getRegistryName(), LostWorldsBiomes.CRETACEOUS_FROZEN_FOREST.getRegistryName(), LostWorldsBiomes.CRETACEOUS_GINKGO_FOREST.getRegistryName());
		if (zephyrosaurusBiomes.contains(event.getName())) {
			FeatureAdder.addSpawn(event, MobCategory.CREATURE, LostWorldsEntities.ZEPHYROSAURUS.get(), LostWorldsConfig.COMMON_CONFIG.zephyrosaurusSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.zephyrosaurusSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.zephyrosaurusSpawnGroupMaximum.get());
		}

		// Jurassic

		// Terrestrial
		ArrayList<ResourceLocation> allosaurusBiomes = Lists.newArrayList(LostWorldsBiomes.JURASSIC_ARAUCARIA_FOREST.getRegistryName(), LostWorldsBiomes.JURASSIC_CONIFER_FOREST.getRegistryName(), LostWorldsBiomes.JURASSIC_GINKGO_FOREST.getRegistryName(), LostWorldsBiomes.JURASSIC_DESERT.getRegistryName());
		if (allosaurusBiomes.contains(event.getName())) {
			FeatureAdder.addSpawn(event, MobCategory.CREATURE, LostWorldsEntities.ALLOSAURUS.get(), LostWorldsConfig.COMMON_CONFIG.allosaurusSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.allosaurusSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.allosaurusSpawnGroupMaximum.get());
		}
		ArrayList<ResourceLocation> chilesaurusBiomes = Lists.newArrayList(LostWorldsBiomes.JURASSIC_PLAINS.getRegistryName(), LostWorldsBiomes.JURASSIC_FEN.getRegistryName(), LostWorldsBiomes.JURASSIC_BOG.getRegistryName(), LostWorldsBiomes.JURASSIC_DESERT.getRegistryName());
		if (chilesaurusBiomes.contains(event.getName())) {
			FeatureAdder.addSpawn(event, MobCategory.CREATURE, LostWorldsEntities.CHILESAURUS.get(), LostWorldsConfig.COMMON_CONFIG.chilesaurusSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.chilesaurusSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.chilesaurusSpawnGroupMaximum.get());
		}
		ArrayList<ResourceLocation> cryolophosaurusBiomes = Lists.newArrayList(LostWorldsBiomes.JURASSIC_WINDSWEPT_HILLS.getRegistryName(), LostWorldsBiomes.JURASSIC_ERRODED_WINDSWEPT_HILLS.getRegistryName());
		if (cryolophosaurusBiomes.contains(event.getName())) {
			FeatureAdder.addSpawn(event, MobCategory.CREATURE, LostWorldsEntities.CRYOLOPHOSAURUS.get(), LostWorldsConfig.COMMON_CONFIG.cryolophosaurusSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.cryolophosaurusSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.cryolophosaurusSpawnGroupMaximum.get());
		}
		ArrayList<ResourceLocation> dilophosaurusBiomes = Lists.newArrayList(LostWorldsBiomes.JURASSIC_WINDSWEPT_HILLS.getRegistryName(), LostWorldsBiomes.JURASSIC_ERRODED_WINDSWEPT_HILLS.getRegistryName(), LostWorldsBiomes.JURASSIC_REDWOODS_FOREST.getRegistryName(), LostWorldsBiomes.JURASSIC_CONIFER_FOREST.getRegistryName());
		if (dilophosaurusBiomes.contains(event.getName())) {
			FeatureAdder.addSpawn(event, MobCategory.CREATURE, LostWorldsEntities.DILOPHOSAURUS.get(), LostWorldsConfig.COMMON_CONFIG.dilophosaurusSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.dilophosaurusSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.dilophosaurusSpawnGroupMaximum.get());
		}
		ArrayList<ResourceLocation> eustreptospondylusBiomes = Lists.newArrayList(LostWorldsBiomes.JURASSIC_BOG.getRegistryName(), LostWorldsBiomes.JURASSIC_FEN.getRegistryName(), LostWorldsBiomes.JURASSIC_REDWOODS_FOREST.getRegistryName(), LostWorldsBiomes.CRETACEOUS_MARSH.getRegistryName(), LostWorldsBiomes.JURASSIC_SWAMP.getRegistryName());
		if (eustreptospondylusBiomes.contains(event.getName())) {
			FeatureAdder.addSpawn(event, MobCategory.CREATURE, LostWorldsEntities.EUSTREPTOSPONDYLUS.get(), LostWorldsConfig.COMMON_CONFIG.eustreptospondylusSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.eustreptospondylusSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.eustreptospondylusSpawnGroupMaximum.get());
		}
		ArrayList<ResourceLocation> kentrosaurusBiomes = Lists.newArrayList(LostWorldsBiomes.JURASSIC_PLAINS.getRegistryName(), LostWorldsBiomes.JURASSIC_REDWOODS_FOREST.getRegistryName(), LostWorldsBiomes.JURASSIC_ARAUCARIA_FOREST.getRegistryName());
		if (kentrosaurusBiomes.contains(event.getName())) {
			FeatureAdder.addSpawn(event, MobCategory.CREATURE, LostWorldsEntities.KENTROSAURUS.get(), LostWorldsConfig.COMMON_CONFIG.kentrosaurusSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.kentrosaurusSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.kentrosaurusSpawnGroupMaximum.get());
		}
		ArrayList<ResourceLocation> liaoningosaurusBiomes = Lists.newArrayList(LostWorldsBiomes.JURASSIC_PLAINS.getRegistryName(), LostWorldsBiomes.JURASSIC_GINKGO_FOREST.getRegistryName(), LostWorldsBiomes.JURASSIC_ARAUCARIA_FOREST.getRegistryName());
		if (liaoningosaurusBiomes.contains(event.getName())) {
			FeatureAdder.addSpawn(event, MobCategory.CREATURE, LostWorldsEntities.LIAONINGOSAURUS.get(), LostWorldsConfig.COMMON_CONFIG.liaoningosaurusSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.liaoningosaurusSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.liaoningosaurusSpawnGroupMaximum.get());
		}
		ArrayList<ResourceLocation> ostromiaBiomes = Lists.newArrayList(LostWorldsBiomes.JURASSIC_GINKGO_FOREST.getRegistryName(), LostWorldsBiomes.JURASSIC_ARAUCARIA_FOREST.getRegistryName(), LostWorldsBiomes.JURASSIC_REDWOODS_FOREST.getRegistryName());
		if (ostromiaBiomes.contains(event.getName())) {
			FeatureAdder.addSpawn(event, MobCategory.CREATURE, LostWorldsEntities.OSTROMIA.get(), LostWorldsConfig.COMMON_CONFIG.ostromiaSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.ostromiaSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.ostromiaSpawnGroupMaximum.get());
		}

		// Semi-Aquatic
		ArrayList<ResourceLocation> protosuchusBiomes = Lists.newArrayList(LostWorldsBiomes.JURASSIC_RIVER.getRegistryName(), LostWorldsBiomes.JURASSIC_SHORE.getRegistryName(), LostWorldsBiomes.JURASSIC_SWAMP.getRegistryName(), LostWorldsBiomes.JURASSIC_BOG.getRegistryName(), LostWorldsBiomes.JURASSIC_MARSH.getRegistryName());
		if (protosuchusBiomes.contains(event.getName())) {
			FeatureAdder.addSpawn(event, MobCategory.CREATURE, LostWorldsEntities.PROTOSUCHUS.get(), LostWorldsConfig.COMMON_CONFIG.protosuchusSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.protosuchusSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.protosuchusSpawnGroupMaximum.get());
		}

		// Aquatic
		ArrayList<ResourceLocation> ophthalmosaurusBiomes = Lists.newArrayList(LostWorldsBiomes.JURASSIC_RIVER.getRegistryName(), LostWorldsBiomes.JURASSIC_OCEAN.getRegistryName(), LostWorldsBiomes.WARM_JURASSIC_OCEAN.getRegistryName());
		if (ophthalmosaurusBiomes.contains(event.getName())) {
			FeatureAdder.addSpawn(event, MobCategory.CREATURE, LostWorldsEntities.OPHTHALMOSAURUS.get(), LostWorldsConfig.COMMON_CONFIG.ophthalmosaurusSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.ophthalmosaurusSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.ophthalmosaurusSpawnGroupMaximum.get());
		}

		// Permian

		// Terrestrial
		ArrayList<ResourceLocation> diictodonBiomes = Lists.newArrayList(LostWorldsBiomes.PERMIAN_DESERT.getRegistryName());
		if (diictodonBiomes.contains(event.getName())) {
			FeatureAdder.addSpawn(event, MobCategory.CREATURE, LostWorldsEntities.DIICTODON.get(), LostWorldsConfig.COMMON_CONFIG.diictodonSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.diictodonSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.diictodonSpawnGroupMaximum.get());
		}

		ArrayList<ResourceLocation> dimetrodonBiomes = Lists.newArrayList(LostWorldsBiomes.PERMIAN_DRIED_PLAINS.getRegistryName(), LostWorldsBiomes.PERMIAN_CONIFER_FOREST.getRegistryName());
		if (dimetrodonBiomes.contains(event.getName())) {
			FeatureAdder.addSpawn(event, MobCategory.CREATURE, LostWorldsEntities.DIMETRODON.get(), LostWorldsConfig.COMMON_CONFIG.dimetrodonSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.dimetrodonSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.dimetrodonSpawnGroupMaximum.get());
		}

		ArrayList<ResourceLocation> edaphosaurusBiomes = Lists.newArrayList(LostWorldsBiomes.PERMIAN_DRIED_PLAINS.getRegistryName(), LostWorldsBiomes.PERMIAN_CONIFER_FOREST.getRegistryName(), LostWorldsBiomes.ARAUCARIA_FOREST.getRegistryName());
		if (edaphosaurusBiomes.contains(event.getName())) {
			FeatureAdder.addSpawn(event, MobCategory.CREATURE, LostWorldsEntities.EDAPHOSAURUS.get(), LostWorldsConfig.COMMON_CONFIG.edaphosaurusSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.edaphosaurusSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.edaphosaurusSpawnGroupMaximum.get());
		}

		ArrayList<ResourceLocation> gorgonopsBiomes = Lists.newArrayList(LostWorldsBiomes.PERMIAN_DESERT.getRegistryName(), LostWorldsBiomes.PERMIAN_WINDSWEPT_HILLS.getRegistryName());
		if (gorgonopsBiomes.contains(event.getName())) {
			FeatureAdder.addSpawn(event, MobCategory.CREATURE, LostWorldsEntities.GORGONOPS.get(), LostWorldsConfig.COMMON_CONFIG.gorgonopsSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.gorgonopsSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.gorgonopsSpawnGroupMaximum.get());
		}

		ArrayList<ResourceLocation> tetraceratopsBiomes = Lists.newArrayList(LostWorldsBiomes.PERMIAN_DESERT.getRegistryName(), LostWorldsBiomes.PERMIAN_GINKGO_FOREST.getRegistryName());
		if (tetraceratopsBiomes.contains(event.getName())) {
			FeatureAdder.addSpawn(event, MobCategory.CREATURE, LostWorldsEntities.TETRACERATOPS.get(), LostWorldsConfig.COMMON_CONFIG.tetraceratopsSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.tetraceratopsSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.tetraceratopsSpawnGroupMaximum.get());
		}

		// Semi-Aquatic
		ArrayList<ResourceLocation> rhinesuchusBiomes = Lists.newArrayList(LostWorldsBiomes.PERMIAN_DESERT.getRegistryName());
		if (rhinesuchusBiomes.contains(event.getName())) {
			FeatureAdder.addSpawn(event, MobCategory.CREATURE, LostWorldsEntities.RHINESUCHUS.get(), LostWorldsConfig.COMMON_CONFIG.rhinesuchusSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.rhinesuchusSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.rhinesuchusSpawnGroupMaximum.get());
		}

		// Aquatic

		ArrayList<ResourceLocation> palaeoniscumBiomes = Lists.newArrayList(LostWorldsBiomes.PERMIAN_OCEAN.getRegistryName(), LostWorldsBiomes.WARM_PERMIAN_OCEAN.getRegistryName());
		if (palaeoniscumBiomes.contains(event.getName())) {
			FeatureAdder.addSpawn(event, MobCategory.CREATURE, LostWorldsEntities.PALAEONISCUM.get(), LostWorldsConfig.COMMON_CONFIG.palaeoniscumSpawnWeight.get(), LostWorldsConfig.COMMON_CONFIG.palaeoniscumSpawnGroupMinimum.get(), LostWorldsConfig.COMMON_CONFIG.palaeoniscumSpawnGroupMaximum.get());
		}

	}
}
