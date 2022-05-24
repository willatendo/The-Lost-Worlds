package lostworlds.server.craft;

import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;

import lostworlds.server.menu.recipes.LostWorldsRecipeTypes;
import lostworlds.server.menu.recipes.TimeMachineRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;

@ZenRegister
@ZenCodeType.Name("mods.lostworlds.time_enchanting")
public class TimeMachineRecipeManager implements IRecipeManager {
	@ZenCodeType.Method
	public void addRecipe(String name, IIngredient book, IIngredient power, IItemStack output) {
		name = fixRecipeName(name);
		TimeMachineRecipe recipe = makeRecipe(name, book, power, output);
		CraftTweakerAPI.apply(new ActionAddRecipe(this, recipe, ""));
	}

	private TimeMachineRecipe makeRecipe(String name, IIngredient book, IIngredient power, IItemStack output) {
		ResourceLocation id = new ResourceLocation("crafttweaker", name);
		return new TimeMachineRecipe(id, output.getDefinition().getDefaultInstance(), book.asVanillaIngredient(), power.asVanillaIngredient());
	}

	@Override
	public RecipeType getRecipeType() {
		return LostWorldsRecipeTypes.FOSSIL_CLEANER_RECIPE;
	}
}
