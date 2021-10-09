package lostworlds.library.item.tool;

import java.util.Set;

import com.google.common.collect.Sets;

import lostworlds.content.ModUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;

public class BrushItem extends ToolItem
{
	private static final Set<Material> EFFECTIVE_ON_MATERIALS = Sets.newHashSet(ModMaterials.SOFT);
	private static final Set<Block> EFFECTIVE_ON_BLOCKS = Sets.newHashSet();
	
	public BrushItem(ModItemTier modteir) 
	{
		super(1.0F, -2.4F, modteir, EFFECTIVE_ON_BLOCKS, new Properties().addToolType(ModToolTypes.BRUSH, modteir.getLevel()).tab(ModUtils.ITEMS));
	}

	public BrushItem(ModItemTier modteir, Properties properties) 
	{
		super(1.0F, -2.4F, modteir, EFFECTIVE_ON_BLOCKS, properties.addToolType(ModToolTypes.BRUSH, modteir.getLevel()).tab(ModUtils.ITEMS));
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) 
	{
		Material material = state.getMaterial();
		return EFFECTIVE_ON_MATERIALS.contains(material) ? this.speed : super.getDestroySpeed(stack, state);
	}
}
