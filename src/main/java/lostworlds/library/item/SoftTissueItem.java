package lostworlds.library.item;

import lostworlds.library.entity.DinoTypes;
import lostworlds.library.tab.ModItemGroup;
import lostworlds.library.util.ModRegistry;
import net.minecraft.item.Item;

public class SoftTissueItem extends Item
{
	protected SoftTissueItem() 
	{
		super(new Properties().tab(ModItemGroup.ITEMS));
	}
	
	public static Item create(DinoTypes dino)
	{
		return ModRegistry.register(dino.toString().toLowerCase() + "_soft_tissue", new SoftTissueItem());
	}
}
