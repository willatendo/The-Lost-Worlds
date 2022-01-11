package lostworlds.library.dimension.jurassic;

import java.util.function.Supplier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.DimensionSettings;
import tyrannotitanlib.library.base.dimension.WorldSeedHolder;
import tyrannotitanlib.library.base.dimension.generators.SeedNoiseChunkGenerator;

public class JurassicChunkGenerator extends SeedNoiseChunkGenerator {
	public static final Codec<JurassicChunkGenerator> CODEC = RecordCodecBuilder.create((c) -> {
		return c.group(BiomeProvider.CODEC.fieldOf("biome_source").forGetter((chunkGenerator) -> {
			return chunkGenerator.biomeSource;
		}), Codec.LONG.fieldOf("seed").orElseGet(WorldSeedHolder::getSeed).forGetter((chunkGenerator) -> {
			return chunkGenerator.seed;
		}), DimensionSettings.CODEC.fieldOf("settings").forGetter((chunkGenerator) -> {
			return chunkGenerator.settings;
		})).apply(c, c.stable(JurassicChunkGenerator::new));
	});
	private long seed;
	public static long hackSeed;

	public JurassicChunkGenerator(BiomeProvider provider, long seed, Supplier<DimensionSettings> settingsIn) {
		super(provider, seed, settingsIn);
		this.seed = WorldSeedHolder.getSeed();
	}

	@Override
	protected Codec<? extends ChunkGenerator> codec() {
		return CODEC;
	}

	@Override
	public ChunkGenerator withSeed(long seed) {
		return new JurassicChunkGenerator(biomeSource.withSeed(seed), seed, getDimensionSettings());
	}

	private Supplier<DimensionSettings> getDimensionSettings() {
		return settings;
	}
}
