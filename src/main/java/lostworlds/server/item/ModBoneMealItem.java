package lostworlds.server.item;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.item.BoneMealItem;

public class ModBoneMealItem extends BoneMealItem {
	public ModBoneMealItem() {
		super(new Properties().tab(LostWorldsUtils.ITEMS));
	}
}
