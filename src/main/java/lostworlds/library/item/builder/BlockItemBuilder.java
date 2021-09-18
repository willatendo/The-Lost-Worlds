package lostworlds.library.item.builder;

import lostworlds.library.tab.ModItemGroup;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;

public class BlockItemBuilder extends BlockItem
{
	public BlockItemBuilder(Block block) 
	{
		super(block, new Properties().tab(ModItemGroup.BLOCKS));
	}
	
	public BlockItemBuilder(Block block, ItemGroup group) 
	{
		super(block, new Properties().tab(group));
	}
}
