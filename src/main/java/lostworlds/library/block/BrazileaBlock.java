package lostworlds.library.block;

import lostworlds.library.item.BrazileaBlockItem;
import lostworlds.library.util.ModRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.LilyPadBlock;
import net.minecraft.item.Item;

public class BrazileaBlock extends LilyPadBlock
{	
	public BrazileaBlock(Properties properties) 
	{
		super(properties);
	}
	
	public static Block create(String id, Block block)
	{
		Item item = new BrazileaBlockItem(block);
		ModRegistry.register(id, block);
		ModRegistry.register(id, item);
		return block;
	}
}
