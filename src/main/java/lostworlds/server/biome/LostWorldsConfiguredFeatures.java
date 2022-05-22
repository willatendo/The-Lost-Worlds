package lostworlds.server.biome;

import com.google.common.collect.ImmutableList;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.feature.LostWorldsFeatures;
import lostworlds.server.feature.config.ModBlockstateFeatureConfig;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.CountConfiguration;
import net.minecraft.data.worldgen.Features;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleRandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraft.world.level.levelgen.placement.NoiseCountFactorDecoratorConfiguration;

public class LostWorldsConfiguredFeatures {
	// Rocks
	public static final ConfiguredFeature<?, ?> PERMIAN_ROCK = register("permian_rock", LostWorldsFeatures.MOD_ROCK.configured(new ModBlockstateFeatureConfig(() -> LostWorldsBlocks.PERMIAN_COBBLESTONE.getDefaultState())).decorated(Features.Decorators.HEIGHTMAP_SQUARE).countRandom(1));
	public static final ConfiguredFeature<?, ?> JURASSIC_ROCK = register("jurassic_rock", LostWorldsFeatures.MOD_ROCK.configured(new ModBlockstateFeatureConfig(() -> LostWorldsBlocks.JURASSIC_COBBLESTONE.getDefaultState())).decorated(Features.Decorators.HEIGHTMAP_SQUARE).countRandom(1));
	public static final ConfiguredFeature<?, ?> CRETACEOUS_ROCK = register("cretaceous_rock", LostWorldsFeatures.MOD_ROCK.configured(new ModBlockstateFeatureConfig(() -> Blocks.COBBLESTONE.defaultBlockState())).decorated(Features.Decorators.HEIGHTMAP_SQUARE).countRandom(1));

	// Lakes
	// Misc Decoration
	public static final ConfiguredFeature<?, ?> ASH_LAYER = register("ash_layer", LostWorldsFeatures.ASH_LAYER.configured(FeatureConfiguration.NONE));
	public static final ConfiguredFeature<?, ?> MOD_CORAL_REEF = register("mod_coral_reef", Feature.SIMPLE_RANDOM_SELECTOR.configured(new SimpleRandomFeatureConfiguration(ImmutableList.of(() -> {
		return Feature.CORAL_TREE.configured(FeatureConfiguration.NONE);
	}, () -> {
		return Feature.CORAL_CLAW.configured(FeatureConfiguration.NONE);
	}, () -> {
		return Feature.CORAL_MUSHROOM.configured(FeatureConfiguration.NONE);
	}))).decorated(Features.Decorators.TOP_SOLID_HEIGHTMAP).squared().decorated(FeatureDecorator.COUNT_NOISE_BIASED.configured(new NoiseCountFactorDecoratorConfiguration(20, 400.0D, 0.0D))));
	public static final ConfiguredFeature<?, ?> GEYSER_BLOCK = register("geyser_block", LostWorldsFeatures.GEYSER.configured(new CountConfiguration(20)).decorated(Features.Decorators.TOP_SOLID_HEIGHTMAP_SQUARE).chance(16));
	public static final ConfiguredFeature<?, ?> SPONGE_COLONEY = register("sponge_coloney", LostWorldsFeatures.SPONGE_COLONEY.configured(new CountConfiguration(20)).decorated(Features.Decorators.TOP_SOLID_HEIGHTMAP_SQUARE).chance(16));

	public static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String id, ConfiguredFeature<FC, ?> configuredFeature) {
		return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, LostWorldsUtils.rL(id), configuredFeature);
	}

	public static void init() {
		OreFeatures.init();
		PlantPatchFeatures.init();
		DisksFeatures.init();
		TreeFeatures.init();
		WaterFeatures.init();
	}
}
