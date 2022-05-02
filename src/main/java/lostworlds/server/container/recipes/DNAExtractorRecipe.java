package lostworlds.server.container.recipes;

import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class DNAExtractorRecipe implements IRecipe<IInventory> {
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
	public boolean matches(IInventory inv, World worldIn) {
		if (this.input.test(inv.getItem(0))) {
			return true;
		}
		if (this.vile.test(inv.getItem(0))) {
			return true;
		}
		return false;
	}

	@Override
	public ItemStack assemble(IInventory inv) {
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
	public IRecipeSerializer<?> getSerializer() {
		return LostWorldsRecipes.DNA_EXTRACTOR_SERIALIZER.get();
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
	public IRecipeType<?> getType() {
		return LostWorldsRecipes.DNA_EXTRACTOR_RECIPE;
	}

	@Override
	public ItemStack getToastSymbol() {
		return LostWorldsBlocks.DNA_EXTRACTOR.asStack();
	}
}
