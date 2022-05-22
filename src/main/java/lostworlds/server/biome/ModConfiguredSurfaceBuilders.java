package lostworlds.server.biome;

import net.minecraft.world.level.levelgen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;

public class ModConfiguredSurfaceBuilders {
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> JURASSIC_PODZOL_BUILDER = SurfaceBuilder.DEFAULT.configured(ModSurfaceBuilderConfigs.PODZOL_CONFIG.get());
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> CRETACEOUS_PODZOL_BUILDER = SurfaceBuilder.DEFAULT.configured(ModSurfaceBuilderConfigs.PODZOL_CONFIG.get());
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> CRETACEOUS_SNOW_BUILDER = SurfaceBuilder.DEFAULT.configured(ModSurfaceBuilderConfigs.SNOW_CONFIG.get());
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> CRETACEOUS_SHORE_BUILDER = SurfaceBuilder.DEFAULT.configured(ModSurfaceBuilderConfigs.SAND_CONFIG.get());
}
