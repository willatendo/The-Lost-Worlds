package lostworlds.server.biome;

import static lostworlds.LostWorldsMod.getRegistrate;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import lostworlds.LostWorldsMod;
import lostworlds.server.biome.biomes.cretaceous.arctic.CretaceousArcticBiome;
import lostworlds.server.biome.biomes.cretaceous.arctic.CretaceousArcticSpiresBiome;
import lostworlds.server.biome.biomes.cretaceous.arctic.CretaceousFrozenForestBiome;
import lostworlds.server.biome.biomes.cretaceous.desert.CretaceousDesertBiome;
import lostworlds.server.biome.biomes.cretaceous.desert.CretaceousRedDesertBiome;
import lostworlds.server.biome.biomes.cretaceous.forest.CretaceousAraucariaForestBiome;
import lostworlds.server.biome.biomes.cretaceous.forest.CretaceousConiferForestBiome;
import lostworlds.server.biome.biomes.cretaceous.forest.CretaceousGinkgoForestBiome;
import lostworlds.server.biome.biomes.cretaceous.forest.CretaceousRedwoodsForestBiome;
import lostworlds.server.biome.biomes.cretaceous.mountains.CretaceousErrodedMountainsBiome;
import lostworlds.server.biome.biomes.cretaceous.mountains.CretaceousMountainsBiome;
import lostworlds.server.biome.biomes.cretaceous.ocean.ColdCretaceousOceanBiome;
import lostworlds.server.biome.biomes.cretaceous.ocean.CretaceousOceanBiome;
import lostworlds.server.biome.biomes.cretaceous.ocean.WarmCretaceousOceanBiome;
import lostworlds.server.biome.biomes.cretaceous.plains.CretaceousFloodBasaltsBiome;
import lostworlds.server.biome.biomes.cretaceous.plains.CretaceousGameTrailBiome;
import lostworlds.server.biome.biomes.cretaceous.plains.CretaceousMedowBiome;
import lostworlds.server.biome.biomes.cretaceous.plains.CretaceousPlainsBiome;
import lostworlds.server.biome.biomes.cretaceous.river.CretaceousRiverBiome;
import lostworlds.server.biome.biomes.cretaceous.shore.CretaceousShoreBiome;
import lostworlds.server.biome.biomes.cretaceous.swamp.CretaceousBogBiome;
import lostworlds.server.biome.biomes.cretaceous.swamp.CretaceousFenBiome;
import lostworlds.server.biome.biomes.cretaceous.swamp.CretaceousMarshBiome;
import lostworlds.server.biome.biomes.cretaceous.swamp.CretaceousSwampBiome;
import lostworlds.server.biome.biomes.jurassic.desert.JurassicDesertBiome;
import lostworlds.server.biome.biomes.jurassic.forest.JurassicAraucariaForestBiome;
import lostworlds.server.biome.biomes.jurassic.forest.JurassicConiferForestBiome;
import lostworlds.server.biome.biomes.jurassic.forest.JurassicGinkgoForestBiome;
import lostworlds.server.biome.biomes.jurassic.forest.JurassicRedwoodsForestBiome;
import lostworlds.server.biome.biomes.jurassic.mountains.JurassicErrodedMountainsBiome;
import lostworlds.server.biome.biomes.jurassic.mountains.JurassicMountainsBiome;
import lostworlds.server.biome.biomes.jurassic.mountains.JurassicVolcanicRangeBiome;
import lostworlds.server.biome.biomes.jurassic.ocean.JurassicOceanBiome;
import lostworlds.server.biome.biomes.jurassic.ocean.WarmJurassicOceanBiome;
import lostworlds.server.biome.biomes.jurassic.plains.JurassicPlainsBiome;
import lostworlds.server.biome.biomes.jurassic.river.JurassicRiverBiome;
import lostworlds.server.biome.biomes.jurassic.shore.JurassicShoreBiome;
import lostworlds.server.biome.biomes.jurassic.swamp.JurassicBogBiome;
import lostworlds.server.biome.biomes.jurassic.swamp.JurassicFenBiome;
import lostworlds.server.biome.biomes.jurassic.swamp.JurassicMarshBiome;
import lostworlds.server.biome.biomes.jurassic.swamp.JurassicSwampBiome;
import lostworlds.server.biome.biomes.overworld.forest.AraucariaForestBiome;
import lostworlds.server.biome.biomes.overworld.forest.ConiferForestBiome;
import lostworlds.server.biome.biomes.overworld.forest.GinkgoForestBiome;
import lostworlds.server.biome.biomes.overworld.forest.RedwoodsForestBiome;
import lostworlds.server.biome.biomes.overworld.mountain.VolcanoBiome;
import lostworlds.server.biome.biomes.permian.desert.PermianDesertBiome;
import lostworlds.server.biome.biomes.permian.forest.PermianConiferForestBiome;
import lostworlds.server.biome.biomes.permian.forest.PermianGinkgoForestBiome;
import lostworlds.server.biome.biomes.permian.mountain.PermianMountainBiome;
import lostworlds.server.biome.biomes.permian.ocean.PermianOceanBiome;
import lostworlds.server.biome.biomes.permian.ocean.WarmPermianOceanBiome;
import lostworlds.server.biome.biomes.permian.plains.PermianAshyMedowBiome;
import lostworlds.server.biome.biomes.permian.plains.PermianDriedPlainsBiome;
import lostworlds.server.biome.biomes.permian.plains.PermianFloodBasaltsBiome;
import lostworlds.server.biome.biomes.permian.plains.PermianPlainsBiome;
import lostworlds.server.biome.biomes.permian.river.PermianRiverBiome;
import lostworlds.server.biome.biomes.permian.shore.PermianShoreBiome;
import lostworlds.server.biome.biomes.permian.swamp.PermianMarshBiome;
import lostworlds.server.util.registrate.LostWorldsRegistrate;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsBiomes {
	private static final LostWorldsRegistrate REGISTRATE = getRegistrate();
	public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, LostWorldsMod.ID);

	// Permian
	public static final RegistryObject<Biome> PERMIAN_CONIFER_FOREST = register("permian_conifer_forest", new PermianConiferForestBiome());
	public static final RegistryObject<Biome> PERMIAN_CONIFER_FOREST_HILLS = register("permian_conifer_forest_hills", new PermianConiferForestBiome(0.45F, 0.3F));

	public static final RegistryObject<Biome> PERMIAN_GINKGO_FOREST = register("permian_ginkgo_forest", new PermianGinkgoForestBiome());
	public static final RegistryObject<Biome> PERMIAN_GINKGO_FOREST_HILLS = register("permian_ginkgo_forest_hills", new PermianGinkgoForestBiome(0.45F, 0.3F));

	public static final RegistryObject<Biome> PERMIAN_PLAINS = register("permian_plains", new PermianPlainsBiome());
	public static final RegistryObject<Biome> PERMIAN_PLAINS_HILLS = register("permian_plains_hills", new PermianPlainsBiome(0.45F, 0.3F));

	public static final RegistryObject<Biome> PERMIAN_DRIED_PLAINS = register("permian_dried_plains", new PermianDriedPlainsBiome());
	public static final RegistryObject<Biome> PERMIAN_DRIED_PLAINS_HILLS = register("permian_dried_plains_hills", new PermianDriedPlainsBiome(0.45F, 0.3F));

	public static final RegistryObject<Biome> PERMIAN_DESERT = register("permian_desert", new PermianDesertBiome());
	public static final RegistryObject<Biome> PERMIAN_DESERT_HILLS = register("permian_desert_hills", new PermianDesertBiome(0.45F, 0.3F));

	public static final RegistryObject<Biome> PERMIAN_FLOOD_BASALTS = register("permian_flood_basalts", new PermianFloodBasaltsBiome());
	public static final RegistryObject<Biome> PERMIAN_ASHY_MEDOWS = register("permian_ashy_medows", new PermianAshyMedowBiome());

	public static final RegistryObject<Biome> PERMIAN_MOUNTAINS = register("permian_mountains", new PermianMountainBiome());

	public static final RegistryObject<Biome> PERMIAN_MARSH = register("permian_marsh", new PermianMarshBiome());

	public static final RegistryObject<Biome> PERMIAN_RIVER = register("permian_river", new PermianRiverBiome());

	public static final RegistryObject<Biome> PERMIAN_OCEAN = register("permian_ocean", new PermianOceanBiome());
	public static final RegistryObject<Biome> DEEP_PERMIAN_OCEAN = register("deep_permian_ocean", new PermianOceanBiome(-1.8F, 0.1F));

	public static final RegistryObject<Biome> WARM_PERMIAN_OCEAN = register("warm_permian_ocean", new WarmPermianOceanBiome());
	public static final RegistryObject<Biome> WARM_DEEP_PERMIAN_OCEAN = register("warm_deep_permian_ocean", new WarmPermianOceanBiome());

	public static final RegistryObject<Biome> PERMIAN_SHORE = register("permian_shore", new PermianShoreBiome());

	// Jurassic
	public static final RegistryObject<Biome> JURASSIC_ARAUCARIA_FOREST = register("jurassic_araucaria_forest", new JurassicAraucariaForestBiome());
	public static final RegistryObject<Biome> JURASSIC_ARAUCARIA_FOREST_HILLS = register("jurassic_araucaria_forest_hills", new JurassicAraucariaForestBiome(0.45F, 0.3F));

	public static final RegistryObject<Biome> JURASSIC_CONIFER_FOREST = register("jurassic_conifer_forest", new JurassicConiferForestBiome());
	public static final RegistryObject<Biome> JURASSIC_CONIFER_FOREST_HILLS = register("jurassic_conifer_forest_hills", new JurassicConiferForestBiome(0.45F, 0.3F));

	public static final RegistryObject<Biome> JURASSIC_GINKGO_FOREST = register("jurassic_ginkgo_forest", new JurassicGinkgoForestBiome());
	public static final RegistryObject<Biome> JURASSIC_GINKGO_FOREST_HILLS = register("jurassic_ginkgo_forest_hills", new JurassicGinkgoForestBiome(0.45F, 0.3F));

	public static final RegistryObject<Biome> JURASSIC_REDWOODS_FOREST = register("jurassic_redwoods_forest", new JurassicRedwoodsForestBiome());
	public static final RegistryObject<Biome> JURASSIC_REDWOODS_FOREST_HILLS = register("jurassic_redwoods_forest_hills", new JurassicRedwoodsForestBiome(0.45F, 0.3F));

	public static final RegistryObject<Biome> JURASSIC_PLAINS = register("jurassic_plains", new JurassicPlainsBiome());
	public static final RegistryObject<Biome> JURASSIC_PLAINS_HILLS = register("jurassic_plains_hills", new JurassicPlainsBiome(0.45F, 0.3F));

	public static final RegistryObject<Biome> JURASSIC_MOUNTAINS = register("jurassic_mountains", new JurassicMountainsBiome());
	public static final RegistryObject<Biome> JURASSIC_ERRODED_MOUNTAINS = register("jurassic_erroded_mountains", new JurassicErrodedMountainsBiome());
	public static final RegistryObject<Biome> JURASSIC_VOLCANIC_RANGE = register("jurassic_volcanic_range", new JurassicVolcanicRangeBiome());

	public static final RegistryObject<Biome> JURASSIC_DESERT = register("jurassic_desert", new JurassicDesertBiome());
	public static final RegistryObject<Biome> JURASSIC_DESERT_HILLS = register("jurassic_desert_hills", new JurassicDesertBiome(0.45F, 0.3F));

	public static final RegistryObject<Biome> JURASSIC_MARSH = register("jurassic_marsh", new JurassicMarshBiome());
	public static final RegistryObject<Biome> JURASSIC_SWAMP = register("jurassic_swamp", new JurassicSwampBiome());
	public static final RegistryObject<Biome> JURASSIC_FEN = register("jurassic_fen", new JurassicFenBiome());
	public static final RegistryObject<Biome> JURASSIC_BOG = register("jurassic_bog", new JurassicBogBiome());

	public static final RegistryObject<Biome> JURASSIC_RIVER = register("jurassic_river", new JurassicRiverBiome());

	public static final RegistryObject<Biome> JURASSIC_OCEAN = register("jurassic_ocean", new JurassicOceanBiome());
	public static final RegistryObject<Biome> DEEP_JURASSIC_OCEAN = register("deep_jurassic_ocean", new JurassicOceanBiome(-1.8F, 0.1F));

	public static final RegistryObject<Biome> WARM_JURASSIC_OCEAN = register("warm_jurassic_ocean", new WarmJurassicOceanBiome());
	public static final RegistryObject<Biome> WARM_DEEP_JURASSIC_OCEAN = register("warm_deep_jurassic_ocean", new WarmJurassicOceanBiome(-1.8F, 0.1F));

	public static final RegistryObject<Biome> JURASSIC_SHORE = register("jurassic_shore", new JurassicShoreBiome());

	// Creataceous
	public static final RegistryObject<Biome> CRETACEOUS_ARAUCARIA_FOREST = register("cretaceous_araucaria_forest", new CretaceousAraucariaForestBiome());
	public static final RegistryObject<Biome> CRETACEOUS_ARAUCARIA_FOREST_HILLS = register("cretaceous_araucaria_forest_hills", new CretaceousAraucariaForestBiome(0.45F, 0.3F));

	public static final RegistryObject<Biome> CRETACEOUS_CONIFER_FOREST = register("cretaceous_conifer_forest", new CretaceousConiferForestBiome());
	public static final RegistryObject<Biome> CRETACEOUS_CONIFER_FOREST_HILLS = register("cretaceous_conifer_forest_hills", new CretaceousConiferForestBiome(0.45F, 0.3F));

	public static final RegistryObject<Biome> CRETACEOUS_GINKGO_FOREST = register("cretaceous_ginkgo_forest", new CretaceousGinkgoForestBiome());
	public static final RegistryObject<Biome> CRETACEOUS_GINKGO_FOREST_HILLS = register("cretaceous_ginkgo_forest_hills", new CretaceousGinkgoForestBiome(0.45F, 0.3F));

	public static final RegistryObject<Biome> CRETACEOUS_REDWOODS_FOREST = register("cretaceous_redwoods_forest", new CretaceousRedwoodsForestBiome());
	public static final RegistryObject<Biome> CRETACEOUS_REDWOODS_FOREST_HILLS = register("cretaceous_redwoods_forest_hills", new CretaceousRedwoodsForestBiome(0.45F, 0.3F));

	public static final RegistryObject<Biome> CRETACEOUS_GAME_TRAIL = register("cretaceous_game_trail", new CretaceousGameTrailBiome());

	public static final RegistryObject<Biome> CRETACEOUS_MEDOW = register("cretaceous_medow", new CretaceousMedowBiome());

	public static final RegistryObject<Biome> CRETACEOUS_PLAINS = register("cretaceous_plains", new CretaceousPlainsBiome());
	public static final RegistryObject<Biome> CRETACEOUS_PLAINS_HILLS = register("cretaceous_plains_hills", new CretaceousPlainsBiome(0.45F, 0.3F));

	public static final RegistryObject<Biome> CRETACEOUS_FLOOD_BASALTS = register("cretaceous_flood_basalts", new CretaceousFloodBasaltsBiome());

	public static final RegistryObject<Biome> CRETACEOUS_ARCTIC = register("cretaceous_arctic", new CretaceousArcticBiome());
	public static final RegistryObject<Biome> CRETACEOUS_ARCTIC_HILLS = register("cretaceous_arctic_hills", new CretaceousArcticBiome(0.45F, 0.3F));

	public static final RegistryObject<Biome> CRETACEOUS_ARCTIC_SPIRES = register("cretaceous_arctic_spires", new CretaceousArcticSpiresBiome());

	public static final RegistryObject<Biome> CRETACEOUS_FROZEN_FOREST = register("cretaceous_frozen_forest", new CretaceousFrozenForestBiome());
	public static final RegistryObject<Biome> CRETACEOUS_FROZEN_FOREST_HILLS = register("cretaceous_frozen_forest_hills", new CretaceousFrozenForestBiome(0.45F, 0.3F));

	public static final RegistryObject<Biome> CRETACEOUS_MOUNTAINS = register("cretaceous_mountains", new CretaceousMountainsBiome());
	public static final RegistryObject<Biome> CRETACEOUS_ERRODED_MOUNTAINS = register("cretaceous_erroded_mountains", new CretaceousErrodedMountainsBiome());

	public static final RegistryObject<Biome> CRETACEOUS_DESERT = register("cretaceous_desert", new CretaceousDesertBiome());
	public static final RegistryObject<Biome> CRETACEOUS_DESERT_HILLS = register("cretaceous_desert_hills", new CretaceousDesertBiome(0.45F, 0.3F));

	public static final RegistryObject<Biome> CRETACEOUS_RED_DESERT = register("cretaceous_red_desert", new CretaceousRedDesertBiome());
	public static final RegistryObject<Biome> CRETACEOUS_RED_DESERT_HILLS = register("cretaceous_red_desert_hills", new CretaceousRedDesertBiome(0.45F, 0.3F));

	public static final RegistryObject<Biome> CRETACEOUS_MARSH = register("cretaceous_marsh", new CretaceousMarshBiome());
	public static final RegistryObject<Biome> CRETACEOUS_SWAMP = register("cretaceous_swamp", new CretaceousSwampBiome());
	public static final RegistryObject<Biome> CRETACEOUS_FEN = register("cretaceous_fen", new CretaceousFenBiome());
	public static final RegistryObject<Biome> CRETACEOUS_BOG = register("cretaceous_bog", new CretaceousBogBiome());

	public static final RegistryObject<Biome> CRETACEOUS_RIVER = register("cretaceous_river", new CretaceousRiverBiome());

	public static final RegistryObject<Biome> COLD_CRETACEOUS_OCEAN = register("cold_cretaceous_ocean", new ColdCretaceousOceanBiome());
	public static final RegistryObject<Biome> COLD_DEEP_CRETACEOUS_OCEAN = register("cold_deep_cretaceous_ocean", new ColdCretaceousOceanBiome(-1.8F, 0.1F));

	public static final RegistryObject<Biome> CRETACEOUS_OCEAN = register("cretaceous_ocean", new CretaceousOceanBiome());
	public static final RegistryObject<Biome> DEEP_CRETACEOUS_OCEAN = register("deep_cretaceous_ocean", new CretaceousOceanBiome(-1.8F, 0.1F));

	public static final RegistryObject<Biome> WARM_CRETACEOUS_OCEAN = register("warm_cretaceous_ocean", new WarmCretaceousOceanBiome());
	public static final RegistryObject<Biome> WARM_DEEP_CRETACEOUS_OCEAN = register("warm_deep_cretaceous_ocean", new WarmCretaceousOceanBiome(-1.8F, 0.1F));

	public static final RegistryObject<Biome> CRETACEOUS_SHORE = register("cretaceous_shore", new CretaceousShoreBiome());

	// Overworld
	public static final RegistryObject<Biome> ARAUCARIA_FOREST = register("araucaria_forest", new AraucariaForestBiome());
	public static final RegistryObject<Biome> ARAUCARIA_FOREST_HILLS = register("araucaria_forest_hills", new AraucariaForestBiome(0.45F, 0.3F));

	public static final RegistryObject<Biome> CONIFER_FOREST = register("conifer_forest", new ConiferForestBiome());
	public static final RegistryObject<Biome> CONIFER_FOREST_HILLS = register("conifer_forest_hills", new ConiferForestBiome(0.45F, 0.3F));

	public static final RegistryObject<Biome> GINKGO_FOREST = register("ginkgo_forest", new GinkgoForestBiome());
	public static final RegistryObject<Biome> GINKGO_FOREST_HILLS = register("ginkgo_forest_hills", new GinkgoForestBiome(0.45F, 0.3F));

	public static final RegistryObject<Biome> REDWOODS_FOREST = register("redwoods_forest", new RedwoodsForestBiome());
	public static final RegistryObject<Biome> REDWOODS_FOREST_HILLS = register("redwoods_forest_hills", new RedwoodsForestBiome(0.45F, 0.3F));

	public static final RegistryObject<Biome> VOLCANO = register("volcano", new VolcanoBiome());

	public static RegistryObject<Biome> register(String id, ModBiome biome) {
		Biome realBiome = biome.getBiome();
		REGISTRATE.addRawLang("biome.lostworlds." + id, Arrays.stream(id.toLowerCase(Locale.ROOT).split("_")).map(StringUtils::capitalize).collect(Collectors.joining(" ")));
		return BIOMES.register(id, () -> realBiome);
	}

	// Registry
	public static void deferred(IEventBus bus) {
		BIOMES.register(bus);
	}
}
