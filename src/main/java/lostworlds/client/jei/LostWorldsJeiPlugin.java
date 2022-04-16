package lostworlds.client.jei;

import java.util.Collection;
import java.util.stream.Collectors;

import lostworlds.client.jei.categories.AmberDNAExtractorCategory;
import lostworlds.client.jei.categories.AnalyzerCategory;
import lostworlds.client.jei.categories.ArchaeologyTableCategory;
import lostworlds.client.jei.categories.CultivatorCategory;
import lostworlds.client.jei.categories.DNAExtractorCategory;
import lostworlds.client.jei.categories.DNAInjectorCategory;
import lostworlds.client.jei.categories.FossilCleanerCategory;
import lostworlds.client.jei.categories.FossilGrinderCategory;
import lostworlds.client.jei.categories.PaleontologyTableCategory;
import lostworlds.client.jei.categories.TimeMachineCategory;
import lostworlds.client.jei.categories.WaterFuelCategory;
import lostworlds.client.screen.AnalyzerScreen;
import lostworlds.client.screen.ArchaeologyTableScreen;
import lostworlds.client.screen.CultivatorScreen;
import lostworlds.client.screen.DNAExtractorScreen;
import lostworlds.client.screen.DNAInjectorScreen;
import lostworlds.client.screen.FossilCleanerScreen;
import lostworlds.client.screen.FossilGrinderScreen;
import lostworlds.client.screen.PaleontologyTableScreen;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.container.AnalyzerContainer;
import lostworlds.server.container.ArchaeologyTableContainer;
import lostworlds.server.container.CultivatorContainer;
import lostworlds.server.container.DNAExtractorContainer;
import lostworlds.server.container.FossilCleanerContainer;
import lostworlds.server.container.FossilGrinderContainer;
import lostworlds.server.container.PaleontologyTableContainer;
import lostworlds.server.container.recipes.LostWorldsRecipes;
import lostworlds.server.item.LostWorldsItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import mezz.jei.api.runtime.IIngredientManager;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class LostWorldsJeiPlugin implements IModPlugin {
	@Override
	public ResourceLocation getPluginUid() {
		return LostWorldsJeiConstants.LOST_WORLDS_PLUGIN;
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();
		IJeiHelpers jeiHelpers = registration.getJeiHelpers();
		IIngredientManager ingredientManager = registration.getIngredientManager();

		registration.addRecipes(lostworlds.server.container.recipes.RecipeManager.getWaterFuelRecipes(ingredientManager, jeiHelpers), LostWorldsJeiConstants.WATER_FUEL_CATEGORY);
		registration.addRecipes(getRecipes(manager, LostWorldsRecipes.FOSSIL_CLEANER_RECIPE), LostWorldsJeiConstants.FOSSIL_CLEANER_CATEGORY);
		registration.addRecipes(getRecipes(manager, LostWorldsRecipes.FOSSIL_GRINDER_RECIPE), LostWorldsJeiConstants.FOSSIL_GRINDER_CATEGORY);
		registration.addRecipes(getRecipes(manager, LostWorldsRecipes.DNA_EXTRACTOR_RECIPE), LostWorldsJeiConstants.DNA_EXTRACTOR_CATEGORY);
		registration.addRecipes(lostworlds.server.container.recipes.RecipeManager.getAmberRecipes(), LostWorldsJeiConstants.AMBER_DNA_EXTRACTOR_CATEGORY);
		registration.addRecipes(getRecipes(manager, LostWorldsRecipes.ANALYZER_RECIPE), LostWorldsJeiConstants.ANALYZER_CATEGORY);
		registration.addRecipes(getRecipes(manager, LostWorldsRecipes.DNA_INJECTOR_RECIPE), LostWorldsJeiConstants.DNA_INJECTOR_CATEGORY);
		registration.addRecipes(getRecipes(manager, LostWorldsRecipes.CULTIVATOR_RECIPE), LostWorldsJeiConstants.CULTIVATOR_CATEGORY);
		registration.addRecipes(getRecipes(manager, LostWorldsRecipes.TIME_MACHINE_RECIPE), LostWorldsJeiConstants.TIME_MACHINE_CATEGORY);
		registration.addRecipes(getRecipes(manager, LostWorldsRecipes.ARCHAEOLOGY_TABLE_RECIPE), LostWorldsJeiConstants.ARCHAEOLOGY_TABLE_CATEGORY);
		registration.addRecipes(getRecipes(manager, LostWorldsRecipes.PALEONTOLOGY_TABLE_RECIPE), LostWorldsJeiConstants.PALEONTOLOGY_TABLE_CATEGORY);

		registration.addIngredientInfo(LostWorldsItems.CHARGED_CRYSTAL_SCARAB_GEM.get().getDefaultInstance(), VanillaTypes.ITEM, LostWorldsUtils.tTC("jeiInfo", "charged_crystal_scarab_gem").getString());
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		IGuiHelper helper = registration.getJeiHelpers().getGuiHelper();

		registration.addRecipeCategories(new WaterFuelCategory(helper));
		registration.addRecipeCategories(new FossilCleanerCategory(helper));
		registration.addRecipeCategories(new FossilGrinderCategory(helper));
		registration.addRecipeCategories(new DNAExtractorCategory(helper));
		registration.addRecipeCategories(new AmberDNAExtractorCategory(helper));
		registration.addRecipeCategories(new AnalyzerCategory(helper));
		registration.addRecipeCategories(new DNAInjectorCategory(helper));
		registration.addRecipeCategories(new CultivatorCategory(helper));
		registration.addRecipeCategories(new TimeMachineCategory(helper));
		registration.addRecipeCategories(new ArchaeologyTableCategory(helper));
		registration.addRecipeCategories(new PaleontologyTableCategory(helper));
	}

	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration) {
		registration.addRecipeClickArea(FossilCleanerScreen.class, 75, 37, 35, 15, LostWorldsJeiConstants.FOSSIL_CLEANER_CATEGORY, LostWorldsJeiConstants.WATER_FUEL_CATEGORY);
		registration.addRecipeClickArea(FossilGrinderScreen.class, 75, 37, 35, 15, LostWorldsJeiConstants.FOSSIL_GRINDER_CATEGORY);
		registration.addRecipeClickArea(DNAExtractorScreen.class, 75, 38, 34, 10, LostWorldsJeiConstants.DNA_EXTRACTOR_CATEGORY, LostWorldsJeiConstants.AMBER_DNA_EXTRACTOR_CATEGORY);
		registration.addRecipeClickArea(AnalyzerScreen.class, 75, 38, 34, 12, LostWorldsJeiConstants.ANALYZER_CATEGORY);
		registration.addRecipeClickArea(DNAInjectorScreen.class, 85, 34, 16, 16, LostWorldsJeiConstants.DNA_INJECTOR_CATEGORY);
		registration.addRecipeClickArea(CultivatorScreen.class, 76, 34, 33, 17, LostWorldsJeiConstants.CULTIVATOR_CATEGORY);
		registration.addRecipeClickArea(ArchaeologyTableScreen.class, 88, 32, 28, 23, LostWorldsJeiConstants.ARCHAEOLOGY_TABLE_CATEGORY);
		registration.addRecipeClickArea(PaleontologyTableScreen.class, 88, 32, 28, 23, LostWorldsJeiConstants.PALEONTOLOGY_TABLE_CATEGORY);
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
		registration.addRecipeTransferHandler(FossilCleanerContainer.class, LostWorldsJeiConstants.FOSSIL_CLEANER_CATEGORY, 0, 1, 3, 36);
		registration.addRecipeTransferHandler(FossilCleanerContainer.class, LostWorldsJeiConstants.WATER_FUEL_CATEGORY, 1, 1, 3, 36);
		registration.addRecipeTransferHandler(FossilGrinderContainer.class, LostWorldsJeiConstants.FOSSIL_GRINDER_CATEGORY, 0, 1, 3, 36);
		registration.addRecipeTransferHandler(DNAExtractorContainer.class, LostWorldsJeiConstants.DNA_EXTRACTOR_CATEGORY, 0, 2, 3, 36);
		registration.addRecipeTransferHandler(DNAExtractorContainer.class, LostWorldsJeiConstants.AMBER_DNA_EXTRACTOR_CATEGORY, 0, 2, 3, 36);
		registration.addRecipeTransferHandler(AnalyzerContainer.class, LostWorldsJeiConstants.ANALYZER_CATEGORY, 0, 2, 3, 36);
		registration.addRecipeTransferHandler(DNAExtractorContainer.class, LostWorldsJeiConstants.DNA_INJECTOR_CATEGORY, 0, 2, 3, 36);
		registration.addRecipeTransferHandler(CultivatorContainer.class, LostWorldsJeiConstants.CULTIVATOR_CATEGORY, 0, 1, 3, 18);
		registration.addRecipeTransferHandler(ArchaeologyTableContainer.class, LostWorldsJeiConstants.ARCHAEOLOGY_TABLE_CATEGORY, 1, 9, 10, 36);
		registration.addRecipeTransferHandler(PaleontologyTableContainer.class, LostWorldsJeiConstants.PALEONTOLOGY_TABLE_CATEGORY, 1, 9, 10, 36);
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(LostWorldsBlocks.FOSSIL_CLEANER.asStack(), LostWorldsJeiConstants.FOSSIL_CLEANER_CATEGORY, LostWorldsJeiConstants.WATER_FUEL_CATEGORY);
		registration.addRecipeCatalyst(LostWorldsBlocks.FOSSIL_GRINDER.asStack(), LostWorldsJeiConstants.FOSSIL_GRINDER_CATEGORY);
		registration.addRecipeCatalyst(LostWorldsBlocks.DNA_EXTRACTOR.asStack(), LostWorldsJeiConstants.DNA_EXTRACTOR_CATEGORY, LostWorldsJeiConstants.AMBER_DNA_EXTRACTOR_CATEGORY);
		registration.addRecipeCatalyst(LostWorldsBlocks.ANALYZER.asStack(), LostWorldsJeiConstants.ANALYZER_CATEGORY);
		registration.addRecipeCatalyst(LostWorldsBlocks.DNA_INJECTOR.asStack(), LostWorldsJeiConstants.DNA_INJECTOR_CATEGORY);
		registration.addRecipeCatalyst(LostWorldsBlocks.CULTIVATOR.asStack(), LostWorldsJeiConstants.CULTIVATOR_CATEGORY);
		registration.addRecipeCatalyst(LostWorldsBlocks.TIME_MACHINE.asStack(), LostWorldsJeiConstants.TIME_MACHINE_CATEGORY);
		registration.addRecipeCatalyst(LostWorldsBlocks.ARCHAEOLOGY_TABLE.asStack(), LostWorldsJeiConstants.ARCHAEOLOGY_TABLE_CATEGORY);
		registration.addRecipeCatalyst(LostWorldsBlocks.PALEONTOLOGY_TABLE.asStack(), LostWorldsJeiConstants.PALEONTOLOGY_TABLE_CATEGORY);
	}

	private static Collection<?> getRecipes(RecipeManager manager, IRecipeType<?> type) {
		return manager.getRecipes().parallelStream().filter(recipe -> recipe.getType() == type).collect(Collectors.toList());
	}
}
