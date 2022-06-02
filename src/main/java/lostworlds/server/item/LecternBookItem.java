package lostworlds.server.item;

import lostworlds.client.book.BlockEntityHelper;
import lostworlds.client.book.LecternBook;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.block.entity.LecternBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public abstract class LecternBookItem extends Item implements LecternBook {
	public LecternBookItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState state = level.getBlockState(pos);
		if (state.is(Blocks.LECTERN)) {
			if (LecternBlock.tryPlaceBook(context.getPlayer(), level, pos, state, context.getItemInHand())) {
				return InteractionResult.sidedSuccess(level.isClientSide);
			}
		}
		return InteractionResult.PASS;
	}

	public static void interactWithBlock(PlayerInteractEvent.RightClickBlock event) {
		Level world = event.getWorld();
		if (world.isClientSide() || event.getPlayer().isShiftKeyDown()) {
			return;
		}

		BlockPos pos = event.getPos();
		BlockState state = world.getBlockState(pos);
		if (state.is(Blocks.LECTERN)) {
			BlockEntityHelper.get(LecternBlockEntity.class, world, pos).ifPresent(te -> {
				ItemStack book = te.getBook();
				if (!book.isEmpty() && book.getItem() instanceof LecternBookItem && ((LecternBookItem) book.getItem()).openLecternScreen(world, pos, event.getPlayer(), book)) {
					event.setCanceled(true);
				}
			});
		}
	}
}
