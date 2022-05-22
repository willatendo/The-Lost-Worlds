package lostworlds.server.block;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class ModBushBlock extends BushBlock implements BonemealableBlock {
	public ModBushBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
		return state.is(Blocks.DIRT) || state.is(LostWorldsBlocks.MOSSY_SOIL.get()) || state.is(LostWorldsBlocks.PERMIAN_SAND.get()) || state.is(LostWorldsBlocks.DRIED_SOIL.get());
	}

	@Override
	public boolean isValidBonemealTarget(BlockGetter level, BlockPos pos, BlockState state, boolean bool) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(Level level, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel level, Random rand, BlockPos pos, BlockState state) {
		DoublePlantBlock doubleplantblock = (DoublePlantBlock) (this == LostWorldsBlocks.GROUND_FERNS.get() ? Blocks.LARGE_FERN : Blocks.TALL_GRASS);
		if (doubleplantblock.defaultBlockState().canSurvive(level, pos) && level.isEmptyBlock(pos.above())) {
			doubleplantblock.placeAt(level, state, pos, 2);
		}
	}

	@Override
	public BlockBehaviour.OffsetType getOffsetType() {
		return BlockBehaviour.OffsetType.XYZ;
	}
}
