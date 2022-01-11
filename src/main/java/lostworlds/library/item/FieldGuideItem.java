package lostworlds.library.item;

import java.util.List;

import lostworlds.content.ModUtils;
import lostworlds.content.client.book.LostWorldsBooks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import tyrannotitanlib.library.tyrannobook.item.TyrannobookItem;

public class FieldGuideItem extends TyrannobookItem {
	public FieldGuideItem() {
		super(new Properties().tab(ModUtils.ITEMS).stacksTo(1));
	}

	@Override
	public void appendHoverText(ItemStack stack, World world, List<ITextComponent> toolTip, ITooltipFlag flag) {
		toolTip.add(ModUtils.gTC("item", "field_guide.desc"));
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
		ItemStack stack = entity.getItemInHand(hand);
		if (world.isClientSide) {
			LostWorldsBooks.FIELD_GUIDE.openGui(hand, stack);
		}
		return ActionResult.success(stack);
	}

	@Override
	public void openLecternScreenClient(BlockPos pos, ItemStack stack) {
		LostWorldsBooks.FIELD_GUIDE.openGui(pos, stack);
		;
	}
}
