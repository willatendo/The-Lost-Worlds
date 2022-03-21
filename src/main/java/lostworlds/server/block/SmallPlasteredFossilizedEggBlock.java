package lostworlds.server.block;

import javax.annotation.Nullable;

import lostworlds.server.block.properties.ModBlockStateProperties;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class SmallPlasteredFossilizedEggBlock extends Block {
	private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 4, 16);
	public static final IntegerProperty EGGS = ModBlockStateProperties.SMALL_EGGS;

	public SmallPlasteredFossilizedEggBlock(AbstractBlock.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(EGGS, Integer.valueOf(1)));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockItemUseContext context) {
		return context.getItemInHand().getItem() == this.asItem() && state.getValue(EGGS) < 10 ? true : super.canBeReplaced(state, context);
	}

	@Override
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
		return blockstate.is(this) ? blockstate.setValue(EGGS, Integer.valueOf(Math.min(10, blockstate.getValue(EGGS) + 1))) : super.getStateForPlacement(context);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(EGGS);
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity entity, Hand hand, BlockRayTraceResult result) {
		if (entity.isCrouching()) {
			world.setBlockAndUpdate(pos, LostWorldsBlocks.SMALL_FOSSILISED_EGG.defaultBlockState().setValue(EGGS, state.getValue(EGGS)));
			world.playSound(entity, pos, SoundEvents.WOOL_BREAK, SoundCategory.BLOCKS, 0.7F, 1.0F);
			return ActionResultType.SUCCESS;
		} else {
			return ActionResultType.FAIL;
		}
	}
}
