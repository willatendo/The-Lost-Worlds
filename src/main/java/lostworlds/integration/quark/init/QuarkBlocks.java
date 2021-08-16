package lostworlds.integration.quark.init;

import lostworlds.integration.quark.block.VerticalSlabBlock;
import lostworlds.integration.quark.util.QuarkRegistry;
import lostworlds.library.item.builder.BlockItemBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;

public class QuarkBlocks 
{
	public static final Block PERMIAN_STONE_VERTICAL_SLAB = QuarkRegistry.register("permian_stone_vertical_slab", new VerticalSlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Item PERMIAN_STONE_VERTICAL_SLAB_ITEM = QuarkRegistry.register("permian_stone_vertical_slab", new BlockItemBuilder(PERMIAN_STONE_VERTICAL_SLAB));
	
	public static void init() { }
}
