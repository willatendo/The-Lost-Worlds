package lostworlds.library.biome;

import lostworlds.content.server.init.BlockInit;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class ModSurfaceBuilders 
{
	public static final SurfaceBuilderConfig PERMIAN_SAND_CONFIG = new SurfaceBuilderConfig(BlockInit.PERMIAN_SAND.defaultBlockState(), BlockInit.PERMIAN_SAND.defaultBlockState(), BlockInit.PERMIAN_SAND.defaultBlockState());
	public static final SurfaceBuilderConfig SAND_CONFIG = new SurfaceBuilderConfig(Blocks.SAND.defaultBlockState(), Blocks.SAND.defaultBlockState(), Blocks.SAND.defaultBlockState());
	public static final SurfaceBuilderConfig ROCKY_SOIL_CONFIG = new SurfaceBuilderConfig(BlockInit.ROCKY_SOIL.defaultBlockState(), BlockInit.ROCKY_SOIL.defaultBlockState(), BlockInit.ROCKY_SOIL.defaultBlockState());
	public static final SurfaceBuilderConfig RED_SAND_CONFIG = new SurfaceBuilderConfig(Blocks.RED_SAND.defaultBlockState(), Blocks.RED_SAND.defaultBlockState(), Blocks.RED_SAND.defaultBlockState());
	public static final SurfaceBuilderConfig SILT_CONFIG = new SurfaceBuilderConfig(BlockInit.SILT.defaultBlockState(), BlockInit.SILT.defaultBlockState(), BlockInit.SILT.defaultBlockState());
	public static final SurfaceBuilderConfig PERMIAN_STONE_CONFIG = new SurfaceBuilderConfig(BlockInit.PERMIAN_STONE.defaultBlockState(), BlockInit.PERMIAN_STONE.defaultBlockState(), BlockInit.PERMIAN_STONE.defaultBlockState());
	public static final SurfaceBuilderConfig JURASSIC_STONE_CONFIG = new SurfaceBuilderConfig(BlockInit.JURASSIC_STONE.defaultBlockState(), BlockInit.JURASSIC_STONE.defaultBlockState(), BlockInit.JURASSIC_STONE.defaultBlockState());
	public static final SurfaceBuilderConfig JURASSIC_COBBLESTONE_CONFIG = new SurfaceBuilderConfig(BlockInit.JURASSIC_COBBLESTONE.defaultBlockState(), BlockInit.JURASSIC_COBBLESTONE.defaultBlockState(), BlockInit.JURASSIC_COBBLESTONE.defaultBlockState());
	public static final SurfaceBuilderConfig DRIED_SOIL_CONFIG = new SurfaceBuilderConfig(BlockInit.DRIED_SOIL.defaultBlockState(), BlockInit.DRIED_SOIL.defaultBlockState(), BlockInit.DRIED_SOIL.defaultBlockState());
	public static final SurfaceBuilderConfig DRIED_SOIL_MUD_CONFIG = new SurfaceBuilderConfig(BlockInit.DRIED_SOIL.defaultBlockState(), BlockInit.DRIED_SOIL.defaultBlockState(), BlockInit.MUD.defaultBlockState());
	public static final SurfaceBuilderConfig CRACKED_SOIL_CONFIG = new SurfaceBuilderConfig(BlockInit.CRACKED_SOIL.defaultBlockState(), BlockInit.CRACKED_SOIL.defaultBlockState(), BlockInit.CRACKED_SOIL.defaultBlockState());
	public static final SurfaceBuilderConfig DIRT_CONFIG = new SurfaceBuilderConfig(Blocks.DIRT.defaultBlockState(), Blocks.DIRT.defaultBlockState(), Blocks.DIRT.defaultBlockState());
	public static final SurfaceBuilderConfig MOSSY_SOIL_CONFIG = new SurfaceBuilderConfig(BlockInit.MOSSY_SOIL.defaultBlockState(), Blocks.DIRT.defaultBlockState(), Blocks.DIRT.defaultBlockState());
	public static final SurfaceBuilderConfig COARSE_DIRT = new SurfaceBuilderConfig(Blocks.COARSE_DIRT.defaultBlockState(), Blocks.DIRT.defaultBlockState(), Blocks.DIRT.defaultBlockState());
	public static final SurfaceBuilderConfig PODZOL_CONFIG = new SurfaceBuilderConfig(Blocks.PODZOL.defaultBlockState(), Blocks.DIRT.defaultBlockState(), Blocks.DIRT.defaultBlockState());
	public static final SurfaceBuilderConfig VOLCANIC_ASH_CONFIG = new SurfaceBuilderConfig(BlockInit.VOLCANIC_ASH.defaultBlockState(), BlockInit.VOLCANIC_ASH.defaultBlockState(), BlockInit.VOLCANIC_ASH.defaultBlockState());
	public static final SurfaceBuilderConfig MUD_CONFIG = new SurfaceBuilderConfig(BlockInit.MUD.defaultBlockState(), BlockInit.MUD.defaultBlockState(), BlockInit.MUD.defaultBlockState());
	public static final SurfaceBuilderConfig MOSSY_SOIL_SILT_CONFIG = new SurfaceBuilderConfig(BlockInit.MOSSY_SOIL.defaultBlockState(), Blocks.DIRT.defaultBlockState(), BlockInit.SILT.defaultBlockState());
	public static final SurfaceBuilderConfig MAGMA_CONFIG = new SurfaceBuilderConfig(Blocks.MAGMA_BLOCK.defaultBlockState(), Blocks.MAGMA_BLOCK.defaultBlockState(), Blocks.MAGMA_BLOCK.defaultBlockState());
	public static final SurfaceBuilderConfig SNOW_CONFIG = new SurfaceBuilderConfig(Blocks.SNOW_BLOCK.defaultBlockState(), Blocks.SNOW_BLOCK.defaultBlockState(), Blocks.SNOW_BLOCK.defaultBlockState());
	
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> PERMIAN_ASHY_MEDOWS_BUILDER = SurfaceBuilder.DEFAULT.configured(VOLCANIC_ASH_CONFIG);
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> PERMIAN_DESERT_BUILDER = SurfaceBuilder.DEFAULT.configured(PERMIAN_SAND_CONFIG);
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> PERMIAN_DRIED_PLAINS_SURFACE_BUILDER = SurfaceBuilder.DEFAULT.configured(DRIED_SOIL_CONFIG);
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> PERMIAN_MOUNTAINS_BUILDER = SurfaceBuilder.DEFAULT.configured(PERMIAN_STONE_CONFIG);
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> FOREST_BUILDER = SurfaceBuilder.DEFAULT.configured(MOSSY_SOIL_CONFIG);
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> PERMIAN_STONE_BUILDER = SurfaceBuilder.DEFAULT.configured(PERMIAN_STONE_CONFIG);
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> PERMIAN_OCEAN_BUILDER = SurfaceBuilder.DEFAULT.configured(PERMIAN_SAND_CONFIG);
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> PERMIAN_WARM_OCEAN_BUILDER = SurfaceBuilder.DEFAULT.configured(SILT_CONFIG);

	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> JURASSIC_ROCKY_SOIL_BUILDER = SurfaceBuilder.DEFAULT.configured(ROCKY_SOIL_CONFIG);
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> JURASSIC_PODZOL_BUILDER = SurfaceBuilder.DEFAULT.configured(PODZOL_CONFIG);
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> JURASSIC_STONE_BUILDER = SurfaceBuilder.DEFAULT.configured(JURASSIC_STONE_CONFIG);

	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> CRETACEOUS_PODZOL_BUILDER = SurfaceBuilder.DEFAULT.configured(PODZOL_CONFIG);
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> CRETACEOUS_SNOW_BUILDER = SurfaceBuilder.DEFAULT.configured(SNOW_CONFIG);
}
