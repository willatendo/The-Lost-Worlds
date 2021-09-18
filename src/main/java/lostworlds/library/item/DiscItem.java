package lostworlds.library.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class DiscItem extends Item
{
	public DiscItem(ItemGroup group) 
	{
		super(new Properties().tab(group));
	}
}
