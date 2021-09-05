package lostworlds.library.item;

import lostworlds.library.tab.ItemCategories;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;

public class HammerItem extends AxeItem implements ISortableItem
{
	public HammerItem(IItemTier teir, float attackDamage, float speed, Properties properties) 
	{
		super(teir, attackDamage, speed, properties);
	}

	@Override
	public ItemCategories getCategory(int meta) 
	{
		return ItemCategories.GEAR;
	}
}
