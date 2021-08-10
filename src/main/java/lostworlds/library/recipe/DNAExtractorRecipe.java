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

public class DNAExtractorRecipe implements IRecipe<IInventory>
{
	private final ResourceLocation id;
	private Ingredient softTissue;
	private Ingredient vile;
	private final ItemStack output;
	
	public DNAExtractorRecipe(ResourceLocation id, Ingredient softTissue, Ingredient vile, ItemStack output) 
	{
		this.id = id;
		this.output = output;
		this.softTissue = softTissue;
		this.vile = vile;
	}

	@Override
	public boolean matches(IInventory inv, World worldIn) 
	{
		if(this.softTissue.test(inv.getItem(0))) 
		{
			return true;
		}
		if(this.vile.test(inv.getItem(0))) 
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
	
	public int getExtractingTime() 
	{
		return 60;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() 
	{
		return RecipeInit.DNA_EXTRACTOR_SERIALIZER;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() 
	{
		NonNullList<Ingredient> nonnulllist = NonNullList.create();
		nonnulllist.add(this.softTissue);
		nonnulllist.add(this.vile);
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
		return RecipeInit.DNA_EXTRACTOR_RECIPE;
	}
	
	@Override
	public ItemStack getToastSymbol() 
	{
		return new ItemStack(BlockInit.DNA_EXTRACTOR.asItem());
	}
}
