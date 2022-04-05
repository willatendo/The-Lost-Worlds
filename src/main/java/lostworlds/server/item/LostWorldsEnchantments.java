package lostworlds.server.item;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.item.enchantment.CurseOfBreakingEnchantment;
import lostworlds.server.item.enchantment.PrecisionEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsEnchantments {
	public static final Enchantment PRECISION = register("precision", new PrecisionEnchantment());
	public static final Enchantment CURSE_OF_BREAKING = register("curse_of_breaking", new CurseOfBreakingEnchantment());

	public static Enchantment register(String id, Enchantment enchantment) {
		enchantment.setRegistryName(LostWorldsUtils.rL(id));
		ForgeRegistries.ENCHANTMENTS.register(enchantment);
		return enchantment;
	}

	public static void init() {
	}
}
