package lostworlds.content.craft;

import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;

import lostworlds.content.jei.recipe.AmberDNAExtractorRecipe;
import lostworlds.content.server.init.ItemInit;
import lostworlds.content.server.init.RecipeInit;
import lostworlds.library.container.recipes.RecipeManager;
import net.minecraft.item.crafting.IRecipeType;
import tyrannotitanlib.library.base.recipe.TyrannoRandomOutputGenerator;

@ZenRegister
@ZenCodeType.Name("mods.lostworlds.amber_dna_extractor")
public class AmberDNAExtractorRecipeManager implements IRecipeManager {
	@ZenCodeType.Method
	public void addAdditionalOutputs(IItemStack... additionalOutput) {
		TyrannoRandomOutputGenerator amber = new TyrannoRandomOutputGenerator(ItemInit.AMBER.getDefaultInstance());
		for (IItemStack iStacks : additionalOutput) {
			amber.addOutput(() -> iStacks.getDefinition());
			new AmberDNAExtractorRecipe(iStacks.getInternal());
		}
		RecipeManager.registerAmber(amber);
	}

	@Override
	public IRecipeType getRecipeType() {
		return RecipeInit.AMBER_DNA_EXTRACTOR_RECIPE;
	}
}
