package lostworlds.server.item;

import java.util.List;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class FieldGuideItem extends Item {
	public FieldGuideItem(Properties properties) {
		super(properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, Level level, List<Component> text, TooltipFlag flag) {
		text.add(LostWorldsUtils.gTC("item", "field_guide.desc"));
	}

//	@Override
//	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
//		ItemStack stack = player.getItemInHand(hand);
//		if (level.isClientSide) {
//			LostWorldsBooks.FIELD_GUIDE.openGui(hand, stack);
//		}
//		return InteractionResultHolder.success(stack);
//	}
//
//	@Override
//	public void openLecternScreenClient(BlockPos pos, ItemStack stack) {
//		LostWorldsBooks.FIELD_GUIDE.openGui(pos, stack);
//		;
//	}
}
