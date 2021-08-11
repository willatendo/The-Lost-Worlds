package lostworlds.library.tab;

import lostworlds.content.server.init.ItemInit;
import lostworlds.library.util.ModUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemsTab extends ItemGroup
{
	public ItemsTab() 
	{
		super(ModUtil.ID + ".items");
	}

	@Override
	public ItemStack makeIcon() 
	{
		return ItemInit.WET_PAPER.getDefaultInstance();
	}
	
	@Override
	public void fillItemList(NonNullList<ItemStack> stack) 
	{
		for(Item items : ForgeRegistries.ITEMS)
		{
			if(items.getItemCategory() == ModItemGroup.ITEMS)
			{	
				stack.add(items.getDefaultInstance());
			}
		}
	}
}