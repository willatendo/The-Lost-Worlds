package lostworlds.server.menu.recipes;

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

public class DNAInjectorRecipe implements Recipe<Container> {
	private final ResourceLocation id;
	private Ingredient dnaDisc;
	private Ingredient egg;
	private final ItemStack output;

	public DNAInjectorRecipe(ResourceLocation id, Ingredient dnaDisc, Ingredient egg, ItemStack output) {
		this.id = id;
		this.output = output;
		this.dnaDisc = dnaDisc;
		this.egg = egg;
	}

	@Override
	public boolean matches(Container inv, Level worldIn) {
		if (this.dnaDisc.test(inv.getItem(0))) {
			return true;
		}
		if (this.egg.test(inv.getItem(0))) {
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

	public int getInjectingTime() {
		return 50;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return LostWorldsRecipes.DNA_INJECTOR_SERIALIZER.get();
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> nonnulllist = NonNullList.create();
		nonnulllist.add(this.dnaDisc);
		nonnulllist.add(this.egg);
		return nonnulllist;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return false;
	}

	@Override
	public RecipeType<?> getType() {
		return LostWorldsRecipes.DNA_INJECTOR_RECIPE;
	}

	@Override
	public ItemStack getToastSymbol() {
		return LostWorldsBlocks.DNA_INJECTOR.asStack();
	}
}
