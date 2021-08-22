package lostworlds.library.biome;

import lostworlds.library.util.ModUtils;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class BiomeKeys 
{
	public static final RegistryKey<Biome> PERMIAN_ASHY_MEDOWS = register("permian_ashy_medows");
	
	public static final RegistryKey<Biome> PERMIAN_CONIFER_FOREST = register("permian_conifer_forest");
	public static final RegistryKey<Biome> PERMIAN_CONIFER_FOREST_HILLS = register("permian_conifer_forest_hills");
	
	public static final RegistryKey<Biome> PERMIAN_DESERT = register("permian_desert");
	public static final RegistryKey<Biome> PERMIAN_DESERT_HILLS = register("permian_desert_hills");
	public static final RegistryKey<Biome> PERMIAN_DESERT_LAKE = register("permian_desert_lake");
	
	public static final RegistryKey<Biome> PERMIAN_DRIED_PLAINS = register("permian_dried_plains");
	public static final RegistryKey<Biome> PERMIAN_DRIED_PLAINS_HILLS = register("permian_dried_plains_hills");

	public static final RegistryKey<Biome> PERMIAN_FLOOD_BASALTS = register("permian_flood_basalts");
	
	public static final RegistryKey<Biome> PERMIAN_GINKGO_FOREST = register("permian_ginkgo_forest");
	public static final RegistryKey<Biome> PERMIAN_GINKGO_FOREST_HILLS = register("permian_ginkgo_forest_hills");

	public static final RegistryKey<Biome> PERMIAN_MOUNTAINS = register("permian_mountains");

	public static final RegistryKey<Biome> PERMIAN_OCEAN = register("permian_ocean");
	public static final RegistryKey<Biome> WARM_PERMIAN_OCEAN = register("warm_permian_ocean");
	public static final RegistryKey<Biome> DEEP_PERMIAN_OCEAN = register("deep_permian_ocean");
	public static final RegistryKey<Biome> WARM_DEEP_PERMIAN_OCEAN = register("warm_deep_permian_ocean");
	
	public static final RegistryKey<Biome> PERMIAN_PLAINS = register("permian_plains");
	public static final RegistryKey<Biome> PERMIAN_PLAINS_HILLS = register("permian_plains_hills");
	
	private static RegistryKey<Biome> register(String id)
	{
		return RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(ModUtils.ID, id));
	}
}
