package lostworlds.server.biome.features.configured;

import static lostworlds.server.biome.features.configured.LostWorldsConfiguredFeatures.register;

import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.feature.LostWorldsFeatures;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraftforge.registries.RegistryObject;

public class PlantPatchFeatures {
	public static final RegistryObject<ConfiguredFeature<?, ?>> ARCHAEFRUTUS_PATCH = register("archaefrutus_patch", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(LostWorldsBlocks.ARCHAEFRUTUS.get()))));
	public static final RegistryObject<ConfiguredFeature<?, ?>> ALETHOPTERIS_PATCH = register("alethopteris_patch", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(LostWorldsBlocks.ALETHOPTERIS.get()))));
	public static final RegistryObject<ConfiguredFeature<?, ?>> BRAZILEA_PATCH = register("brazilea_patch", Feature.RANDOM_PATCH, new RandomPatchConfiguration(10, 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(LostWorldsBlocks.BRAZILEA.get())))));
	public static final RegistryObject<ConfiguredFeature<?, ?>> CALAMITES_SUCKOWII = register("calamites_suckwii", LostWorldsFeatures.CALAMITES_SUCKOWII, new ProbabilityFeatureConfiguration(0.0F));
	public static final RegistryObject<ConfiguredFeature<?, ?>> CYCAD_PATCH = register("cycad_patch", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(LostWorldsBlocks.CYCAD.get()))));
	public static final RegistryObject<ConfiguredFeature<?, ?>> DUISBERGIA_PATCH = register("duisbergia_patch", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(LostWorldsBlocks.DUISBERGIA.get()))));
	public static final RegistryObject<ConfiguredFeature<?, ?>> DICKSONIA_PATCH = register("dicksonia_patch", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(LostWorldsBlocks.DICKSONIA.get()))));
	public static final RegistryObject<ConfiguredFeature<?, ?>> EUDICOTS_PATCH = register("eudicots_patch", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(LostWorldsBlocks.EUDICOTS.get()))));
	public static final RegistryObject<ConfiguredFeature<?, ?>> FERN_PATCH = register("fern_patch", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.FERN))));
	public static final RegistryObject<ConfiguredFeature<?, ?>> MAGNOLIA_PATCH = register("magnolia_patch", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(LostWorldsBlocks.MAGNOLIA.get()))));
	public static final RegistryObject<ConfiguredFeature<?, ?>> OSMUNDA_PATCH = register("osmunda_patch", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(LostWorldsBlocks.OSMUNDA.get()))));
	public static final RegistryObject<ConfiguredFeature<?, ?>> WILLIAMSONIA_PATCH = register("williamsonia_patch", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(LostWorldsBlocks.WILLIAMSONIA.get()))));
	public static final RegistryObject<ConfiguredFeature<?, ?>> ZAMITES_PATCH = register("zamites_patch", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(LostWorldsBlocks.ZAMITES.get()))));

	public static final RegistryObject<ConfiguredFeature<?, ?>> PERMIAN_DESERT_SHRUB_PATCH = register("permian_desert_shrub_patch", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(LostWorldsBlocks.PERMIAN_DESERT_SHRUB.get()))));
	public static final RegistryObject<ConfiguredFeature<?, ?>> PERMIAN_DESERT_FERNS_PATCH = register("permian_desert_ferns_patch", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(LostWorldsBlocks.PERMIAN_DESERT_FERNS.get()))));

	public static void init() {
	}
}
