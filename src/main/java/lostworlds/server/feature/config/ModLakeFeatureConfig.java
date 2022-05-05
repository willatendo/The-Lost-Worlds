package lostworlds.server.feature.config;

import java.util.function.Supplier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class ModLakeFeatureConfig implements IFeatureConfig {
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
