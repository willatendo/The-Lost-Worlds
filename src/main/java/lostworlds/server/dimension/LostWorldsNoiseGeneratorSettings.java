package lostworlds.server.dimension;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseRouterData;
import net.minecraft.world.level.levelgen.NoiseSettings;

public class LostWorldsNoiseGeneratorSettings {
	public static final ResourceKey<NoiseGeneratorSettings> CRETACEOUS = ResourceKey.create(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, LostWorldsUtils.rL("cretaceous_noise"));

	public static NoiseGeneratorSettings cretaceous() {
		NoiseSettings noisesettings = NoiseSettings.overworldNoiseSettings(false);
		return new NoiseGeneratorSettings(noisesettings, Blocks.STONE.defaultBlockState(), Blocks.WATER.defaultBlockState(), NoiseRouterData.overworld(noisesettings, false), SurfaceRuleData.overworld(), 63, false, true, true, true);
	}

	public static void init() {
		NoiseGeneratorSettings.register(CRETACEOUS, cretaceous());
	}
}
