package lostworlds.server.biome;

import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class ModConfiguredSurfaceBuilders {
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> PERMIAN_ASHY_MEDOWS_BUILDER = SurfaceBuilder.DEFAULT.configured(ModSurfaceBuilderConfigs.VOLCANIC_ASH_CONFIG.get());
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> PERMIAN_DESERT_BUILDER = SurfaceBuilder.DEFAULT.configured(ModSurfaceBuilderConfigs.PERMIAN_SAND_CONFIG.get());
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> PERMIAN_MOUNTAINS_BUILDER = SurfaceBuilder.DEFAULT.configured(ModSurfaceBuilderConfigs.PERMIAN_STONE_CONFIG.get());
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> PERMIAN_STONE_BUILDER = SurfaceBuilder.DEFAULT.configured(ModSurfaceBuilderConfigs.PERMIAN_STONE_CONFIG.get());
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> PERMIAN_OCEAN_BUILDER = SurfaceBuilder.DEFAULT.configured(ModSurfaceBuilderConfigs.PERMIAN_SAND_CONFIG.get());

	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> JURASSIC_ROCKY_SOIL_BUILDER = SurfaceBuilder.DEFAULT.configured(ModSurfaceBuilderConfigs.ROCKY_SOIL_CONFIG.get());
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> JURASSIC_PODZOL_BUILDER = SurfaceBuilder.DEFAULT.configured(ModSurfaceBuilderConfigs.PODZOL_CONFIG.get());
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> JURASSIC_STONE_BUILDER = SurfaceBuilder.DEFAULT.configured(ModSurfaceBuilderConfigs.JURASSIC_STONE_CONFIG.get());

	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> CRETACEOUS_ROCKY_SOIL_BUILDER = SurfaceBuilder.DEFAULT.configured(ModSurfaceBuilderConfigs.ROCKY_SOIL_CONFIG.get());
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> CRETACEOUS_PODZOL_BUILDER = SurfaceBuilder.DEFAULT.configured(ModSurfaceBuilderConfigs.PODZOL_CONFIG.get());
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> CRETACEOUS_SNOW_BUILDER = SurfaceBuilder.DEFAULT.configured(ModSurfaceBuilderConfigs.SNOW_CONFIG.get());
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> CRETACEOUS_SHORE_BUILDER = SurfaceBuilder.DEFAULT.configured(ModSurfaceBuilderConfigs.SAND_CONFIG.get());
}
