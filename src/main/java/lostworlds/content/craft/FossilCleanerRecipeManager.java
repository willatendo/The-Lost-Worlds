package lostworlds.content.craft;

import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;

import lostworlds.content.server.init.RecipeInit;
import lostworlds.library.container.recipes.FossilCleanerRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;

@ZenRegister
@ZenCodeType.Name("mods.lostworlds.fossil_cleaner")
public class FossilCleanerRecipeManager implements IRecipeManager {
	@ZenCodeType.Method
	public void addRecipe(String name, IIngredient input, IItemStack output) {
		name = fixRecipeName(name);
		FossilCleanerRecipe recipe = makeRecipe(name, input, output);
		CraftTweakerAPI.apply(new ActionAddRecipe(this, recipe, ""));
	}

	private FossilCleanerRecipe makeRecipe(String name, IIngredient input, IItemStack output) {
		ResourceLocation id = new ResourceLocation("crafttweaker", name);
		return new FossilCleanerRecipe(id, input.asVanillaIngredient(), output.getDefinition().getDefaultInstance());
	}

	@Override
	public IRecipeType getRecipeType() {
		return RecipeInit.FOSSIL_CLEANER_RECIPE;
	}
}
