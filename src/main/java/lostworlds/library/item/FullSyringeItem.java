package lostworlds.library.item;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class FullSyringeItem extends ModItem
{
	private final ITextComponent name;
	
	public FullSyringeItem(ITextComponent name) 
	{
		super(1);
		this.name = name;
	}
	
	@Override
	public ITextComponent getName(ItemStack stack) 
	{
		return new TranslationTextComponent("item.lostworlds.syringe", this.name);	
	}
}
