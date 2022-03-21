package lostworlds.server.item;

import lostworlds.server.LostWorldsRegistry;
import lostworlds.server.item.enchantment.CurseOfBreakingEnchantment;
import lostworlds.server.item.enchantment.PrecisionEnchantment;
import net.minecraft.enchantment.Enchantment;

public class LostWorldsEnchantments {
	public static final Enchantment PRECISION = LostWorldsRegistry.register("precision", new PrecisionEnchantment());
	public static final Enchantment CURSE_OF_BREAKING = LostWorldsRegistry.register("curse_of_breaking", new CurseOfBreakingEnchantment());

	public static void init() {
	}
}
