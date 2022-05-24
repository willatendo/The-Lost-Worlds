package lostworlds.server.dimension.permian;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;

import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.Climate.Sampler;

public class PermianBiomeSource extends BiomeSource {
	private final Climate.ParameterList<Holder<Biome>> climate;

	public PermianBiomeSource(Climate.ParameterList<Holder<Biome>> climate) {
		super(climate.values().stream().map(Pair::getSecond));
		this.climate = climate;
	}

	@Override
	protected Codec<? extends BiomeSource> codec() {
		return CODEC;
	}

	@Override
	public BiomeSource withSeed(long seed) {
		return this;
	}

	@Override
	public Holder<Biome> getNoiseBiome(int p_204238_, int p_204239_, int p_204240_, Sampler climateSampler) {
		return this.climate.findValue(climateSampler.sample(p_204238_, p_204239_, p_204240_));
	}
}
