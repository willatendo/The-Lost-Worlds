package lostworlds.library.item;

import lostworlds.library.tab.ItemCategories;
import lostworlds.library.tab.ModItemGroup;
import lostworlds.library.util.ModRegistry;
import net.minecraft.item.Item;

public class BoneMealItem extends net.minecraft.item.BoneMealItem implements ISortableItem
{
	protected BoneMealItem() 
	{
		super(new Properties().tab(ModItemGroup.ITEMS));
	}
	
	@Override
	public ItemCategories getCategory(int meta) 
	{
		return ItemCategories.MISS;
	}
	
	public static Item create(String id)
	{
		Item item = new BoneMealItem();
		ModRegistry.register(id, item);
		return item;
	}
}
