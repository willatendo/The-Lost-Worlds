package lostworlds.client.books.tyrannibook.item;

import lostworlds.client.books.tyranninetwork.util.TileEntityHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LecternBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.tileentity.LecternTileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public abstract class TyrannobookItem extends Item implements TyrannoLecternBookItem {
	public TyrannobookItem(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResultType useOn(ItemUseContext context) {
		World world = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState state = world.getBlockState(pos);
		if (state.is(Blocks.LECTERN)) {
			if (LecternBlock.tryPlaceBook(world, pos, state, context.getItemInHand())) {
				return ActionResultType.sidedSuccess(world.isClientSide);
			}
		}
		return ActionResultType.PASS;
	}

	public static void interactWithBlock(PlayerInteractEvent.RightClickBlock event) {
		World world = event.getWorld();
		if (world.isClientSide() || event.getPlayer().isCrouching()) {
			return;
		}

		BlockPos pos = event.getPos();
		BlockState state = world.getBlockState(pos);
		if (state.is(Blocks.LECTERN)) {
			TileEntityHelper.getTile(LecternTileEntity.class, world, pos).ifPresent(te -> {
				ItemStack book = te.getBook();
				if (!book.isEmpty() && book.getItem() instanceof TyrannoLecternBookItem && ((TyrannoLecternBookItem) book.getItem()).openLecternScreen(world, pos, event.getPlayer(), book)) {
					event.setCanceled(true);
				}
			});
		}
	}
}
