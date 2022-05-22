package lostworlds.server.block;

import java.util.Random;

import lostworlds.server.LostWorldsTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BambooLeaves;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CalamtiesSuckowiiSaplingBlock extends Block implements BonemealableBlock {
	private static final VoxelShape SAPLING_SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 12.0D, 12.0D);

	public CalamtiesSuckowiiSaplingBlock(Properties properties) {
		super(properties);
	}

	@Override
	public BlockBehaviour.OffsetType getOffsetType() {
		return BlockBehaviour.OffsetType.XZ;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
		Vec3 vector3d = state.getOffset(reader, pos);
		return SAPLING_SHAPE.move(vector3d.x, vector3d.y, vector3d.z);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random rand) {
		if (rand.nextInt(3) == 0 && world.isEmptyBlock(pos.above()) && world.getRawBrightness(pos.above(), 0) >= 9) {
			this.growPlant(world, pos);
		}
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
		return reader.getBlockState(pos.below()).is(LostWorldsTags.ModBlockTags.CALAMITES_PLACEABLES.tag);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor world, BlockPos pos, BlockPos newPos) {
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
	public ItemStack getCloneItemStack(BlockGetter reader, BlockPos pos, BlockState state) {
		return LostWorldsBlocks.CALAMITES_SUCKOWII.asStack();
	}

	@Override
	public boolean isValidBonemealTarget(BlockGetter reader, BlockPos pos, BlockState state, boolean valid) {
		return reader.getBlockState(pos.above()).isAir();
	}

	@Override
	public boolean isBonemealSuccess(Level world, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel world, Random rand, BlockPos pos, BlockState state) {
		this.growPlant(world, pos);
	}

	@Override
	public float getDestroyProgress(BlockState state, Player entity, BlockGetter reader, BlockPos pos) {
		return entity.getMainHandItem().getItem() instanceof SwordItem ? 1.0F : super.getDestroyProgress(state, entity, reader, pos);
	}

	protected void growPlant(Level world, BlockPos pos) {
		world.setBlock(pos.above(), LostWorldsBlocks.CALAMITES_SUCKOWII.getDefaultState().setValue(CalamtiesSuckowiiBlock.LEAVES, BambooLeaves.SMALL), 3);
	}
}
