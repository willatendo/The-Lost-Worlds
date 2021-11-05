package lostworlds.library.container.recipes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lostworlds.content.jei.recipe.WaterFuelRecipe;
import lostworlds.content.server.init.ItemInit;
import lostworlds.library.entity.DinoTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.runtime.IIngredientManager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;

public class RecipeManager 
{
	public static List<RandomRecipe> amberRecipes = new ArrayList<>();
	
	public static void initAlternateRecipes()
	{
		RandomRecipe amber = new RandomRecipe(ItemInit.AMBER.getDefaultInstance());
		for(DinoTypes types : DinoTypes.values()) 
		{
			amber.addOutput(() -> types.getDNA());
		}
		registerAmber(amber);
	}
	
	public static void registerAmber(RandomRecipe recipe) 
	{
		amberRecipes.add(recipe);
	}
	
	public static RandomRecipe getItemForRecipe(ItemStack stack) 
	{
		for(RandomRecipe recipe : amberRecipes) 
		{
			if(ItemStack.matches(recipe.getInput(), stack))
			{
				return recipe;
			}
		}
		
		return null;
	}
	
	public static List<WaterFuelRecipe> getWaterFuelRecipes(IIngredientManager ingredientManager, IJeiHelpers helpers) 
	{
		IGuiHelper guiHelper = helpers.getGuiHelper();
		List<WaterFuelRecipe> fuelRecipes = new ArrayList<>();
		fuelRecipes.add(new WaterFuelRecipe(guiHelper, Collections.singleton(Items.WATER_BUCKET.getDefaultInstance()), 3500));
		fuelRecipes.add(new WaterFuelRecipe(guiHelper, Collections.singleton(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER)), 100));
		return fuelRecipes;
	}
}
