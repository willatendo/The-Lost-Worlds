package lostworlds.server.biome;

import java.util.function.Supplier;

import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class ModSurfaceBuilderConfigs {
	public static final Supplier<SurfaceBuilderConfig> PERMIAN_SAND_CONFIG = () -> new SurfaceBuilderConfig(LostWorldsBlocks.PERMIAN_SAND.getDefaultState(), LostWorldsBlocks.PERMIAN_SAND.getDefaultState(), LostWorldsBlocks.PERMIAN_SAND.getDefaultState());
	public static final Supplier<SurfaceBuilderConfig> SAND_CONFIG = () -> new SurfaceBuilderConfig(Blocks.SAND.defaultBlockState(), Blocks.SAND.defaultBlockState(), Blocks.SAND.defaultBlockState());
	public static final Supplier<SurfaceBuilderConfig> STONE_CONFIG = () -> new SurfaceBuilderConfig(Blocks.STONE.defaultBlockState(), Blocks.STONE.defaultBlockState(), Blocks.STONE.defaultBlockState());
	public static final Supplier<SurfaceBuilderConfig> COBBLESTONE_CONFIG = () -> new SurfaceBuilderConfig(Blocks.COBBLESTONE.defaultBlockState(), Blocks.COBBLESTONE.defaultBlockState(), Blocks.COBBLESTONE.defaultBlockState());
	public static final Supplier<SurfaceBuilderConfig> ROCKY_SOIL_CONFIG = () -> new SurfaceBuilderConfig(LostWorldsBlocks.ROCKY_SOIL.getDefaultState(), LostWorldsBlocks.ROCKY_SOIL.getDefaultState(), LostWorldsBlocks.ROCKY_SOIL.getDefaultState());
	public static final Supplier<SurfaceBuilderConfig> RED_SAND_CONFIG = () -> new SurfaceBuilderConfig(Blocks.RED_SAND.defaultBlockState(), Blocks.RED_SAND.defaultBlockState(), Blocks.RED_SAND.defaultBlockState());
	public static final Supplier<SurfaceBuilderConfig> SILT_CONFIG = () -> new SurfaceBuilderConfig(LostWorldsBlocks.SILT.getDefaultState(), LostWorldsBlocks.SILT.getDefaultState(), LostWorldsBlocks.SILT.getDefaultState());
	public static final Supplier<SurfaceBuilderConfig> PERMIAN_STONE_CONFIG = () -> new SurfaceBuilderConfig(LostWorldsBlocks.PERMIAN_STONE.getDefaultState(), LostWorldsBlocks.PERMIAN_STONE.getDefaultState(), LostWorldsBlocks.PERMIAN_STONE.getDefaultState());
	public static final Supplier<SurfaceBuilderConfig> JURASSIC_STONE_CONFIG = () -> new SurfaceBuilderConfig(LostWorldsBlocks.JURASSIC_STONE.defaultBlockState(), LostWorldsBlocks.JURASSIC_STONE.defaultBlockState(), LostWorldsBlocks.JURASSIC_STONE.defaultBlockState());
	public static final Supplier<SurfaceBuilderConfig> JURASSIC_COBBLESTONE_CONFIG = () -> new SurfaceBuilderConfig(LostWorldsBlocks.JURASSIC_COBBLESTONE.defaultBlockState(), LostWorldsBlocks.JURASSIC_COBBLESTONE.defaultBlockState(), LostWorldsBlocks.JURASSIC_COBBLESTONE.defaultBlockState());
	public static final Supplier<SurfaceBuilderConfig> DRIED_SOIL_CONFIG = () -> new SurfaceBuilderConfig(LostWorldsBlocks.DRIED_SOIL.getDefaultState(), LostWorldsBlocks.DRIED_SOIL.getDefaultState(), LostWorldsBlocks.DRIED_SOIL.getDefaultState());
	public static final Supplier<SurfaceBuilderConfig> DRIED_SOIL_MUD_CONFIG = () -> new SurfaceBuilderConfig(LostWorldsBlocks.DRIED_SOIL.getDefaultState(), LostWorldsBlocks.DRIED_SOIL.getDefaultState(), LostWorldsBlocks.MUD.getDefaultState());
	public static final Supplier<SurfaceBuilderConfig> CRACKED_SOIL_CONFIG = () -> new SurfaceBuilderConfig(LostWorldsBlocks.CRACKED_SOIL.getDefaultState(), LostWorldsBlocks.CRACKED_SOIL.getDefaultState(), LostWorldsBlocks.CRACKED_SOIL.getDefaultState());
	public static final Supplier<SurfaceBuilderConfig> DIRT_CONFIG = () -> new SurfaceBuilderConfig(Blocks.DIRT.defaultBlockState(), Blocks.DIRT.defaultBlockState(), Blocks.DIRT.defaultBlockState());
	public static final Supplier<SurfaceBuilderConfig> MOSSY_SOIL_CONFIG = () -> new SurfaceBuilderConfig(LostWorldsBlocks.MOSSY_SOIL.getDefaultState(), Blocks.DIRT.defaultBlockState(), Blocks.DIRT.defaultBlockState());
	public static final Supplier<SurfaceBuilderConfig> MOSSY_SOIL_MUD_CONFIG = () -> new SurfaceBuilderConfig(LostWorldsBlocks.MOSSY_SOIL.getDefaultState(), Blocks.DIRT.defaultBlockState(), LostWorldsBlocks.MUD.getDefaultState());
	public static final Supplier<SurfaceBuilderConfig> COARSE_DIRT = () -> new SurfaceBuilderConfig(Blocks.COARSE_DIRT.defaultBlockState(), Blocks.DIRT.defaultBlockState(), Blocks.DIRT.defaultBlockState());
	public static final Supplier<SurfaceBuilderConfig> PODZOL_CONFIG = () -> new SurfaceBuilderConfig(Blocks.PODZOL.defaultBlockState(), Blocks.DIRT.defaultBlockState(), Blocks.DIRT.defaultBlockState());
	public static final Supplier<SurfaceBuilderConfig> VOLCANIC_ASH_CONFIG = () -> new SurfaceBuilderConfig(LostWorldsBlocks.VOLCANIC_ASH.getDefaultState(), LostWorldsBlocks.VOLCANIC_ASH.getDefaultState(), LostWorldsBlocks.VOLCANIC_ASH.getDefaultState());
	public static final Supplier<SurfaceBuilderConfig> MUD_CONFIG = () -> new SurfaceBuilderConfig(LostWorldsBlocks.MUD.getDefaultState(), LostWorldsBlocks.MUD.getDefaultState(), LostWorldsBlocks.MUD.getDefaultState());
	public static final Supplier<SurfaceBuilderConfig> MOSSY_SOIL_SILT_CONFIG = () -> new SurfaceBuilderConfig(LostWorldsBlocks.MOSSY_SOIL.getDefaultState(), Blocks.DIRT.defaultBlockState(), LostWorldsBlocks.SILT.getDefaultState());
	public static final Supplier<SurfaceBuilderConfig> MAGMA_CONFIG = () -> new SurfaceBuilderConfig(Blocks.MAGMA_BLOCK.defaultBlockState(), Blocks.MAGMA_BLOCK.defaultBlockState(), Blocks.MAGMA_BLOCK.defaultBlockState());
	public static final Supplier<SurfaceBuilderConfig> SNOW_CONFIG = () -> new SurfaceBuilderConfig(Blocks.SNOW_BLOCK.defaultBlockState(), Blocks.SNOW_BLOCK.defaultBlockState(), Blocks.SNOW_BLOCK.defaultBlockState());
	public static final Supplier<SurfaceBuilderConfig> TERRACOTTA_CONFIG = () -> new SurfaceBuilderConfig(Blocks.TERRACOTTA.defaultBlockState(), Blocks.TERRACOTTA.defaultBlockState(), Blocks.TERRACOTTA.defaultBlockState());
	public static final Supplier<SurfaceBuilderConfig> RED_TERRACOTTA_CONFIG = () -> new SurfaceBuilderConfig(Blocks.RED_TERRACOTTA.defaultBlockState(), Blocks.RED_TERRACOTTA.defaultBlockState(), Blocks.RED_TERRACOTTA.defaultBlockState());

}
