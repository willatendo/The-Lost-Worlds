package lostworlds.client.jei.recipe;

import lostworlds.server.item.LostWorldsItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;

public class AmberDNAExtractorRecipe {
	private final ItemStack output;

	public AmberDNAExtractorRecipe(ItemStack output) {
		this.output = output;
	}

	public int getExtractingTime() {
		return 60;
	}

	public NonNullList<ItemStack> getOutputs() {
		NonNullList<ItemStack> nonnulllist = NonNullList.create();
		nonnulllist.add(this.output);
		return nonnulllist;
	}

	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> nonnulllist = NonNullList.create();
		nonnulllist.add(Ingredient.of(LostWorldsItems.AMBER));
		nonnulllist.add(Ingredient.of(LostWorldsItems.EMPTY_VILE));
		return nonnulllist;
	}
}