package lostworlds.content.craft;

import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;

import lostworlds.content.server.init.RecipeInit;
import lostworlds.library.container.recipes.CultivatorRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;

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
	public IRecipeType getRecipeType() {
		return RecipeInit.CULTIVATOR_RECIPE;
	}
}
