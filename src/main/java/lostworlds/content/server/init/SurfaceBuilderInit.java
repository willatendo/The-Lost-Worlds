package lostworlds.content.server.init;

import lostworlds.content.ModRegistry;
import lostworlds.content.ModUtils;
import lostworlds.library.biome.surfacebuilders.JurassicConiferForestSurfaceBuilder;
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
	
	public static final SurfaceBuilder<?> PERMIAN_DRIED_PLAINS = ModRegistry.register("permian_dried_plains", NAKED_PERMIAN_DRIED_PLAINS);
	public static final SurfaceBuilder<?> PERMIAN_MOUNTAINS = ModRegistry.register("permian_mountains", NAKED_PERMIAN_MOUNTAINS);
	public static final SurfaceBuilder<?> PERMIAN_PLAINS = ModRegistry.register("permian_plains", NAKED_PERMIAN_PLAINS);
	public static final SurfaceBuilder<?> PERMIAN_OCEAN = ModRegistry.register("permian_ocean", NAKED_PERMIAN_OCEAN);
	public static final SurfaceBuilder<?> PERMIAN_MARSH = ModRegistry.register("permian_marsh", NAKED_PERMIAN_MARSH);

	public static final SurfaceBuilder<?> JURASSIC_CONIFER_FOREST = ModRegistry.register("jurassic_conifer_forest", NAKED_JURASSIC_CONIFER_FOREST);

	//Registry
	public static void init() { ModUtils.LOGGER.debug("Registering Mod Surface Builders"); }
}
