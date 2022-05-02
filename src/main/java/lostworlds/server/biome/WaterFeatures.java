package lostworlds.server.biome;

import static lostworlds.server.biome.ModConfiguredFeatures.register;

import java.util.function.Supplier;

import lostworlds.server.feature.LostWorldsFeatures;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;

public class WaterFeatures {
	// Permian
	public static final Supplier<ConfiguredFeature<?, ?>> ANCIENT_SPRING = () -> register("ancient_spring", LostWorldsFeatures.ANCIENT_SPRING.configured(IFeatureConfig.NONE).decorated(Placement.RANGE_BIASED.configured(new TopSolidRangeConfig(8, 8, 256))).squared().count(50));

	public static void init() {
	}
}
