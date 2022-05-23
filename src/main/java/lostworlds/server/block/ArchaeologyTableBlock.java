package lostworlds.server.block;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.menu.ArchaeologyTableMenu;
import lostworlds.server.menu.LostWorldsMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class ArchaeologyTableBlock extends Block {
	private static final Component NAME = LostWorldsUtils.tTC("container", "archaeology_table");

	public ArchaeologyTableBlock(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if (world.isClientSide) {
			return InteractionResult.SUCCESS;
		} else {
			player.openMenu(state.getMenuProvider(world, pos));
			return InteractionResult.CONSUME;
		}
	}

	@Override
	public MenuProvider getMenuProvider(BlockState state, Level world, BlockPos pos) {
		return new SimpleMenuProvider((windowID, playerInventory, player) -> {
			return new ArchaeologyTableMenu(LostWorldsMenus.ARCHAEOLOGY_CONTAINER.get(), windowID, playerInventory, ContainerLevelAccess.create(world, pos));
		}, NAME);
	}
}
