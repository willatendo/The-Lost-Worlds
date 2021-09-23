package lostworlds.library.item.block;

import lostworlds.content.ModUtils;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class GroupedBlockItem extends BlockItem
{
	public GroupedBlockItem(Block block) 
	{
		super(block, new Properties().tab(ModUtils.BLOCKS));
	}
}
