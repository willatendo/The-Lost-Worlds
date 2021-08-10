package lostworlds.library.tab;

import lostworlds.content.server.init.ItemInit;
import lostworlds.library.util.ModUtil;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

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
}