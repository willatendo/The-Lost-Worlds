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

public class CultivatorRecipe implements Recipe<Container> {
	private final ResourceLocation id;
	private Ingredient dnaDisc;
	private final ItemStack output;

	public CultivatorRecipe(ResourceLocation id, Ingredient dnaDisc, ItemStack output) {
		this.id = id;
		this.output = output;
		this.dnaDisc = dnaDisc;
	}

	@Override
	public boolean matches(Container inv, Level worldIn) {
		if (this.dnaDisc.test(inv.getItem(0))) {
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

	public int getCultivatingTime() {
		return 4000;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return LostWorldsRecipes.CULTIVATOR_RECIPE_SERIALIZER;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> nonnulllist = NonNullList.create();
		nonnulllist.add(this.dnaDisc);
		return nonnulllist;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return false;
	}

	@Override
	public RecipeType<?> getType() {
		return LostWorldsRecipes.CULTIVATOR_RECIPE;
	}

	@Override
	public ItemStack getToastSymbol() {
		return LostWorldsBlocks.CULTIVATOR.asStack();
	}
}
