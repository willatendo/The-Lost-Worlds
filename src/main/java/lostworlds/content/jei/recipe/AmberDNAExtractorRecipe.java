package lostworlds.content.jei.recipe;

import lostworlds.content.server.init.ItemInit;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;

public class AmberDNAExtractorRecipe 
{
	private final ItemStack output;

	public AmberDNAExtractorRecipe(ItemStack output) 
	{
		this.output = output;
	}
	
	public int getExtractingTime() 
	{
		return 60;
	}

	public NonNullList<ItemStack> getOutputs() 
	{
		NonNullList<ItemStack> nonnulllist = NonNullList.create();
		nonnulllist.add(this.output);
		return nonnulllist;
	}

	public NonNullList<Ingredient> getIngredients() 
	{
		NonNullList<Ingredient> nonnulllist = NonNullList.create();
		nonnulllist.add(Ingredient.of(ItemInit.AMBER));
		nonnulllist.add(Ingredient.of(ItemInit.EMPTY_VILE));
		return nonnulllist;
	}
}
