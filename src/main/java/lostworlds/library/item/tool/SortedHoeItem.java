package lostworlds.library.item.tool;

import lostworlds.library.item.ISortableItem;
import lostworlds.library.tab.ItemCategories;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;

public class SortedHoeItem extends HoeItem implements ISortableItem
{
	public SortedHoeItem(IItemTier teir, int attackDamage, float speed, Properties properties) 
	{
		super(teir, attackDamage, speed, properties);
	}

	@Override
	public ItemCategories getCategory(int meta) 
	{
		return ItemCategories.GEAR;
	}
}
