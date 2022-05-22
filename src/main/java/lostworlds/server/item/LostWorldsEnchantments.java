package lostworlds.server.item;

import static lostworlds.LostWorldsMod.getRegistrate;

import com.tterrag.registrate.util.entry.RegistryEntry;

import lostworlds.server.item.enchantment.CurseOfBreakingEnchantment;
import lostworlds.server.item.enchantment.PrecisionEnchantment;
import lostworlds.server.util.registrate.LostWorldsRegistrate;
import net.minecraft.world.item.enchantment.Enchantment.Rarity;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.entity.EquipmentSlot;

public class LostWorldsEnchantments {
	public static final LostWorldsRegistrate REGISTRATE = getRegistrate();

	public static final RegistryEntry<PrecisionEnchantment> PRECISION = REGISTRATE.object("precision").enchantment(LostWorldsEnchantments.HAMMER, PrecisionEnchantment::new).addSlots(EquipmentSlot.MAINHAND).lang("Precision").rarity(Rarity.RARE).register();
	public static final RegistryEntry<CurseOfBreakingEnchantment> CURSE_OF_BREAKING = REGISTRATE.object("curse_of_breaking").enchantment(LostWorldsEnchantments.HAMMER, CurseOfBreakingEnchantment::new).addSlots(EquipmentSlot.MAINHAND).lang("Curse of Breaking").rarity(Rarity.VERY_RARE).register();

	public static final EnchantmentCategory HAMMER = EnchantmentCategory.create("hammer", (item) -> {
		return item instanceof HammerItem;
	});

	public static void registrate() {
	}
}
