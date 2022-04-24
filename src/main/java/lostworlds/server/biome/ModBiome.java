package lostworlds.server.biome;

import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ModBiome extends ForgeRegistryEntry.UncheckedRegistryEntry<ModBiome> {
	private final Biome biome;

	public ModBiome(Biome.Builder builder) {
		this.biome = builder.build();
	}

	public Biome getBiome() {
		return this.biome;
	}

	public static int calculateSkyColor(float temperature) {
		float colour = temperature / 3.0F;
		colour = MathHelper.clamp(colour, -1.0F, 1.0F);
		return MathHelper.hsvToRgb(0.62222224F - colour * 0.05F, 0.5F + colour * 0.1F, 1.0F);
	}

	public static MobSpawnInfo.Builder defaultOverworldSpawns() {
		MobSpawnInfo.Builder mobspawninfo$builder = new MobSpawnInfo.Builder();
		DefaultBiomeFeatures.farmAnimals(mobspawninfo$builder);
		DefaultBiomeFeatures.commonSpawns(mobspawninfo$builder);
		return mobspawninfo$builder;
	}

	public static <C extends ISurfaceBuilderConfig> BiomeGenerationSettings.Builder genSettings(SurfaceBuilder<C> surfaceBuilder, C config) {
		return new BiomeGenerationSettings.Builder().surfaceBuilder(surfaceBuilder.configured(config));
	}
}
