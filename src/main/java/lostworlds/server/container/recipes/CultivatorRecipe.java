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

public class CultivatorRecipe implements IRecipe<IInventory> {
	private final ResourceLocation id;
	private Ingredient dnaDisc;
	private final ItemStack output;

	public CultivatorRecipe(ResourceLocation id, Ingredient dnaDisc, ItemStack output) {
		this.id = id;
		this.output = output;
		this.dnaDisc = dnaDisc;
	}

	@Override
	public boolean matches(IInventory inv, World worldIn) {
		if (this.dnaDisc.test(inv.getItem(0))) {
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

	public int getCultivatingTime() {
		return 4000;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
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
	public IRecipeType<?> getType() {
		return LostWorldsRecipes.CULTIVATOR_RECIPE;
	}

	@Override
	public ItemStack getToastSymbol() {
		return LostWorldsBlocks.CULTIVATOR.asStack();
	}
}
