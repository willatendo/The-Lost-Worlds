package lostworlds.library.util;

import javax.annotation.Nonnull;

import com.google.common.base.Predicate;

import lostworlds.mixin.ItemMixin;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class AlphabeticalItemCategoryFillerUtil 
{
	private final Predicate<Item> shouldInclude;

	public AlphabeticalItemCategoryFillerUtil(Predicate<Item> shouldInclude) 
	{
		this.shouldInclude = shouldInclude;
	}

	public static <I extends Item> AlphabeticalItemCategoryFillerUtil forClass(Class<I> clazz) 
	{
		return new AlphabeticalItemCategoryFillerUtil(clazz::isInstance);
	}

	public static boolean isAllowedInTab(Item item, @Nonnull ItemGroup tab) 
	{
		return ((ItemMixin) item).callAllowdedIn(tab);
	}

	public void fillItem(Item item, ItemGroup group, NonNullList<ItemStack> items) 
	{
		if(isAllowedInTab(item, group)) 
		{
			ResourceLocation location = item.getRegistryName();
			if(location != null) 
			{
				String itemName = location.getPath();
				int insert = -1;
				Predicate<Item> shouldInclude = this.shouldInclude;
				for(int i = 0; i < items.size(); i++) 
				{
					Item next = items.get(i).getItem();
					if(shouldInclude.test(next)) 
					{
						ResourceLocation nextName = next.getRegistryName();
						if(nextName == null || itemName.compareTo(nextName.getPath()) > 0) 
						{
							insert = i + 1;
						} 
						else if(insert == -1) 
						{
							insert += i + 1;
						} 
						else 
						{
							break;
						}
					}
				}
				if(insert == -1) 
				{
					items.add(new ItemStack(item));
				} 
				else
				{
					items.add(insert, new ItemStack(item));
				}
			} 
			else 
			{
				items.add(new ItemStack(item));
			}
		}
	}
}
