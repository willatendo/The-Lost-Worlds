package lostworlds.server.block;

import lostworlds.server.item.WetPaperItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class FossilizedTrackBlock extends Block {
	public static final DirectionProperty HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;
	private static Block turnTo;

	public FossilizedTrackBlock(Properties properties, Block turnTo) {
		super(properties);
		this.turnTo = turnTo;
		this.registerDefaultState(this.stateDefinition.any().setValue(HORIZONTAL_FACING, Direction.NORTH));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(HORIZONTAL_FACING)));
	}

	@Override
	public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
		return state.setValue(HORIZONTAL_FACING, direction.rotate(state.getValue(HORIZONTAL_FACING)));
	}

	@Override
	public boolean useShapeForLightOcclusion(BlockState state) {
		return true;
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader reader, BlockPos pos) {
		return reader.getBlockState(pos.above()).is(Blocks.AIR);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return !this.defaultBlockState().canSurvive(context.getLevel(), context.getClickedPos()) ? turnTo.defaultBlockState() : this.defaultBlockState().setValue(HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState newstate, IWorld world, BlockPos pos, BlockPos newpos) {
		if (!world.getBlockState(pos.above()).is(Blocks.AIR)) {
			world.setBlock(pos, turnTo.defaultBlockState(), 3);
		}

		return super.updateShape(state, direction, newstate, world, pos, newpos);
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity entity, Hand hand, BlockRayTraceResult result) {
		if (entity.getItemInHand(hand) != null) {
			Item item = entity.getItemInHand(hand).getItem();
			if (item instanceof WetPaperItem) {
				world.setBlockAndUpdate(pos, LostWorldsBlocks.PLASTERED_FOSSILIZED_TRACK.defaultBlockState().setValue(HORIZONTAL_FACING, state.getValue(HORIZONTAL_FACING)));
				world.playSound(entity, pos, SoundEvents.WOOL_PLACE, SoundCategory.BLOCKS, 0.7F, 1.0F);

				if (!entity.abilities.instabuild) {
					ItemStack stack = entity.getItemInHand(hand);
					stack.shrink(1);
				}
				return ActionResultType.SUCCESS;
			}
		}
		return super.use(state, world, pos, entity, hand, result);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(HORIZONTAL_FACING);
	}
}
