package lostworlds.server.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MudBlock extends Block {
	public static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D);

	public MudBlock(Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public VoxelShape getBlockSupportShape(BlockState state, BlockGetter reader, BlockPos pos) {
		return Shapes.block();
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
		return Shapes.block();
	}
}