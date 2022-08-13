package lostworlds.server.menu.recipes;

import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class TimeMachineRecipe implements Recipe<Container> {
	protected final Ingredient book;
	protected final Ingredient power;
	public final ItemStack result;
	protected final ResourceLocation id;

	public TimeMachineRecipe(ResourceLocation recipeId, ItemStack result, Ingredient book, Ingredient power) {
		this.id = recipeId;
		this.result = result;
		this.book = book;
		this.power = power;
	}

	@Override
	public RecipeType<?> getType() {
		return LostWorldsRecipeTypes.TIME_MACHINE_RECIPE.get();
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return LostWorldsRecipeSerializers.TIME_MACHINE_SERIALIZER.get();
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	@Override
	public ItemStack getResultItem() {
		return this.result;
	}

	public NonNullList<ItemStack> getOutputs() {
		NonNullList<ItemStack> output = NonNullList.create();
		output.add(this.result);
		return output;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> nonnulllist = NonNullList.create();
		nonnulllist.add(this.book);
		nonnulllist.add(this.power);
		return nonnulllist;
	}

	@Override
	public boolean canCraftInDimensions(int x, int y) {
		return true;
	}

	@Override
	public ItemStack assemble(Container inv) {
		return this.result.copy();
	}

	@Override
	public boolean matches(Container inv, Level worldIn) {
		if (this.book.test(inv.getItem(0)) && this.power.test(inv.getItem(1))) {
			return true;
		}
		if (this.power.test(inv.getItem(0)) && this.book.test(inv.getItem(1))) {
			return true;
		}
		return false;
	}

	@Override
	public ItemStack getToastSymbol() {
		return LostWorldsBlocks.TIME_MACHINE.asStack();
	}
}
