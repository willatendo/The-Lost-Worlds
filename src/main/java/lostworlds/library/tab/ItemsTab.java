package lostworlds.library.tab;

import lostworlds.content.server.init.ItemInit;
import lostworlds.library.util.ModUtil;
import net.minecraft.block.Block;
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
		for(Block blocks : ForgeRegistries.BLOCKS)
		{
			if(blocks.asItem().getItemCategory() == ModItemGroup.ITEMS)
			{	
				stack.add(blocks.asItem().getDefaultInstance());
			}
		}
	}
}