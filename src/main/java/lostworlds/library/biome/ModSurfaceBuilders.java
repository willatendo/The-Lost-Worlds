package lostworlds.library.biome;

import lostworlds.content.server.init.BlockInit;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

/*
 * Author: Willatendo
 * Date: July 10, 2021
 */


public class ModSurfaceBuilders 
{
	public static final SurfaceBuilderConfig PERMIAN_SAND_CONFIG = new SurfaceBuilderConfig(BlockInit.PERMIAN_SAND.defaultBlockState(), BlockInit.PERMIAN_SAND.defaultBlockState(), BlockInit.PERMIAN_SAND.defaultBlockState());
	public static final SurfaceBuilderConfig PERMIAN_STONE_CONFIG = new SurfaceBuilderConfig(BlockInit.PERMIAN_STONE.defaultBlockState(), BlockInit.PERMIAN_STONE.defaultBlockState(), BlockInit.PERMIAN_STONE.defaultBlockState());
	public static final SurfaceBuilderConfig DRIED_SOIL_CONFIG = new SurfaceBuilderConfig(BlockInit.DRIED_SOIL.defaultBlockState(), BlockInit.DRIED_SOIL.defaultBlockState(), BlockInit.DRIED_SOIL.defaultBlockState());
	public static final SurfaceBuilderConfig DRIED_SOIL_MUD_CONFIG = new SurfaceBuilderConfig(BlockInit.DRIED_SOIL.defaultBlockState(), BlockInit.DRIED_SOIL.defaultBlockState(), BlockInit.MUD.defaultBlockState());
	public static final SurfaceBuilderConfig CRACKED_SOIL_CONFIG = new SurfaceBuilderConfig(BlockInit.CRACKED_SOIL.defaultBlockState(), BlockInit.CRACKED_SOIL.defaultBlockState(), BlockInit.CRACKED_SOIL.defaultBlockState());
	public static final SurfaceBuilderConfig DIRT_CONFIG = new SurfaceBuilderConfig(Blocks.DIRT.defaultBlockState(), Blocks.DIRT.defaultBlockState(), Blocks.DIRT.defaultBlockState());
	public static final SurfaceBuilderConfig MOSSY_DIRT_CONFIG = new SurfaceBuilderConfig(BlockInit.MOSSY_DIRT.defaultBlockState(), Blocks.DIRT.defaultBlockState(), Blocks.DIRT.defaultBlockState());
	public static final SurfaceBuilderConfig VOLCANIC_ASH_CONFIG = new SurfaceBuilderConfig(BlockInit.VOLCANIC_ASH.defaultBlockState(), BlockInit.VOLCANIC_ASH.defaultBlockState(), BlockInit.VOLCANIC_ASH.defaultBlockState());
	
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> PERMIAN_DESERT_SURFACE_BUILDER = SurfaceBuilder.DEFAULT.configured(PERMIAN_SAND_CONFIG);
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> PERMIAN_DRIED_PLAINS_SURFACE_BUILDER = SurfaceBuilder.DEFAULT.configured(DRIED_SOIL_CONFIG);
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> PERMIAN_ASHY_MEDOWS_SURFACE_BUILDER = SurfaceBuilder.DEFAULT.configured(DIRT_CONFIG);
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> PERMIAN_MOUNTAINS_BUILDER = SurfaceBuilder.DEFAULT.configured(PERMIAN_STONE_CONFIG);
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> FOREST_BUILDER = SurfaceBuilder.DEFAULT.configured(MOSSY_DIRT_CONFIG);
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> PERMIAN_OCEAN_BUILDER = SurfaceBuilder.DEFAULT.configured(PERMIAN_SAND_CONFIG);
}
