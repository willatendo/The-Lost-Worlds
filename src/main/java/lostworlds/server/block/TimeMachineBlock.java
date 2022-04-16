package lostworlds.server.block;

import javax.annotation.Nullable;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.container.TimeMachineContainer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class TimeMachineBlock extends Block {
	private static final ITextComponent CONTAINER_NAME = LostWorldsUtils.tTC("container", "time_machine");
	public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 12, 16);

	public TimeMachineBlock(Properties properties) {
		super(properties);
	}

	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity entity, Hand hand, BlockRayTraceResult result) {
		if (world.isClientSide) {
			return ActionResultType.SUCCESS;
		} else {
			entity.openMenu(state.getMenuProvider(world, pos));
			return ActionResultType.CONSUME;
		}
	}

	@Nullable
	public INamedContainerProvider getMenuProvider(BlockState state, World world, BlockPos pos) {
		return new SimpleNamedContainerProvider((windowId, inv, u) -> {
			return new TimeMachineContainer(windowId, inv, IWorldPosCallable.create(world, pos));
		}, CONTAINER_NAME);
	}

	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pso, ISelectionContext context) {
		return SHAPE;
	}

	public boolean useShapeForLightOcclusion(BlockState state) {
		return true;
	}

	public BlockRenderType getRenderShape(BlockState state) {
		return BlockRenderType.MODEL;
	}
}
