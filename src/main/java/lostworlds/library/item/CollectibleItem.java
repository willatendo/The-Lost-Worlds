package lostworlds.library.item;

import java.util.List;

import lostworlds.content.ClientUtils;
import lostworlds.content.ModUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class CollectibleItem extends ModItem {
	@Override
	public void appendHoverText(ItemStack stack, World world, List<ITextComponent> text, ITooltipFlag flag) {
		if (ClientUtils.isHoldingLeftShift()) {
			text.add(ModUtils.gTC("toolTip", "item.collectible"));
		}
	}
}
