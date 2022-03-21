package lostworlds.server.item.enchantment;

import lostworlds.server.item.HammerItem;
import net.minecraft.enchantment.EnchantmentType;

public class ModEnchantmentTypes {
	public static final EnchantmentType HAMMER = EnchantmentType.create("hammer", (Item) -> {
		return Item instanceof HammerItem;
	});
}
