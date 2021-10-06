package lostworlds.content.server.init;

import lostworlds.content.ModRegistry;
import lostworlds.library.item.enchantment.CurseOfBreakingEnchantment;
import lostworlds.library.item.enchantment.PrecisionEnchantment;
import net.minecraft.enchantment.Enchantment;

public class EnchantmentInit 
{
	public static final Enchantment PRECISION = ModRegistry.register("precision", new PrecisionEnchantment());
	public static final Enchantment CURSE_OF_BREAKING = ModRegistry.register("curse_of_breaking", new CurseOfBreakingEnchantment());
	
	public static void init() { }
}
