package lostworlds.server.events;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.block.NautilusShellBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = LostWorldsUtils.ID, bus = Bus.FORGE)
public class RightClickBlockEvents {
	@SubscribeEvent
	public static void addNautilusShell(RightClickBlock event) {
		Player player = event.getPlayer();
		InteractionHand hand = event.getHand();
		ItemStack stack = player.getItemInHand(hand);
		Item item = stack.getItem();

		if (item == Items.NAUTILUS_SHELL) {
			Level world = event.getWorld();
			BlockPos pos = event.getPos().relative(event.getFace());
			BlockPos clickedPos = event.getPos();
			Direction direction = player.getDirection().getOpposite();

			if (!(world.getBlockState(clickedPos).getBlock() instanceof EntityBlock) || !(world.getBlockState(clickedPos).hasBlockEntity())) {
				if (world.getBlockState(pos.below()).isFaceSturdy(world, pos, direction)) {
					player.swing(hand);
					if (!player.isCreative()) {
						stack.shrink(1);
					}
					world.setBlockAndUpdate(pos, LostWorldsBlocks.NAUTILUS_SHELL.getDefaultState().setValue(NautilusShellBlock.HORIZONTAL_FACING, direction));
					world.playSound(player, pos, LostWorldsBlocks.NAUTILUS_SHELL.getDefaultState().getSoundType().getPlaceSound(), SoundSource.BLOCKS, 1.0F, 1.0F);
				}
			}

			if (player.isCrouching()) {
				if (world.getBlockState(pos.below()).isFaceSturdy(world, pos, direction)) {
					player.swing(hand);
					if (!player.isCreative()) {
						stack.shrink(1);
					}
					world.setBlockAndUpdate(pos, LostWorldsBlocks.NAUTILUS_SHELL.getDefaultState().setValue(NautilusShellBlock.HORIZONTAL_FACING, direction));
					world.playSound(player, pos, LostWorldsBlocks.NAUTILUS_SHELL.getDefaultState().getSoundType().getPlaceSound(), SoundSource.BLOCKS, 1.0F, 1.0F);
				}
				event.setCanceled(true);
			}
		}
	}
}
