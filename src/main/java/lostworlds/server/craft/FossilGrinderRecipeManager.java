package lostworlds.server.craft;

import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;

import lostworlds.server.menu.recipes.FossilGrinderRecipe;
import lostworlds.server.menu.recipes.LostWorldsRecipeTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;

@ZenRegister
@ZenCodeType.Name("mods.lostworlds.fossil_grinder")
public class FossilGrinderRecipeManager implements IRecipeManager {
	@ZenCodeType.Method
	public void addRecipe(String name, IIngredient input, IItemStack output, boolean isPlant) {
		name = fixRecipeName(name);
		FossilGrinderRecipe recipe = makeRecipe(name, input, output, isPlant);
		CraftTweakerAPI.apply(new ActionAddRecipe(this, recipe, ""));
	}

	private FossilGrinderRecipe makeRecipe(String name, IIngredient input, IItemStack output, boolean plant) {
		ResourceLocation id = new ResourceLocation("crafttweaker", name);
		return new FossilGrinderRecipe(id, input.asVanillaIngredient(), output.getDefinition().getDefaultInstance(), plant);
	}

	@Override
	public RecipeType getRecipeType() {
		return LostWorldsRecipeTypes.FOSSIL_GRINDER_RECIPE.get();
	}
}
