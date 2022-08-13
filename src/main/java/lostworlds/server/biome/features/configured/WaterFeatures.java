package lostworlds.server.biome.features.configured;

import static lostworlds.server.biome.features.configured.LostWorldsConfiguredFeatures.register;

import lostworlds.server.feature.LostWorldsFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.registries.RegistryObject;

public class WaterFeatures {
	public static final RegistryObject<ConfiguredFeature<?, ?>> ANCIENT_SPRING = register("ancient_spring", LostWorldsFeatures.ANCIENT_SPRING);

	public static void init() {
	}
}
