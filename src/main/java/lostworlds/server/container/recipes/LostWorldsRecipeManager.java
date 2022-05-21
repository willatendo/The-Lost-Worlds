package lostworlds.server.container.recipes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lostworlds.api.APIAmberRecipe;
import lostworlds.server.entity.utils.enums.DinoTypes;
import lostworlds.server.item.LostWorldsItems;
import lostworlds.server.jei.recipe.AmberDNAExtractorRecipe;
import lostworlds.server.jei.recipe.WaterFuelRecipe;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.runtime.IIngredientManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;

public class LostWorldsRecipeManager {
	public static List<RandomItemGenerator> amberRecipes = new ArrayList<>();

	public static void initAlternateRecipes() {
		RandomItemGenerator builtIn = new RandomItemGenerator(() -> LostWorldsItems.AMBER.get().getDefaultInstance());
		for (DinoTypes types : DinoTypes.values()) {
			builtIn.addOutput(types.getDNA());
			new AmberDNAExtractorRecipe(() -> types.getDNA().get().getDefaultInstance());
		}
		registerAmber(builtIn);
		RandomItemGenerator additional = new RandomItemGenerator(() -> LostWorldsItems.AMBER.get().getDefaultInstance());
		for (Item item : APIAmberRecipe.ADDITIONAL_OUTPUTS) {
			additional.addOutput(() -> item);
			new AmberDNAExtractorRecipe(() -> item.getDefaultInstance());
		}
		registerAmber(additional);
	}

	public static void registerAmber(RandomItemGenerator recipe) {
		amberRecipes.add(recipe);
	}

	public static RandomItemGenerator getItemForRecipe(ItemStack stack) {
		for (RandomItemGenerator recipe : amberRecipes) {
			if (ItemStack.matches(recipe.getInput(), stack)) {
				return recipe;
			}
		}

		return null;
	}

	public static List<AmberDNAExtractorRecipe> getAmberRecipes() {
		List<AmberDNAExtractorRecipe> recipes = new ArrayList<>();
		for (DinoTypes types : DinoTypes.values()) {
			recipes.add(new AmberDNAExtractorRecipe(() -> types.getDNA().get().getDefaultInstance()));
		}
		return recipes;
	}

	public static List<WaterFuelRecipe> getWaterFuelRecipes(IIngredientManager ingredientManager, IJeiHelpers helpers) {
		IGuiHelper guiHelper = helpers.getGuiHelper();
		List<WaterFuelRecipe> fuelRecipes = new ArrayList<>();
		fuelRecipes.add(new WaterFuelRecipe(guiHelper, Collections.singleton(Items.WATER_BUCKET.getDefaultInstance()), 3500));
		fuelRecipes.add(new WaterFuelRecipe(guiHelper, Collections.singleton(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER)), 100));
		return fuelRecipes;
	}
}
