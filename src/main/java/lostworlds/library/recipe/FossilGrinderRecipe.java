package lostworlds.library.recipe;

import java.util.Random;

import lostworlds.content.server.init.ItemInit;
import lostworlds.content.server.init.RecipeInit;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/*
 * Author: Willatendo
 * Date: July 8, 2021
 */


public class FossilGrinderRecipe implements IRecipe<IInventory>
{
	private final ResourceLocation id;
	private Ingredient fossil;
	public final ItemStack output;
	public final ItemStack broken = ItemInit.GROUND_FOSSIL.getDefaultInstance();
		
	public FossilGrinderRecipe(ResourceLocation id, Ingredient fossil, ItemStack output) 
	{
		this.id = id;
		this.output = output;
		this.fossil = fossil;
	}

	@Override
	public boolean matches(IInventory inv, World worldIn) 
	{
		if(this.fossil.test(inv.getItem(0))) 
		{
			return true;
		}
		return false;
	}

	@Override
	public ItemStack assemble(IInventory inv) 
	{
		Random rand = new Random();
		int chance = rand.nextInt(4);
		
		if(chance == 0)
		{
			return this.output;
		}
		else
		{
			return this.broken;
		}
	}

	@Override
	public ItemStack getResultItem() 
	{
		Random rand = new Random();
		int chance = rand.nextInt(4);
		
		if(chance == 0)
		{
			return this.output;
		}
		else
		{
			return this.broken;
		}
	}
	
	public NonNullList<ItemStack> getOutputs()
	{
		NonNullList<ItemStack> outputs = NonNullList.create();
		outputs.add(this.output);
		outputs.add(this.broken);
		return outputs;
	}
	
	@Override
	public ResourceLocation getId() 
	{
		return this.id;
	}
	
	public int getGrindTime() 
	{
		return 300;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() 
	{
		return RecipeInit.FOSSIL_GRINDER_SERIALIZER;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() 
	{
		NonNullList<Ingredient> nonnulllist = NonNullList.create();
		nonnulllist.add(this.fossil);
		return nonnulllist;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) 
	{
		return false;
	}

	@Override
	public IRecipeType<?> getType() 
	{
		return RecipeInit.FOSSIL_GRINDER_RECIPE;
	}
}
