package lostworlds.server.item;

import java.util.List;

import lostworlds.client.LostWorldsBooks;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class LexiconItem extends LecternBookItem {
	public LexiconItem(Properties properties) {
		super(properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, Level level, List<Component> text, TooltipFlag flag) {
		text.add(LostWorldsUtils.gTC("item", "lost_worlds_lexicon.desc"));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (level.isClientSide) {
			LostWorldsBooks.LOST_WORLDS_LEXICON.openGui(hand, stack);
		}
		return InteractionResultHolder.success(stack);
	}

	@Override
	public void openLecternScreenClient(BlockPos pos, ItemStack stack) {
		LostWorldsBooks.LOST_WORLDS_LEXICON.openGui(pos, stack);
	}
}
