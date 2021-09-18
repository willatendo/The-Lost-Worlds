package lostworlds.library.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class DNAItem extends Item
{
	public DNAItem(ItemGroup group) 
	{
		super(new Properties().tab(group));
	}
}
