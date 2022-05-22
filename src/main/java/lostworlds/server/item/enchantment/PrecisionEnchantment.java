package lostworlds.server.item.enchantment;

import lostworlds.server.item.HammerItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class PrecisionEnchantment extends Enchantment {
	public PrecisionEnchantment(Rarity rarity, EnchantmentCategory type, EquipmentSlot[] slot) {
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
