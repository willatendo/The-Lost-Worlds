package lostworlds.library.item;

import lostworlds.library.block.ModStandingSignBlock;
import lostworlds.library.block.ModWallSignBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SignItem;

public class ModSignItem extends SignItem
{
	public ModSignItem(Properties properties, ModStandingSignBlock standingBlock, ModWallSignBlock wallBlock) 
	{
		super(properties, standingBlock, wallBlock);
	}
	
	@Override
	public int getBurnTime(ItemStack itemStack) 
	{
		return 200;
	}
}
