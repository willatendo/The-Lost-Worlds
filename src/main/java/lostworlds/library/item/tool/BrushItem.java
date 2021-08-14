package lostworlds.library.item.tool;

import java.util.Set;

import com.google.common.collect.Sets;

import lostworlds.library.tab.ModItemGroup;
import lostworlds.library.util.ModRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;

public class BrushItem extends ToolItem
{
	private static final Set<Material> EFFECTIVE_ON_MATERIALS = Sets.newHashSet(ModMaterials.MADE_FOR_BRUSH);
	private static final Set<Block> EFFECTIVE_ON_BLOCKS = Sets.newHashSet(/*BlockInit.STONE_FOSSIL.get(), BlockInit.TERRACOTTA_FOSSIL.get(), BlockInit.RED_TERRACOTTA_FOSSIL.get(), BlockInit.ORANGE_TERRACOTTA_FOSSIL.get(), BlockInit.YELLOW_TERRACOTTA_FOSSIL.get(), BlockInit.LIME_TERRACOTTA_FOSSIL.get(), BlockInit.GREEN_TERRACOTTA_FOSSIL.get(), BlockInit.CYAN_TERRACOTTA_FOSSIL.get(), BlockInit.LIGHT_BLUE_TERRACOTTA_FOSSIL.get(), BlockInit.BLUE_TERRACOTTA_FOSSIL.get(), BlockInit.MAGENTA_TERRACOTTA_FOSSIL.get(), BlockInit.PURPLE_TERRACOTTA_FOSSIL.get(), BlockInit.PINK_TERRACOTTA_FOSSIL.get(), BlockInit.BROWN_TERRACOTTA_FOSSIL.get(), BlockInit.BLACK_TERRACOTTA_FOSSIL.get(), BlockInit.GREY_TERRACOTTA_FOSSIL.get(), BlockInit.LIGHT_GREY_TERRACOTTA_FOSSIL.get(), BlockInit.WHITE_TERRACOTTA_FOSSIL.get()*/);	
	
	protected BrushItem(ModItemTeir modteir, Properties properties) 
	{
		super(1.0F, -2.4F, modteir, EFFECTIVE_ON_BLOCKS, properties.addToolType(ModToolTypes.BRUSH, modteir.getLevel()).tab(ModItemGroup.ITEMS));
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) 
	{
		Material material = state.getMaterial();
		return EFFECTIVE_ON_MATERIALS.contains(material) ? this.speed : super.getDestroySpeed(stack, state);
	}
	
	public static Item create(ModItemTeir teir, Properties properties)
	{
		Item item = new BrushItem(teir, properties);
		ModRegistry.register(teir.toString().toLowerCase() + "_brush", item);
		return item;
	}
	
	private static Item create(ModItemTeir teir)
	{
		Item item = new BrushItem(teir, new Properties());
		ModRegistry.register(teir.toString().toLowerCase() + "_brush", item);
		return item;
	}
	
	public static Item createLeather()
	{
		return create(ModItemTeir.LEATHER);
	}
	
	public static Item createGold()
	{
		return create(ModItemTeir.GOLD);
	}
	
	public static Item createIron()
	{
		return create(ModItemTeir.IRON);
	}
	
	public static Item createDiamond()
	{
		return create(ModItemTeir.DIAMOND);
	}
	
	public static Item createNetherite()
	{
		return create(ModItemTeir.NETHERITE);
	}
}
