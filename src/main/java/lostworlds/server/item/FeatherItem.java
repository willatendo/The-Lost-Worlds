package lostworlds.server.item;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class FeatherItem extends ModItem {
	private final ITextComponent name;

	public FeatherItem(ITextComponent name) {
		this.name = name;
	}

	@Override
	public ITextComponent getName(ItemStack stack) {
		return new TranslationTextComponent("item.lostworlds.feather", this.name);
	}
}
