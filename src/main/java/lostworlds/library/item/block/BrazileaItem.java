package lostworlds.library.item.block;

import lostworlds.content.ModUtils;
import net.minecraft.block.Block;
import net.minecraft.item.LilyPadItem;

public class BrazileaItem extends LilyPadItem
{
	public BrazileaItem(Block block) 
	{
		super(block, new Properties().tab(ModUtils.LOST_WORLDS));
	}
}
