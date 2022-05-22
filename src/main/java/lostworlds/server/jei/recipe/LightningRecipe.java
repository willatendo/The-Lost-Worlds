package lostworlds.server.jei.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.core.NonNullList;

public class LightningRecipe {
	private final Ingredient input;
	private final ItemStack output;

	public LightningRecipe(Ingredient input, ItemStack output) {
		this.input = input;
		this.output = output;
	}

	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> nonnulllist = NonNullList.create();
		nonnulllist.add(this.input);
		return nonnulllist;
	}

	public NonNullList<ItemStack> getOutputs() {
		NonNullList<ItemStack> outputs = NonNullList.create();
		outputs.add(this.output);
		return outputs;
	}
}
