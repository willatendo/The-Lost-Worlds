package lostworlds.library.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;

public class FossilFeatureConfig implements IFeatureConfig {
	public static final Codec<FossilFeatureConfig> CODEC = RecordCodecBuilder.create((instanceconfig) -> {
		return instanceconfig.group(RuleTest.CODEC.fieldOf("target").forGetter((config) -> {
			return config.target;
		}), BlockState.CODEC.fieldOf("state").forGetter((config) -> {
			return config.state;
		}), Codec.intRange(0, 64).fieldOf("size").forGetter((config) -> {
			return config.size;
		})).apply(instanceconfig, FossilFeatureConfig::new);
	});
	public final RuleTest target;
	public final BlockState state;
	public final int size;

	public FossilFeatureConfig(RuleTest targetBlock, BlockState state, int size) {
		this.target = targetBlock;
		this.state = state;
		this.size = size;
	}
}
