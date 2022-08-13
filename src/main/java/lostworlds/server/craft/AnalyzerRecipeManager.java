package lostworlds.server.craft;

import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;

import lostworlds.server.menu.recipes.AnalyzerRecipe;
import lostworlds.server.menu.recipes.LostWorldsRecipeTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;

@ZenRegister
@ZenCodeType.Name("mods.lostworlds.analyzer")
public class AnalyzerRecipeManager implements IRecipeManager {
	@ZenCodeType.Method
	public void addRecipe(String name, IIngredient dna, IIngredient dnaDisc, IItemStack output) {
		name = fixRecipeName(name);
		AnalyzerRecipe recipe = makeRecipe(name, dna, dnaDisc, output);
		CraftTweakerAPI.apply(new ActionAddRecipe(this, recipe, ""));
	}

	private AnalyzerRecipe makeRecipe(String name, IIngredient dna, IIngredient dnaDisc, IItemStack output) {
		ResourceLocation id = new ResourceLocation("crafttweaker", name);
		return new AnalyzerRecipe(id, dna.asVanillaIngredient(), dnaDisc.asVanillaIngredient(), output.getDefinition().getDefaultInstance());
	}

	@Override
	public RecipeType getRecipeType() {
		return LostWorldsRecipeTypes.ANALYZER_RECIPE.get();
	}
}
