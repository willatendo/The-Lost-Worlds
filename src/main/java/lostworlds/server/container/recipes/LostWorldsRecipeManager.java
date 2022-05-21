package lostworlds.server.container.recipes;

import java.util.ArrayList;
import java.util.Collections;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.utils.enums.DinoTypes;
import lostworlds.server.item.LostWorldsItems;
import lostworlds.server.jei.recipe.LightningRecipe;
import lostworlds.server.jei.recipe.WaterFuelRecipe;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.runtime.IIngredientManager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;

public class LostWorldsRecipeManager {
	public static ArrayList<DNAExtractorRecipe> getAmberRecipes() {
		ArrayList<DNAExtractorRecipe> recipes = new ArrayList<>();
		for (DinoTypes types : DinoTypes.values()) {
			recipes.add(new DNAExtractorRecipe(LostWorldsUtils.rL("amber_recipes"), Ingredient.of(LostWorldsItems.AMBER.get()), Ingredient.of(LostWorldsItems.EMPTY_VILE.get()), types.getDNA().get().getDefaultInstance()));
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
