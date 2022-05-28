package lostworlds.server.biome.features.configured;

import static lostworlds.server.biome.features.configured.LostWorldsConfiguredFeatures.register;

import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.feature.LostWorldsFeatures;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class PlantPatchFeatures {
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> ARCHAEFRUTUS_PATCH = register("archaefrutus_patch", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(LostWorldsBlocks.ARCHAEFRUTUS.get()))));
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> ALETHOPTERIS_PATCH = register("alethopteris_patch", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(LostWorldsBlocks.ALETHOPTERIS.get()))));
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> BRAZILEA_PATCH = register("brazilea_patch", Feature.RANDOM_PATCH, new RandomPatchConfiguration(10, 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(LostWorldsBlocks.BRAZILEA.get())))));
	public static final Holder<ConfiguredFeature<ProbabilityFeatureConfiguration, ?>> CALAMITES_SUCKOWII = register("calamites_suckwii", LostWorldsFeatures.CALAMITES_SUCKOWII, new ProbabilityFeatureConfiguration(0.0F));
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> CYCAD_PATCH = register("cycad_patch", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(LostWorldsBlocks.CYCAD.get()))));
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> DUISBERGIA_PATCH = register("duisbergia_patch", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(LostWorldsBlocks.DUISBERGIA.get()))));
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> DICKSONIA_PATCH = register("dicksonia_patch", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(LostWorldsBlocks.DICKSONIA.get()))));
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> EUDICOTS_PATCH = register("eudicots_patch", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(LostWorldsBlocks.EUDICOTS.get()))));
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> FERN_PATCH = register("fern_patch", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.FERN))));
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> MAGNOLIA_PATCH = register("magnolia_patch", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(LostWorldsBlocks.MAGNOLIA.get()))));
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> OSMUNDA_PATCH = register("osmunda_patch", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(LostWorldsBlocks.OSMUNDA.get()))));
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> WILLIAMSONIA_PATCH = register("williamsonia_patch", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(LostWorldsBlocks.WILLIAMSONIA.get()))));
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> ZAMITES_PATCH = register("zamites_patch", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(LostWorldsBlocks.ZAMITES.get()))));

	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PERMIAN_DESERT_SHRUB_PATCH = register("permian_desert_shrub_patch", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(LostWorldsBlocks.PERMIAN_DESERT_SHRUB.get()))));
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PERMIAN_DESERT_FERNS_PATCH = register("permian_desert_ferns_patch", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(LostWorldsBlocks.PERMIAN_DESERT_FERNS.get()))));

	public static void init() {
	}
}
