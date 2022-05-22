package lostworlds.server.item;

import java.util.List;

import lostworlds.client.ClientUtils;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class CollectibleItem extends Item {
	public CollectibleItem(Properties properties) {
		super(properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, Level level, List<Component> text, TooltipFlag flag) {
		if (ClientUtils.isHoldingLeftShift()) {
			text.add(LostWorldsUtils.gTC("item", "collectible.desc"));
		}
	}
}
