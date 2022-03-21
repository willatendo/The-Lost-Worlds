package lostworlds.server.item;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class SoftTissueItem extends ModItem {
	private final ITextComponent name;

	public SoftTissueItem(ITextComponent name) {
		this.name = name;
	}

	@Override
	public ITextComponent getName(ItemStack stack) {
		return new TranslationTextComponent("item.lostworlds.soft_tissue", this.name);
	}
}
