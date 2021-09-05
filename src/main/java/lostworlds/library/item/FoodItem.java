package lostworlds.library.item;

import lostworlds.content.server.init.FoodInit;
import lostworlds.library.tab.ItemCategories;
import lostworlds.library.tab.ModItemGroup;
import lostworlds.library.util.ModRegistry;
import net.minecraft.item.Item;

public class FoodItem extends Item implements ISortableItem
{	
	protected FoodItem(Properties properties)
	{
		super(properties);
	}
	
	private static Properties properties()
	{
		return new Properties().tab(ModItemGroup.ITEMS);
	}
	
	public static Item create(FoodType type)
	{
		Item item = new FoodItem(type == FoodType.FERN_LEAVES ? properties().stacksTo(16).food(FoodInit.FERN_LEAVES) : type == FoodType.COOKED_FERN_LEAVES ? properties().stacksTo(16).food(FoodInit.COOKED_LEAVES) : type == FoodType.PALEO_SALAD ? properties().food(FoodInit.PALEO_SALAD) : properties());
		ModRegistry.register(type.toString().toLowerCase(), item);
		return item;
	}
	
	@Override
	public ItemCategories getCategory(int meta) 
	{
		return ItemCategories.FOOD;
	}
	
	public enum FoodType
	{
		FERN_LEAVES,
		COOKED_FERN_LEAVES,
		PALEO_SALAD;
	}
}
