package lostworlds.server.item;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.item.Item;

public class AmberItem extends Item {
	public AmberItem() {
		super(new Properties().tab(LostWorldsUtils.ITEMS).stacksTo(1));
	}
}
