package lostworlds.server.craft;

import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;

import lostworlds.server.container.recipes.CultivatorRecipe;
import lostworlds.server.container.recipes.LostWorldsRecipes;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.resources.ResourceLocation;

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
		return LostWorldsRecipes.CULTIVATOR_RECIPE;
	}
}
