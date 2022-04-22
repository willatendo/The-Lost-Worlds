package lostworlds.server.item.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class CurseOfBreakingEnchantment extends Enchantment {
	public CurseOfBreakingEnchantment(Rarity rarity, EnchantmentType type, EquipmentSlotType[] slot) {
		super(rarity, type, slot);
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
		return 1;
	}

	@Override
	public boolean isCurse() {
		return true;
	}

	@Override
	protected boolean checkCompatibility(Enchantment enchantment) {
		return enchantment instanceof PrecisionEnchantment ? false : super.checkCompatibility(enchantment);
	}
}
