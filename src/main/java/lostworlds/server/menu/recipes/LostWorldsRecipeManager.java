package lostworlds.server.menu.recipes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lostworlds.api.APIAmberRecipeType;
import lostworlds.api.APIRegistry;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.item.LostWorldsItems;
import lostworlds.server.jei.recipe.LightningRecipe;
import lostworlds.server.jei.recipe.WaterFuelRecipe;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.runtime.IIngredientManager;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;

public class LostWorldsRecipeManager {
	public static ArrayList<DNAExtractorRecipe> getAmberRecipes() {
		ArrayList<DNAExtractorRecipe> recipes = new ArrayList<>();
		List<Item> tag = new ArrayList<>();
		for (APIAmberRecipeType recipeTypes : APIRegistry.AMBER_RECIPE_TYPES) {
			for (Item item : recipeTypes.outputs()) {
				tag.add(item);
			}
		}
		for (Item types : tag) {
			recipes.add(new DNAExtractorRecipe(LostWorldsUtils.rL("amber_recipes"), Ingredient.of(LostWorldsItems.AMBER.get()), Ingredient.of(LostWorldsItems.EMPTY_VILE.get()), types.getDefaultInstance()));
		}
		return recipes;
	}

	public static ArrayList<LightningRecipe> getLightningRecipes() {
		ArrayList<LightningRecipe> recipes = new ArrayList<>();
		recipes.add(new LightningRecipe(Ingredient.of(LostWorldsItems.CRYSTAL_SCARAB_GEM.get()), LostWorldsItems.CHARGED_CRYSTAL_SCARAB_GEM.asStack()));
		return recipes;
	}

	public static ArrayList<WaterFuelRecipe> getWaterFuelRecipes(IIngredientManager ingredientManager, IJeiHelpers helpers) {
		IGuiHelper guiHelper = helpers.getGuiHelper();
		ArrayList<WaterFuelRecipe> fuelRecipes = new ArrayList<>();
		fuelRecipes.add(new WaterFuelRecipe(guiHelper, Collections.singleton(Items.WATER_BUCKET.getDefaultInstance()), 3500));
		fuelRecipes.add(new WaterFuelRecipe(guiHelper, Collections.singleton(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER)), 100));
		return fuelRecipes;
	}
}
