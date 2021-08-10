package lostworlds.library.item;

import lostworlds.library.enums.DinoTypes;
import lostworlds.library.tab.ModItemGroup;
import lostworlds.library.util.ModRegistry;
import net.minecraft.item.Item;

/*
 * Author: Willatendo
 * Date: July 8, 2021
 */

public class DiscItem extends Item
{
	protected DiscItem() 
	{
		super(new Properties().tab(ModItemGroup.ITEMS));
	}
	
	public static Item create(DinoTypes dino)
	{
		return ModRegistry.register(dino.toString().toLowerCase() + "_dna_disc", new DiscItem());
	}
}
