package lostworlds.server.craft;

import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;

import lostworlds.server.container.recipes.LostWorldsRecipes;
import lostworlds.server.container.recipes.RandomItemGenerator;
import lostworlds.server.container.recipes.LostWorldsRecipeManager;
import lostworlds.server.item.LostWorldsItems;
import lostworlds.server.jei.recipe.AmberDNAExtractorRecipe;
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
		LostWorldsRecipeManager.registerAmber(amber);
	}

	@Override
	public IRecipeType getRecipeType() {
		return LostWorldsRecipes.AMBER_DNA_EXTRACTOR_RECIPE;
	}
}
