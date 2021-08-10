package lostworlds.library.recipe;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

import lostworlds.content.server.init.BlockInit;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.NonNullSupplier;

/*
 * Author: Willatendo
 * Date: July 1, 2021
 */

public class FossilCleanerRecipe 
{
	private NavigableMap<Float, ItemStack> outputMap = new TreeMap<Float, ItemStack>();
	private float totalWeight;
	
	public FossilCleanerRecipe addOutput(final NonNullSupplier<? extends Item> item) 
	{
		totalWeight += 35.0F;
		outputMap.put(totalWeight, item.get().getDefaultInstance());
		return this;
	}
	
	public ItemStack getInput() 
	{
		return new ItemStack(BlockInit.PLASTERED_FOSSIL.asItem());
	}
	
	public ItemStack generateOutput(Random random) 
	{
		if(totalWeight < 100)
		{
			if(random.nextFloat() >= totalWeight * 0.01F)
			{
				return ItemStack.EMPTY;
			}
		}
		float entry = random.nextFloat() * totalWeight;
		return outputMap.higherEntry(entry).getValue().copy();
	}
	
	public NavigableMap<Float, ItemStack> getDisplayMap() 
	{
		return outputMap;
	}
	
	public float getTotalWeight() 
	{
		return totalWeight;
	}
}
