package lostworlds.server.block;

import javax.annotation.Nullable;

import lostworlds.server.block.properties.ModBlockStateProperties;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;

public class SmallPlasteredFossilizedEggBlock extends Block {
	private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 4, 16);
	public static final IntegerProperty EGGS = ModBlockStateProperties.SMALL_EGGS;

	public SmallPlasteredFossilizedEggBlock(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(EGGS, Integer.valueOf(1)));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
		return context.getItemInHand().getItem() == this.asItem() && state.getValue(EGGS) < 10 ? true : super.canBeReplaced(state, context);
	}

	@Override
	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
		return blockstate.is(this) ? blockstate.setValue(EGGS, Integer.valueOf(Math.min(10, blockstate.getValue(EGGS) + 1))) : super.getStateForPlacement(context);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(EGGS);
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult result) {
		if (entity.isCrouching()) {
			world.setBlockAndUpdate(pos, LostWorldsBlocks.SMALL_FOSSILISED_EGG.getDefaultState().setValue(EGGS, state.getValue(EGGS)));
			world.playSound(entity, pos, SoundEvents.WOOL_BREAK, SoundSource.BLOCKS, 0.7F, 1.0F);
			return InteractionResult.SUCCESS;
		} else {
			return InteractionResult.FAIL;
		}
	}
}
