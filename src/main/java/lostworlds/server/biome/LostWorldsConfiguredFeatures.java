package lostworlds.server.biome;

import com.google.common.collect.ImmutableList;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.feature.LostWorldsFeatures;
import lostworlds.server.feature.config.ModBlockstateFeatureConfig;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpreadConfig;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.SingleRandomFeature;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidWithNoiseConfig;

public class LostWorldsConfiguredFeatures {
	// Rocks
	public static final ConfiguredFeature<?, ?> PERMIAN_ROCK = register("permian_rock", LostWorldsFeatures.MOD_ROCK.configured(new ModBlockstateFeatureConfig(() -> LostWorldsBlocks.PERMIAN_COBBLESTONE.getDefaultState())).decorated(Features.Placements.HEIGHTMAP_SQUARE).countRandom(1));
	public static final ConfiguredFeature<?, ?> JURASSIC_ROCK = register("jurassic_rock", LostWorldsFeatures.MOD_ROCK.configured(new ModBlockstateFeatureConfig(() -> LostWorldsBlocks.JURASSIC_COBBLESTONE.getDefaultState())).decorated(Features.Placements.HEIGHTMAP_SQUARE).countRandom(1));
	public static final ConfiguredFeature<?, ?> CRETACEOUS_ROCK = register("cretaceous_rock", LostWorldsFeatures.MOD_ROCK.configured(new ModBlockstateFeatureConfig(() -> Blocks.COBBLESTONE.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_SQUARE).countRandom(1));

	// Lakes
	// Misc Decoration
	public static final ConfiguredFeature<?, ?> ASH_LAYER = register("ash_layer", LostWorldsFeatures.ASH_LAYER.configured(IFeatureConfig.NONE));
	public static final ConfiguredFeature<?, ?> MOD_CORAL_REEF = register("mod_coral_reef", Feature.SIMPLE_RANDOM_SELECTOR.configured(new SingleRandomFeature(ImmutableList.of(() -> {
		return Feature.CORAL_TREE.configured(IFeatureConfig.NONE);
	}, () -> {
		return Feature.CORAL_CLAW.configured(IFeatureConfig.NONE);
	}, () -> {
		return Feature.CORAL_MUSHROOM.configured(IFeatureConfig.NONE);
	}))).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP).squared().decorated(Placement.COUNT_NOISE_BIASED.configured(new TopSolidWithNoiseConfig(20, 400.0D, 0.0D))));
	public static final ConfiguredFeature<?, ?> GEYSER_BLOCK = register("geyser_block", LostWorldsFeatures.GEYSER.configured(new FeatureSpreadConfig(20)).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE).chance(16));
	public static final ConfiguredFeature<?, ?> SPONGE_COLONEY = register("sponge_coloney", LostWorldsFeatures.SPONGE_COLONEY.configured(new FeatureSpreadConfig(20)).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE).chance(16));

	public static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String id, ConfiguredFeature<FC, ?> configuredFeature) {
		return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, LostWorldsUtils.rL(id), configuredFeature);
	}

	public static void init() {
		OreFeatures.init();
		PlantPatchFeatures.init();
		DisksFeatures.init();
		TreeFeatures.init();
		WaterFeatures.init();
	}
}
