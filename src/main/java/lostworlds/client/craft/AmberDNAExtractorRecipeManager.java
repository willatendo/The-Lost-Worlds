package lostworlds.client.craft;

import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;

import lostworlds.client.jei.recipe.AmberDNAExtractorRecipe;
import lostworlds.server.container.recipes.LostWorldsRecipes;
import lostworlds.server.container.recipes.RandomItemGenerator;
import lostworlds.server.container.recipes.RecipeManager;
import lostworlds.server.item.LostWorldsItems;
import net.minecraft.item.crafting.IRecipeType;

@ZenRegister
@ZenCodeType.Name("mods.lostworlds.amber_dna_extractor")
public class AmberDNAExtractorRecipeManager implements IRecipeManager {
	public static final RandomItemGenerator amber = new RandomItemGenerator(() -> LostWorldsItems.AMBER.get().getDefaultInstance());

	@ZenCodeType.Method
	public void addAdditionalOutput(IItemStack additionalOutput) {
		amber.addOutput(() -> additionalOutput.getDefinition());
		new AmberDNAExtractorRecipe(() -> additionalOutput.getInternal());
	}

	public static void init() {
		RecipeManager.registerAmber(amber);
	}

	@Override
	public IRecipeType getRecipeType() {
		return LostWorldsRecipes.AMBER_DNA_EXTRACTOR_RECIPE;
	}
}
