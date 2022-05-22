package lostworlds.server.feature.config;

import java.util.function.Supplier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

public class ModOreFeatureConfig implements FeatureConfiguration {
	public static final Codec<ModOreFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> {
		return instance.group(RuleTest.CODEC.fieldOf("target").forGetter((config) -> {
			return config.target;
		}), BlockState.CODEC.fieldOf("state").forGetter((config) -> {
			return config.state.get();
		}), Codec.intRange(0, 64).fieldOf("size").forGetter((config) -> {
			return config.size;
		})).apply(instance, ModOreFeatureConfig::new);
	});
	public final RuleTest target;
	public final int size;
	public final Supplier<BlockState> state;

	public ModOreFeatureConfig(RuleTest target, Supplier<BlockState> state, int size) {
		this.size = size;
		this.state = state;
		this.target = target;
	}

	public ModOreFeatureConfig(RuleTest target, BlockState state, int size) {
		this(target, () -> state, size);
	}
}
