package lostworlds.content.server.init;

import lostworlds.content.ModRegistry;
import lostworlds.content.ModUtils;
import lostworlds.library.biome.surfacebuilders.JurassicConiferForestSurfaceBuilder;
import lostworlds.library.biome.surfacebuilders.JurassicDesertSurfaceBuilder;
import lostworlds.library.biome.surfacebuilders.JurassicErrodedMountainsSurfaceBuilder;
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
	
	public static final SurfaceBuilder NAKED_JURASSIC_CONIFER_FOREST = new JurassicConiferForestSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder NAKED_JURASSIC_PLAINS = new JurassicPlainsSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder NAKED_JURASSIC_DESERT = new JurassicDesertSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder NAKED_JURASSIC_MOUNTAINS = new JurassicMountainsSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder NAKED_JURASSIC_ERRODED_MOUNTAINS = new JurassicErrodedMountainsSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder NAKED_JURASSIC_SWAMP = new JurassicSwampSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	
	public static final SurfaceBuilder<?> PERMIAN_DRIED_PLAINS = ModRegistry.register("permian_dried_plains", NAKED_PERMIAN_DRIED_PLAINS);
	public static final SurfaceBuilder<?> PERMIAN_MOUNTAINS = ModRegistry.register("permian_mountains", NAKED_PERMIAN_MOUNTAINS);
	public static final SurfaceBuilder<?> PERMIAN_PLAINS = ModRegistry.register("permian_plains", NAKED_PERMIAN_PLAINS);
	public static final SurfaceBuilder<?> PERMIAN_OCEAN = ModRegistry.register("permian_ocean", NAKED_PERMIAN_OCEAN);
	public static final SurfaceBuilder<?> PERMIAN_MARSH = ModRegistry.register("permian_marsh", NAKED_PERMIAN_MARSH);

	public static final SurfaceBuilder<?> JURASSIC_CONIFER_FOREST = ModRegistry.register("jurassic_conifer_forest", NAKED_JURASSIC_CONIFER_FOREST);
	public static final SurfaceBuilder<?> JURASSIC_PLAINS = ModRegistry.register("jurassic_plains", NAKED_JURASSIC_PLAINS);
	public static final SurfaceBuilder<?> JURASSIC_DESERT = ModRegistry.register("jurassic_desert", NAKED_JURASSIC_DESERT);
	public static final SurfaceBuilder<?> JURASSIC_MOUNTIANS = ModRegistry.register("jurassic_mountains", NAKED_JURASSIC_MOUNTAINS);
	public static final SurfaceBuilder<?> JURASSIC_ERRODED_MOUNTIANS = ModRegistry.register("jurassic_erroded_mountains", NAKED_JURASSIC_ERRODED_MOUNTAINS);
	public static final SurfaceBuilder<?> JURASSIC_SWAMP = ModRegistry.register("jurassic_swamp", NAKED_JURASSIC_SWAMP);

	//Registry
	public static void init() { ModUtils.LOGGER.debug("Registering Mod Surface Builders"); }
}
