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

public class DNAExtractorRecipe implements Recipe<Container> {
	private final ResourceLocation id;
	private Ingredient input;
	private Ingredient vile;
	private final ItemStack output;

	public DNAExtractorRecipe(ResourceLocation id, Ingredient input, Ingredient vile, ItemStack output) {
		this.id = id;
		this.output = output;
		this.input = input;
		this.vile = vile;
	}

	@Override
	public boolean matches(Container inv, Level worldIn) {
		if (this.input.test(inv.getItem(0))) {
			return true;
		}
		if (this.vile.test(inv.getItem(0))) {
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
		return 60;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return LostWorldsRecipeSerializers.DNA_EXTRACTOR_SERIALIZER.get();
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> nonnulllist = NonNullList.create();
		nonnulllist.add(this.input);
		nonnulllist.add(this.vile);
		return nonnulllist;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return false;
	}

	@Override
	public RecipeType<?> getType() {
		return LostWorldsRecipeTypes.DNA_EXTRACTOR_RECIPE.get();
	}

	@Override
	public ItemStack getToastSymbol() {
		return LostWorldsBlocks.DNA_EXTRACTOR.asStack();
	}
}
