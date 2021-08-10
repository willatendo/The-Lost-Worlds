package lostworlds.library.tab;

import lostworlds.content.server.init.BlockInit;
import lostworlds.library.util.ModUtil;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class BlocksTab extends ItemGroup
{
	public BlocksTab() 
	{
		super(ModUtil.ID + ".blocks");
	}

	@Override
	public ItemStack makeIcon() 
	{
		return BlockInit.LIGHT_CONCRETE.asItem().getDefaultInstance();
	}
}