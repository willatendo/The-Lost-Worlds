package lostworlds.server.biome;

import static lostworlds.server.util.GeneralGetter.getStateWhenCan;

import java.util.function.Supplier;

import com.google.common.collect.ImmutableList;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.feature.LostWorldsFeatures;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpreadConfig;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.SingleRandomFeature;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidWithNoiseConfig;

public class ModConfiguredFeatures {
	// Rocks
	public static final Supplier<ConfiguredFeature<?, ?>> PERMIAN_ROCK = () -> register("permian_rock", LostWorldsFeatures.MOD_ROCK.configured(new BlockStateFeatureConfig(getStateWhenCan(LostWorldsBlocks.PERMIAN_COBBLESTONE))).decorated(Features.Placements.HEIGHTMAP_SQUARE).countRandom(1));
	public static final Supplier<ConfiguredFeature<?, ?>> JURASSIC_ROCK = () -> register("jurassic_rock", LostWorldsFeatures.MOD_ROCK.configured(new BlockStateFeatureConfig(getStateWhenCan(LostWorldsBlocks.JURASSIC_COBBLESTONE))).decorated(Features.Placements.HEIGHTMAP_SQUARE).countRandom(1));
	public static final Supplier<ConfiguredFeature<?, ?>> CRETACEOUS_ROCK = () -> register("cretaceous_rock", LostWorldsFeatures.MOD_ROCK.configured(new BlockStateFeatureConfig(Blocks.COBBLESTONE.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_SQUARE).countRandom(1));

	// Lakes
	public static final Supplier<ConfiguredFeature<?, ?>> PERMIAN_WATER_LAKE = () -> register("permian_water_lake", LostWorldsFeatures.MOD_LAKE.configured(new BlockStateFeatureConfig(Blocks.WATER.defaultBlockState())).decorated(Placement.WATER_LAKE.configured(new ChanceConfig(4))));
	public static final Supplier<ConfiguredFeature<?, ?>> PERMIAN_LAVA_LAKE = () -> register("permian_lava_lake", LostWorldsFeatures.MOD_LAKE.configured(new BlockStateFeatureConfig(Blocks.LAVA.defaultBlockState())).decorated(Placement.LAVA_LAKE.configured(new ChanceConfig(80))));

	// Misc Decoration
	public static final Supplier<ConfiguredFeature<?, ?>> ASH_LAYER = () -> register("ash_layer", LostWorldsFeatures.ASH_LAYER.configured(IFeatureConfig.NONE));
	public static final Supplier<ConfiguredFeature<?, ?>> MOD_CORAL_REEF = () -> register("mod_coral_reef", Feature.SIMPLE_RANDOM_SELECTOR.configured(new SingleRandomFeature(ImmutableList.of(() -> {
		return Feature.CORAL_TREE.configured(IFeatureConfig.NONE);
	}, () -> {
		return Feature.CORAL_CLAW.configured(IFeatureConfig.NONE);
	}, () -> {
		return Feature.CORAL_MUSHROOM.configured(IFeatureConfig.NONE);
	}))).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP).squared().decorated(Placement.COUNT_NOISE_BIASED.configured(new TopSolidWithNoiseConfig(20, 400.0D, 0.0D))));
	public static final Supplier<ConfiguredFeature<?, ?>> GEYSER_BLOCK = () -> register("geyser_block", LostWorldsFeatures.GEYSER.configured(new FeatureSpreadConfig(20)).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE).chance(16));
	public static final Supplier<ConfiguredFeature<?, ?>> SPONGE_COLONEY = () -> register("sponge_coloney", LostWorldsFeatures.SPONGE_COLONEY.configured(new FeatureSpreadConfig(20)).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE).chance(16));

	public static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String id, ConfiguredFeature<FC, ?> configuredFeature) {
		return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, LostWorldsUtils.rL(id), configuredFeature);
	}
}
