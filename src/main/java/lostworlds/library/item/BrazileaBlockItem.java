package lostworlds.library.item;

import lostworlds.library.tab.ModItemGroup;
import net.minecraft.block.Block;
import net.minecraft.item.LilyPadItem;

public class BrazileaBlockItem extends LilyPadItem
{
	public BrazileaBlockItem(Block block) 
	{
		super(block, new Properties().tab(ModItemGroup.BLOCKS));
	}
}
