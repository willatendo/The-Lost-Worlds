package lostworlds.server.dimension.cretaceous;

import java.util.function.Supplier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import lostworlds.server.dimension.SeedNoiseChunkGenerator;
import lostworlds.server.dimension.WorldSeedHolder;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.DimensionSettings;

public class CretaceousChunkGenerator extends SeedNoiseChunkGenerator {
	public static final Codec<CretaceousChunkGenerator> CODEC = RecordCodecBuilder.create((c) -> {
		return c.group(BiomeProvider.CODEC.fieldOf("biome_source").forGetter((chunkGenerator) -> {
			return chunkGenerator.biomeSource;
		}), Codec.LONG.fieldOf("seed").orElseGet(WorldSeedHolder::getSeed).forGetter((chunkGenerator) -> {
			return chunkGenerator.seed;
		}), DimensionSettings.CODEC.fieldOf("settings").forGetter((chunkGenerator) -> {
			return chunkGenerator.settings;
		})).apply(c, c.stable(CretaceousChunkGenerator::new));
	});
	private long seed;
	public static long hackSeed;

	public CretaceousChunkGenerator(BiomeProvider provider, long seed, Supplier<DimensionSettings> settingsIn) {
		super(provider, seed, settingsIn);
		this.seed = WorldSeedHolder.getSeed();
	}

	@Override
	protected Codec<? extends ChunkGenerator> codec() {
		return CODEC;
	}

	@Override
	public ChunkGenerator withSeed(long seed) {
		return new CretaceousChunkGenerator(biomeSource.withSeed(seed), seed, getDimensionSettings());
	}

	private Supplier<DimensionSettings> getDimensionSettings() {
		return settings;
	}
}
