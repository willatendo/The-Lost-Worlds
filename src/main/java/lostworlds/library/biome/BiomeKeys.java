package lostworlds.library.biome;

import lostworlds.content.ModUtils;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class BiomeKeys 
{
	//Permian
	public static final RegistryKey<Biome> PERMIAN_ASHY_MEDOWS = register("permian_ashy_medows");
	
	public static final RegistryKey<Biome> PERMIAN_CONIFER_FOREST = register("permian_conifer_forest");
	public static final RegistryKey<Biome> PERMIAN_CONIFER_FOREST_HILLS = register("permian_conifer_forest_hills");
	
	public static final RegistryKey<Biome> PERMIAN_DESERT = register("permian_desert");
	public static final RegistryKey<Biome> PERMIAN_DESERT_HILLS = register("permian_desert_hills");
	
	public static final RegistryKey<Biome> PERMIAN_DRIED_PLAINS = register("permian_dried_plains");
	public static final RegistryKey<Biome> PERMIAN_DRIED_PLAINS_HILLS = register("permian_dried_plains_hills");

	public static final RegistryKey<Biome> PERMIAN_FLOOD_BASALTS = register("permian_flood_basalts");
	
	public static final RegistryKey<Biome> PERMIAN_GINKGO_FOREST = register("permian_ginkgo_forest");
	public static final RegistryKey<Biome> PERMIAN_GINKGO_FOREST_HILLS = register("permian_ginkgo_forest_hills");

	public static final RegistryKey<Biome> PERMIAN_MARSH = register("permian_marsh");

	public static final RegistryKey<Biome> PERMIAN_MOUNTAINS = register("permian_mountains");

	public static final RegistryKey<Biome> PERMIAN_RIVER = register("permian_river");

	public static final RegistryKey<Biome> PERMIAN_OCEAN = register("permian_ocean");
	public static final RegistryKey<Biome> WARM_PERMIAN_OCEAN = register("warm_permian_ocean");
	public static final RegistryKey<Biome> DEEP_PERMIAN_OCEAN = register("deep_permian_ocean");
	public static final RegistryKey<Biome> WARM_DEEP_PERMIAN_OCEAN = register("warm_deep_permian_ocean");
	
	public static final RegistryKey<Biome> PERMIAN_PLAINS = register("permian_plains");
	public static final RegistryKey<Biome> PERMIAN_PLAINS_HILLS = register("permian_plains_hills");
	
	public static final RegistryKey<Biome> PERMIAN_SHORE = register("permian_shore");
	
	//Jurassic
	public static final RegistryKey<Biome> JURASSIC_CONIFER_FOREST = register("jurassic_conifer_forest");
	
	public static final RegistryKey<Biome> JURASSIC_GINKGO_FOREST = register("jurassic_ginkgo_forest");

	public static final RegistryKey<Biome> JURASSIC_ARAUCARIA_FOREST = register("jurassic_araucaria_forest");

	public static final RegistryKey<Biome> JURASSIC_REDWOODS_FOREST = register("jurassic_redwoods_forest");

	public static final RegistryKey<Biome> JURASSIC_PLAINS = register("jurassic_plains");

	public static final RegistryKey<Biome> JURASSIC_MOUNTAINS = register("jurassic_mountains");
	public static final RegistryKey<Biome> JURASSIC_ERRODED_MOUNTAINS = register("jurassic_erroded_mountains");
	public static final RegistryKey<Biome> JURASSIC_VOLCANIC_RANGE = register("jurassic_volcanic_range");

	public static final RegistryKey<Biome> JURASSIC_DESERT = register("jurassic_desert");

	public static final RegistryKey<Biome> JURASSIC_MARSH = register("jurassic_marsh");
	public static final RegistryKey<Biome> JURASSIC_SWAMP = register("jurassic_swamp");
	public static final RegistryKey<Biome> JURASSIC_FEN = register("jurassic_fen");
	public static final RegistryKey<Biome> JURASSIC_BOG = register("jurassic_bog");

	//Overworld
	public static final RegistryKey<Biome> CONIFER_FOREST = register("conifer_forest");
	public static final RegistryKey<Biome> CONIFER_FOREST_HILLS = register("conifer_forest_hills");
	
	public static final RegistryKey<Biome> GINKGO_FOREST = register("ginkgo_forest");
	public static final RegistryKey<Biome> GINKGO_FOREST_HILLS = register("ginkgo_forest_hills");
	
	private static RegistryKey<Biome> register(String id)
	{
		return RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(ModUtils.ID, id));
	}
}
