package lostworlds.content.server.init;

import lostworlds.content.ModRegistry;
import lostworlds.content.ModUtils;
import lostworlds.library.biome.biomes.cretaceous.plains.CreataceousGameTrail;
import lostworlds.library.biome.biomes.jurassic.desert.JurassicDesert;
import lostworlds.library.biome.biomes.jurassic.forest.JurassicAraucariaForest;
import lostworlds.library.biome.biomes.jurassic.forest.JurassicConiferForest;
import lostworlds.library.biome.biomes.jurassic.forest.JurassicGinkgoForest;
import lostworlds.library.biome.biomes.jurassic.forest.JurassicRedwoodsForest;
import lostworlds.library.biome.biomes.jurassic.mountains.JurassicErrodedMountains;
import lostworlds.library.biome.biomes.jurassic.mountains.JurassicMountains;
import lostworlds.library.biome.biomes.jurassic.mountains.JurassicVolcanicRange;
import lostworlds.library.biome.biomes.jurassic.ocean.JurassicOcean;
import lostworlds.library.biome.biomes.jurassic.ocean.WarmJurassicOcean;
import lostworlds.library.biome.biomes.jurassic.plains.JurassicPlains;
import lostworlds.library.biome.biomes.jurassic.river.JurassicRiver;
import lostworlds.library.biome.biomes.jurassic.shore.JurassicShore;
import lostworlds.library.biome.biomes.jurassic.swamp.JurassicBog;
import lostworlds.library.biome.biomes.jurassic.swamp.JurassicFen;
import lostworlds.library.biome.biomes.jurassic.swamp.JurassicMarsh;
import lostworlds.library.biome.biomes.jurassic.swamp.JurassicSwamp;
import lostworlds.library.biome.biomes.overworld.forest.AraucariaForest;
import lostworlds.library.biome.biomes.overworld.forest.ConiferForest;
import lostworlds.library.biome.biomes.overworld.forest.GinkgoForest;
import lostworlds.library.biome.biomes.overworld.forest.SequoiaForest;
import lostworlds.library.biome.biomes.overworld.mountain.Volcano;
import lostworlds.library.biome.biomes.permian.desert.PermianDesert;
import lostworlds.library.biome.biomes.permian.forest.PermianConiferForest;
import lostworlds.library.biome.biomes.permian.forest.PermianGinkgoForest;
import lostworlds.library.biome.biomes.permian.mountain.PermianMountain;
import lostworlds.library.biome.biomes.permian.ocean.PermianOcean;
import lostworlds.library.biome.biomes.permian.ocean.WarmPermianOcean;
import lostworlds.library.biome.biomes.permian.plains.PermianAshyMedow;
import lostworlds.library.biome.biomes.permian.plains.PermianDriedPlains;
import lostworlds.library.biome.biomes.permian.plains.PermianFloodBasalts;
import lostworlds.library.biome.biomes.permian.plains.PermianPlains;
import lostworlds.library.biome.biomes.permian.river.PermianRiver;
import lostworlds.library.biome.biomes.permian.shore.PermianShore;
import lostworlds.library.biome.biomes.permian.swamp.PermianMarsh;
import net.minecraft.world.biome.Biome;

public class BiomeInit 
{
	//Permian	
	public static final Biome PERMIAN_CONIFER_FOREST = ModRegistry.register("permian_conifer_forest", new PermianConiferForest());
	public static final Biome PERMIAN_CONIFER_FOREST_HILLS = ModRegistry.register("permian_conifer_forest_hills", new PermianConiferForest(0.45F, 0.3F));

	public static final Biome PERMIAN_GINKGO_FOREST = ModRegistry.register("permian_ginkgo_forest", new PermianGinkgoForest());
	public static final Biome PERMIAN_GINKGO_FOREST_HILLS = ModRegistry.register("permian_ginkgo_forest_hills", new PermianGinkgoForest(0.45F, 0.3F));

	public static final Biome PERMIAN_PLAINS = ModRegistry.register("permian_plains", new PermianPlains());
	public static final Biome PERMIAN_PLAINS_HILLS = ModRegistry.register("permian_plains_hills", new PermianPlains(0.45F, 0.3F));

	public static final Biome PERMIAN_DRIED_PLAINS = ModRegistry.register("permian_dried_plains", new PermianDriedPlains());
	public static final Biome PERMIAN_DRIED_PLAINS_HILLS = ModRegistry.register("permian_dried_plains_hills", new PermianDriedPlains(0.45F, 0.3F));

	public static final Biome PERMIAN_DESERT = ModRegistry.register("permian_desert", new PermianDesert());
	public static final Biome PERMIAN_DESERT_HILLS = ModRegistry.register("permian_desert_hills", new PermianDesert(0.45F, 0.3F));

	public static final Biome PERMIAN_FLOOD_BASALTS = ModRegistry.register("permian_flood_basalts", new PermianFloodBasalts());
	public static final Biome PERMIAN_ASHY_MEDOWS = ModRegistry.register("permian_ashy_medows", new PermianAshyMedow());

	public static final Biome PERMIAN_MOUNTAINS = ModRegistry.register("permian_mountains", new PermianMountain());

	public static final Biome PERMIAN_MARSH = ModRegistry.register("permian_marsh", new PermianMarsh());

	public static final Biome PERMIAN_RIVER = ModRegistry.register("permian_river", new PermianRiver());

	public static final Biome PERMIAN_OCEAN = ModRegistry.register("permian_ocean", new PermianOcean());
	public static final Biome DEEP_PERMIAN_OCEAN = ModRegistry.register("deep_permian_ocean", new PermianOcean(-1.8F, 0.1F));
	
	public static final Biome WARM_PERMIAN_OCEAN = ModRegistry.register("warm_permian_ocean", new WarmPermianOcean());	
	public static final Biome WARM_DEEP_PERMIAN_OCEAN = ModRegistry.register("warm_deep_permian_ocean", new WarmPermianOcean());

	public static final Biome PERMIAN_SHORE = ModRegistry.register("permian_shore", new PermianShore());
	
	//Jurassic
	public static final Biome JURASSIC_ARAUCARIA_FOREST = ModRegistry.register("jurassic_araucaria_forest", new JurassicAraucariaForest());
	public static final Biome JURASSIC_ARAUCARIA_FOREST_HILLS = ModRegistry.register("jurassic_araucaria_forest_hills", new JurassicAraucariaForest(0.45F, 0.3F));

	public static final Biome JURASSIC_CONIFER_FOREST = ModRegistry.register("jurassic_conifer_forest", new JurassicConiferForest());
	public static final Biome JURASSIC_CONIFER_FOREST_HILLS = ModRegistry.register("jurassic_conifer_forest_hills", new JurassicConiferForest(0.45F, 0.3F));
	
	public static final Biome JURASSIC_GINKGO_FOREST = ModRegistry.register("jurassic_ginkgo_forest", new JurassicGinkgoForest());
	public static final Biome JURASSIC_GINKGO_FOREST_HILLS = ModRegistry.register("jurassic_ginkgo_forest_hills", new JurassicGinkgoForest(0.45F, 0.3F));

	public static final Biome JURASSIC_REDWOODS_FOREST = ModRegistry.register("jurassic_redwoods_forest", new JurassicRedwoodsForest());
	public static final Biome JURASSIC_REDWOODS_FOREST_HILLS = ModRegistry.register("jurassic_redwoods_forest_hills", new JurassicRedwoodsForest(0.45F, 0.3F));

	public static final Biome JURASSIC_PLAINS = ModRegistry.register("jurassic_plains", new JurassicPlains());
	public static final Biome JURASSIC_PLAINS_HILLS = ModRegistry.register("jurassic_plains_hills", new JurassicPlains(0.45F, 0.3F));

	public static final Biome JURASSIC_MOUNTAINS = ModRegistry.register("jurassic_mountains", new JurassicMountains());
	public static final Biome JURASSIC_ERRODED_MOUNTAINS = ModRegistry.register("jurassic_erroded_mountains", new JurassicErrodedMountains());
	public static final Biome JURASSIC_VOLCANIC_RANGE = ModRegistry.register("jurassic_volcanic_range", new JurassicVolcanicRange());

	public static final Biome JURASSIC_DESERT = ModRegistry.register("jurassic_desert", new JurassicDesert());
	public static final Biome JURASSIC_DESERT_HILLS = ModRegistry.register("jurassic_desert_hills", new JurassicDesert(0.45F, 0.3F));

	public static final Biome JURASSIC_MARSH = ModRegistry.register("jurassic_marsh", new JurassicMarsh());
	public static final Biome JURASSIC_SWAMP = ModRegistry.register("jurassic_swamp", new JurassicSwamp());
	public static final Biome JURASSIC_FEN = ModRegistry.register("jurassic_fen", new JurassicFen());
	public static final Biome JURASSIC_BOG = ModRegistry.register("jurassic_bog", new JurassicBog());

	public static final Biome JURASSIC_RIVER = ModRegistry.register("jurassic_river", new JurassicRiver());

	public static final Biome JURASSIC_OCEAN = ModRegistry.register("jurassic_ocean", new JurassicOcean());
	public static final Biome DEEP_JURASSIC_OCEAN = ModRegistry.register("deep_jurassic_ocean", new JurassicOcean(-1.8F, 0.1F));

	public static final Biome WARM_JURASSIC_OCEAN = ModRegistry.register("warm_jurassic_ocean", new WarmJurassicOcean());
	public static final Biome WARM_DEEP_JURASSIC_OCEAN = ModRegistry.register("warm_deep_jurassic_ocean", new WarmJurassicOcean(-1.8F, 0.1F));

	public static final Biome JURASSIC_SHORE = ModRegistry.register("jurassic_shore", new JurassicShore());

	//Creataceous
	public static final Biome CREATACEOUS_GAME_TRAIL = ModRegistry.register("creataceous_game_trail", new CreataceousGameTrail());
	
	//Overworld
	public static final Biome ARAUCARIA_FOREST = ModRegistry.register("araucaria_forest", new AraucariaForest());
	public static final Biome ARAUCARIA_FOREST_HILLS = ModRegistry.register("araucaria_forest_hills", new AraucariaForest(0.45F, 0.3F));
	
	public static final Biome CONIFER_FOREST = ModRegistry.register("conifer_forest", new ConiferForest());
	public static final Biome CONIFER_FOREST_HILLS = ModRegistry.register("conifer_forest_hills", new ConiferForest(0.45F, 0.3F));
	
	public static final Biome GINKGO_FOREST = ModRegistry.register("ginkgo_forest", new GinkgoForest());
	public static final Biome GINKGO_FOREST_HILLS = ModRegistry.register("ginkgo_forest_hills", new GinkgoForest(0.45F, 0.3F));
	
	public static final Biome REDWOODS_FOREST = ModRegistry.register("redwoods_forest", new SequoiaForest());
	public static final Biome REDWOODS_FOREST_HILLS = ModRegistry.register("redwoods_forest_hills", new SequoiaForest(0.45F, 0.3F));

	public static final Biome VOLCANO = ModRegistry.register("volcano", new Volcano());

	//Registry
	public static void init() { ModUtils.LOGGER.debug("Registering Mod Biomes"); }
}
