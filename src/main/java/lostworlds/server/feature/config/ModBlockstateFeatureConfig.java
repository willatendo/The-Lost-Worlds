package lostworlds.server.feature.config;

import java.util.function.Supplier;

import com.mojang.serialization.Codec;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class ModBlockstateFeatureConfig implements FeatureConfiguration {
	public static final Codec<ModBlockstateFeatureConfig> CODEC = BlockState.CODEC.fieldOf("state").xmap(ModBlockstateFeatureConfig::new, (config) -> {
		return config.state.get();
	}).codec();
	public final Supplier<BlockState> state;

	public ModBlockstateFeatureConfig(Supplier<BlockState> state) {
		this.state = state;
	}

	private ModBlockstateFeatureConfig(BlockState state) {
		this(() -> state);
	}
}
