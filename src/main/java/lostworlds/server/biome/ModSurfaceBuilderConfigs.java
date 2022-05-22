package lostworlds.server.biome;

import java.util.function.Supplier;

import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;

public class ModSurfaceBuilderConfigs {
	public static final Supplier<SurfaceBuilderBaseConfiguration> PERMIAN_SAND_CONFIG = () -> new SurfaceBuilderBaseConfiguration(LostWorldsBlocks.PERMIAN_SAND.getDefaultState(), LostWorldsBlocks.PERMIAN_SAND.getDefaultState(), LostWorldsBlocks.PERMIAN_SAND.getDefaultState());
	public static final Supplier<SurfaceBuilderBaseConfiguration> SAND_CONFIG = () -> new SurfaceBuilderBaseConfiguration(Blocks.SAND.defaultBlockState(), Blocks.SAND.defaultBlockState(), Blocks.SAND.defaultBlockState());
	public static final Supplier<SurfaceBuilderBaseConfiguration> STONE_CONFIG = () -> new SurfaceBuilderBaseConfiguration(Blocks.STONE.defaultBlockState(), Blocks.STONE.defaultBlockState(), Blocks.STONE.defaultBlockState());
	public static final Supplier<SurfaceBuilderBaseConfiguration> COBBLESTONE_CONFIG = () -> new SurfaceBuilderBaseConfiguration(Blocks.COBBLESTONE.defaultBlockState(), Blocks.COBBLESTONE.defaultBlockState(), Blocks.COBBLESTONE.defaultBlockState());
	public static final Supplier<SurfaceBuilderBaseConfiguration> ROCKY_SOIL_CONFIG = () -> new SurfaceBuilderBaseConfiguration(LostWorldsBlocks.ROCKY_SOIL.getDefaultState(), LostWorldsBlocks.ROCKY_SOIL.getDefaultState(), LostWorldsBlocks.ROCKY_SOIL.getDefaultState());
	public static final Supplier<SurfaceBuilderBaseConfiguration> RED_SAND_CONFIG = () -> new SurfaceBuilderBaseConfiguration(Blocks.RED_SAND.defaultBlockState(), Blocks.RED_SAND.defaultBlockState(), Blocks.RED_SAND.defaultBlockState());
	public static final Supplier<SurfaceBuilderBaseConfiguration> SILT_CONFIG = () -> new SurfaceBuilderBaseConfiguration(LostWorldsBlocks.SILT.getDefaultState(), LostWorldsBlocks.SILT.getDefaultState(), LostWorldsBlocks.SILT.getDefaultState());
	public static final Supplier<SurfaceBuilderBaseConfiguration> PERMIAN_STONE_CONFIG = () -> new SurfaceBuilderBaseConfiguration(LostWorldsBlocks.PERMIAN_STONE.getDefaultState(), LostWorldsBlocks.PERMIAN_STONE.getDefaultState(), LostWorldsBlocks.PERMIAN_STONE.getDefaultState());
	public static final Supplier<SurfaceBuilderBaseConfiguration> JURASSIC_STONE_CONFIG = () -> new SurfaceBuilderBaseConfiguration(LostWorldsBlocks.JURASSIC_STONE.getDefaultState(), LostWorldsBlocks.JURASSIC_STONE.getDefaultState(), LostWorldsBlocks.JURASSIC_STONE.getDefaultState());
	public static final Supplier<SurfaceBuilderBaseConfiguration> JURASSIC_COBBLESTONE_CONFIG = () -> new SurfaceBuilderBaseConfiguration(LostWorldsBlocks.JURASSIC_COBBLESTONE.getDefaultState(), LostWorldsBlocks.JURASSIC_COBBLESTONE.getDefaultState(), LostWorldsBlocks.JURASSIC_COBBLESTONE.getDefaultState());
	public static final Supplier<SurfaceBuilderBaseConfiguration> DRIED_SOIL_CONFIG = () -> new SurfaceBuilderBaseConfiguration(LostWorldsBlocks.DRIED_SOIL.getDefaultState(), LostWorldsBlocks.DRIED_SOIL.getDefaultState(), LostWorldsBlocks.DRIED_SOIL.getDefaultState());
	public static final Supplier<SurfaceBuilderBaseConfiguration> DRIED_SOIL_MUD_CONFIG = () -> new SurfaceBuilderBaseConfiguration(LostWorldsBlocks.DRIED_SOIL.getDefaultState(), LostWorldsBlocks.DRIED_SOIL.getDefaultState(), LostWorldsBlocks.MUD.getDefaultState());
	public static final Supplier<SurfaceBuilderBaseConfiguration> CRACKED_SOIL_CONFIG = () -> new SurfaceBuilderBaseConfiguration(LostWorldsBlocks.CRACKED_SOIL.getDefaultState(), LostWorldsBlocks.CRACKED_SOIL.getDefaultState(), LostWorldsBlocks.CRACKED_SOIL.getDefaultState());
	public static final Supplier<SurfaceBuilderBaseConfiguration> DIRT_CONFIG = () -> new SurfaceBuilderBaseConfiguration(Blocks.DIRT.defaultBlockState(), Blocks.DIRT.defaultBlockState(), Blocks.DIRT.defaultBlockState());
	public static final Supplier<SurfaceBuilderBaseConfiguration> MOSSY_SOIL_CONFIG = () -> new SurfaceBuilderBaseConfiguration(LostWorldsBlocks.MOSSY_SOIL.getDefaultState(), Blocks.DIRT.defaultBlockState(), Blocks.DIRT.defaultBlockState());
	public static final Supplier<SurfaceBuilderBaseConfiguration> MOSSY_SOIL_MUD_CONFIG = () -> new SurfaceBuilderBaseConfiguration(LostWorldsBlocks.MOSSY_SOIL.getDefaultState(), Blocks.DIRT.defaultBlockState(), LostWorldsBlocks.MUD.getDefaultState());
	public static final Supplier<SurfaceBuilderBaseConfiguration> COARSE_DIRT = () -> new SurfaceBuilderBaseConfiguration(Blocks.COARSE_DIRT.defaultBlockState(), Blocks.DIRT.defaultBlockState(), Blocks.DIRT.defaultBlockState());
	public static final Supplier<SurfaceBuilderBaseConfiguration> PODZOL_CONFIG = () -> new SurfaceBuilderBaseConfiguration(Blocks.PODZOL.defaultBlockState(), Blocks.DIRT.defaultBlockState(), Blocks.DIRT.defaultBlockState());
	public static final Supplier<SurfaceBuilderBaseConfiguration> VOLCANIC_ASH_CONFIG = () -> new SurfaceBuilderBaseConfiguration(LostWorldsBlocks.VOLCANIC_ASH.getDefaultState(), LostWorldsBlocks.VOLCANIC_ASH.getDefaultState(), LostWorldsBlocks.VOLCANIC_ASH.getDefaultState());
	public static final Supplier<SurfaceBuilderBaseConfiguration> MUD_CONFIG = () -> new SurfaceBuilderBaseConfiguration(LostWorldsBlocks.MUD.getDefaultState(), LostWorldsBlocks.MUD.getDefaultState(), LostWorldsBlocks.MUD.getDefaultState());
	public static final Supplier<SurfaceBuilderBaseConfiguration> MOSSY_SOIL_SILT_CONFIG = () -> new SurfaceBuilderBaseConfiguration(LostWorldsBlocks.MOSSY_SOIL.getDefaultState(), Blocks.DIRT.defaultBlockState(), LostWorldsBlocks.SILT.getDefaultState());
	public static final Supplier<SurfaceBuilderBaseConfiguration> MAGMA_CONFIG = () -> new SurfaceBuilderBaseConfiguration(Blocks.MAGMA_BLOCK.defaultBlockState(), Blocks.MAGMA_BLOCK.defaultBlockState(), Blocks.MAGMA_BLOCK.defaultBlockState());
	public static final Supplier<SurfaceBuilderBaseConfiguration> SNOW_CONFIG = () -> new SurfaceBuilderBaseConfiguration(Blocks.SNOW_BLOCK.defaultBlockState(), Blocks.SNOW_BLOCK.defaultBlockState(), Blocks.SNOW_BLOCK.defaultBlockState());
	public static final Supplier<SurfaceBuilderBaseConfiguration> TERRACOTTA_CONFIG = () -> new SurfaceBuilderBaseConfiguration(Blocks.TERRACOTTA.defaultBlockState(), Blocks.TERRACOTTA.defaultBlockState(), Blocks.TERRACOTTA.defaultBlockState());
	public static final Supplier<SurfaceBuilderBaseConfiguration> RED_TERRACOTTA_CONFIG = () -> new SurfaceBuilderBaseConfiguration(Blocks.RED_TERRACOTTA.defaultBlockState(), Blocks.RED_TERRACOTTA.defaultBlockState(), Blocks.RED_TERRACOTTA.defaultBlockState());

}
