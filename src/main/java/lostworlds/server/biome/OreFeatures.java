package lostworlds.server.biome;

import static lostworlds.server.biome.ModConfiguredFeatures.register;
import static lostworlds.server.util.BlockGetter.getStateWhenCan;

import java.util.function.Supplier;

import lostworlds.client.LostWorldsConfig;
import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.ReplaceBlockConfig;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;

public class OreFeatures {
	// Overworld
	public static final Supplier<ConfiguredFeature<?, ?>> SILT_PATCH = () -> register("silt_patch", Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, LostWorldsBlocks.SILT.getDefaultState(), LostWorldsConfig.SERVER_CONFIG.siltVeinSize.get())).range(LostWorldsConfig.SERVER_CONFIG.siltRange.get()).squared().count(LostWorldsConfig.SERVER_CONFIG.siltCountPerChunk.get()));

	// Permian
	public static final Supplier<ConfiguredFeature<?, ?>> PERMIAN_MAGMA_ORE = () -> register("permian_magma_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.PERMIAN_STONE.get(), Blocks.MAGMA_BLOCK.defaultBlockState(), 33)).decorated(Placement.MAGMA.configured(NoPlacementConfig.INSTANCE)).squared().count(4));

	public static final Supplier<ConfiguredFeature<?, ?>> PERMIAN_COAL_ORE = () -> register("permian_coal_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.PERMIAN_STONE.get(), getStateWhenCan(LostWorldsBlocks.PERMIAN_COAL_ORE), 17)).range(128).squared().count(20));
	public static final Supplier<ConfiguredFeature<?, ?>> PERMIAN_IRON_ORE = () -> register("permian_iron_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.PERMIAN_STONE.get(), getStateWhenCan(LostWorldsBlocks.PERMIAN_IRON_ORE), 9)).range(64).squared().count(20));
	public static final Supplier<ConfiguredFeature<?, ?>> PERMIAN_GOLD_ORE = () -> register("permian_gold_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.PERMIAN_STONE.get(), getStateWhenCan(LostWorldsBlocks.PERMIAN_GOLD_ORE), 9)).range(32).squared().count(2));
	public static final Supplier<ConfiguredFeature<?, ?>> PERMIAN_REDSTONE_ORE = () -> register("permian_redstone_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.PERMIAN_STONE.get(), getStateWhenCan(LostWorldsBlocks.PERMIAN_REDSTONE_ORE), 8)).range(16).squared().count(8));
	public static final Supplier<ConfiguredFeature<?, ?>> PERMIAN_DIAMOND_ORE = () -> register("permian_diamond_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.PERMIAN_STONE.get(), getStateWhenCan(LostWorldsBlocks.PERMIAN_DIAMOND_ORE), 8)).range(16).squared());
	public static final Supplier<ConfiguredFeature<?, ?>> PERMIAN_LAPIS_ORE = () -> register("permian_lapis_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.PERMIAN_STONE.get(), getStateWhenCan(LostWorldsBlocks.PERMIAN_LAPIS_ORE), 7)).decorated(Placement.DEPTH_AVERAGE.configured(new DepthAverageConfig(16, 16))).squared());
	public static final Supplier<ConfiguredFeature<?, ?>> PERMIAN_EMERALD_ORE = () -> register("permian_emerald_ore", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(getStateWhenCan(LostWorldsBlocks.PERMIAN_STONE), getStateWhenCan(LostWorldsBlocks.PERMIAN_EMERALD_ORE))).decorated(Placement.EMERALD_ORE.configured(IPlacementConfig.NONE)));
	public static final Supplier<ConfiguredFeature<?, ?>> PERMIAN_COPPER_ORE = () -> register("permian_copper_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.PERMIAN_STONE.get(), getStateWhenCan(LostWorldsBlocks.PERMIAN_COPPER_ORE), LostWorldsConfig.SERVER_CONFIG.copperVeinSize.get())).range(LostWorldsConfig.SERVER_CONFIG.copperRange.get()).squared().count(LostWorldsConfig.SERVER_CONFIG.copperCountPerChunk.get()));

	public static final Supplier<ConfiguredFeature<?, ?>> PERMIAN_DIRT_ORE = () -> register("permian_dirt_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.PERMIAN_STONE.get(), Blocks.DIRT.defaultBlockState(), 33)).range(256).squared().count(10));
	public static final Supplier<ConfiguredFeature<?, ?>> PERMIAN_GRAVEL_ORE = () -> register("permian_gravel_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.PERMIAN_STONE.get(), Blocks.GRAVEL.defaultBlockState(), 33)).range(256).squared().count(8));

	public static final Supplier<ConfiguredFeature<?, ?>> PERMIAN_LATERLITE_ORE = () -> register("permian_laterlite_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.PERMIAN_STONE.get(), LostWorldsBlocks.LATERLITE.defaultBlockState(), 33)).range(256).squared().count(10));
	public static final Supplier<ConfiguredFeature<?, ?>> PERMIAN_RAW_MARBLE_ORE = () -> register("permian_raw_marble_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.PERMIAN_STONE.get(), LostWorldsBlocks.RAW_MARBLE.defaultBlockState(), 33)).range(256).squared().count(8));
	public static final Supplier<ConfiguredFeature<?, ?>> PERMIAN_LIMESTONE_ORE = () -> register("permian_limestone_ore", Feature.ORE.configured(new OreFeatureConfig(ModBlockFillerTypes.PERMIAN_STONE.get(), LostWorldsBlocks.LIMESTONE.defaultBlockState(), 33)).range(256).squared().count(8));

}
