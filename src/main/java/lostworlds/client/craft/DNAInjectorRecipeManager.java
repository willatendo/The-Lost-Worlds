package lostworlds.client.craft;

import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;

import lostworlds.server.container.recipes.DNAInjectorRecipe;
import lostworlds.server.container.recipes.LostWorldsRecipes;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;

@ZenRegister
@ZenCodeType.Name("mods.lostworlds.dna_injector")
public class DNAInjectorRecipeManager implements IRecipeManager {
	@ZenCodeType.Method
	public void addRecipe(String name, IIngredient softTissue, IIngredient egg, IItemStack output) {
		name = fixRecipeName(name);
		DNAInjectorRecipe recipe = makeRecipe(name, softTissue, egg, output);
		CraftTweakerAPI.apply(new ActionAddRecipe(this, recipe, ""));
	}

	private DNAInjectorRecipe makeRecipe(String name, IIngredient softTissue, IIngredient egg, IItemStack output) {
		ResourceLocation id = new ResourceLocation("crafttweaker", name);
		return new DNAInjectorRecipe(id, softTissue.asVanillaIngredient(), egg.asVanillaIngredient(), output.getDefinition().getDefaultInstance());
	}

	@Override
	public IRecipeType getRecipeType() {
		return LostWorldsRecipes.DNA_INJECTOR_RECIPE;
	}
}
