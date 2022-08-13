package lostworlds.server.craft;

import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;

import lostworlds.server.menu.recipes.CultivatorRecipe;
import lostworlds.server.menu.recipes.LostWorldsRecipeTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;

@ZenRegister
@ZenCodeType.Name("mods.lostworlds.cultivator")
public class CultivatorRecipeManager implements IRecipeManager {
	@ZenCodeType.Method
	public void addRecipe(String name, IIngredient dnaDisc, IItemStack output) {
		name = fixRecipeName(name);
		CultivatorRecipe recipe = makeRecipe(name, dnaDisc, output);
		CraftTweakerAPI.apply(new ActionAddRecipe(this, recipe, ""));
	}

	private CultivatorRecipe makeRecipe(String name, IIngredient dnaDisc, IItemStack output) {
		ResourceLocation id = new ResourceLocation("crafttweaker", name);
		return new CultivatorRecipe(id, dnaDisc.asVanillaIngredient(), output.getDefinition().getDefaultInstance());
	}

	@Override
	public RecipeType getRecipeType() {
		return LostWorldsRecipeTypes.CULTIVATOR_RECIPE.get();
	}
}
