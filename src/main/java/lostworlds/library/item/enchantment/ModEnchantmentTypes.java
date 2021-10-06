package lostworlds.library.item.enchantment;

import lostworlds.library.item.HammerItem;
import net.minecraft.enchantment.EnchantmentType;

public class ModEnchantmentTypes 
{
	public static final EnchantmentType HAMMER = EnchantmentType.create("hammer", (Item) -> 
	{
		return Item instanceof HammerItem;
	});
}
