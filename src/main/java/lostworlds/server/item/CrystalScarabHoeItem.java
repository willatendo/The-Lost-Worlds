package lostworlds.server.item;

import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;

public class CrystalScarabHoeItem extends HoeItem {
	public CrystalScarabHoeItem(IItemTier teir, int damageMultiplier, float cooldown, Properties properties) {
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
