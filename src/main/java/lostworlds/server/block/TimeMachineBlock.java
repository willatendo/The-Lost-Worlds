package lostworlds.server.block;

import javax.annotation.Nullable;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.menu.LostWorldsMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TimeMachineBlock extends Block {
	private static final Component CONTAINER_NAME = LostWorldsUtils.tTC("menu", "time_machine");
	public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 12, 16);

	public TimeMachineBlock(Properties properties) {
		super(properties);
	}

	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult result) {
		if (world.isClientSide) {
			return InteractionResult.SUCCESS;
		} else {
			entity.openMenu(state.getMenuProvider(world, pos));
			return InteractionResult.CONSUME;
		}
	}

	@Nullable
	public MenuProvider getMenuProvider(BlockState state, Level world, BlockPos pos) {
		return new SimpleMenuProvider((windowId, playerInventory, player) -> {
			return LostWorldsMenus.TIME_MACHINE_CONTAINER.create(windowId, playerInventory);
		}, CONTAINER_NAME);
	}

	public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pso, CollisionContext context) {
		return SHAPE;
	}

	public boolean useShapeForLightOcclusion(BlockState state) {
		return true;
	}

	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}
}
