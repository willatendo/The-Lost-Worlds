package lostworlds.library.tab;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;

import net.minecraft.item.ItemStack;

public class OrderTabs 
{
	public static Map<ItemCategories, List<StackSorted>> sortMapItems = Maps.newHashMap();
	
	public static void finaliseItemsSort()
	{
		List<StackSorted> itemOrder = Lists.newArrayList();
		for(ItemCategories categories : ItemCategories.values())
		{
			List stackSorted = sortMapItems.get(categories);
			if(stackSorted != null)
			{
				itemOrder.addAll(stackSorted);
			}
			else
			{
				System.out.println("ERROR: null sort stack: " + categories.toString());
			}
		}
		
		Comparator<ItemStack> tabSorterItems = Ordering.explicit(itemOrder).onResultOf(input -> new StackSorted(input.getItem(), input.getDamageValue()));
		
		ModItemGroup.ITEMS.setTabSorter(tabSorterItems);
	}
	
	public static void finaliseBlocksSort()
	{
		List<StackSorted> itemOrder = Lists.newArrayList();
		for(ItemCategories categories : ItemCategories.values())
		{
			List stackSorted = sortMapItems.get(categories);
			if(stackSorted != null)
			{
				itemOrder.addAll(stackSorted);
			}
			else
			{
				System.out.println("ERROR: null sort stack: " + categories.toString());
			}
		}
		
		Comparator<ItemStack> tabSorterItems = Ordering.explicit(itemOrder).onResultOf(input -> new StackSorted(input.getItem(), input.getDamageValue()));
		
		ModItemGroup.ITEMS.setTabSorter(tabSorterItems);
	}
}
