package lostworlds.library.item.block;

import lostworlds.content.server.init.FoodInit;
import lostworlds.library.tab.ModItemGroup;
import lostworlds.library.util.ModRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class SeedItem extends BlockItem
{
	protected SeedItem(Block block, Properties properties) 
	{
		super(block, properties);
	}
	
	private static Properties properties()
	{
		return new Properties().tab(ModItemGroup.ITEMS);
	}
	
	public static Item create(String plant, Block block)
	{
		Item item = new SeedItem(block, properties().stacksTo(1).food(FoodInit.SEEDS));
		ModRegistry.register(plant + "_seeds", item);
		return item;
	}
}
