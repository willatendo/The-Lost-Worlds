package lostworlds.library.block.builder;

import lostworlds.content.ModUtils;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraftforge.fml.loading.FMLEnvironment;

public class DebugBlockItemBuilder extends BlockItem
{
	public DebugBlockItemBuilder(Block block) 
	{
		super(block, determineProperties());
	}
	
	private static Properties determineProperties()
	{
		return FMLEnvironment.production ? new Properties() : new Properties().tab(ModUtils.BLOCKS);
	}
}
