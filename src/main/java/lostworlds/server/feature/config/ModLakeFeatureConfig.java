package lostworlds.server.feature.config;

import java.util.function.Supplier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class ModLakeFeatureConfig implements FeatureConfiguration {
	public static final Codec<ModLakeFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> {
		return instance.group(BlockState.CODEC.fieldOf("liquid").forGetter((config) -> {
			return config.liquid.get();
		}), BlockState.CODEC.fieldOf("stone").forGetter((config) -> {
			return config.stone.get();
		})).apply(instance, ModLakeFeatureConfig::new);
	});
	public final Supplier<BlockState> liquid;
	public final Supplier<BlockState> stone;

	public ModLakeFeatureConfig(Supplier<BlockState> liquid, Supplier<BlockState> stone) {
		this.liquid = liquid;
		this.stone = stone;
	}

	private ModLakeFeatureConfig(BlockState liquid, BlockState stone) {
		this(() -> liquid, () -> stone);
	}
}
