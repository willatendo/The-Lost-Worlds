package lostworlds.server.dimension;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.dimension.surface.CretaceousSurfaceRules;
import lostworlds.server.dimension.surface.JurassicSurfaceRules;
import lostworlds.server.dimension.surface.PermianSurfaceRules;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseRouterData;
import net.minecraft.world.level.levelgen.NoiseSettings;

public class LostWorldsNoiseGeneratorSettings {
	public static final ResourceKey<NoiseGeneratorSettings> CRETACEOUS = ResourceKey.create(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, LostWorldsUtils.rL("cretaceous_noise"));
	public static final ResourceKey<NoiseGeneratorSettings> JURASSIC = ResourceKey.create(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, LostWorldsUtils.rL("jurassic_noise"));
	public static final ResourceKey<NoiseGeneratorSettings> PERMIAN = ResourceKey.create(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, LostWorldsUtils.rL("permian_noise"));

	public static NoiseGeneratorSettings cretaceous() {
		NoiseSettings noisesettings = NoiseSettings.overworldNoiseSettings(false);
		return new NoiseGeneratorSettings(noisesettings, Blocks.STONE.defaultBlockState(), Blocks.WATER.defaultBlockState(), NoiseRouterData.overworld(noisesettings, false), CretaceousSurfaceRules.cretaceous(), 63, false, true, true, true);
	}

	public static NoiseGeneratorSettings jurassic() {
		NoiseSettings noisesettings = NoiseSettings.overworldNoiseSettings(false);
		return new NoiseGeneratorSettings(noisesettings, LostWorldsBlocks.JURASSIC_STONE.getDefaultState(), Blocks.WATER.defaultBlockState(), NoiseRouterData.overworld(noisesettings, false), JurassicSurfaceRules.jurassic(), 63, false, true, true, true);
	}

	public static NoiseGeneratorSettings permian() {
		NoiseSettings noisesettings = NoiseSettings.overworldNoiseSettings(false);
		return new NoiseGeneratorSettings(noisesettings, LostWorldsBlocks.PERMIAN_STONE.getDefaultState(), Blocks.WATER.defaultBlockState(), NoiseRouterData.overworld(noisesettings, false), PermianSurfaceRules.permian(), 63, false, true, true, false);
	}

	public static void init() {
		NoiseGeneratorSettings.register(CRETACEOUS, cretaceous());
		NoiseGeneratorSettings.register(JURASSIC, jurassic());
		NoiseGeneratorSettings.register(PERMIAN, permian());
	}
}
