package lostworlds.library.block;

import lostworlds.library.block.builder.BlockAndItemBuilder;
import net.minecraft.block.Block;
import net.minecraft.item.DyeColor;

public enum VaseBlock 
{
	KYLIX(AbstractVaseBlock.kylix());
	
	private final Block block;
	
	private VaseBlock(Block block) 
	{
		this.block = block;
	}
	
	public Block getBlock()
	{
		return this.block;
	}	
	
	public static Block makeAll()
	{
		for(DyeColor colour : DyeColor.values())
		{
			BlockAndItemBuilder.create(colour.toString().toLowerCase() + "_" + "kylix", AbstractVaseBlock.kylix());
		}
		return null;
	}
	
	public enum Stages
	{
		PRISTINE,
		CHIPPED,
		DAMAGED;
	}
}
