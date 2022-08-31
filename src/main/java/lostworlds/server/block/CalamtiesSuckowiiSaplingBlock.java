package lostworlds.server.block;

import java.util.Random;

import lostworlds.server.LostWorldsTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.state.properties.BambooLeaves;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class CalamtiesSuckowiiSaplingBlock extends Block implements IGrowable {
	private static final VoxelShape SAPLING_SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 12.0D, 12.0D);

	public CalamtiesSuckowiiSaplingBlock(Properties properties) {
		super(properties);
	}

	@Override
	public AbstractBlock.OffsetType getOffsetType() {
		return AbstractBlock.OffsetType.XZ;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
		Vector3d vector3d = state.getOffset(reader, pos);
		return SAPLING_SHAPE.move(vector3d.x, vector3d.y, vector3d.z);
	}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
		if (rand.nextInt(3) == 0 && world.isEmptyBlock(pos.above()) && world.getRawBrightness(pos.above(), 0) >= 9) {
			this.growPlant(world, pos);
		}
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader reader, BlockPos pos) {
		return reader.getBlockState(pos.below()).is(LostWorldsTags.ModBlockTags.CALAMITES_PLACEABLES);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState newState, IWorld world, BlockPos pos, BlockPos newPos) {
		if (!state.canSurvive(world, pos)) {
			return Blocks.AIR.defaultBlockState();
		} else {
			if (direction == Direction.UP && newState.is(LostWorldsBlocks.CALAMITES_SUCKOWII.get())) {
				world.setBlock(pos, LostWorldsBlocks.CALAMITES_SUCKOWII.getDefaultState(), 2);
			}

			return super.updateShape(state, direction, newState, world, pos, newPos);
		}
	}

	@Override
	public ItemStack getCloneItemStack(IBlockReader reader, BlockPos pos, BlockState state) {
		return LostWorldsBlocks.CALAMITES_SUCKOWII.asStack();
	}

	@Override
	public boolean isValidBonemealTarget(IBlockReader reader, BlockPos pos, BlockState state, boolean valid) {
		return reader.getBlockState(pos.above()).isAir();
	}

	@Override
	public boolean isBonemealSuccess(World world, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerWorld world, Random rand, BlockPos pos, BlockState state) {
		this.growPlant(world, pos);
	}

	@Override
	public float getDestroyProgress(BlockState state, PlayerEntity entity, IBlockReader reader, BlockPos pos) {
		return entity.getMainHandItem().getItem() instanceof SwordItem ? 1.0F : super.getDestroyProgress(state, entity, reader, pos);
	}

	protected void growPlant(World world, BlockPos pos) {
		world.setBlock(pos.above(), LostWorldsBlocks.CALAMITES_SUCKOWII.getDefaultState().setValue(CalamtiesSuckowiiBlock.LEAVES, BambooLeaves.SMALL), 3);
	}
}
