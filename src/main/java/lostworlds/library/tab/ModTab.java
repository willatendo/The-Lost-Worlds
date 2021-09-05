package lostworlds.library.tab;

import java.util.Collections;
import java.util.Comparator;

import lostworlds.library.util.ModUtils;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ModTab extends ItemGroup
{
	private ItemStack icon;
	private Comparator<ItemStack> sorter;
	
	public ModTab(String id, ItemStack icon, Comparator<ItemStack> sorter) 
	{
		super(ModUtils.ID + "." + id);
		this.icon = icon;
		this.sorter = sorter;
	}

	@Override
	public ItemStack makeIcon() 
	{
		return this.icon;
	}
	
	public void setIcon(ItemStack icon)
	{
		this.icon = icon;
	}
	
	@Override
	public void fillItemList(NonNullList<ItemStack> stack) 
	{
		super.fillItemList(stack);
		if(this.sorter != null)
		{
			try 
			{
				Collections.sort(stack, sorter);
			} 
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void setTabSorter(Comparator<ItemStack> tabSorter)
	{
		this.sorter = tabSorter;
	}
}
