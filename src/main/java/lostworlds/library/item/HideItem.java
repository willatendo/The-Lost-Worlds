package lostworlds.library.item;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class HideItem extends ModItem
{
	private final ITextComponent name;

	public HideItem(ITextComponent name) 
	{
		this.name = name;
	}
	
	@Override
	public ITextComponent getName(ItemStack stack) 
	{
		return new TranslationTextComponent("item.lostworlds.hide", this.name);
	}
}
