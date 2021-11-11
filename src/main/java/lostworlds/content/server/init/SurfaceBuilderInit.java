package lostworlds.content.server.init;

import lostworlds.content.ModRegistry;
import lostworlds.content.ModUtils;
import lostworlds.library.biome.surfacebuilders.CretaceousDesertSurfaceBuilder;
import lostworlds.library.biome.surfacebuilders.CretaceousErrodedMountainsSurfaceBuilder;
import lostworlds.library.biome.surfacebuilders.CretaceousForestSurfaceBuilder;
import lostworlds.library.biome.surfacebuilders.CretaceousGameTrailSurfaceBuilder;
import lostworlds.library.biome.surfacebuilders.CretaceousMountainsSurfaceBuilder;
import lostworlds.library.biome.surfacebuilders.CretaceousPlainsSurfaceBuilder;
import lostworlds.library.biome.surfacebuilders.CretaceousRedDesertSurfaceBuilder;
import lostworlds.library.biome.surfacebuilders.CretaceousSwampSurfaceBuilder;
import lostworlds.library.biome.surfacebuilders.JurassicDesertSurfaceBuilder;
import lostworlds.library.biome.surfacebuilders.JurassicErrodedMountainsSurfaceBuilder;
import lostworlds.library.biome.surfacebuilders.JurassicForestSurfaceBuilder;
import lostworlds.library.biome.surfacebuilders.JurassicMountainsSurfaceBuilder;
import lostworlds.library.biome.surfacebuilders.JurassicPlainsSurfaceBuilder;
import lostworlds.library.biome.surfacebuilders.JurassicSwampSurfaceBuilder;
import lostworlds.library.biome.surfacebuilders.PermianDriedPlainsSurfaceBuilder;
import lostworlds.library.biome.surfacebuilders.PermianMarshSurfaceBuilder;
import lostworlds.library.biome.surfacebuilders.PermianMoutainsSurfaceBuilder;
import lostworlds.library.biome.surfacebuilders.PermianOceanSurfaceBuilder;
import lostworlds.library.biome.surfacebuilders.PermianPlainsSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class SurfaceBuilderInit 
{
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
	
	public static final SurfaceBuilder<?> PERMIAN_DRIED_PLAINS = ModRegistry.register("permian_dried_plains_sb", NAKED_PERMIAN_DRIED_PLAINS);
	public static final SurfaceBuilder<?> PERMIAN_MOUNTAINS = ModRegistry.register("permian_mountains_sb", NAKED_PERMIAN_MOUNTAINS);
	public static final SurfaceBuilder<?> PERMIAN_PLAINS = ModRegistry.register("permian_plains_sb", NAKED_PERMIAN_PLAINS);
	public static final SurfaceBuilder<?> PERMIAN_OCEAN = ModRegistry.register("permian_ocean_sb", NAKED_PERMIAN_OCEAN);
	public static final SurfaceBuilder<?> PERMIAN_MARSH = ModRegistry.register("permian_marsh_sb", NAKED_PERMIAN_MARSH);

	public static final SurfaceBuilder<?> JURASSIC_FOREST = ModRegistry.register("jurassic_forest_sb", NAKED_JURASSIC_FOREST);
	public static final SurfaceBuilder<?> JURASSIC_PLAINS = ModRegistry.register("jurassic_plains_sb", NAKED_JURASSIC_PLAINS);
	public static final SurfaceBuilder<?> JURASSIC_DESERT = ModRegistry.register("jurassic_desert_sb", NAKED_JURASSIC_DESERT);
	public static final SurfaceBuilder<?> JURASSIC_MOUNTIANS = ModRegistry.register("jurassic_mountains_sb", NAKED_JURASSIC_MOUNTAINS);
	public static final SurfaceBuilder<?> JURASSIC_ERRODED_MOUNTIANS = ModRegistry.register("jurassic_erroded_mountains_sb", NAKED_JURASSIC_ERRODED_MOUNTAINS);
	public static final SurfaceBuilder<?> JURASSIC_SWAMP = ModRegistry.register("jurassic_swamp_sb", NAKED_JURASSIC_SWAMP);
	
	public static final SurfaceBuilder<?> CRETACEOUS_FOREST = ModRegistry.register("cretaceous_forest_sb", NAKED_CRETACEOUS_FOREST);
	public static final SurfaceBuilder<?> CRETACEOUS_GAME_TRAIL = ModRegistry.register("cretaceous_game_trail_sb", NAKED_CRETACEOUS_GAME_TRAIL);
	public static final SurfaceBuilder<?> CRETACEOUS_PLAINS = ModRegistry.register("cretaceous_plains_sb", NAKED_CRETACEOUS_PLAINS);
	public static final SurfaceBuilder<?> CRETACEOUS_MOUNTAINS = ModRegistry.register("cretaceous_mountains_sb", NAKED_CRETACEOUS_MOUNTAINS);
	public static final SurfaceBuilder<?> CRETACEOUS_ERRODED_MOUNTIANS = ModRegistry.register("cretaceous_erroded_mountains_sb", NAKED_CRETACEOUS_ERRODED_MOUNTAINS);
	public static final SurfaceBuilder<?> CRETACEOUS_DESERT = ModRegistry.register("cretaceous_desert_sb", NAKED_CRETACEOUS_DESERT);
	public static final SurfaceBuilder<?> CRETACEOUS_RED_DESERT = ModRegistry.register("cretaceous_red_desert_sb", NAKED_CRETACEOUS_RED_DESERT);
	public static final SurfaceBuilder<?> CRETACEOUS_SWAMP = ModRegistry.register("cretaceous_swamp_sb", NAKED_CRETACEOUS_SWAMP);

	//Registry
	public static void init() { ModUtils.LOGGER.debug("Registering Mod Surface Builders"); }
}
