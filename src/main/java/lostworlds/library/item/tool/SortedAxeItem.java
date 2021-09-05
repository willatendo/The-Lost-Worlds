package lostworlds.library.item.tool;

import lostworlds.library.item.ISortableItem;
import lostworlds.library.tab.ItemCategories;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;

public class SortedAxeItem extends AxeItem implements ISortableItem
{
	public SortedAxeItem(IItemTier teir, float attackDamage, float speed, Properties properties) 
	{
		super(teir, attackDamage, speed, properties);
	}

	@Override
	public ItemCategories getCategory(int meta) 
	{
		return ItemCategories.GEAR;
	}
}
