package lostworlds.server.craft;

import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;

import lostworlds.server.container.recipes.DNAExtractorRecipe;
import lostworlds.server.container.recipes.LostWorldsRecipes;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;

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
	public IRecipeType getRecipeType() {
		return LostWorldsRecipes.DNA_EXTRACTOR_RECIPE;
	}
}
