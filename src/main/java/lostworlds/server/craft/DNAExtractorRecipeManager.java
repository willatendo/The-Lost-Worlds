package lostworlds.server.craft;

import static lostworlds.api.APIRegistry.AMBER_RECIPE_TYPES;

import java.util.ArrayList;

import org.apache.commons.compress.utils.Lists;
import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;

import lostworlds.server.menu.recipes.DNAExtractorRecipe;
import lostworlds.server.menu.recipes.LostWorldsRecipeTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
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

	@ZenCodeType.Method
	public void addAmberRecipeOutput(IItemStack... outputs) {
		ArrayList<Item> amberOutput = Lists.newArrayList();
		for (IItemStack item : outputs) {
			amberOutput.add(item.getDefinition());
		}
		AMBER_RECIPE_TYPES.add(new AmberOutput(amberOutput));
	}

	private DNAExtractorRecipe makeRecipe(String name, IIngredient softTissue, IIngredient vile, IItemStack output) {
		ResourceLocation id = new ResourceLocation("crafttweaker", name);
		return new DNAExtractorRecipe(id, softTissue.asVanillaIngredient(), vile.asVanillaIngredient(), output.getDefinition().getDefaultInstance());
	}

	@Override
	public RecipeType getRecipeType() {
		return LostWorldsRecipeTypes.DNA_EXTRACTOR_RECIPE.get();
	}
}
