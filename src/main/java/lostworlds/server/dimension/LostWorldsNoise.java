package lostworlds.server.dimension;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.mojang.datafixers.util.Pair;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.dimension.biomes.PermianBiomeBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;

public class LostWorldsNoise {
	public static final MultiNoiseBiomeSource.Preset PERMIAN = new MultiNoiseBiomeSource.Preset(LostWorldsUtils.rL("permian_noise"), (biome) -> {
		Builder<Pair<Climate.ParameterPoint, Holder<Biome>>> builder = ImmutableList.builder();
		(new PermianBiomeBuilder()).addBiomes((points) -> {
			builder.add(points.mapSecond(biome::getOrCreateHolder));
		});
		return new Climate.ParameterList<>(builder.build());
	});

	public static void init() {
	}
}
