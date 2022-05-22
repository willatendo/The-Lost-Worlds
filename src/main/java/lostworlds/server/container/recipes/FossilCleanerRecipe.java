package lostworlds.server.container.recipes;

import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class FossilCleanerRecipe implements Recipe<Container> {
	private final ResourceLocation id;
	private Ingredient input;
	private final ItemStack output;

	public FossilCleanerRecipe(ResourceLocation id, Ingredient input, ItemStack output) {
		this.id = id;
		this.output = output;
		this.input = input;
	}

	@Override
	public boolean matches(Container inv, Level world) {
		if (this.input.test(inv.getItem(0))) {
			return true;
		}
		return false;
	}

	@Override
	public ItemStack assemble(Container inv) {
		return this.output;
	}

	@Override
	public ItemStack getResultItem() {
		return this.output;
	}

	public NonNullList<ItemStack> getOutputs() {
		NonNullList<ItemStack> outputs = NonNullList.create();
		outputs.add(this.output);
		return outputs;
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	public int getExtractingTime() {
		return 1000;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return LostWorldsRecipes.FOSSIL_CLEANER_SERIALIZER.get();
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> nonnulllist = NonNullList.create();
		nonnulllist.add(this.input);
		return nonnulllist;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return false;
	}

	@Override
	public RecipeType<?> getType() {
		return LostWorldsRecipes.FOSSIL_CLEANER_RECIPE;
	}

	@Override
	public ItemStack getToastSymbol() {
		return LostWorldsBlocks.FOSSIL_CLEANER.asStack();
	}
}
