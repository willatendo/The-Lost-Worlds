package lostworlds.library.item;

import lostworlds.content.ModUtils;
import net.minecraft.item.Item;

public class AmberItem extends Item
{
	public AmberItem() 
	{
		super(new Properties().tab(ModUtils.ITEMS).stacksTo(1));
	}
}
