package lostworlds.server.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DNADiscItem extends Item {
	public DNADiscItem(Properties properties) {
		super(properties);
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return false;
	}
}
