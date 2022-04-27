package lostworlds.server.item.enchantment;

import lostworlds.server.item.HammerItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class PrecisionEnchantment extends Enchantment {
	public PrecisionEnchantment(Rarity rarity, EnchantmentType type, EquipmentSlotType[] slot) {
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
		return 3;
	}

	@Override
	protected boolean checkCompatibility(Enchantment enchantment) {
		return enchantment instanceof CurseOfBreakingEnchantment ? false : super.checkCompatibility(enchantment);
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return stack.getItem() instanceof HammerItem;
	}
}
