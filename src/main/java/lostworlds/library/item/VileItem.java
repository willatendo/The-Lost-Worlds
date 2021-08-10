package lostworlds.library.item;

import lostworlds.library.enums.DinoTypes;
import lostworlds.library.tab.ModItemGroup;
import lostworlds.library.util.ModRegistry;
import net.minecraft.item.Item;

/*
 * Author: Willatendo
 * Date: July 2, 2021
 */

public class VileItem extends Item
{
	protected VileItem() 
	{
		super(new Properties().tab(ModItemGroup.ITEMS));
	}
	
	public static Item create(DinoTypes dino)
	{
		return ModRegistry.register(dino.toString().toLowerCase() + "_blood_vile", new VileItem());
	}
}
