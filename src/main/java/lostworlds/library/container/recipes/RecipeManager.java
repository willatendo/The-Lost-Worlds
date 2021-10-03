package lostworlds.library.container.recipes;

import java.util.ArrayList;
import java.util.List;

import lostworlds.content.server.init.ItemInit;
import lostworlds.library.entity.DinoTypes;
import net.minecraft.item.ItemStack;

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
}
