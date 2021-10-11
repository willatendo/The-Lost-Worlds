package lostworlds.content.server.init;

import lostworlds.content.ModRegistry;
import lostworlds.content.ModUtils;
import lostworlds.library.biome.biomes.jurassic.desert.JurassicDesert;
import lostworlds.library.biome.biomes.jurassic.forest.JurassicAraucariaForest;
import lostworlds.library.biome.biomes.jurassic.forest.JurassicConiferForest;
import lostworlds.library.biome.biomes.jurassic.forest.JurassicGinkgoForest;
import lostworlds.library.biome.biomes.jurassic.mountains.JurassicErrodedMountains;
import lostworlds.library.biome.biomes.jurassic.mountains.JurassicMountains;
import lostworlds.library.biome.biomes.jurassic.plains.JurassicPlains;
import lostworlds.library.biome.biomes.overworld.forest.ConiferForest;
import lostworlds.library.biome.biomes.overworld.forest.GinkgoForest;
import lostworlds.library.biome.biomes.permian.desert.PermianDesert;
import lostworlds.library.biome.biomes.permian.forest.PermianConiferForest;
import lostworlds.library.biome.biomes.permian.forest.PermianGinkgoForest;
import lostworlds.library.biome.biomes.permian.mountain.PermianMountain;
import lostworlds.library.biome.biomes.permian.ocean.DeepPermianOcean;
import lostworlds.library.biome.biomes.permian.ocean.DeepWarmPermianOcean;
import lostworlds.library.biome.biomes.permian.ocean.PermianOcean;
import lostworlds.library.biome.biomes.permian.ocean.WarmPermianOcean;
import lostworlds.library.biome.biomes.permian.plains.PermianAshyMedow;
import lostworlds.library.biome.biomes.permian.plains.PermianDriedPlains;
import lostworlds.library.biome.biomes.permian.plains.PermianFloodBasalts;
import lostworlds.library.biome.biomes.permian.plains.PermianPlains;
import lostworlds.library.biome.biomes.permian.river.PermianRiver;
import lostworlds.library.biome.biomes.permian.shore.PermianShore;
import lostworlds.library.biome.biomes.permian.shore.PermianStoneShore;
import lostworlds.library.biome.biomes.permian.swamp.PermianMarsh;
import net.minecraft.world.biome.Biome;

public class BiomeInit 
{
	//Permian
	public static final Biome PERMIAN_ASHY_MEDOWS = ModRegistry.register("permian_ashy_medows", PermianAshyMedow.create());
	
	public static final Biome PERMIAN_CONIFER_FOREST = ModRegistry.register("permian_conifer_forest", PermianConiferForest.create());
	public static final Biome PERMIAN_CONIFER_FOREST_HILLS = ModRegistry.register("permian_conifer_forest_hills", PermianConiferForest.create(0.45F, 0.3F));

	public static final Biome PERMIAN_DESERT = ModRegistry.register("permian_desert", PermianDesert.create());
	public static final Biome PERMIAN_DESERT_HILLS = ModRegistry.register("permian_desert_hills", PermianDesert.create(0.45F, 0.3F));

	public static final Biome PERMIAN_DRIED_PLAINS = ModRegistry.register("permian_dried_plains", PermianDriedPlains.create());
	public static final Biome PERMIAN_DRIED_PLAINS_HILLS = ModRegistry.register("permian_dried_plains_hills", PermianDriedPlains.create(0.45F, 0.3F));

	public static final Biome PERMIAN_FLOOD_BASALTS = ModRegistry.register("permian_flood_basalts", PermianFloodBasalts.create());

	public static final Biome PERMIAN_GINKGO_FOREST = ModRegistry.register("permian_ginkgo_forest", PermianGinkgoForest.create());
	public static final Biome PERMIAN_GINKGO_FOREST_HILLS = ModRegistry.register("permian_ginkgo_forest_hills", PermianGinkgoForest.create(0.45F, 0.3F));

	public static final Biome PERMIAN_MARSH = ModRegistry.register("permian_marsh", PermianMarsh.create());

	public static final Biome PERMIAN_MOUNTAINS = ModRegistry.register("permian_mountains", PermianMountain.create());

	public static final Biome PERMIAN_RIVER = ModRegistry.register("permian_river", PermianRiver.create());

	public static final Biome PERMIAN_OCEAN = ModRegistry.register("permian_ocean", PermianOcean.create());
	public static final Biome WARM_PERMIAN_OCEAN = ModRegistry.register("warm_permian_ocean", WarmPermianOcean.create());
	
	public static final Biome DEEP_PERMIAN_OCEAN = ModRegistry.register("deep_permian_ocean", DeepPermianOcean.create());
	public static final Biome WARM_DEEP_PERMIAN_OCEAN = ModRegistry.register("warm_deep_permian_ocean", DeepWarmPermianOcean.create());

	public static final Biome PERMIAN_PLAINS = ModRegistry.register("permian_plains", PermianPlains.create());
	public static final Biome PERMIAN_PLAINS_HILLS = ModRegistry.register("permian_plains_hills", PermianPlains.create(0.45F, 0.3F));

	public static final Biome PERMIAN_SHORE = ModRegistry.register("permian_shore", PermianShore.create());
	public static final Biome PERMIAN_STONE_SHORE = ModRegistry.register("permian_stone_shore", PermianStoneShore.create());
	
	//Jurassic
	public static final Biome JURASSIC_CONIFER_FOREST = ModRegistry.register("jurassic_conifer_forest", JurassicConiferForest.create());
	
	public static final Biome JURASSIC_GINKGO_FOREST = ModRegistry.register("jurassic_ginkgo_forest", JurassicGinkgoForest.create());

	public static final Biome JURASSIC_ARAUCARIA_FOREST = ModRegistry.register("jurassic_araucaria_forest", JurassicAraucariaForest.create());

	public static final Biome JURASSIC_PLAINS = ModRegistry.register("jurassic_plains", JurassicPlains.create());

	public static final Biome JURASSIC_MOUNTAINS = ModRegistry.register("jurassic_mountains", JurassicMountains.create());
	public static final Biome JURASSIC_ERRODED_MOUNTAINS = ModRegistry.register("jurassic_erroded_mountains", JurassicErrodedMountains.create());

	public static final Biome JURASSIC_DESERT = ModRegistry.register("jurassic_desert", JurassicDesert.create());

	//Overworld
	public static final Biome CONIFER_FOREST = ModRegistry.register("conifer_forest", ConiferForest.create());
	public static final Biome CONIFER_FOREST_HILLS = ModRegistry.register("conifer_forest_hills", ConiferForest.create(0.45F, 0.3F));
	
	public static final Biome GINKGO_FOREST = ModRegistry.register("ginkgo_forest", GinkgoForest.create());
	public static final Biome GINKGO_FOREST_HILLS = ModRegistry.register("ginkgo_forest_hills", GinkgoForest.create(0.45F, 0.3F));
	
	//Registry
	public static void init() { ModUtils.LOGGER.debug("Registering Mod Biomes"); }
}
