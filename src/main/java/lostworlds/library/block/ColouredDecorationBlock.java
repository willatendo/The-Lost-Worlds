package lostworlds.library.block;

import lostworlds.library.block.builder.BlockAndItemBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.DyeColor;

public class ColouredDecorationBlock extends Block
{
	protected ColouredDecorationBlock() 
	{
		super(AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL));
	}

	public static Block create(DyeColor colour)
	{
		return BlockAndItemBuilder.create(colour == DyeColor.GRAY ? "grey_decoration_block" : colour == DyeColor.LIGHT_GRAY ? "light_grey_decoration_block" : colour.getName().toLowerCase() + "_decoration_block", new ColouredDecorationBlock());
	}
}
