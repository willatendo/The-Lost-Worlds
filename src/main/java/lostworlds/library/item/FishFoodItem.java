package lostworlds.library.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class FishFoodItem extends Item
{
	private final ITextComponent name;
	private boolean raw;

	public FishFoodItem(Properties properties, ITextComponent name, boolean raw) 
	{
		super(properties);
		this.name = name;
		this.raw = raw;
	}
	
	@Override
	public ITextComponent getName(ItemStack stack) 
	{
		return raw ? new TranslationTextComponent("item.lostworlds.raw_fish", this.name) : new TranslationTextComponent("item.lostworlds.cooked_fish", this.name);
	}
}
