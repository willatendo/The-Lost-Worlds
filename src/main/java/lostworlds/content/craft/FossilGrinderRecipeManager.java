package lostworlds.content.craft;

import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;

import lostworlds.content.server.init.RecipeInit;
import lostworlds.library.container.recipes.FossilGrinderRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;

@ZenRegister
@ZenCodeType.Name("mods.lostworlds.fossil_grinder")
public class FossilGrinderRecipeManager implements IRecipeManager {
	@ZenCodeType.Method
	public void addRecipe(String name, IIngredient input, IItemStack output, boolean isPlant) {
		name = fixRecipeName(name);
		FossilGrinderRecipe recipe = makeRecipe(name, input, output, isPlant);
		CraftTweakerAPI.apply(new ActionAddRecipe(this, recipe, ""));
	}

	private FossilGrinderRecipe makeRecipe(String name, IIngredient input, IItemStack output, boolean plant) {
		ResourceLocation id = new ResourceLocation("crafttweaker", name);
		return new FossilGrinderRecipe(id, input.asVanillaIngredient(), output.getDefinition().getDefaultInstance(), plant);
	}

	@Override
	public IRecipeType getRecipeType() {
		return RecipeInit.FOSSIL_GRINDER_RECIPE;
	}
}
