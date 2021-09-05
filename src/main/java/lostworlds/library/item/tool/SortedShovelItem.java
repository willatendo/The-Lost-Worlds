package lostworlds.library.item.tool;

import lostworlds.library.item.ISortableItem;
import lostworlds.library.tab.ItemCategories;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ShovelItem;

public class SortedShovelItem extends ShovelItem implements ISortableItem
{
	public SortedShovelItem(IItemTier teir, float attackDamage, float speed, Properties properties) 
	{
		super(teir, attackDamage, speed, properties);
	}

	@Override
	public ItemCategories getCategory(int meta) 
	{
		return ItemCategories.GEAR;
	}
}
