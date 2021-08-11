package lostworlds.library.tab;

import lostworlds.content.server.init.BlockInit;
import lostworlds.library.util.ModUtils;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.registries.ForgeRegistries;

public class BlocksTab extends ItemGroup
{
	public BlocksTab() 
	{
		super(ModUtils.ID + ".blocks");
	}

	@Override
	public ItemStack makeIcon() 
	{
		return BlockInit.LIGHT_CONCRETE.asItem().getDefaultInstance();
	}
	
	@Override
	public void fillItemList(NonNullList<ItemStack> stack) 
	{
		for(Block blocks : ForgeRegistries.BLOCKS)
		{
			if(blocks.asItem().getItemCategory() == ModItemGroup.BLOCKS)
			{	
				stack.add(blocks.asItem().getDefaultInstance());
			}
		}
	}
}