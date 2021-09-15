package lostworlds.library.item;

import lostworlds.library.entity.DinoTypes;
import lostworlds.library.tab.ModItemGroup;
import lostworlds.library.util.ModRegistry;
import net.minecraft.item.Item;

public class DNAItem extends Item
{
	protected DNAItem() 
	{
		super(new Properties().tab(ModItemGroup.ITEMS));
	}
	
	public static Item create(DinoTypes dino)
	{
		return ModRegistry.register(dino.toString().toLowerCase() + "_dna", new DNAItem());
	}
}
