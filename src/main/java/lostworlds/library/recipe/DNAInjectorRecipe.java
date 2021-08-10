package lostworlds.library.recipe;

import lostworlds.content.server.init.BlockInit;
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

public class DNAInjectorRecipe implements IRecipe<IInventory>
{
	private final ResourceLocation id;
	private Ingredient dnaDisc;
	private Ingredient egg;
	private final ItemStack output;
	
	public DNAInjectorRecipe(ResourceLocation id, Ingredient dnaDisc, Ingredient egg, ItemStack output) 
	{
		this.id = id;
		this.output = output;
		this.dnaDisc = dnaDisc;
		this.egg = egg;
	}

	@Override
	public boolean matches(IInventory inv, World worldIn) 
	{
		if(this.dnaDisc.test(inv.getItem(0))) 
		{
			return true;
		}
		if(this.egg.test(inv.getItem(0))) 
		{
			return true;
		}
		return false;
	}

	@Override
	public ItemStack assemble(IInventory inv) 
	{
		return this.output;
	}

	@Override
	public ItemStack getResultItem() 
	{
		return this.output;
	}

	@Override
	public ResourceLocation getId() 
	{
		return this.id;
	}
	
	public int getInjectingTime() 
	{
		return 50;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() 
	{
		return RecipeInit.DNA_INJECTOR_SERIALIZER;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() 
	{
		NonNullList<Ingredient> nonnulllist = NonNullList.create();
		nonnulllist.add(this.dnaDisc);
		nonnulllist.add(this.egg);
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
		return RecipeInit.DNA_INJECTOR_RECIPE;
	}
	
	@Override
	public ItemStack getToastSymbol() 
	{
		return new ItemStack(BlockInit.DNA_INJECTOR.asItem());
	}
}
