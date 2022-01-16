package lostworlds.content.server.init;

import lostworlds.content.ModRegistry;
import lostworlds.content.ModUtils;
import lostworlds.library.biome.biomes.cretaceous.arctic.CretaceousArctic;
import lostworlds.library.biome.biomes.cretaceous.arctic.CretaceousArcticSpires;
import lostworlds.library.biome.biomes.cretaceous.arctic.CretaceousFrozenForest;
import lostworlds.library.biome.biomes.cretaceous.desert.CretaceousDesert;
import lostworlds.library.biome.biomes.cretaceous.desert.CretaceousRedDesert;
import lostworlds.library.biome.biomes.cretaceous.forest.CretaceousAraucariaForest;
import lostworlds.library.biome.biomes.cretaceous.forest.CretaceousConiferForest;
import lostworlds.library.biome.biomes.cretaceous.forest.CretaceousGinkgoForest;
import lostworlds.library.biome.biomes.cretaceous.forest.CretaceousRedwoodsForest;
import lostworlds.library.biome.biomes.cretaceous.mountains.CretaceousErrodedMountains;
import lostworlds.library.biome.biomes.cretaceous.mountains.CretaceousMountains;
import lostworlds.library.biome.biomes.cretaceous.ocean.ColdCretaceousOcean;
import lostworlds.library.biome.biomes.cretaceous.ocean.CretaceousOcean;
import lostworlds.library.biome.biomes.cretaceous.ocean.WarmCretaceousOcean;
import lostworlds.library.biome.biomes.cretaceous.plains.CretaceousFloodBasalts;
import lostworlds.library.biome.biomes.cretaceous.plains.CretaceousGameTrail;
import lostworlds.library.biome.biomes.cretaceous.plains.CretaceousMedow;
import lostworlds.library.biome.biomes.cretaceous.plains.CretaceousPlains;
import lostworlds.library.biome.biomes.cretaceous.river.CretaceousRiver;
import lostworlds.library.biome.biomes.cretaceous.shore.CretaceousShore;
import lostworlds.library.biome.biomes.cretaceous.swamp.CretaceousBog;
import lostworlds.library.biome.biomes.cretaceous.swamp.CretaceousFen;
import lostworlds.library.biome.biomes.cretaceous.swamp.CretaceousMarsh;
import lostworlds.library.biome.biomes.cretaceous.swamp.CretaceousSwamp;
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

public class BiomeInit {
	// Permian
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

	// Jurassic
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

	// Creataceous
	public static final Biome CRETACEOUS_ARAUCARIA_FOREST = ModRegistry.register("cretaceous_araucaria_forest", new CretaceousAraucariaForest());
	public static final Biome CRETACEOUS_ARAUCARIA_FOREST_HILLS = ModRegistry.register("cretaceous_araucaria_forest_hills", new CretaceousAraucariaForest(0.45F, 0.3F));

	public static final Biome CRETACEOUS_CONIFER_FOREST = ModRegistry.register("cretaceous_conifer_forest", new CretaceousConiferForest());
	public static final Biome CRETACEOUS_CONIFER_FOREST_HILLS = ModRegistry.register("cretaceous_conifer_forest_hills", new CretaceousConiferForest(0.45F, 0.3F));

	public static final Biome CRETACEOUS_GINKGO_FOREST = ModRegistry.register("cretaceous_ginkgo_forest", new CretaceousGinkgoForest());
	public static final Biome CRETACEOUS_GINKGO_FOREST_HILLS = ModRegistry.register("cretaceous_ginkgo_forest_hills", new CretaceousGinkgoForest(0.45F, 0.3F));

	public static final Biome CRETACEOUS_REDWOODS_FOREST = ModRegistry.register("cretaceous_redwoods_forest", new CretaceousRedwoodsForest());
	public static final Biome CRETACEOUS_REDWOODS_FOREST_HILLS = ModRegistry.register("cretaceous_redwoods_forest_hills", new CretaceousRedwoodsForest(0.45F, 0.3F));

	public static final Biome CRETACEOUS_GAME_TRAIL = ModRegistry.register("cretaceous_game_trail", new CretaceousGameTrail());

	public static final Biome CRETACEOUS_MEDOW = ModRegistry.register("cretaceous_medow", new CretaceousMedow());

	public static final Biome CRETACEOUS_PLAINS = ModRegistry.register("cretaceous_plains", new CretaceousPlains());
	public static final Biome CRETACEOUS_PLAINS_HILLS = ModRegistry.register("cretaceous_plains_hills", new CretaceousPlains(0.45F, 0.3F));

	public static final Biome CRETACEOUS_FLOOD_BASALTS = ModRegistry.register("cretaceous_flood_basalts", new CretaceousFloodBasalts());

	public static final Biome CRETACEOUS_ARCTIC = ModRegistry.register("cretaceous_arctic", new CretaceousArctic());
	public static final Biome CRETACEOUS_ARCTIC_HILLS = ModRegistry.register("cretaceous_arctic_hills", new CretaceousArctic(0.45F, 0.3F));

	public static final Biome CRETACEOUS_ARCTIC_SPIRES = ModRegistry.register("cretaceous_arctic_spires", new CretaceousArcticSpires());

	public static final Biome CRETACEOUS_FROZEN_FOREST = ModRegistry.register("cretaceous_frozen_forest", new CretaceousFrozenForest());
	public static final Biome CRETACEOUS_FROZEN_FOREST_HILLS = ModRegistry.register("cretaceous_frozen_forest_hills", new CretaceousFrozenForest(0.45F, 0.3F));

	public static final Biome CRETACEOUS_MOUNTAINS = ModRegistry.register("cretaceous_mountains", new CretaceousMountains());
	public static final Biome CRETACEOUS_ERRODED_MOUNTAINS = ModRegistry.register("cretaceous_erroded_mountains", new CretaceousErrodedMountains());

	public static final Biome CRETACEOUS_DESERT = ModRegistry.register("cretaceous_desert", new CretaceousDesert());
	public static final Biome CRETACEOUS_DESERT_HILLS = ModRegistry.register("cretaceous_desert_hills", new CretaceousDesert(0.45F, 0.3F));

	public static final Biome CRETACEOUS_RED_DESERT = ModRegistry.register("cretaceous_red_desert", new CretaceousRedDesert());
	public static final Biome CRETACEOUS_RED_DESERT_HILLS = ModRegistry.register("cretaceous_red_desert_hills", new CretaceousRedDesert(0.45F, 0.3F));

	public static final Biome CRETACEOUS_MARSH = ModRegistry.register("cretaceous_marsh", new CretaceousMarsh());
	public static final Biome CRETACEOUS_SWAMP = ModRegistry.register("cretaceous_swamp", new CretaceousSwamp());
	public static final Biome CRETACEOUS_FEN = ModRegistry.register("cretaceous_fen", new CretaceousFen());
	public static final Biome CRETACEOUS_BOG = ModRegistry.register("cretaceous_bog", new CretaceousBog());

	public static final Biome CRETACEOUS_RIVER = ModRegistry.register("cretaceous_river", new CretaceousRiver());

	public static final Biome COLD_CRETACEOUS_OCEAN = ModRegistry.register("cold_cretaceous_ocean", new ColdCretaceousOcean());
	public static final Biome COLD_DEEP_CRETACEOUS_OCEAN = ModRegistry.register("cold_deep_cretaceous_ocean", new ColdCretaceousOcean(-1.8F, 0.1F));

	public static final Biome CRETACEOUS_OCEAN = ModRegistry.register("cretaceous_ocean", new CretaceousOcean());
	public static final Biome DEEP_CRETACEOUS_OCEAN = ModRegistry.register("deep_cretaceous_ocean", new CretaceousOcean(-1.8F, 0.1F));

	public static final Biome WARM_CRETACEOUS_OCEAN = ModRegistry.register("warm_cretaceous_ocean", new WarmCretaceousOcean());
	public static final Biome WARM_DEEP_CRETACEOUS_OCEAN = ModRegistry.register("warm_deep_cretaceous_ocean", new WarmCretaceousOcean(-1.8F, 0.1F));

	public static final Biome CRETACEOUS_SHORE = ModRegistry.register("cretaceous_shore", new CretaceousShore());

	// Overworld
	public static final Biome ARAUCARIA_FOREST = ModRegistry.register("araucaria_forest", new AraucariaForest());
	public static final Biome ARAUCARIA_FOREST_HILLS = ModRegistry.register("araucaria_forest_hills", new AraucariaForest(0.45F, 0.3F));

	public static final Biome CONIFER_FOREST = ModRegistry.register("conifer_forest", new ConiferForest());
	public static final Biome CONIFER_FOREST_HILLS = ModRegistry.register("conifer_forest_hills", new ConiferForest(0.45F, 0.3F));

	public static final Biome GINKGO_FOREST = ModRegistry.register("ginkgo_forest", new GinkgoForest());
	public static final Biome GINKGO_FOREST_HILLS = ModRegistry.register("ginkgo_forest_hills", new GinkgoForest(0.45F, 0.3F));

	public static final Biome REDWOODS_FOREST = ModRegistry.register("redwoods_forest", new SequoiaForest());
	public static final Biome REDWOODS_FOREST_HILLS = ModRegistry.register("redwoods_forest_hills", new SequoiaForest(0.45F, 0.3F));

	public static final Biome VOLCANO = ModRegistry.register("volcano", new Volcano());

	// Registry
	public static void init() {
		ModUtils.LOGGER.debug("Registering Mod Biomes");
	}
}
