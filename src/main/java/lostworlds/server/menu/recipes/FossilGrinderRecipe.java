package lostworlds.server.menu.recipes;

import java.util.Random;

import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.item.LostWorldsItems;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class FossilGrinderRecipe implements Recipe<Container> {
	private final ResourceLocation id;
	private Ingredient fossil;
	public final ItemStack output;
	public final ItemStack broken_fossil = LostWorldsItems.GROUND_FOSSIL.get().getDefaultInstance();
	public final ItemStack broken_plant = LostWorldsItems.PLANT_WASTE.get().getDefaultInstance();
	private final boolean plant;

	public FossilGrinderRecipe(ResourceLocation id, Ingredient fossil, ItemStack output, boolean plant) {
		this.id = id;
		this.output = output;
		this.fossil = fossil;
		this.plant = plant;
	}

	@Override
	public boolean matches(Container inv, Level worldIn) {
		if (this.fossil.test(inv.getItem(0))) {
			return true;
		}
		return false;
	}

	@Override
	public ItemStack assemble(Container inv) {
		Random rand = new Random();
		int chance = rand.nextInt(4);

		if (chance == 0) {
			return this.output;
		} else {
			if (this.plant == true) {
				return this.broken_plant;
			} else {
				return this.broken_fossil;
			}
		}
	}

	@Override
	public ItemStack getResultItem() {
		Random rand = new Random();
		int chance = rand.nextInt(4);

		if (chance == 0) {
			return this.output;
		} else {
			if (this.plant == true) {
				return this.broken_plant;
			} else {
				return this.broken_fossil;
			}
		}
	}

	public NonNullList<ItemStack> getOutputs() {
		NonNullList<ItemStack> outputs = NonNullList.create();
		outputs.add(this.output);
		outputs.add(this.broken_fossil);
		outputs.add(this.broken_plant);
		return outputs;
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	public int getGrindTime() {
		return 300;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return LostWorldsRecipeSerializers.FOSSIL_GRINDER_SERIALIZER.get();
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> nonnulllist = NonNullList.create();
		nonnulllist.add(this.fossil);
		return nonnulllist;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return false;
	}

	@Override
	public RecipeType<?> getType() {
		return LostWorldsRecipeTypes.FOSSIL_GRINDER_RECIPE.get();
	}

	@Override
	public ItemStack getToastSymbol() {
		return LostWorldsBlocks.FOSSIL_GRINDER.asStack();
	}
}
