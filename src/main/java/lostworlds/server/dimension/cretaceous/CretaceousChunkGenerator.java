package lostworlds.server.dimension.cretaceous;

import java.util.function.Supplier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import lostworlds.server.dimension.WorldSeedHolder;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;

public class CretaceousChunkGenerator extends NoiseBasedChunkGenerator {
	public static final Codec<CretaceousChunkGenerator> CODEC = RecordCodecBuilder.create((c) -> {
		return c.group(BiomeSource.CODEC.fieldOf("biome_source").forGetter((chunkGenerator) -> {
			return chunkGenerator.biomeSource;
		}), Codec.LONG.fieldOf("seed").orElseGet(WorldSeedHolder::getSeed).forGetter((chunkGenerator) -> {
			return chunkGenerator.seed;
		}), NoiseGeneratorSettings.CODEC.fieldOf("settings").forGetter((chunkGenerator) -> {
			return chunkGenerator.settings;
		})).apply(c, c.stable(CretaceousChunkGenerator::new));
	});
	private long seed;
	public static long hackSeed;

	public CretaceousChunkGenerator(BiomeSource provider, long seed, Supplier<NoiseGeneratorSettings> settingsIn) {
		super(provider, seed, settingsIn);
		this.seed = WorldSeedHolder.getSeed();
	}

	@Override
	protected Codec<? extends ChunkGenerator> codec() {
		return CODEC;
	}

	@Override
	public ChunkGenerator withSeed(long seed) {
		return new CretaceousChunkGenerator(biomeSource.withSeed(WorldSeedHolder.getSeed()), WorldSeedHolder.getSeed(), getDimensionSettings());
	}

	private Supplier<NoiseGeneratorSettings> getDimensionSettings() {
		return this.settings;
	}
}
