package lostworlds.server.feature.config;

import java.util.List;
import java.util.function.Supplier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record SuppliedDiskConfiguration(Supplier<BlockState> state, IntProvider radius, int halfHeight, Supplier<List<BlockState>> targets) implements FeatureConfiguration {

	public static final Codec<SuppliedDiskConfiguration> CODEC = RecordCodecBuilder.create((instance) -> {
		return instance.group(BlockState.CODEC.fieldOf("state").forGetter((configuration) -> {
			return configuration.state.get();
		}), IntProvider.codec(0, 8).fieldOf("radius").forGetter(SuppliedDiskConfiguration::radius), Codec.intRange(0, 4).fieldOf("half_height").forGetter(SuppliedDiskConfiguration::halfHeight), BlockState.CODEC.listOf().fieldOf("targets").forGetter((configuration) -> {
			return configuration.targets.get();
		})).apply(instance, SuppliedDiskConfiguration::new);
	});

	private SuppliedDiskConfiguration(BlockState state, IntProvider radius, int halfHeight, List<BlockState> targets) {
		this(() -> state, radius, halfHeight, () -> targets);
	}
}
