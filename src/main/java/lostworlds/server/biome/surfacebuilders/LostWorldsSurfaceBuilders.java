package lostworlds.server.biome.surfacebuilders;

import lostworlds.server.LostWorldsRegistry;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class LostWorldsSurfaceBuilders {
	public static final SurfaceBuilder NAKED_PERMIAN_DRIED_PLAINS = new PermianDriedPlainsSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder NAKED_PERMIAN_MOUNTAINS = new PermianMoutainsSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder NAKED_PERMIAN_PLAINS = new PermianPlainsSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder NAKED_PERMIAN_OCEAN = new PermianOceanSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder NAKED_PERMIAN_MARSH = new PermianMarshSurfaceBuilder(SurfaceBuilderConfig.CODEC);

	public static final SurfaceBuilder NAKED_JURASSIC_FOREST = new JurassicForestSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder NAKED_JURASSIC_PLAINS = new JurassicPlainsSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder NAKED_JURASSIC_DESERT = new JurassicDesertSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder NAKED_JURASSIC_MOUNTAINS = new JurassicMountainsSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder NAKED_JURASSIC_ERRODED_MOUNTAINS = new JurassicErrodedMountainsSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder NAKED_JURASSIC_SWAMP = new JurassicSwampSurfaceBuilder(SurfaceBuilderConfig.CODEC);

	public static final SurfaceBuilder NAKED_CRETACEOUS_FOREST = new CretaceousForestSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder NAKED_CRETACEOUS_GAME_TRAIL = new CretaceousGameTrailSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder NAKED_CRETACEOUS_PLAINS = new CretaceousPlainsSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder NAKED_CRETACEOUS_MOUNTAINS = new CretaceousMountainsSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder NAKED_CRETACEOUS_ERRODED_MOUNTAINS = new CretaceousErrodedMountainsSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder NAKED_CRETACEOUS_DESERT = new CretaceousDesertSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder NAKED_CRETACEOUS_RED_DESERT = new CretaceousRedDesertSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder NAKED_CRETACEOUS_SWAMP = new CretaceousSwampSurfaceBuilder(SurfaceBuilderConfig.CODEC);

	public static final SurfaceBuilder<?> PERMIAN_DRIED_PLAINS = LostWorldsRegistry.register("permian_dried_plains_sb", NAKED_PERMIAN_DRIED_PLAINS);
	public static final SurfaceBuilder<?> PERMIAN_MOUNTAINS = LostWorldsRegistry.register("permian_mountains_sb", NAKED_PERMIAN_MOUNTAINS);
	public static final SurfaceBuilder<?> PERMIAN_PLAINS = LostWorldsRegistry.register("permian_plains_sb", NAKED_PERMIAN_PLAINS);
	public static final SurfaceBuilder<?> PERMIAN_OCEAN = LostWorldsRegistry.register("permian_ocean_sb", NAKED_PERMIAN_OCEAN);
	public static final SurfaceBuilder<?> PERMIAN_MARSH = LostWorldsRegistry.register("permian_marsh_sb", NAKED_PERMIAN_MARSH);

	public static final SurfaceBuilder<?> JURASSIC_FOREST = LostWorldsRegistry.register("jurassic_forest_sb", NAKED_JURASSIC_FOREST);
	public static final SurfaceBuilder<?> JURASSIC_PLAINS = LostWorldsRegistry.register("jurassic_plains_sb", NAKED_JURASSIC_PLAINS);
	public static final SurfaceBuilder<?> JURASSIC_DESERT = LostWorldsRegistry.register("jurassic_desert_sb", NAKED_JURASSIC_DESERT);
	public static final SurfaceBuilder<?> JURASSIC_MOUNTIANS = LostWorldsRegistry.register("jurassic_mountains_sb", NAKED_JURASSIC_MOUNTAINS);
	public static final SurfaceBuilder<?> JURASSIC_ERRODED_MOUNTIANS = LostWorldsRegistry.register("jurassic_erroded_mountains_sb", NAKED_JURASSIC_ERRODED_MOUNTAINS);
	public static final SurfaceBuilder<?> JURASSIC_SWAMP = LostWorldsRegistry.register("jurassic_swamp_sb", NAKED_JURASSIC_SWAMP);

	public static final SurfaceBuilder<?> CRETACEOUS_FOREST = LostWorldsRegistry.register("cretaceous_forest_sb", NAKED_CRETACEOUS_FOREST);
	public static final SurfaceBuilder<?> CRETACEOUS_GAME_TRAIL = LostWorldsRegistry.register("cretaceous_game_trail_sb", NAKED_CRETACEOUS_GAME_TRAIL);
	public static final SurfaceBuilder<?> CRETACEOUS_PLAINS = LostWorldsRegistry.register("cretaceous_plains_sb", NAKED_CRETACEOUS_PLAINS);
	public static final SurfaceBuilder<?> CRETACEOUS_MOUNTAINS = LostWorldsRegistry.register("cretaceous_mountains_sb", NAKED_CRETACEOUS_MOUNTAINS);
	public static final SurfaceBuilder<?> CRETACEOUS_ERRODED_MOUNTIANS = LostWorldsRegistry.register("cretaceous_erroded_mountains_sb", NAKED_CRETACEOUS_ERRODED_MOUNTAINS);
	public static final SurfaceBuilder<?> CRETACEOUS_DESERT = LostWorldsRegistry.register("cretaceous_desert_sb", NAKED_CRETACEOUS_DESERT);
	public static final SurfaceBuilder<?> CRETACEOUS_RED_DESERT = LostWorldsRegistry.register("cretaceous_red_desert_sb", NAKED_CRETACEOUS_RED_DESERT);
	public static final SurfaceBuilder<?> CRETACEOUS_SWAMP = LostWorldsRegistry.register("cretaceous_swamp_sb", NAKED_CRETACEOUS_SWAMP);

	// Registry
	public static void init() {
		LostWorldsUtils.LOGGER.debug("Registering Mod Surface Builders");
	}
}
