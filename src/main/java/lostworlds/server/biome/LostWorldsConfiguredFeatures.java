package lostworlds.server.biome;

import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.feature.LostWorldsFeatures;
import lostworlds.server.feature.config.ModBlockstateFeatureConfig;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.CountConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class LostWorldsConfiguredFeatures {
	// Rocks
	public static final Holder<ConfiguredFeature<ModBlockstateFeatureConfig, ?>> PERMIAN_ROCK = register("permian_rock", LostWorldsFeatures.MOD_ROCK, new ModBlockstateFeatureConfig(() -> LostWorldsBlocks.PERMIAN_COBBLESTONE.getDefaultState()));
	public static final Holder<ConfiguredFeature<ModBlockstateFeatureConfig, ?>> JURASSIC_ROCK = register("jurassic_rock", LostWorldsFeatures.MOD_ROCK, new ModBlockstateFeatureConfig(() -> LostWorldsBlocks.JURASSIC_COBBLESTONE.getDefaultState()));
	public static final Holder<ConfiguredFeature<ModBlockstateFeatureConfig, ?>> CRETACEOUS_ROCK = register("cretaceous_rock", LostWorldsFeatures.MOD_ROCK, new ModBlockstateFeatureConfig(() -> Blocks.COBBLESTONE.defaultBlockState()));

	// Lakes
	// Misc Decoration
	public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> ASH_LAYER = register("ash_layer", LostWorldsFeatures.ASH_LAYER);
	public static final Holder<ConfiguredFeature<CountConfiguration, ?>> GEYSER_BLOCK = register("geyser_block", LostWorldsFeatures.GEYSER, new CountConfiguration(20));
	public static final Holder<ConfiguredFeature<CountConfiguration, ?>> SPONGE_COLONEY = register("sponge_coloney", LostWorldsFeatures.SPONGE_COLONEY, new CountConfiguration(20));

	public static Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> register(String id, Feature<NoneFeatureConfiguration> feature) {
		return FeatureUtils.register("lostworlds:" + id, feature);
	}

	public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> register(String id, F feature, FC featureConfig) {
		return FeatureUtils.register("lostworlds:" + id, feature, featureConfig);
	}

	public static void init() {
		OreFeatures.init();
		PlantPatchFeatures.init();
		DisksFeatures.init();
		TreeFeatures.init();
		WaterFeatures.init();
	}
}
