package lostworlds.server.craft;

import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;

import lostworlds.server.menu.recipes.DNAExtractorRecipe;
import lostworlds.server.menu.recipes.LostWorldsRecipes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;

@ZenRegister
@ZenCodeType.Name("mods.lostworlds.dna_extractor")
public class DNAExtractorRecipeManager implements IRecipeManager {
	@ZenCodeType.Method
	public void addRecipe(String name, IIngredient softTissue, IIngredient vile, IItemStack output) {
		name = fixRecipeName(name);
		DNAExtractorRecipe recipe = makeRecipe(name, softTissue, vile, output);
		CraftTweakerAPI.apply(new ActionAddRecipe(this, recipe, ""));
	}

	private DNAExtractorRecipe makeRecipe(String name, IIngredient softTissue, IIngredient vile, IItemStack output) {
		ResourceLocation id = new ResourceLocation("crafttweaker", name);
		return new DNAExtractorRecipe(id, softTissue.asVanillaIngredient(), vile.asVanillaIngredient(), output.getDefinition().getDefaultInstance());
	}

	@Override
	public RecipeType getRecipeType() {
		return LostWorldsRecipes.DNA_EXTRACTOR_RECIPE;
	}
}
