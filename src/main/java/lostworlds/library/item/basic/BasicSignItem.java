package lostworlds.library.item.basic;

import lostworlds.library.util.ModRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.SignItem;

public class BasicSignItem 
{
	public static Item create(String id, Block standingSign, Block wallSign)
	{
		return ModRegistry.register(id, new SignItem(new Properties().tab(ItemGroup.TAB_MISC), standingSign, wallSign));
	}
}
