package lostworlds.library.item;

import lostworlds.library.tab.ItemCategories;
import net.minecraft.item.Item;

public class SortedItem extends Item implements ISortableItem
{
	private ItemCategories category;
	
	public SortedItem(ItemCategories category, Properties properties) 
	{
		super(properties);
		this.category = category;
	}

	@Override
	public ItemCategories getCategory(int meta) 
	{
		return this.category;
	}
}
