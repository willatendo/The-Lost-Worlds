package lostworlds.server.biome;

import static lostworlds.LostWorldsMod.getRegistrate;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.mojang.datafixers.util.Pair;
import com.tterrag.registrate.util.nullness.NonNullSupplier;

import lostworlds.LostWorldsMod;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.util.registrate.LostWorldsRegistrate;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LostWorldsBiomes {
	private static final LostWorldsRegistrate REGISTRATE = getRegistrate();
	public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, LostWorldsMod.ID);

	// Permian
	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> PERMIAN_CONIFER_FOREST = register("permian_conifer_forest", PermianBiomes::permianConiferForest);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> PERMIAN_GINKGO_FOREST = register("permian_ginkgo_forest", PermianBiomes::permianGinkgoForest);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> PERMIAN_PLAINS = register("permian_plains", PermianBiomes::permianPlains);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> PERMIAN_DRIED_PLAINS = register("permian_dried_plains", PermianBiomes::permianDriedPlains);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> PERMIAN_DESERT = register("permian_desert", PermianBiomes::permianDesert);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> PERMIAN_FLOOD_BASALTS = register("permian_flood_basalts", PermianBiomes::permianFloodBasalts);
	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> PERMIAN_ASHY_MEDOWS = register("permian_ashy_medows", PermianBiomes::permianAshyMedows);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> PERMIAN_WINDSWEPT_HILLS = register("permian_windswept_hills", PermianBiomes::permianWindsweptHills);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> PERMIAN_MARSH = register("permian_marsh", PermianBiomes::permianMarsh);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> PERMIAN_RIVER = register("permian_river", PermianBiomes::permianRiver);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> PERMIAN_OCEAN = register("permian_ocean", PermianBiomes::permianOcean);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> WARM_PERMIAN_OCEAN = register("warm_permian_ocean", PermianBiomes::permianWarmOcean);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> PERMIAN_SHORE = register("permian_shore", PermianBiomes::permianShore);

	// Jurassic
	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> JURASSIC_ARAUCARIA_FOREST = register("jurassic_araucaria_forest", JurassicBiomes::jurassicAraucariaForest);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> JURASSIC_CONIFER_FOREST = register("jurassic_conifer_forest", JurassicBiomes::jurassicConiferForest);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> JURASSIC_GINKGO_FOREST = register("jurassic_ginkgo_forest", JurassicBiomes::jurassicGinkgoForest);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> JURASSIC_REDWOODS_FOREST = register("jurassic_redwoods_forest", JurassicBiomes::jurassicRedwoodsForest);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> JURASSIC_PLAINS = register("jurassic_plains", JurassicBiomes::jurassicPlains);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> JURASSIC_WINDSWEPT_HILLS = register("jurassic_windswept_hills", JurassicBiomes::jurassicWindsweptHills);
	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> JURASSIC_ERRODED_WINDSWEPT_HILLS = register("jurassic_erroded_windswept_hills", JurassicBiomes::jurassicWindsweptHills);
	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> JURASSIC_VOLCANIC_RANGE = register("jurassic_volcanic_range", JurassicBiomes::jurassicVolcanicRange);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> JURASSIC_DESERT = register("jurassic_desert", JurassicBiomes::jurassicDesert);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> JURASSIC_MARSH = register("jurassic_marsh", JurassicBiomes::jurassicMarsh);
	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> JURASSIC_SWAMP = register("jurassic_swamp", JurassicBiomes::jurassicSwamp);
	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> JURASSIC_FEN = register("jurassic_fen", JurassicBiomes::jurassicFen);
	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> JURASSIC_BOG = register("jurassic_bog", JurassicBiomes::jurassicBog);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> JURASSIC_RIVER = register("jurassic_river", JurassicBiomes::jurassicRiver);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> JURASSIC_OCEAN = register("jurassic_ocean", JurassicBiomes::jurassicOcean);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> WARM_JURASSIC_OCEAN = register("warm_jurassic_ocean", JurassicBiomes::jurassicWarmOcean);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> JURASSIC_SHORE = register("jurassic_shore", JurassicBiomes::jurassicShore);

	// Creataceous
	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> CRETACEOUS_ARAUCARIA_FOREST = register("cretaceous_araucaria_forest", CretaceousBiomes::cretaceousAraucariaForest);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> CRETACEOUS_CONIFER_FOREST = register("cretaceous_conifer_forest", CretaceousBiomes::cretaceousConiferForest);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> CRETACEOUS_GINKGO_FOREST = register("cretaceous_ginkgo_forest", CretaceousBiomes::cretaceousGinkgoForest);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> CRETACEOUS_REDWOODS_FOREST = register("cretaceous_redwoods_forest", CretaceousBiomes::cretaceousRedwoodsForest);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> CRETACEOUS_GAME_TRAIL = register("cretaceous_game_trail", CretaceousBiomes::cretaceousGameTrailBiome);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> CRETACEOUS_MEDOW = register("cretaceous_medow", CretaceousBiomes::cretaceousMedowBiome);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> CRETACEOUS_PLAINS = register("cretaceous_plains", CretaceousBiomes::cretaceousPlainsBiome);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> CRETACEOUS_FLOOD_BASALTS = register("cretaceous_flood_basalts", CretaceousBiomes::cretaceousFloodBasaltsBiome);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> CRETACEOUS_ARCTIC = register("cretaceous_arctic", CretaceousBiomes::cretaceousArctic);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> CRETACEOUS_ARCTIC_SPIRES = register("cretaceous_arctic_spires", CretaceousBiomes::cretaceousArcticSpires);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> CRETACEOUS_FROZEN_FOREST = register("cretaceous_frozen_forest", CretaceousBiomes::cretaceousFrozenForest);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> CRETACEOUS_MOUNTAINS = register("cretaceous_mountains", CretaceousBiomes::cretaceousWindsweptHills);
	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> CRETACEOUS_ERRODED_MOUNTAINS = register("cretaceous_erroded_mountains", CretaceousBiomes::cretaceousWindsweptHills);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> CRETACEOUS_DESERT = register("cretaceous_desert", CretaceousBiomes::cretaceousDesert);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> CRETACEOUS_RED_DESERT = register("cretaceous_red_desert", CretaceousBiomes::cretaceousRedDesert);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> CRETACEOUS_MARSH = register("cretaceous_marsh", CretaceousBiomes::cretaceousMarsh);
	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> CRETACEOUS_SWAMP = register("cretaceous_swamp", CretaceousBiomes::cretaceousSwamp);
	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> CRETACEOUS_FEN = register("cretaceous_fen", CretaceousBiomes::cretaceousFen);
	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> CRETACEOUS_BOG = register("cretaceous_bog", CretaceousBiomes::cretaceousBog);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> CRETACEOUS_RIVER = register("cretaceous_river", CretaceousBiomes::cretaceousRiverBiome);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> COLD_CRETACEOUS_OCEAN = register("cold_cretaceous_ocean", CretaceousBiomes::cretaceousColdOceanBiome);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> CRETACEOUS_OCEAN = register("cretaceous_ocean", CretaceousBiomes::cretaceousOceanBiome);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> WARM_CRETACEOUS_OCEAN = register("warm_cretaceous_ocean", CretaceousBiomes::cretaceousWarmOceanBiome);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> CRETACEOUS_SHORE = register("cretaceous_shore", CretaceousBiomes::cretaceousShoreBiome);

	// Overworld
	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> ARAUCARIA_FOREST = register("araucaria_forest", OverworldBiomes::araucariaForest);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> CONIFER_FOREST = register("conifer_forest", OverworldBiomes::coniferForest);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> GINKGO_FOREST = register("ginkgo_forest", OverworldBiomes::ginkgoForest);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> REDWOODS_FOREST = register("redwoods_forest", OverworldBiomes::redwoodsForest);

	public static final Pair<RegistryObject<Biome>, ResourceKey<Biome>> VOLCANO = register("volcano", OverworldBiomes::volcano);

	public static Pair<RegistryObject<Biome>, ResourceKey<Biome>> register(String id, NonNullSupplier<Biome> biome) {
		REGISTRATE.addRawLang("biome.lostworlds." + id, Arrays.stream(id.toLowerCase(Locale.ROOT).split("_")).map(StringUtils::capitalize).collect(Collectors.joining(" ")));
		RegistryObject<Biome> reg = BIOMES.register(id, biome);
		ResourceKey<Biome> key = register(id);
		return Pair.of(reg, key);
	}

	public static Pair<RegistryObject<Biome>, ResourceKey<Biome>> register(String id, ModBiome biome) {
		Biome realBiome = biome.getBiome();
		REGISTRATE.addRawLang("biome.lostworlds." + id, Arrays.stream(id.toLowerCase(Locale.ROOT).split("_")).map(StringUtils::capitalize).collect(Collectors.joining(" ")));
		RegistryObject<Biome> reg = BIOMES.register(id, () -> realBiome);
		ResourceKey<Biome> key = register(id);
		return Pair.of(reg, key);
	}

	private static ResourceKey<Biome> register(String id) {
		return ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(LostWorldsUtils.ID, id));
	}

	// Registry
	public static void deferred(IEventBus bus) {
		BIOMES.register(bus);
	}
}
