package lostworlds.server.item;

import java.util.List;

import lostworlds.client.ClientUtils;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class CollectibleItem extends ModItem {
	@Override
	public void appendHoverText(ItemStack stack, World world, List<ITextComponent> text, ITooltipFlag flag) {
		if (ClientUtils.isHoldingLeftShift()) {
			text.add(LostWorldsUtils.gTC("toolTip", "item.collectible"));
		}
	}
}
