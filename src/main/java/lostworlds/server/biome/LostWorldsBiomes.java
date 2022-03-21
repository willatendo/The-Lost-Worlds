package lostworlds.server.biome;

import lostworlds.server.LostWorldsRegistry;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.biome.biomes.cretaceous.arctic.CretaceousArctic;
import lostworlds.server.biome.biomes.cretaceous.arctic.CretaceousArcticSpires;
import lostworlds.server.biome.biomes.cretaceous.arctic.CretaceousFrozenForest;
import lostworlds.server.biome.biomes.cretaceous.desert.CretaceousDesert;
import lostworlds.server.biome.biomes.cretaceous.desert.CretaceousRedDesert;
import lostworlds.server.biome.biomes.cretaceous.forest.CretaceousAraucariaForest;
import lostworlds.server.biome.biomes.cretaceous.forest.CretaceousConiferForest;
import lostworlds.server.biome.biomes.cretaceous.forest.CretaceousGinkgoForest;
import lostworlds.server.biome.biomes.cretaceous.forest.CretaceousRedwoodsForest;
import lostworlds.server.biome.biomes.cretaceous.mountains.CretaceousErrodedMountains;
import lostworlds.server.biome.biomes.cretaceous.mountains.CretaceousMountains;
import lostworlds.server.biome.biomes.cretaceous.ocean.ColdCretaceousOcean;
import lostworlds.server.biome.biomes.cretaceous.ocean.CretaceousOcean;
import lostworlds.server.biome.biomes.cretaceous.ocean.WarmCretaceousOcean;
import lostworlds.server.biome.biomes.cretaceous.plains.CretaceousFloodBasalts;
import lostworlds.server.biome.biomes.cretaceous.plains.CretaceousGameTrail;
import lostworlds.server.biome.biomes.cretaceous.plains.CretaceousMedow;
import lostworlds.server.biome.biomes.cretaceous.plains.CretaceousPlains;
import lostworlds.server.biome.biomes.cretaceous.river.CretaceousRiver;
import lostworlds.server.biome.biomes.cretaceous.shore.CretaceousShore;
import lostworlds.server.biome.biomes.cretaceous.swamp.CretaceousBog;
import lostworlds.server.biome.biomes.cretaceous.swamp.CretaceousFen;
import lostworlds.server.biome.biomes.cretaceous.swamp.CretaceousMarsh;
import lostworlds.server.biome.biomes.cretaceous.swamp.CretaceousSwamp;
import lostworlds.server.biome.biomes.jurassic.desert.JurassicDesert;
import lostworlds.server.biome.biomes.jurassic.forest.JurassicAraucariaForest;
import lostworlds.server.biome.biomes.jurassic.forest.JurassicConiferForest;
import lostworlds.server.biome.biomes.jurassic.forest.JurassicGinkgoForest;
import lostworlds.server.biome.biomes.jurassic.forest.JurassicRedwoodsForest;
import lostworlds.server.biome.biomes.jurassic.mountains.JurassicErrodedMountains;
import lostworlds.server.biome.biomes.jurassic.mountains.JurassicMountains;
import lostworlds.server.biome.biomes.jurassic.mountains.JurassicVolcanicRange;
import lostworlds.server.biome.biomes.jurassic.ocean.JurassicOcean;
import lostworlds.server.biome.biomes.jurassic.ocean.WarmJurassicOcean;
import lostworlds.server.biome.biomes.jurassic.plains.JurassicPlains;
import lostworlds.server.biome.biomes.jurassic.river.JurassicRiver;
import lostworlds.server.biome.biomes.jurassic.shore.JurassicShore;
import lostworlds.server.biome.biomes.jurassic.swamp.JurassicBog;
import lostworlds.server.biome.biomes.jurassic.swamp.JurassicFen;
import lostworlds.server.biome.biomes.jurassic.swamp.JurassicMarsh;
import lostworlds.server.biome.biomes.jurassic.swamp.JurassicSwamp;
import lostworlds.server.biome.biomes.overworld.forest.AraucariaForest;
import lostworlds.server.biome.biomes.overworld.forest.ConiferForest;
import lostworlds.server.biome.biomes.overworld.forest.GinkgoForest;
import lostworlds.server.biome.biomes.overworld.forest.SequoiaForest;
import lostworlds.server.biome.biomes.overworld.mountain.Volcano;
import lostworlds.server.biome.biomes.permian.desert.PermianDesert;
import lostworlds.server.biome.biomes.permian.forest.PermianConiferForest;
import lostworlds.server.biome.biomes.permian.forest.PermianGinkgoForest;
import lostworlds.server.biome.biomes.permian.mountain.PermianMountain;
import lostworlds.server.biome.biomes.permian.ocean.PermianOcean;
import lostworlds.server.biome.biomes.permian.ocean.WarmPermianOcean;
import lostworlds.server.biome.biomes.permian.plains.PermianAshyMedow;
import lostworlds.server.biome.biomes.permian.plains.PermianDriedPlains;
import lostworlds.server.biome.biomes.permian.plains.PermianFloodBasalts;
import lostworlds.server.biome.biomes.permian.plains.PermianPlains;
import lostworlds.server.biome.biomes.permian.river.PermianRiver;
import lostworlds.server.biome.biomes.permian.shore.PermianShore;
import lostworlds.server.biome.biomes.permian.swamp.PermianMarsh;
import net.minecraft.world.biome.Biome;

public class LostWorldsBiomes {
	// Permian
	public static final Biome PERMIAN_CONIFER_FOREST = LostWorldsRegistry.register("permian_conifer_forest", new PermianConiferForest());
	public static final Biome PERMIAN_CONIFER_FOREST_HILLS = LostWorldsRegistry.register("permian_conifer_forest_hills", new PermianConiferForest(0.45F, 0.3F));

	public static final Biome PERMIAN_GINKGO_FOREST = LostWorldsRegistry.register("permian_ginkgo_forest", new PermianGinkgoForest());
	public static final Biome PERMIAN_GINKGO_FOREST_HILLS = LostWorldsRegistry.register("permian_ginkgo_forest_hills", new PermianGinkgoForest(0.45F, 0.3F));

	public static final Biome PERMIAN_PLAINS = LostWorldsRegistry.register("permian_plains", new PermianPlains());
	public static final Biome PERMIAN_PLAINS_HILLS = LostWorldsRegistry.register("permian_plains_hills", new PermianPlains(0.45F, 0.3F));

	public static final Biome PERMIAN_DRIED_PLAINS = LostWorldsRegistry.register("permian_dried_plains", new PermianDriedPlains());
	public static final Biome PERMIAN_DRIED_PLAINS_HILLS = LostWorldsRegistry.register("permian_dried_plains_hills", new PermianDriedPlains(0.45F, 0.3F));

	public static final Biome PERMIAN_DESERT = LostWorldsRegistry.register("permian_desert", new PermianDesert());
	public static final Biome PERMIAN_DESERT_HILLS = LostWorldsRegistry.register("permian_desert_hills", new PermianDesert(0.45F, 0.3F));

	public static final Biome PERMIAN_FLOOD_BASALTS = LostWorldsRegistry.register("permian_flood_basalts", new PermianFloodBasalts());
	public static final Biome PERMIAN_ASHY_MEDOWS = LostWorldsRegistry.register("permian_ashy_medows", new PermianAshyMedow());

	public static final Biome PERMIAN_MOUNTAINS = LostWorldsRegistry.register("permian_mountains", new PermianMountain());

	public static final Biome PERMIAN_MARSH = LostWorldsRegistry.register("permian_marsh", new PermianMarsh());

	public static final Biome PERMIAN_RIVER = LostWorldsRegistry.register("permian_river", new PermianRiver());

	public static final Biome PERMIAN_OCEAN = LostWorldsRegistry.register("permian_ocean", new PermianOcean());
	public static final Biome DEEP_PERMIAN_OCEAN = LostWorldsRegistry.register("deep_permian_ocean", new PermianOcean(-1.8F, 0.1F));

	public static final Biome WARM_PERMIAN_OCEAN = LostWorldsRegistry.register("warm_permian_ocean", new WarmPermianOcean());
	public static final Biome WARM_DEEP_PERMIAN_OCEAN = LostWorldsRegistry.register("warm_deep_permian_ocean", new WarmPermianOcean());

	public static final Biome PERMIAN_SHORE = LostWorldsRegistry.register("permian_shore", new PermianShore());

	// Jurassic
	public static final Biome JURASSIC_ARAUCARIA_FOREST = LostWorldsRegistry.register("jurassic_araucaria_forest", new JurassicAraucariaForest());
	public static final Biome JURASSIC_ARAUCARIA_FOREST_HILLS = LostWorldsRegistry.register("jurassic_araucaria_forest_hills", new JurassicAraucariaForest(0.45F, 0.3F));

	public static final Biome JURASSIC_CONIFER_FOREST = LostWorldsRegistry.register("jurassic_conifer_forest", new JurassicConiferForest());
	public static final Biome JURASSIC_CONIFER_FOREST_HILLS = LostWorldsRegistry.register("jurassic_conifer_forest_hills", new JurassicConiferForest(0.45F, 0.3F));

	public static final Biome JURASSIC_GINKGO_FOREST = LostWorldsRegistry.register("jurassic_ginkgo_forest", new JurassicGinkgoForest());
	public static final Biome JURASSIC_GINKGO_FOREST_HILLS = LostWorldsRegistry.register("jurassic_ginkgo_forest_hills", new JurassicGinkgoForest(0.45F, 0.3F));

	public static final Biome JURASSIC_REDWOODS_FOREST = LostWorldsRegistry.register("jurassic_redwoods_forest", new JurassicRedwoodsForest());
	public static final Biome JURASSIC_REDWOODS_FOREST_HILLS = LostWorldsRegistry.register("jurassic_redwoods_forest_hills", new JurassicRedwoodsForest(0.45F, 0.3F));

	public static final Biome JURASSIC_PLAINS = LostWorldsRegistry.register("jurassic_plains", new JurassicPlains());
	public static final Biome JURASSIC_PLAINS_HILLS = LostWorldsRegistry.register("jurassic_plains_hills", new JurassicPlains(0.45F, 0.3F));

	public static final Biome JURASSIC_MOUNTAINS = LostWorldsRegistry.register("jurassic_mountains", new JurassicMountains());
	public static final Biome JURASSIC_ERRODED_MOUNTAINS = LostWorldsRegistry.register("jurassic_erroded_mountains", new JurassicErrodedMountains());
	public static final Biome JURASSIC_VOLCANIC_RANGE = LostWorldsRegistry.register("jurassic_volcanic_range", new JurassicVolcanicRange());

	public static final Biome JURASSIC_DESERT = LostWorldsRegistry.register("jurassic_desert", new JurassicDesert());
	public static final Biome JURASSIC_DESERT_HILLS = LostWorldsRegistry.register("jurassic_desert_hills", new JurassicDesert(0.45F, 0.3F));

	public static final Biome JURASSIC_MARSH = LostWorldsRegistry.register("jurassic_marsh", new JurassicMarsh());
	public static final Biome JURASSIC_SWAMP = LostWorldsRegistry.register("jurassic_swamp", new JurassicSwamp());
	public static final Biome JURASSIC_FEN = LostWorldsRegistry.register("jurassic_fen", new JurassicFen());
	public static final Biome JURASSIC_BOG = LostWorldsRegistry.register("jurassic_bog", new JurassicBog());

	public static final Biome JURASSIC_RIVER = LostWorldsRegistry.register("jurassic_river", new JurassicRiver());

	public static final Biome JURASSIC_OCEAN = LostWorldsRegistry.register("jurassic_ocean", new JurassicOcean());
	public static final Biome DEEP_JURASSIC_OCEAN = LostWorldsRegistry.register("deep_jurassic_ocean", new JurassicOcean(-1.8F, 0.1F));

	public static final Biome WARM_JURASSIC_OCEAN = LostWorldsRegistry.register("warm_jurassic_ocean", new WarmJurassicOcean());
	public static final Biome WARM_DEEP_JURASSIC_OCEAN = LostWorldsRegistry.register("warm_deep_jurassic_ocean", new WarmJurassicOcean(-1.8F, 0.1F));

	public static final Biome JURASSIC_SHORE = LostWorldsRegistry.register("jurassic_shore", new JurassicShore());

	// Creataceous
	public static final Biome CRETACEOUS_ARAUCARIA_FOREST = LostWorldsRegistry.register("cretaceous_araucaria_forest", new CretaceousAraucariaForest());
	public static final Biome CRETACEOUS_ARAUCARIA_FOREST_HILLS = LostWorldsRegistry.register("cretaceous_araucaria_forest_hills", new CretaceousAraucariaForest(0.45F, 0.3F));

	public static final Biome CRETACEOUS_CONIFER_FOREST = LostWorldsRegistry.register("cretaceous_conifer_forest", new CretaceousConiferForest());
	public static final Biome CRETACEOUS_CONIFER_FOREST_HILLS = LostWorldsRegistry.register("cretaceous_conifer_forest_hills", new CretaceousConiferForest(0.45F, 0.3F));

	public static final Biome CRETACEOUS_GINKGO_FOREST = LostWorldsRegistry.register("cretaceous_ginkgo_forest", new CretaceousGinkgoForest());
	public static final Biome CRETACEOUS_GINKGO_FOREST_HILLS = LostWorldsRegistry.register("cretaceous_ginkgo_forest_hills", new CretaceousGinkgoForest(0.45F, 0.3F));

	public static final Biome CRETACEOUS_REDWOODS_FOREST = LostWorldsRegistry.register("cretaceous_redwoods_forest", new CretaceousRedwoodsForest());
	public static final Biome CRETACEOUS_REDWOODS_FOREST_HILLS = LostWorldsRegistry.register("cretaceous_redwoods_forest_hills", new CretaceousRedwoodsForest(0.45F, 0.3F));

	public static final Biome CRETACEOUS_GAME_TRAIL = LostWorldsRegistry.register("cretaceous_game_trail", new CretaceousGameTrail());

	public static final Biome CRETACEOUS_MEDOW = LostWorldsRegistry.register("cretaceous_medow", new CretaceousMedow());

	public static final Biome CRETACEOUS_PLAINS = LostWorldsRegistry.register("cretaceous_plains", new CretaceousPlains());
	public static final Biome CRETACEOUS_PLAINS_HILLS = LostWorldsRegistry.register("cretaceous_plains_hills", new CretaceousPlains(0.45F, 0.3F));

	public static final Biome CRETACEOUS_FLOOD_BASALTS = LostWorldsRegistry.register("cretaceous_flood_basalts", new CretaceousFloodBasalts());

	public static final Biome CRETACEOUS_ARCTIC = LostWorldsRegistry.register("cretaceous_arctic", new CretaceousArctic());
	public static final Biome CRETACEOUS_ARCTIC_HILLS = LostWorldsRegistry.register("cretaceous_arctic_hills", new CretaceousArctic(0.45F, 0.3F));

	public static final Biome CRETACEOUS_ARCTIC_SPIRES = LostWorldsRegistry.register("cretaceous_arctic_spires", new CretaceousArcticSpires());

	public static final Biome CRETACEOUS_FROZEN_FOREST = LostWorldsRegistry.register("cretaceous_frozen_forest", new CretaceousFrozenForest());
	public static final Biome CRETACEOUS_FROZEN_FOREST_HILLS = LostWorldsRegistry.register("cretaceous_frozen_forest_hills", new CretaceousFrozenForest(0.45F, 0.3F));

	public static final Biome CRETACEOUS_MOUNTAINS = LostWorldsRegistry.register("cretaceous_mountains", new CretaceousMountains());
	public static final Biome CRETACEOUS_ERRODED_MOUNTAINS = LostWorldsRegistry.register("cretaceous_erroded_mountains", new CretaceousErrodedMountains());

	public static final Biome CRETACEOUS_DESERT = LostWorldsRegistry.register("cretaceous_desert", new CretaceousDesert());
	public static final Biome CRETACEOUS_DESERT_HILLS = LostWorldsRegistry.register("cretaceous_desert_hills", new CretaceousDesert(0.45F, 0.3F));

	public static final Biome CRETACEOUS_RED_DESERT = LostWorldsRegistry.register("cretaceous_red_desert", new CretaceousRedDesert());
	public static final Biome CRETACEOUS_RED_DESERT_HILLS = LostWorldsRegistry.register("cretaceous_red_desert_hills", new CretaceousRedDesert(0.45F, 0.3F));

	public static final Biome CRETACEOUS_MARSH = LostWorldsRegistry.register("cretaceous_marsh", new CretaceousMarsh());
	public static final Biome CRETACEOUS_SWAMP = LostWorldsRegistry.register("cretaceous_swamp", new CretaceousSwamp());
	public static final Biome CRETACEOUS_FEN = LostWorldsRegistry.register("cretaceous_fen", new CretaceousFen());
	public static final Biome CRETACEOUS_BOG = LostWorldsRegistry.register("cretaceous_bog", new CretaceousBog());

	public static final Biome CRETACEOUS_RIVER = LostWorldsRegistry.register("cretaceous_river", new CretaceousRiver());

	public static final Biome COLD_CRETACEOUS_OCEAN = LostWorldsRegistry.register("cold_cretaceous_ocean", new ColdCretaceousOcean());
	public static final Biome COLD_DEEP_CRETACEOUS_OCEAN = LostWorldsRegistry.register("cold_deep_cretaceous_ocean", new ColdCretaceousOcean(-1.8F, 0.1F));

	public static final Biome CRETACEOUS_OCEAN = LostWorldsRegistry.register("cretaceous_ocean", new CretaceousOcean());
	public static final Biome DEEP_CRETACEOUS_OCEAN = LostWorldsRegistry.register("deep_cretaceous_ocean", new CretaceousOcean(-1.8F, 0.1F));

	public static final Biome WARM_CRETACEOUS_OCEAN = LostWorldsRegistry.register("warm_cretaceous_ocean", new WarmCretaceousOcean());
	public static final Biome WARM_DEEP_CRETACEOUS_OCEAN = LostWorldsRegistry.register("warm_deep_cretaceous_ocean", new WarmCretaceousOcean(-1.8F, 0.1F));

	public static final Biome CRETACEOUS_SHORE = LostWorldsRegistry.register("cretaceous_shore", new CretaceousShore());

	// Overworld
	public static final Biome ARAUCARIA_FOREST = LostWorldsRegistry.register("araucaria_forest", new AraucariaForest());
	public static final Biome ARAUCARIA_FOREST_HILLS = LostWorldsRegistry.register("araucaria_forest_hills", new AraucariaForest(0.45F, 0.3F));

	public static final Biome CONIFER_FOREST = LostWorldsRegistry.register("conifer_forest", new ConiferForest());
	public static final Biome CONIFER_FOREST_HILLS = LostWorldsRegistry.register("conifer_forest_hills", new ConiferForest(0.45F, 0.3F));

	public static final Biome GINKGO_FOREST = LostWorldsRegistry.register("ginkgo_forest", new GinkgoForest());
	public static final Biome GINKGO_FOREST_HILLS = LostWorldsRegistry.register("ginkgo_forest_hills", new GinkgoForest(0.45F, 0.3F));

	public static final Biome REDWOODS_FOREST = LostWorldsRegistry.register("redwoods_forest", new SequoiaForest());
	public static final Biome REDWOODS_FOREST_HILLS = LostWorldsRegistry.register("redwoods_forest_hills", new SequoiaForest(0.45F, 0.3F));

	public static final Biome VOLCANO = LostWorldsRegistry.register("volcano", new Volcano());

	// Registry
	public static void init() {
		LostWorldsUtils.LOGGER.debug("Registering Mod Biomes");
	}
}
