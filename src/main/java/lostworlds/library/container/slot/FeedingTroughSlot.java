package lostworlds.library.container.slot;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class FeedingTroughSlot extends SlotItemHandler
{
	public static final ArrayList<Item> HERBIVORE_FOODS = Lists.newArrayList(Items.WHEAT, Items.WHEAT_SEEDS, Items.MELON, Items.MELON_SEEDS, Items.PUMPKIN, Items.PUMPKIN_SEEDS, Items.CARROT, Items.POTATO, Items.BEETROOT, Items.BEETROOT_SEEDS);
	public static final ArrayList<Item> CARNIVORE_FOODS = Lists.newArrayList(Items.BEEF, Items.PORKCHOP, Items.CHICKEN, Items.RABBIT, Items.RABBIT_FOOT, Items.PUMPKIN_SEEDS, Items.MUTTON);
	public static final ArrayList<Item> PISCAVORE_FOODS = Lists.newArrayList(Items.COD, Items.SALMON, Items.TROPICAL_FISH);
	public static final ArrayList<Item> INSECTIVORE_FOODS = Lists.newArrayList();
	
	public FeedingTroughSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) 
	{
		super(itemHandler, index, xPosition, yPosition);
	}
	
	@Override
	public boolean mayPlace(ItemStack stack) 
	{	
		return HERBIVORE_FOODS.contains(stack.getItem()) || CARNIVORE_FOODS.contains(stack.getItem()) || PISCAVORE_FOODS.contains(stack.getItem()) || INSECTIVORE_FOODS.contains(stack.getItem());
	}
}
