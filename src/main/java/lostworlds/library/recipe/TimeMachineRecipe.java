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

public class TimeMachineRecipe implements IRecipe<IInventory> 
{
	protected final Ingredient book;
	protected final Ingredient power;
	protected final ItemStack result;
	protected final ResourceLocation id;

	public TimeMachineRecipe(ResourceLocation recipeId, ItemStack result, Ingredient book, Ingredient power) 
	{
		this.id = recipeId;
		this.result = result;
		this.book = book;
		this.power = power;
	}

	@Override
	public IRecipeType<?> getType() 
	{
		return RecipeInit.TIME_MACHINE_RECIPE;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() 
	{
		return RecipeInit.TIME_MACHINE_SERIALIZER;
	}

	@Override
	public ResourceLocation getId() 
	{
		return this.id;
	}

	@Override
	public ItemStack getResultItem() 
	{
		return this.result;
	}
	
	public NonNullList<ItemStack> getOutput()
	{
		NonNullList<ItemStack> output = NonNullList.create();
		output.add(this.result);
		return output;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() 
	{
		NonNullList<Ingredient> nonnulllist = NonNullList.create();
		nonnulllist.add(this.book);
		nonnulllist.add(this.power);
		return nonnulllist;
	}

	@Override
	public boolean canCraftInDimensions(int x, int y) 
	{
		return true;
	}

	@Override
	public ItemStack assemble(IInventory inv) 
	{
		return this.result.copy();
	}
	
	@Override
	public boolean matches(IInventory inv, World worldIn) 
	{
		if(this.book.test(inv.getItem(0)) && this.power.test(inv.getItem(1))) 
		{
			return true;
		}
		if(this.power.test(inv.getItem(0)) && this.book.test(inv.getItem(1)))
		{
			return true;
		}
		return false;
	}
	
	@Override
	public ItemStack getToastSymbol() 
	{
		return new ItemStack(BlockInit.TIME_MACHINE);
	}
}
