package lostworlds.server.tab;

import java.util.function.Supplier;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.item.LostWorldsEnchantments;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ModTab extends ItemGroup {
	private Supplier<ItemStack> icon;

	public ModTab(String id) {
		super(LostWorldsUtils.ID + "." + id);
	}

	@Override
	public ItemStack makeIcon() {
		return this.icon.get();
	}

	public void setIcon(Supplier<ItemStack> icon) {
		this.icon = icon;
	}

	@Override
	public void fillItemList(NonNullList<ItemStack> itemStacks) {
		super.fillItemList(itemStacks);
		itemStacks.add(EnchantedBookItem.createForEnchantment(new EnchantmentData(LostWorldsEnchantments.PRECISION.get(), 1)));
		itemStacks.add(EnchantedBookItem.createForEnchantment(new EnchantmentData(LostWorldsEnchantments.PRECISION.get(), 2)));
		itemStacks.add(EnchantedBookItem.createForEnchantment(new EnchantmentData(LostWorldsEnchantments.PRECISION.get(), 3)));
		itemStacks.add(EnchantedBookItem.createForEnchantment(new EnchantmentData(LostWorldsEnchantments.CURSE_OF_BREAKING.get(), 1)));
	}
}
