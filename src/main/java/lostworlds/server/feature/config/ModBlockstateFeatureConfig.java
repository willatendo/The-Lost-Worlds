package lostworlds.server.feature.config;

import java.util.function.Supplier;

import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class ModBlockstateFeatureConfig implements IFeatureConfig {
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
