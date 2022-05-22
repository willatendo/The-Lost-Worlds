package lostworlds.server.block;

import java.util.function.Supplier;

import lostworlds.server.item.WetPaperItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;

public class FossilizedTrackBlock extends Block {
	public static final DirectionProperty HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;
	private final Supplier<Block> turnToBlock;

	public FossilizedTrackBlock(Supplier<Block> turnToBlock, Properties properties) {
		super(properties);
		this.turnToBlock = turnToBlock;
		this.registerDefaultState(this.stateDefinition.any().setValue(HORIZONTAL_FACING, Direction.NORTH));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(HORIZONTAL_FACING)));
	}

	@Override
	public BlockState rotate(BlockState state, LevelAccessor world, BlockPos pos, Rotation direction) {
		return state.setValue(HORIZONTAL_FACING, direction.rotate(state.getValue(HORIZONTAL_FACING)));
	}

	@Override
	public boolean useShapeForLightOcclusion(BlockState state) {
		return true;
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
		return reader.getBlockState(pos.above()).is(Blocks.AIR);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return !this.defaultBlockState().canSurvive(context.getLevel(), context.getClickedPos()) ? turnToBlock.get().defaultBlockState() : this.defaultBlockState().setValue(HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState newstate, LevelAccessor world, BlockPos pos, BlockPos newpos) {
		if (!world.getBlockState(pos.above()).is(Blocks.AIR)) {
			world.setBlock(pos, turnToBlock.get().defaultBlockState(), 3);
		}

		return super.updateShape(state, direction, newstate, world, pos, newpos);
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult result) {
		if (entity.getItemInHand(hand) != null) {
			Item item = entity.getItemInHand(hand).getItem();
			if (item instanceof WetPaperItem) {
				world.setBlockAndUpdate(pos, LostWorldsBlocks.PLASTERED_FOSSILIZED_TRACK.getDefaultState().setValue(HORIZONTAL_FACING, state.getValue(HORIZONTAL_FACING)));
				world.playSound(entity, pos, SoundEvents.WOOL_PLACE, SoundSource.BLOCKS, 0.7F, 1.0F);

				if (!entity.isCreative()) {
					ItemStack stack = entity.getItemInHand(hand);
					stack.shrink(1);
				}
				return InteractionResult.SUCCESS;
			}
		}
		return super.use(state, world, pos, entity, hand, result);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(HORIZONTAL_FACING);
	}
}
