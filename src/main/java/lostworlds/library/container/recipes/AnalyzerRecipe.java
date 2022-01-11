package lostworlds.library.container.recipes;

import lostworlds.content.server.init.BlockInit;
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

public class AnalyzerRecipe implements IRecipe<IInventory> {
	private final ResourceLocation id;
	private Ingredient dna;
	private Ingredient dnaDisc;
	private final ItemStack output;

	public AnalyzerRecipe(ResourceLocation id, Ingredient dna, Ingredient dnaDisc, ItemStack output) {
		this.id = id;
		this.output = output;
		this.dna = dna;
		this.dnaDisc = dnaDisc;
	}

	@Override
	public boolean matches(IInventory inv, World worldIn) {
		if (this.dna.test(inv.getItem(0))) {
			return true;
		}
		if (this.dnaDisc.test(inv.getItem(0))) {
			return true;
		}
		return false;
	}

	@Override
	public ItemStack assemble(IInventory inv) {
		return this.output;
	}

	public NonNullList<ItemStack> getOutputs() {
		NonNullList<ItemStack> outputs = NonNullList.create();
		outputs.add(this.output);
		return outputs;
	}

	@Override
	public ItemStack getResultItem() {
		return this.output;
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	public int getAnalysingTime() {
		return 1000;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		return RecipeInit.ANALYZER_SERIALIZER;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> nonnulllist = NonNullList.create();
		nonnulllist.add(this.dna);
		nonnulllist.add(this.dnaDisc);
		return nonnulllist;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return false;
	}

	@Override
	public IRecipeType<?> getType() {
		return RecipeInit.ANALYZER_RECIPE;
	}

	@Override
	public ItemStack getToastSymbol() {
		return new ItemStack(BlockInit.ANALYZER.asItem());
	}
}
