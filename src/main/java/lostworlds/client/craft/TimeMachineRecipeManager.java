package lostworlds.client.craft;

import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;

import lostworlds.server.container.recipes.LostWorldsRecipes;
import lostworlds.server.container.recipes.TimeMachineRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;

@ZenRegister
@ZenCodeType.Name("mods.lostworlds.time_enchanting")
public class TimeMachineRecipeManager implements IRecipeManager {
	@ZenCodeType.Method
	public void addRecipe(String name, IIngredient book, IIngredient power, IItemStack output) {
		name = fixRecipeName(name);
		TimeMachineRecipe recipe = makeRecipe(name, book, power, output);
		CraftTweakerAPI.apply(new ActionAddRecipe(this, recipe, ""));
	}

	private TimeMachineRecipe makeRecipe(String name, IIngredient book, IIngredient power, IItemStack output) {
		ResourceLocation id = new ResourceLocation("crafttweaker", name);
		return new TimeMachineRecipe(id, output.getDefinition().getDefaultInstance(), book.asVanillaIngredient(), power.asVanillaIngredient());
	}

	@Override
	public IRecipeType getRecipeType() {
		return LostWorldsRecipes.FOSSIL_CLEANER_RECIPE;
	}
}
