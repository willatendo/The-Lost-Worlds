package lostworlds.library.item;

import lostworlds.content.ModUtils;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ModItem extends Item
{
	public ModItem() 
	{
		this(ModUtils.ITEMS);
	}
	
	public ModItem(ItemGroup group) 
	{
		this(new Properties().tab(group));
	}
	
	public ModItem(Food food) 
	{
		this(new Properties().tab(ModUtils.ITEMS).food(food));
	}
	
	public ModItem(int stacksize) 
	{
		super(new Properties().tab(ModUtils.ITEMS).stacksTo(stacksize));
	}
	
	public ModItem(Properties properties) 
	{
		super(properties);
	}
}
