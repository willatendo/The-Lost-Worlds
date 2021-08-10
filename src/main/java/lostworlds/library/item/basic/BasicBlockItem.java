package lostworlds.library.item.basic;

import lostworlds.library.tab.ModItemGroup;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class BasicBlockItem extends BlockItem
{
	public BasicBlockItem(Block block) 
	{
		super(block, new Properties().tab(ModItemGroup.BLOCKS));
	}
}
