package lostworlds.server.biome.features.configured;

import static lostworlds.server.biome.features.configured.LostWorldsConfiguredFeatures.register;

import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.feature.LostWorldsFeatures;
import lostworlds.server.feature.config.ModLakeFeatureConfig;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.ChanceDecoratorConfiguration;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraft.world.level.levelgen.feature.configurations.RangeDecoratorConfiguration;

public class WaterFeatures {
	public static final ConfiguredFeature<?, ?> ANCIENT_SPRING = register("ancient_spring", LostWorldsFeatures.ANCIENT_SPRING.configured(FeatureConfiguration.NONE).decorated(FeatureDecorator.RANGE_BIASED.configured(new RangeDecoratorConfiguration(8, 8, 256))).squared().count(50));

	public static final ConfiguredFeature<?, ?> PERMIAN_WATER_LAKE = register("permian_water_lake", LostWorldsFeatures.MOD_LAKE.configured(new ModLakeFeatureConfig(() -> Blocks.WATER.defaultBlockState(), () -> LostWorldsBlocks.PERMIAN_STONE.get().defaultBlockState())).decorated(FeatureDecorator.WATER_LAKE.configured(new ChanceDecoratorConfiguration(4))));
	public static final ConfiguredFeature<?, ?> PERMIAN_LAVA_LAKE = register("permian_lava_lake", LostWorldsFeatures.MOD_LAKE.configured(new ModLakeFeatureConfig(() -> Blocks.LAVA.defaultBlockState(), () -> LostWorldsBlocks.PERMIAN_STONE.get().defaultBlockState())).decorated(FeatureDecorator.LAVA_LAKE.configured(new ChanceDecoratorConfiguration(80))));

	public static final ConfiguredFeature<?, ?> JURASSIC_WATER_LAKE = register("jurassic_water_lake", LostWorldsFeatures.MOD_LAKE.configured(new ModLakeFeatureConfig(() -> Blocks.WATER.defaultBlockState(), () -> LostWorldsBlocks.JURASSIC_STONE.get().defaultBlockState())).decorated(FeatureDecorator.WATER_LAKE.configured(new ChanceDecoratorConfiguration(4))));
	public static final ConfiguredFeature<?, ?> JURASSIC_LAVA_LAKE = register("jurassic_lava_lake", LostWorldsFeatures.MOD_LAKE.configured(new ModLakeFeatureConfig(() -> Blocks.LAVA.defaultBlockState(), () -> LostWorldsBlocks.JURASSIC_STONE.get().defaultBlockState())).decorated(FeatureDecorator.LAVA_LAKE.configured(new ChanceDecoratorConfiguration(80))));

	public static void init() {
	}
}
