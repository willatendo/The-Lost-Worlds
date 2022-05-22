package lostworlds.server.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;

public class CrystalScarabShovelItem extends ShovelItem {
	public CrystalScarabShovelItem(Tier teir, float damageMultiplier, float cooldown, Properties properties) {
		super(teir, damageMultiplier, cooldown, properties);
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return false;
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return true;
	}
}
