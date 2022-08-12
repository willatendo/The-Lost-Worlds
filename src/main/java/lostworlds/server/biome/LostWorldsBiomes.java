package lostworlds.server.biome;

import static lostworlds.LostWorldsMod.getRegistrate;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.util.registrate.BiomeEntry;
import lostworlds.server.util.registrate.LostWorldsRegistrate;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsBiomes {
	private static final LostWorldsRegistrate REGISTRATE = getRegistrate();
	public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.Keys.BIOMES, LostWorldsUtils.ID);

	// Permian
	public static final BiomeEntry<Biome> PERMIAN_CONIFER_FOREST = REGISTRATE.biome("permian_conifer_forest", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianConiferForest()).register();

	public static final BiomeEntry<Biome> PERMIAN_GINKGO_FOREST = REGISTRATE.biome("permian_ginkgo_forest", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianGinkgoForest()).register();

	public static final BiomeEntry<Biome> PERMIAN_PLAINS = REGISTRATE.biome("permian_plains", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianPlains()).register();

	public static final BiomeEntry<Biome> PERMIAN_DRIED_PLAINS = REGISTRATE.biome("permian_dried_plains", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianDriedPlains()).register();

	public static final BiomeEntry<Biome> PERMIAN_DESERT = REGISTRATE.biome("permian_desert", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianDesert()).register();

	public static final BiomeEntry<Biome> PERMIAN_FLOOD_BASALTS = REGISTRATE.biome("permian_flood_basalts", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianFloodBasalts()).register();
	public static final BiomeEntry<Biome> PERMIAN_ASHY_MEDOWS = REGISTRATE.biome("permian_ashy_medows", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianAshyMedows()).register();

	public static final BiomeEntry<Biome> PERMIAN_WINDSWEPT_HILLS = REGISTRATE.biome("permian_windswept_hills", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianWindsweptHills()).register();

	public static final BiomeEntry<Biome> PERMIAN_MARSH = REGISTRATE.biome("permian_marsh", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianMarsh()).register();

	public static final BiomeEntry<Biome> PERMIAN_RIVER = REGISTRATE.biome("permian_river", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianRiver()).register();

	public static final BiomeEntry<Biome> PERMIAN_OCEAN = REGISTRATE.biome("permian_ocean", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianOcean()).register();
	public static final BiomeEntry<Biome> DEEP_PERMIAN_OCEAN = REGISTRATE.biome("deep_permian_ocean", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianOcean()).register();

	public static final BiomeEntry<Biome> WARM_PERMIAN_OCEAN = REGISTRATE.biome("warm_permian_ocean", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianWarmOcean()).register();
	public static final BiomeEntry<Biome> DEEP_WARM_PERMIAN_OCEAN = REGISTRATE.biome("deep_warm_permian_ocean", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianWarmOcean()).register();

	public static final BiomeEntry<Biome> PERMIAN_SHORE = REGISTRATE.biome("permian_shore", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianShore()).register();

	public static final BiomeEntry<Biome> PERMIAN_DRIPSTONE_CAVES = REGISTRATE.biome("permian_dripstone_caves", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianDripstoneCaves()).register();

	public static final BiomeEntry<Biome> PERMIAN_GROVE = REGISTRATE.biome("permian_grove", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianGrove()).register();
	public static final BiomeEntry<Biome> PERMIAN_SNOWY_SLOPES = REGISTRATE.biome("permian_snowy_slopes", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianSnowySlopes()).register();
	public static final BiomeEntry<Biome> PERMIAN_FROZEN_PEAKS = REGISTRATE.biome("permian_frozen_peaks", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianFrozenPeaks()).register();
	public static final BiomeEntry<Biome> PERMIAN_JAGGED_PEAKS = REGISTRATE.biome("permian_jagged_peaks", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianJaggedPeaks()).register();
	public static final BiomeEntry<Biome> PERMIAN_STONY_PEAKS = REGISTRATE.biome("permian_stony_peaks", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianStonyPeaks()).register();

	// Jurassic
	public static final BiomeEntry<Biome> JURASSIC_ARAUCARIA_FOREST = REGISTRATE.biome("jurassic_araucaria_forest", b -> b.build()).properties(biomeBuilder -> builder -> JurassicBiomes.jurassicAraucariaForest()).register();

	public static final BiomeEntry<Biome> JURASSIC_CONIFER_FOREST = REGISTRATE.biome("jurassic_conifer_forest", b -> b.build()).properties(biomeBuilder -> builder -> JurassicBiomes.jurassicConiferForest()).register();

	public static final BiomeEntry<Biome> JURASSIC_GINKGO_FOREST = REGISTRATE.biome("jurassic_ginkgo_forest", b -> b.build()).properties(biomeBuilder -> builder -> JurassicBiomes.jurassicGinkgoForest()).register();

	public static final BiomeEntry<Biome> JURASSIC_REDWOODS_FOREST = REGISTRATE.biome("jurassic_redwoods_forest", b -> b.build()).properties(biomeBuilder -> builder -> JurassicBiomes.jurassicRedwoodsForest()).register();

	public static final BiomeEntry<Biome> JURASSIC_PLAINS = REGISTRATE.biome("jurassic_plains", b -> b.build()).properties(biomeBuilder -> builder -> JurassicBiomes.jurassicPlains()).register();

	public static final BiomeEntry<Biome> JURASSIC_WINDSWEPT_HILLS = REGISTRATE.biome("jurassic_windswept_hills", b -> b.build()).properties(biomeBuilder -> builder -> JurassicBiomes.jurassicWindsweptHills()).register();
	public static final BiomeEntry<Biome> JURASSIC_ERRODED_WINDSWEPT_HILLS = REGISTRATE.biome("jurassic_erroded_windswept_hills", b -> b.build()).properties(biomeBuilder -> builder -> JurassicBiomes.jurassicWindsweptHills()).register();
	public static final BiomeEntry<Biome> JURASSIC_VOLCANIC_RANGE = REGISTRATE.biome("jurassic_volcanic_range", b -> b.build()).properties(biomeBuilder -> builder -> JurassicBiomes.jurassicVolcanicRange()).register();

	public static final BiomeEntry<Biome> JURASSIC_DESERT = REGISTRATE.biome("jurassic_desert", b -> b.build()).properties(biomeBuilder -> builder -> JurassicBiomes.jurassicDesert()).register();

	public static final BiomeEntry<Biome> JURASSIC_MARSH = REGISTRATE.biome("jurassic_marsh", b -> b.build()).properties(biomeBuilder -> builder -> JurassicBiomes.jurassicMarsh()).register();
	public static final BiomeEntry<Biome> JURASSIC_SWAMP = REGISTRATE.biome("jurassic_swamp", b -> b.build()).properties(biomeBuilder -> builder -> JurassicBiomes.jurassicSwamp()).register();
	public static final BiomeEntry<Biome> JURASSIC_FEN = REGISTRATE.biome("jurassic_fen", b -> b.build()).properties(biomeBuilder -> builder -> JurassicBiomes.jurassicFen()).register();
	public static final BiomeEntry<Biome> JURASSIC_BOG = REGISTRATE.biome("jurassic_bog", b -> b.build()).properties(biomeBuilder -> builder -> JurassicBiomes.jurassicBog()).register();

	public static final BiomeEntry<Biome> JURASSIC_RIVER = REGISTRATE.biome("jurassic_river", b -> b.build()).properties(biomeBuilder -> builder -> JurassicBiomes.jurassicRiver()).register();

	public static final BiomeEntry<Biome> JURASSIC_OCEAN = REGISTRATE.biome("jurassic_ocean", b -> b.build()).properties(biomeBuilder -> builder -> JurassicBiomes.jurassicOcean()).register();
	public static final BiomeEntry<Biome> DEEP_JURASSIC_OCEAN = REGISTRATE.biome("deep_jurassic_ocean", b -> b.build()).properties(biomeBuilder -> builder -> JurassicBiomes.jurassicOcean()).register();

	public static final BiomeEntry<Biome> WARM_JURASSIC_OCEAN = REGISTRATE.biome("warm_jurassic_ocean", b -> b.build()).properties(biomeBuilder -> builder -> JurassicBiomes.jurassicWarmOcean()).register();
	public static final BiomeEntry<Biome> DEEP_WARM_JURASSIC_OCEAN = REGISTRATE.biome("deep_warm_jurassic_ocean", b -> b.build()).properties(biomeBuilder -> builder -> JurassicBiomes.jurassicWarmOcean()).register();

	public static final BiomeEntry<Biome> JURASSIC_SHORE = REGISTRATE.biome("jurassic_shore", b -> b.build()).properties(biomeBuilder -> builder -> JurassicBiomes.jurassicShore()).register();

	public static final BiomeEntry<Biome> JURASSIC_DRIPSTONE_CAVES = REGISTRATE.biome("jurassic_dripstone_caves", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianDripstoneCaves()).register();

	public static final BiomeEntry<Biome> JURASSIC_GROVE = REGISTRATE.biome("jurassic_grove", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianGrove()).register();
	public static final BiomeEntry<Biome> JURASSIC_SNOWY_SLOPES = REGISTRATE.biome("jurassic_snowy_slopes", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianSnowySlopes()).register();
	public static final BiomeEntry<Biome> JURASSIC_FROZEN_PEAKS = REGISTRATE.biome("jurassic_frozen_peaks", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianFrozenPeaks()).register();
	public static final BiomeEntry<Biome> JURASSIC_JAGGED_PEAKS = REGISTRATE.biome("jurassic_jagged_peaks", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianJaggedPeaks()).register();
	public static final BiomeEntry<Biome> JURASSIC_STONY_PEAKS = REGISTRATE.biome("jurassic_stony_peaks", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianStonyPeaks()).register();

	// Creataceous
	public static final BiomeEntry<Biome> CRETACEOUS_ARAUCARIA_FOREST = REGISTRATE.biome("cretaceous_araucaria_forest", b -> b.build()).properties(biomeBuilder -> builder -> CretaceousBiomes.cretaceousAraucariaForest()).register();

	public static final BiomeEntry<Biome> CRETACEOUS_CONIFER_FOREST = REGISTRATE.biome("cretaceous_conifer_forest", b -> b.build()).properties(biomeBuilder -> builder -> CretaceousBiomes.cretaceousConiferForest()).register();

	public static final BiomeEntry<Biome> CRETACEOUS_GINKGO_FOREST = REGISTRATE.biome("cretaceous_ginkgo_forest", b -> b.build()).properties(biomeBuilder -> builder -> CretaceousBiomes.cretaceousGinkgoForest()).register();

	public static final BiomeEntry<Biome> CRETACEOUS_REDWOODS_FOREST = REGISTRATE.biome("cretaceous_redwoods_forest", b -> b.build()).properties(biomeBuilder -> builder -> CretaceousBiomes.cretaceousRedwoodsForest()).register();

	public static final BiomeEntry<Biome> CRETACEOUS_GAME_TRAIL = REGISTRATE.biome("cretaceous_game_trail", b -> b.build()).properties(biomeBuilder -> builder -> CretaceousBiomes.cretaceousGameTrailBiome()).register();

	public static final BiomeEntry<Biome> CRETACEOUS_MEDOW = REGISTRATE.biome("cretaceous_medow", b -> b.build()).properties(biomeBuilder -> builder -> CretaceousBiomes.cretaceousMedowBiome()).register();

	public static final BiomeEntry<Biome> CRETACEOUS_PLAINS = REGISTRATE.biome("cretaceous_plains", b -> b.build()).properties(biomeBuilder -> builder -> CretaceousBiomes.cretaceousPlainsBiome()).register();

	public static final BiomeEntry<Biome> CRETACEOUS_FLOOD_BASALTS = REGISTRATE.biome("cretaceous_flood_basalts", b -> b.build()).properties(biomeBuilder -> builder -> CretaceousBiomes.cretaceousFloodBasaltsBiome()).register();

	public static final BiomeEntry<Biome> CRETACEOUS_ARCTIC = REGISTRATE.biome("cretaceous_arctic", b -> b.build()).properties(biomeBuilder -> builder -> CretaceousBiomes.cretaceousArctic()).register();

	public static final BiomeEntry<Biome> CRETACEOUS_ARCTIC_SPIRES = REGISTRATE.biome("cretaceous_arctic_spires", b -> b.build()).properties(biomeBuilder -> builder -> CretaceousBiomes.cretaceousArcticSpires()).register();

	public static final BiomeEntry<Biome> CRETACEOUS_FROZEN_FOREST = REGISTRATE.biome("cretaceous_frozen_forest", b -> b.build()).properties(biomeBuilder -> builder -> CretaceousBiomes.cretaceousFrozenForest()).register();

	public static final BiomeEntry<Biome> CRETACEOUS_WINDSWEPT_HILLS = REGISTRATE.biome("cretaceous_windswept_hills", b -> b.build()).properties(biomeBuilder -> builder -> CretaceousBiomes.cretaceousWindsweptHills()).register();
	public static final BiomeEntry<Biome> CRETACEOUS_ERRODED_WINDSWEPT_HILLS = REGISTRATE.biome("cretaceous_erroded_windswept_hills", b -> b.build()).properties(biomeBuilder -> builder -> CretaceousBiomes.cretaceousWindsweptHills()).register();

	public static final BiomeEntry<Biome> CRETACEOUS_DESERT = REGISTRATE.biome("cretaceous_desert", b -> b.build()).properties(biomeBuilder -> builder -> CretaceousBiomes.cretaceousDesert()).register();

	public static final BiomeEntry<Biome> CRETACEOUS_RED_DESERT = REGISTRATE.biome("cretaceous_red_desert", b -> b.build()).properties(biomeBuilder -> builder -> CretaceousBiomes.cretaceousRedDesert()).register();

	public static final BiomeEntry<Biome> CRETACEOUS_MARSH = REGISTRATE.biome("cretaceous_marsh", b -> b.build()).properties(biomeBuilder -> builder -> CretaceousBiomes.cretaceousMarsh()).register();
	public static final BiomeEntry<Biome> CRETACEOUS_SWAMP = REGISTRATE.biome("cretaceous_swamp", b -> b.build()).properties(biomeBuilder -> builder -> CretaceousBiomes.cretaceousSwamp()).register();
	public static final BiomeEntry<Biome> CRETACEOUS_FEN = REGISTRATE.biome("cretaceous_fen", b -> b.build()).properties(biomeBuilder -> builder -> CretaceousBiomes.cretaceousFen()).register();
	public static final BiomeEntry<Biome> CRETACEOUS_BOG = REGISTRATE.biome("cretaceous_bog", b -> b.build()).properties(biomeBuilder -> builder -> CretaceousBiomes.cretaceousBog()).register();

	public static final BiomeEntry<Biome> CRETACEOUS_RIVER = REGISTRATE.biome("cretaceous_river", b -> b.build()).properties(biomeBuilder -> builder -> CretaceousBiomes.cretaceousRiverBiome()).register();

	public static final BiomeEntry<Biome> COLD_CRETACEOUS_OCEAN = REGISTRATE.biome("cold_cretaceous_ocean", b -> b.build()).properties(biomeBuilder -> builder -> CretaceousBiomes.cretaceousColdOceanBiome()).register();
	public static final BiomeEntry<Biome> DEEP_COLD_CRETACEOUS_OCEAN = REGISTRATE.biome("deep_cold_cretaceous_ocean", b -> b.build()).properties(biomeBuilder -> builder -> CretaceousBiomes.cretaceousColdOceanBiome()).register();

	public static final BiomeEntry<Biome> CRETACEOUS_OCEAN = REGISTRATE.biome("cretaceous_ocean", b -> b.build()).properties(biomeBuilder -> builder -> CretaceousBiomes.cretaceousOceanBiome()).register();
	public static final BiomeEntry<Biome> DEEP_CRETACEOUS_OCEAN = REGISTRATE.biome("deep_cretaceous_ocean", b -> b.build()).properties(biomeBuilder -> builder -> CretaceousBiomes.cretaceousOceanBiome()).register();

	public static final BiomeEntry<Biome> WARM_CRETACEOUS_OCEAN = REGISTRATE.biome("warm_cretaceous_ocean", b -> b.build()).properties(biomeBuilder -> builder -> CretaceousBiomes.cretaceousWarmOceanBiome()).register();
	public static final BiomeEntry<Biome> DEEP_WARM_CRETACEOUS_OCEAN = REGISTRATE.biome("deep_warm_cretaceous_ocean", b -> b.build()).properties(biomeBuilder -> builder -> CretaceousBiomes.cretaceousWarmOceanBiome()).register();

	public static final BiomeEntry<Biome> CRETACEOUS_SHORE = REGISTRATE.biome("cretaceous_shore", b -> b.build()).properties(biomeBuilder -> builder -> CretaceousBiomes.cretaceousShoreBiome()).register();

	public static final BiomeEntry<Biome> CRETACEOUS_DRIPSTONE_CAVES = REGISTRATE.biome("cretaceous_dripstone_caves", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianDripstoneCaves()).register();

	public static final BiomeEntry<Biome> CRETACEOUS_GROVE = REGISTRATE.biome("cretaceous_grove", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianGrove()).register();
	public static final BiomeEntry<Biome> CRETACEOUS_SNOWY_SLOPES = REGISTRATE.biome("cretaceous_snowy_slopes", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianSnowySlopes()).register();
	public static final BiomeEntry<Biome> CRETACEOUS_FROZEN_PEAKS = REGISTRATE.biome("cretaceous_frozen_peaks", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianFrozenPeaks()).register();
	public static final BiomeEntry<Biome> CRETACEOUS_JAGGED_PEAKS = REGISTRATE.biome("cretaceous_jagged_peaks", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianJaggedPeaks()).register();
	public static final BiomeEntry<Biome> CRETACEOUS_STONY_PEAKS = REGISTRATE.biome("cretaceous_stony_peaks", b -> b.build()).properties(biomeBuilder -> builder -> PermianBiomes.permianStonyPeaks()).register();

	// Overworld
	public static final BiomeEntry<Biome> ARAUCARIA_FOREST = REGISTRATE.biome("araucaria_forest", b -> b.build()).properties(biomeBuilder -> builder -> OverworldBiomes.araucariaForest()).register();

	public static final BiomeEntry<Biome> CONIFER_FOREST = REGISTRATE.biome("conifer_forest", b -> b.build()).properties(biomeBuilder -> builder -> OverworldBiomes.coniferForest()).register();

	public static final BiomeEntry<Biome> GINKGO_FOREST = REGISTRATE.biome("ginkgo_forest", b -> b.build()).properties(biomeBuilder -> builder -> OverworldBiomes.ginkgoForest()).register();

	public static final BiomeEntry<Biome> REDWOODS_FOREST = REGISTRATE.biome("redwoods_forest", b -> b.build()).properties(biomeBuilder -> builder -> OverworldBiomes.redwoodsForest()).register();

	public static final BiomeEntry<Biome> VOLCANO = REGISTRATE.biome("volcano", b -> b.build()).properties(biomeBuilder -> builder -> OverworldBiomes.volcano()).register();
}
