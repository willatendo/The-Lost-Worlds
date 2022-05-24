package lostworlds.server.craft;

import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;

import lostworlds.server.menu.recipes.FossilCleanerRecipe;
import lostworlds.server.menu.recipes.LostWorldsRecipeTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;

@ZenRegister
@ZenCodeType.Name("mods.lostworlds.fossil_cleaner")
public class FossilCleanerRecipeManager implements IRecipeManager {
	@ZenCodeType.Method
	public void addRecipe(String name, IIngredient input, IItemStack output) {
		name = fixRecipeName(name);
		FossilCleanerRecipe recipe = makeRecipe(name, input, output);
		CraftTweakerAPI.apply(new ActionAddRecipe(this, recipe, ""));
	}

	private FossilCleanerRecipe makeRecipe(String name, IIngredient input, IItemStack output) {
		ResourceLocation id = new ResourceLocation("crafttweaker", name);
		return new FossilCleanerRecipe(id, input.asVanillaIngredient(), output.getDefinition().getDefaultInstance());
	}

	@Override
	public RecipeType getRecipeType() {
		return LostWorldsRecipeTypes.FOSSIL_CLEANER_RECIPE;
	}
}
