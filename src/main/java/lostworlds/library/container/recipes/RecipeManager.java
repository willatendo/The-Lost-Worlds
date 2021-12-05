package lostworlds.library.container.recipes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lostworlds.content.jei.recipe.AmberDNAExtractorRecipe;
import lostworlds.content.jei.recipe.WaterFuelRecipe;
import lostworlds.content.server.init.ItemInit;
import lostworlds.library.entity.utils.enums.DinoTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.runtime.IIngredientManager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import tyrannotitanlib.library.base.recipe.TyrannoRandomOutputGenerator;

public class RecipeManager 
{
	public static List<TyrannoRandomOutputGenerator> amberRecipes = new ArrayList<>();
	
	public static void initAlternateRecipes()
	{
		TyrannoRandomOutputGenerator amber = new TyrannoRandomOutputGenerator(ItemInit.AMBER.getDefaultInstance());
		for(DinoTypes types : DinoTypes.values()) 
		{
			amber.addOutput(() -> types.getDNA());
			new AmberDNAExtractorRecipe(types.getDNA().getDefaultInstance());
		}
		registerAmber(amber);
	}
	
	public static void registerAmber(TyrannoRandomOutputGenerator recipe) 
	{
		amberRecipes.add(recipe);
	}
	
	public static TyrannoRandomOutputGenerator getItemForRecipe(ItemStack stack) 
	{
		for(TyrannoRandomOutputGenerator recipe : amberRecipes) 
		{
			if(ItemStack.matches(recipe.getInput(), stack))
			{
				return recipe;
			}
		}
		
		return null;
	}
	
	public static List<AmberDNAExtractorRecipe> getAmberRecipes() 
	{
		List<AmberDNAExtractorRecipe> recipes = new ArrayList<>();
		for(DinoTypes types : DinoTypes.values()) 
		{
			recipes.add(new AmberDNAExtractorRecipe(types.getDNA().getDefaultInstance()));
		}
		return recipes;
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
