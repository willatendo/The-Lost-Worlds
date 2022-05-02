package lostworlds.server.feature.config;

import java.util.function.Supplier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class ModReplaceBlockConfig implements IFeatureConfig {
	public static final Codec<ModReplaceBlockConfig> CODEC = RecordCodecBuilder.create((instance) -> {
		return instance.group(BlockState.CODEC.fieldOf("target").forGetter((config) -> {
			return config.target.get();
		}), BlockState.CODEC.fieldOf("state").forGetter((config) -> {
			return config.state.get();
		})).apply(instance, ModReplaceBlockConfig::new);
	});
	public final Supplier<BlockState> target;
	public final Supplier<BlockState> state;

	public ModReplaceBlockConfig(Supplier<BlockState> target, Supplier<BlockState> state) {
		this.target = target;
		this.state = state;
	}

	private ModReplaceBlockConfig(BlockState target, BlockState state) {
		this(() -> target, () -> state);
	}
}
