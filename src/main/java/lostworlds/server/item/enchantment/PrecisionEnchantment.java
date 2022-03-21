package lostworlds.server.item.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class PrecisionEnchantment extends Enchantment {
	public PrecisionEnchantment() {
		super(Rarity.RARE, ModEnchantmentTypes.HAMMER, new EquipmentSlotType[] { EquipmentSlotType.MAINHAND });
	}

	@Override
	public int getMinCost(int cost) {
		return 20;
	}

	@Override
	public int getMaxCost(int cost) {
		return 50;
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}
}
