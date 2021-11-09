package lostworlds.library.item;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class PlantFossilItem extends ModItem
{
	private final ITextComponent name;
	
	public PlantFossilItem(ITextComponent name) 
	{
		this.name = name;
	}
	
	@Override
	public ITextComponent getName(ItemStack stack) 
	{
		return new TranslationTextComponent("item.lostworlds.fossil", this.name, "Fossil");	
	}
}
