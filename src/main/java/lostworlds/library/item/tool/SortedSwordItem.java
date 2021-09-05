package lostworlds.library.item.tool;

import lostworlds.library.item.ISortableItem;
import lostworlds.library.tab.ItemCategories;
import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;

public class SortedSwordItem extends SwordItem implements ISortableItem
{
	public SortedSwordItem(IItemTier teir, int attackDamage, float speed, Properties properties) 
	{
		super(teir, attackDamage, speed, properties);
	}

	@Override
	public ItemCategories getCategory(int meta) 
	{
		return ItemCategories.GEAR;
	}
}
