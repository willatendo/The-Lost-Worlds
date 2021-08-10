package lostworlds.content.server.init;

import lostworlds.library.surfacebuilders.PermianAshyMedowsSurfaceBuilder;
import lostworlds.library.surfacebuilders.PermianDriedPlainsSurfaceBuilder;
import lostworlds.library.surfacebuilders.PermianMoutainsSurfaceBuilder;
import lostworlds.library.surfacebuilders.PermianPlainsSurfaceBuilder;
import lostworlds.library.util.ModRegistry;
import lostworlds.library.util.ModUtil;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

/*
 * Author: Willatendo
 * Date: July 10, 2021
 */

public class SurfaceBuilderInit 
{
	public static final SurfaceBuilder NAKED_PERMIAN_DRIED_PLAINS = new PermianDriedPlainsSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder NAKED_PERMIAN_ASHY_MEDOWS = new PermianAshyMedowsSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder NAKED_PERMIAN_MOUNTAINS = new PermianMoutainsSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder NAKED_PERMIAN_PLAINS = new PermianPlainsSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	
	public static final SurfaceBuilder<?> PERMIAN_DRIED_PLAINS = ModRegistry.register("permian_dried_plains", NAKED_PERMIAN_DRIED_PLAINS);
	public static final SurfaceBuilder<?> PERMIAN_ASHY_MEDOWS = ModRegistry.register("permian_ashy_medows", NAKED_PERMIAN_ASHY_MEDOWS);
	public static final SurfaceBuilder<?> PERMIAN_MOUNTAINS = ModRegistry.register("permian_mountains", NAKED_PERMIAN_MOUNTAINS);
	public static final SurfaceBuilder<?> PERMIAN_PLAINS = ModRegistry.register("permian_plains", NAKED_PERMIAN_PLAINS);
	
	//Registry
	public static void init() { ModUtil.LOGGER.debug("Registering Mod Surface Builders"); }
}
