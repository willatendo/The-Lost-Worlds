package lostworlds.server.biome;

import java.util.Random;
import java.util.function.Supplier;

import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.BlockStateProviderType;

public class SuppliedBlockStateProvider extends BlockStateProvider {
	public static final Codec<SuppliedBlockStateProvider> CODEC = BlockState.CODEC.fieldOf("state").xmap(SuppliedBlockStateProvider::new, (provider) -> {
		return provider.state.get();
	}).codec();
	private final Supplier<BlockState> state;

	public SuppliedBlockStateProvider(Supplier<BlockState> state) {
		this.state = state;
	}

	private SuppliedBlockStateProvider(BlockState state) {
		this(() -> state);
	}

	@Override
	protected BlockStateProviderType<?> type() {
		return LostWorldsBlockstateProviders.SUPPLIED_BLOCK_STATE_PROVIDER.get();
	}

	@Override
	public BlockState getState(Random rand, BlockPos pos) {
		return this.state.get();
	}
}
