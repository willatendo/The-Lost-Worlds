package lostworlds.server.biome;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class LostWorldsBiomeKeys {
	// Permian
	public static final ResourceKey<Biome> PERMIAN_CONIFER_FOREST = register("permian_conifer_forest");

	public static final ResourceKey<Biome> PERMIAN_GINKGO_FOREST = register("permian_ginkgo_forest");

	public static final ResourceKey<Biome> PERMIAN_PLAINS = register("permian_plains");

	public static final ResourceKey<Biome> PERMIAN_DRIED_PLAINS = register("permian_dried_plains");

	public static final ResourceKey<Biome> PERMIAN_DESERT = register("permian_desert");

	public static final ResourceKey<Biome> PERMIAN_FLOOD_BASALTS = register("permian_flood_basalts");
	public static final ResourceKey<Biome> PERMIAN_ASHY_MEDOWS = register("permian_ashy_medows");

	public static final ResourceKey<Biome> PERMIAN_WINDSWEPT_HILLS = register("permian_windswept_hills");

	public static final ResourceKey<Biome> PERMIAN_MARSH = register("permian_marsh");

	public static final ResourceKey<Biome> PERMIAN_RIVER = register("permian_river");

	public static final ResourceKey<Biome> PERMIAN_OCEAN = register("permian_ocean");

	public static final ResourceKey<Biome> WARM_PERMIAN_OCEAN = register("warm_permian_ocean");

	public static final ResourceKey<Biome> PERMIAN_SHORE = register("permian_shore");

	// Jurassic
	public static final ResourceKey<Biome> JURASSIC_ARAUCARIA_FOREST = register("jurassic_araucaria_forest");

	public static final ResourceKey<Biome> JURASSIC_CONIFER_FOREST = register("jurassic_conifer_forest");

	public static final ResourceKey<Biome> JURASSIC_GINKGO_FOREST = register("jurassic_ginkgo_forest");

	public static final ResourceKey<Biome> JURASSIC_REDWOODS_FOREST = register("jurassic_redwoods_forest");

	public static final ResourceKey<Biome> JURASSIC_PLAINS = register("jurassic_plains");

	public static final ResourceKey<Biome> JURASSIC_WINDSWEPT_HILLS = register("jurassic_windswept_hills");
	public static final ResourceKey<Biome> JURASSIC_ERRODED_WINDSWEPT_HILLS = register("jurassic_erroded_windswept_hills");
	public static final ResourceKey<Biome> JURASSIC_VOLCANIC_RANGE = register("jurassic_volcanic_range");

	public static final ResourceKey<Biome> JURASSIC_DESERT = register("jurassic_desert");

	public static final ResourceKey<Biome> JURASSIC_MARSH = register("jurassic_marsh");
	public static final ResourceKey<Biome> JURASSIC_SWAMP = register("jurassic_swamp");
	public static final ResourceKey<Biome> JURASSIC_FEN = register("jurassic_fen");
	public static final ResourceKey<Biome> JURASSIC_BOG = register("jurassic_bog");

	public static final ResourceKey<Biome> JURASSIC_RIVER = register("jurassic_river");

	public static final ResourceKey<Biome> JURASSIC_OCEAN = register("jurassic_ocean");

	public static final ResourceKey<Biome> WARM_JURASSIC_OCEAN = register("warm_jurassic_ocean");

	public static final ResourceKey<Biome> JURASSIC_SHORE = register("jurassic_shore");

	// Creataceous
	public static final ResourceKey<Biome> CRETACEOUS_ARAUCARIA_FOREST = register("cretaceous_araucaria_forest");

	public static final ResourceKey<Biome> CRETACEOUS_CONIFER_FOREST = register("cretaceous_conifer_forest");

	public static final ResourceKey<Biome> CRETACEOUS_GINKGO_FOREST = register("cretaceous_ginkgo_forest");

	public static final ResourceKey<Biome> CRETACEOUS_REDWOODS_FOREST = register("cretaceous_redwoods_forest");

	public static final ResourceKey<Biome> CRETACEOUS_GAME_TRAIL = register("cretaceous_game_trail");

	public static final ResourceKey<Biome> CRETACEOUS_MEDOW = register("cretaceous_medow");

	public static final ResourceKey<Biome> CRETACEOUS_PLAINS = register("cretaceous_plains");

	public static final ResourceKey<Biome> CRETACEOUS_FLOOD_BASALTS = register("cretaceous_flood_basalts");

	public static final ResourceKey<Biome> CRETACEOUS_ARCTIC = register("cretaceous_arctic");

	public static final ResourceKey<Biome> CRETACEOUS_ARCTIC_SPIRES = register("cretaceous_arctic_spires");

	public static final ResourceKey<Biome> CRETACEOUS_FROZEN_FOREST = register("cretaceous_frozen_forest");

	public static final ResourceKey<Biome> CRETACEOUS_WINDSWEPT_HILLS = register("cretaceous_windswept_hills");
	public static final ResourceKey<Biome> CRETACEOUS_ERRODED_WINDSWEPT_HILLS = register("cretaceous_erroded_windswept_hills");

	public static final ResourceKey<Biome> CRETACEOUS_DESERT = register("cretaceous_desert");

	public static final ResourceKey<Biome> CRETACEOUS_RED_DESERT = register("cretaceous_red_desert");

	public static final ResourceKey<Biome> CRETACEOUS_MARSH = register("cretaceous_marsh");
	public static final ResourceKey<Biome> CRETACEOUS_SWAMP = register("cretaceous_swamp");
	public static final ResourceKey<Biome> CRETACEOUS_FEN = register("cretaceous_fen");
	public static final ResourceKey<Biome> CRETACEOUS_BOG = register("cretaceous_bog");

	public static final ResourceKey<Biome> CRETACEOUS_RIVER = register("cretaceous_river");

	public static final ResourceKey<Biome> COLD_CRETACEOUS_OCEAN = register("cold_cretaceous_ocean");

	public static final ResourceKey<Biome> CRETACEOUS_OCEAN = register("cretaceous_ocean");

	public static final ResourceKey<Biome> WARM_CRETACEOUS_OCEAN = register("warm_cretaceous_ocean");

	public static final ResourceKey<Biome> CRETACEOUS_SHORE = register("cretaceous_shore");

	// Overworld
	public static final ResourceKey<Biome> ARAUCARIA_FOREST = register("araucaria_forest");

	public static final ResourceKey<Biome> CONIFER_FOREST = register("conifer_forest");

	public static final ResourceKey<Biome> GINKGO_FOREST = register("ginkgo_forest");

	public static final ResourceKey<Biome> REDWOODS_FOREST = register("redwoods_forest");

	public static final ResourceKey<Biome> VOLCANO = register("volcano");

	private static ResourceKey<Biome> register(String id) {
		return ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(id));
	}
}
