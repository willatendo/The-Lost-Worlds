package lostworlds.server.biome.features.configured;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.feature.LostWorldsFeatures;
import lostworlds.server.feature.config.SuppliedBlockstateFeatureConfig;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.CountConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class LostWorldsConfiguredFeatures {
	public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, LostWorldsUtils.ID);

	// Misc Decoration
	public static final RegistryObject<ConfiguredFeature<?, ?>> PERMIAN_ROCK = register("permian_rock", LostWorldsFeatures.MOD_ROCK, new SuppliedBlockstateFeatureConfig(() -> LostWorldsBlocks.PERMIAN_COBBLESTONE.getDefaultState()));
	public static final RegistryObject<ConfiguredFeature<?, ?>> JURASSIC_ROCK = register("jurassic_rock", LostWorldsFeatures.MOD_ROCK, new SuppliedBlockstateFeatureConfig(() -> LostWorldsBlocks.JURASSIC_COBBLESTONE.getDefaultState()));
	public static final RegistryObject<ConfiguredFeature<?, ?>> CRETACEOUS_ROCK = register("cretaceous_rock", LostWorldsFeatures.MOD_ROCK, new SuppliedBlockstateFeatureConfig(() -> Blocks.COBBLESTONE.defaultBlockState()));
	public static final RegistryObject<ConfiguredFeature<?, ?>> ASH_LAYER = register("ash_layer", LostWorldsFeatures.ASH_LAYER);
	public static final RegistryObject<ConfiguredFeature<?, ?>> GEYSER_BLOCK = register("geyser_block", LostWorldsFeatures.GEYSER, new CountConfiguration(2));
	public static final RegistryObject<ConfiguredFeature<?, ?>> SPONGE_COLONEY = register("sponge_coloney", LostWorldsFeatures.SPONGE_COLONEY, new CountConfiguration(2));

	public static RegistryObject<ConfiguredFeature<?, ?>> register(String id, Feature<NoneFeatureConfiguration> feature) {
		return CONFIGURED_FEATURES.register(id, () -> new ConfiguredFeature<>(feature, FeatureConfiguration.NONE));
	}

	public static <FC extends FeatureConfiguration, F extends Feature<FC>> RegistryObject<ConfiguredFeature<?, ?>> register(String id, F feature, FC featureConfig) {
		return CONFIGURED_FEATURES.register(id, () -> new ConfiguredFeature<>(feature, featureConfig));
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
