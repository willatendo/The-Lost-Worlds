package lostworlds.server.biome.features.configured;

import static lostworlds.server.biome.features.configured.LostWorldsConfiguredFeatures.register;

import lostworlds.server.feature.LostWorldsFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class WaterFeatures {
	public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> ANCIENT_SPRING = register("ancient_spring", LostWorldsFeatures.ANCIENT_SPRING);

	public static void init() {
	}
}
