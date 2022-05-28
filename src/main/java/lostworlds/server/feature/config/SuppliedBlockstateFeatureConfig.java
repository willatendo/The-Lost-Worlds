package lostworlds.server.feature.config;

import java.util.function.Supplier;

import com.mojang.serialization.Codec;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class SuppliedBlockstateFeatureConfig implements FeatureConfiguration {
	public static final Codec<SuppliedBlockstateFeatureConfig> CODEC = BlockState.CODEC.fieldOf("state").xmap(SuppliedBlockstateFeatureConfig::new, (config) -> {
		return config.state.get();
	}).codec();
	public final Supplier<BlockState> state;

	public SuppliedBlockstateFeatureConfig(Supplier<BlockState> state) {
		this.state = state;
	}

	private SuppliedBlockstateFeatureConfig(BlockState state) {
		this(() -> state);
	}
}
