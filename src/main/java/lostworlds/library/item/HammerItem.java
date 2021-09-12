package lostworlds.library.item;

import java.util.Set;

import com.google.common.collect.Sets;

import lostworlds.library.item.tool.ModToolTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;

public class HammerItem extends ToolItem
{
	private static final Set<Block> EFFECTIVE_ON_BLOCKS = Sets.newHashSet(/*BlockInit.STONE_FOSSIL.get(), BlockInit.TERRACOTTA_FOSSIL.get(), BlockInit.RED_TERRACOTTA_FOSSIL.get(), BlockInit.ORANGE_TERRACOTTA_FOSSIL.get(), BlockInit.YELLOW_TERRACOTTA_FOSSIL.get(), BlockInit.LIME_TERRACOTTA_FOSSIL.get(), BlockInit.GREEN_TERRACOTTA_FOSSIL.get(), BlockInit.CYAN_TERRACOTTA_FOSSIL.get(), BlockInit.LIGHT_BLUE_TERRACOTTA_FOSSIL.get(), BlockInit.BLUE_TERRACOTTA_FOSSIL.get(), BlockInit.MAGENTA_TERRACOTTA_FOSSIL.get(), BlockInit.PURPLE_TERRACOTTA_FOSSIL.get(), BlockInit.PINK_TERRACOTTA_FOSSIL.get(), BlockInit.BROWN_TERRACOTTA_FOSSIL.get(), BlockInit.BLACK_TERRACOTTA_FOSSIL.get(), BlockInit.GREY_TERRACOTTA_FOSSIL.get(), BlockInit.LIGHT_GREY_TERRACOTTA_FOSSIL.get(), BlockInit.WHITE_TERRACOTTA_FOSSIL.get()*/);	

	
	public HammerItem(IItemTier teir, float attackDamage, float speed, Properties properties) 
	{
		super(attackDamage, speed, teir, EFFECTIVE_ON_BLOCKS, properties.addToolType(ModToolTypes.HAMMER, teir.getLevel()));
	}
	
	public float getDestroySpeed(ItemStack stack, BlockState state) 
	{
		Material material = state.getMaterial();
		return material != Material.METAL && material != Material.HEAVY_METAL && material != Material.STONE ? super.getDestroySpeed(stack, state) : this.speed;
	}
}
