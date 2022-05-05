package lostworlds.server.biome;

import static lostworlds.server.biome.LostWorldsConfiguredFeatures.register;

import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.feature.LostWorldsFeatures;
import lostworlds.server.feature.config.ModLakeFeatureConfig;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;

public class WaterFeatures {
	public static final ConfiguredFeature<?, ?> ANCIENT_SPRING = register("ancient_spring", LostWorldsFeatures.ANCIENT_SPRING.configured(IFeatureConfig.NONE).decorated(Placement.RANGE_BIASED.configured(new TopSolidRangeConfig(8, 8, 256))).squared().count(50));

	public static final ConfiguredFeature<?, ?> PERMIAN_WATER_LAKE = register("permian_water_lake", LostWorldsFeatures.MOD_LAKE.configured(new ModLakeFeatureConfig(() -> Blocks.WATER.defaultBlockState(), () -> LostWorldsBlocks.PERMIAN_STONE.get().defaultBlockState())).decorated(Placement.WATER_LAKE.configured(new ChanceConfig(4))));
	public static final ConfiguredFeature<?, ?> PERMIAN_LAVA_LAKE = register("permian_lava_lake", LostWorldsFeatures.MOD_LAKE.configured(new ModLakeFeatureConfig(() -> Blocks.LAVA.defaultBlockState(), () -> LostWorldsBlocks.PERMIAN_STONE.get().defaultBlockState())).decorated(Placement.LAVA_LAKE.configured(new ChanceConfig(80))));

	public static final ConfiguredFeature<?, ?> JURASSIC_WATER_LAKE = register("jurassic_water_lake", LostWorldsFeatures.MOD_LAKE.configured(new ModLakeFeatureConfig(() -> Blocks.WATER.defaultBlockState(), () -> LostWorldsBlocks.JURASSIC_STONE.get().defaultBlockState())).decorated(Placement.WATER_LAKE.configured(new ChanceConfig(4))));
	public static final ConfiguredFeature<?, ?> JURASSIC_LAVA_LAKE = register("jurassic_lava_lake", LostWorldsFeatures.MOD_LAKE.configured(new ModLakeFeatureConfig(() -> Blocks.LAVA.defaultBlockState(), () -> LostWorldsBlocks.JURASSIC_STONE.get().defaultBlockState())).decorated(Placement.LAVA_LAKE.configured(new ChanceConfig(80))));

	public static void init() {
	}
}
