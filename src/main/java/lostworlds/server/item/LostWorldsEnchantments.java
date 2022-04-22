package lostworlds.server.item;

import static lostworlds.LostWorldsMod.getRegistrate;

import com.tterrag.registrate.util.entry.RegistryEntry;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.item.enchantment.CurseOfBreakingEnchantment;
import lostworlds.server.item.enchantment.PrecisionEnchantment;
import lostworlds.server.util.LostWorldsRegistrate;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsEnchantments {
	public static final LostWorldsRegistrate REGISTRATE = getRegistrate();

	public static final RegistryEntry<PrecisionEnchantment> PRECISION = REGISTRATE.object("precision").enchantment(LostWorldsEnchantments.HAMMER, PrecisionEnchantment::new).addSlots(EquipmentSlotType.MAINHAND).lang("Precision").rarity(Rarity.RARE).register();
	public static final RegistryEntry<CurseOfBreakingEnchantment> CURSE_OF_BREAKING = REGISTRATE.object("curse_of_breaking").enchantment(LostWorldsEnchantments.HAMMER, CurseOfBreakingEnchantment::new).addSlots(EquipmentSlotType.MAINHAND).lang("Curse of Breaking").rarity(Rarity.VERY_RARE).register();

	public static final EnchantmentType HAMMER = EnchantmentType.create("hammer", (item) -> {
		return item instanceof HammerItem;
	});

	public static Enchantment register(String id, Enchantment enchantment) {
		enchantment.setRegistryName(LostWorldsUtils.rL(id));
		ForgeRegistries.ENCHANTMENTS.register(enchantment);
		return enchantment;
	}

	public static void init() {
	}
}
