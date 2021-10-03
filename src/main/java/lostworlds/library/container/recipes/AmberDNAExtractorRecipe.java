package lostworlds.library.container.recipes;

import java.util.Random;

import lostworlds.content.server.init.BlockInit;
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

public class AmberDNAExtractorRecipe implements IRecipe<IInventory>
{
	private final ResourceLocation id;
	private Ingredient softTissue;
	private Ingredient vile;
	private ItemStack output = ItemStack.EMPTY;
	
	public AmberDNAExtractorRecipe(ResourceLocation id, Ingredient softTissue, Ingredient vile) 
	{
		this.id = id;
		this.softTissue = softTissue;
		this.vile = vile;
	}

	public ItemStack generateRandom()
	{
		return this.output = RecipeManager.getItemForRecipe(ItemInit.AMBER.getDefaultInstance()).generateOutput(new Random());
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
	
	public NonNullList<ItemStack> getOutputs()
	{
		NonNullList<ItemStack> outputs = NonNullList.create();
		outputs.add(this.output);
		return outputs;
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
		return RecipeInit.AMBER_DNA_EXTRACTOR_RECIPE_SERIALIZER;
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
		return RecipeInit.AMBER_DNA_EXTRACTOR_RECIPE;
	}
	
	@Override
	public ItemStack getToastSymbol() 
	{
		return new ItemStack(BlockInit.DNA_EXTRACTOR.asItem());
	}
}
