package lostworlds.server.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class CrystalScarabSwordItem extends SwordItem {
	public CrystalScarabSwordItem(Tier teir, int damageMultiplier, float cooldown, Properties properties) {
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
