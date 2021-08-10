package lostworlds.library.biome;

import lostworlds.library.util.ModUtil;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class BiomeKeys 
{
	public static final RegistryKey<Biome> PERMIAN_DESERT = register("permian_desert");
	public static final RegistryKey<Biome> PERMIAN_DESERT_HILLS = register("permian_desert_hills");
	public static final RegistryKey<Biome> PERMIAN_DESERT_LAKE = register("permian_desert_lake");
	public static final RegistryKey<Biome> PERMIAN_DRIED_PLAINS = register("permian_dried_plains");
	public static final RegistryKey<Biome> PERMIAN_DRIED_PLAINS_HILLS = register("permian_dried_plains_hills");
	public static final RegistryKey<Biome> PERMIAN_ASHY_MEDOWS = register("permian_ashy_medows");
	public static final RegistryKey<Biome> PERMIAN_FLOOD_BASALTS = register("permian_flood_basalts");
	public static final RegistryKey<Biome> PERMIAN_MOUNTAINS = register("permian_mountains");
	public static final RegistryKey<Biome> PERMIAN_CONIFER_FOREST = register("permian_conifer_forest");
	public static final RegistryKey<Biome> PERMIAN_CONIFER_FOREST_HILLS = register("permian_conifer_forest_hills");
	public static final RegistryKey<Biome> PERMIAN_GINKGO_FOREST = register("permian_ginkgo_forest");
	public static final RegistryKey<Biome> PERMIAN_GINKGO_FOREST_HILLS = register("permian_ginkgo_forest_hills");
	public static final RegistryKey<Biome> PERMIAN_PLAINS = register("permian_plains");
	public static final RegistryKey<Biome> PERMIAN_PLAINS_HILLS = register("permian_plains_hills");
	public static final RegistryKey<Biome> PERMIAN_OCEAN = register("permian_ocean");
	public static final RegistryKey<Biome> PERMIAN_DEEP_OCEAN = register("permian_deep_ocean");
	
	private static RegistryKey<Biome> register(String id)
	{
		return RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(ModUtil.ID, id));
	}
}
