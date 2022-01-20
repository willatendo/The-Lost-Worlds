package lostworlds.content.craft;

import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;

import lostworlds.content.server.init.RecipeInit;
import lostworlds.library.container.recipes.AnalyzerRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;

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
	public IRecipeType getRecipeType() {
		return RecipeInit.ANALYZER_RECIPE;
	}
}
