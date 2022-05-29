package lostworlds.server.biome.features.configured;

import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.feature.LostWorldsFeatures;
import lostworlds.server.feature.config.SuppliedBlockstateFeatureConfig;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.CountConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class LostWorldsConfiguredFeatures {
	// Misc Decoration
	public static final Holder<ConfiguredFeature<SuppliedBlockstateFeatureConfig, ?>> PERMIAN_ROCK = register("permian_rock", LostWorldsFeatures.MOD_ROCK, new SuppliedBlockstateFeatureConfig(() -> LostWorldsBlocks.PERMIAN_COBBLESTONE.getDefaultState()));
	public static final Holder<ConfiguredFeature<SuppliedBlockstateFeatureConfig, ?>> JURASSIC_ROCK = register("jurassic_rock", LostWorldsFeatures.MOD_ROCK, new SuppliedBlockstateFeatureConfig(() -> LostWorldsBlocks.JURASSIC_COBBLESTONE.getDefaultState()));
	public static final Holder<ConfiguredFeature<SuppliedBlockstateFeatureConfig, ?>> CRETACEOUS_ROCK = register("cretaceous_rock", LostWorldsFeatures.MOD_ROCK, new SuppliedBlockstateFeatureConfig(() -> Blocks.COBBLESTONE.defaultBlockState()));
	public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> ASH_LAYER = register("ash_layer", LostWorldsFeatures.ASH_LAYER);
	public static final Holder<ConfiguredFeature<CountConfiguration, ?>> GEYSER_BLOCK = register("geyser_block", LostWorldsFeatures.GEYSER, new CountConfiguration(2));
	public static final Holder<ConfiguredFeature<CountConfiguration, ?>> SPONGE_COLONEY = register("sponge_coloney", LostWorldsFeatures.SPONGE_COLONEY, new CountConfiguration(2));

	public static Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> register(String id, Feature<NoneFeatureConfiguration> feature) {
		return FeatureUtils.register("lostworlds:" + id, feature);
	}

	public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> register(String id, F feature, FC featureConfig) {
		return FeatureUtils.register("lostworlds:" + id, feature, featureConfig);
	}

	public static void init() {
		DisksFeatures.init();
		OreFeatures.init();
		PlantPatchFeatures.init();
		TreeFeatures.init();
		TreePatchFeatures.init();
		WaterFeatures.init();
	}
}
