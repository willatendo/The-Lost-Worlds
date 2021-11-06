package lostworlds.content.jei;

import java.util.Collection;
import java.util.stream.Collectors;

import lostworlds.content.ModUtils;
import lostworlds.content.client.screen.AnalyzerScreen;
import lostworlds.content.client.screen.ArchaeologyTableScreen;
import lostworlds.content.client.screen.CultivatorScreen;
import lostworlds.content.client.screen.DNAExtractorScreen;
import lostworlds.content.client.screen.FossilCleanerScreen;
import lostworlds.content.client.screen.FossilGrinderScreen;
import lostworlds.content.client.screen.PaleontologyTableScreen;
import lostworlds.content.jei.categories.AnalyzerCategory;
import lostworlds.content.jei.categories.ArchaeologyTableCategory;
import lostworlds.content.jei.categories.CultivatorCategory;
import lostworlds.content.jei.categories.DNAExtractorCategory;
import lostworlds.content.jei.categories.FossilCleanerCategory;
import lostworlds.content.jei.categories.FossilGrinderCategory;
import lostworlds.content.jei.categories.PaleontologyTableCategory;
import lostworlds.content.jei.categories.TimeMachineCategory;
import lostworlds.content.jei.categories.WaterFuelCategory;
import lostworlds.content.server.init.BlockInit;
import lostworlds.content.server.init.RecipeInit;
import lostworlds.library.container.AnalyzerContainer;
import lostworlds.library.container.ArchaeologyTableContainer;
import lostworlds.library.container.CultivatorContainer;
import lostworlds.library.container.DNAExtractorContainer;
import lostworlds.library.container.FossilCleanerContainer;
import lostworlds.library.container.FossilGrinderContainer;
import lostworlds.library.container.PaleontologyTableContainer;
import lostworlds.library.item.CrystalScarabGemItem;
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
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class LostWorldsPlugin implements IModPlugin
{
	@Override
	public ResourceLocation getPluginUid() 
	{
		return LostWorldsConstants.LOST_WORLDS_PLUGIN;
	}
	
	@Override
	public void registerRecipes(IRecipeRegistration registration) 
	{
		RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();
		IJeiHelpers jeiHelpers = registration.getJeiHelpers();
		IIngredientManager ingredientManager = registration.getIngredientManager();
		
		registration.addRecipes(lostworlds.library.container.recipes.RecipeManager.getWaterFuelRecipes(ingredientManager, jeiHelpers), LostWorldsConstants.WATER_FUEL_CATEGORY);
		registration.addRecipes(getRecipes(manager, RecipeInit.FOSSIL_CLEANER_RECIPE), LostWorldsConstants.FOSSIL_CLEANER_CATEGORY);
		registration.addRecipes(getRecipes(manager, RecipeInit.FOSSIL_GRINDER_RECIPE), LostWorldsConstants.FOSSIL_GRINDER_CATEGORY);
		registration.addRecipes(getRecipes(manager, RecipeInit.DNA_EXTRACTOR_RECIPE), LostWorldsConstants.DNA_EXTRACTOR_CATEGORY);
		registration.addRecipes(getRecipes(manager, RecipeInit.ANALYZER_RECIPE), LostWorldsConstants.ANALYZER_CATEGORY);
		registration.addRecipes(getRecipes(manager, RecipeInit.CULTIVATOR_RECIPE), LostWorldsConstants.CULTIVATOR_CATEGORY);
		registration.addRecipes(getRecipes(manager, RecipeInit.TIME_MACHINE_RECIPE), LostWorldsConstants.TIME_MACHINE_CATEGORY);
		registration.addRecipes(getRecipes(manager, RecipeInit.ARCHAEOLOGY_TABLE_RECIPE), LostWorldsConstants.ARCHAEOLOGY_TABLE_CATEGORY);
		registration.addRecipes(getRecipes(manager, RecipeInit.PALEONTOLOGY_TABLE_RECIPE), LostWorldsConstants.PALEONTOLOGY_TABLE_CATEGORY);
		
		registration.addIngredientInfo(CrystalScarabGemItem.Gems.CHARGED_CRYSTAL_SCARAB_GEM.getItem().getDefaultInstance(), VanillaTypes.ITEM, ModUtils.tTC("jeiInfo", "charged_crystal_scarab_gem").getString());
	}
	
	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) 
	{
		IGuiHelper helper = registration.getJeiHelpers().getGuiHelper();
		
		registration.addRecipeCategories(new WaterFuelCategory(helper));
		registration.addRecipeCategories(new FossilCleanerCategory(helper));
		registration.addRecipeCategories(new FossilGrinderCategory(helper));
		registration.addRecipeCategories(new DNAExtractorCategory(helper));
		registration.addRecipeCategories(new AnalyzerCategory(helper));
		registration.addRecipeCategories(new CultivatorCategory(helper));
		registration.addRecipeCategories(new TimeMachineCategory(helper));
		registration.addRecipeCategories(new ArchaeologyTableCategory(helper));
		registration.addRecipeCategories(new PaleontologyTableCategory(helper));
	}
	
	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration) 
	{
		registration.addRecipeClickArea(FossilCleanerScreen.class, 75, 37, 35, 15, LostWorldsConstants.FOSSIL_CLEANER_CATEGORY, LostWorldsConstants.WATER_FUEL_CATEGORY);
		registration.addRecipeClickArea(FossilGrinderScreen.class, 75, 37, 35, 15, LostWorldsConstants.FOSSIL_GRINDER_CATEGORY);
		registration.addRecipeClickArea(DNAExtractorScreen.class, 75, 38, 34, 10, LostWorldsConstants.DNA_EXTRACTOR_CATEGORY);
		registration.addRecipeClickArea(AnalyzerScreen.class, 75, 38, 34, 12, LostWorldsConstants.ANALYZER_CATEGORY);
		registration.addRecipeClickArea(CultivatorScreen.class, 76, 34, 33, 17, LostWorldsConstants.CULTIVATOR_CATEGORY);
		registration.addRecipeClickArea(ArchaeologyTableScreen.class, 88, 32, 28, 23, LostWorldsConstants.ARCHAEOLOGY_TABLE_CATEGORY);
		registration.addRecipeClickArea(PaleontologyTableScreen.class, 88, 32, 28, 23, LostWorldsConstants.PALEONTOLOGY_TABLE_CATEGORY);
	}
	
	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) 
	{
		registration.addRecipeTransferHandler(FossilCleanerContainer.class, LostWorldsConstants.FOSSIL_CLEANER_CATEGORY, 0, 1, 3, 36);
		registration.addRecipeTransferHandler(FossilCleanerContainer.class, LostWorldsConstants.WATER_FUEL_CATEGORY, 1, 1, 3, 36);
		registration.addRecipeTransferHandler(FossilGrinderContainer.class, LostWorldsConstants.FOSSIL_GRINDER_CATEGORY, 0, 1, 3, 36);
		registration.addRecipeTransferHandler(DNAExtractorContainer.class, LostWorldsConstants.DNA_EXTRACTOR_CATEGORY, 0, 2, 3, 36);
		registration.addRecipeTransferHandler(AnalyzerContainer.class, LostWorldsConstants.ANALYZER_CATEGORY, 0, 2, 3, 36);
		registration.addRecipeTransferHandler(CultivatorContainer.class, LostWorldsConstants.CULTIVATOR_CATEGORY, 0, 1, 3, 18);
		registration.addRecipeTransferHandler(ArchaeologyTableContainer.class, LostWorldsConstants.ARCHAEOLOGY_TABLE_CATEGORY, 1, 9, 10, 36);
		registration.addRecipeTransferHandler(PaleontologyTableContainer.class, LostWorldsConstants.PALEONTOLOGY_TABLE_CATEGORY, 1, 9, 10, 36);
	}
	
	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) 	
	{
		registration.addRecipeCatalyst(new ItemStack(BlockInit.FOSSIL_CLEANER), LostWorldsConstants.FOSSIL_CLEANER_CATEGORY, LostWorldsConstants.WATER_FUEL_CATEGORY);
		registration.addRecipeCatalyst(new ItemStack(BlockInit.FOSSIL_GRINDER), LostWorldsConstants.FOSSIL_GRINDER_CATEGORY);
		registration.addRecipeCatalyst(new ItemStack(BlockInit.DNA_EXTRACTOR), LostWorldsConstants.DNA_EXTRACTOR_CATEGORY);
		registration.addRecipeCatalyst(new ItemStack(BlockInit.ANALYZER), LostWorldsConstants.ANALYZER_CATEGORY);
		registration.addRecipeCatalyst(new ItemStack(BlockInit.CULTIVATOR), LostWorldsConstants.CULTIVATOR_CATEGORY);
		registration.addRecipeCatalyst(new ItemStack(BlockInit.TIME_MACHINE), LostWorldsConstants.TIME_MACHINE_CATEGORY);
		registration.addRecipeCatalyst(new ItemStack(BlockInit.ARCHAEOLOGY_TABLE), LostWorldsConstants.ARCHAEOLOGY_TABLE_CATEGORY);
		registration.addRecipeCatalyst(new ItemStack(BlockInit.ACACIA_PALEONTOLOGY_TABLE), LostWorldsConstants.PALEONTOLOGY_TABLE_CATEGORY);
		registration.addRecipeCatalyst(new ItemStack(BlockInit.ARAUCARIA_PALEONTOLOGY_TABLE), LostWorldsConstants.PALEONTOLOGY_TABLE_CATEGORY);
		registration.addRecipeCatalyst(new ItemStack(BlockInit.BIRCH_PALEONTOLOGY_TABLE), LostWorldsConstants.PALEONTOLOGY_TABLE_CATEGORY);
		registration.addRecipeCatalyst(new ItemStack(BlockInit.CALAMITES_PALEONTOLOGY_TABLE), LostWorldsConstants.PALEONTOLOGY_TABLE_CATEGORY);
		registration.addRecipeCatalyst(new ItemStack(BlockInit.CONIFER_PALEONTOLOGY_TABLE), LostWorldsConstants.PALEONTOLOGY_TABLE_CATEGORY);
		registration.addRecipeCatalyst(new ItemStack(BlockInit.CRIMSON_PALEONTOLOGY_TABLE), LostWorldsConstants.PALEONTOLOGY_TABLE_CATEGORY);
		registration.addRecipeCatalyst(new ItemStack(BlockInit.CYPRESS_PALEONTOLOGY_TABLE), LostWorldsConstants.PALEONTOLOGY_TABLE_CATEGORY);
		registration.addRecipeCatalyst(new ItemStack(BlockInit.DARK_OAK_PALEONTOLOGY_TABLE), LostWorldsConstants.PALEONTOLOGY_TABLE_CATEGORY);
		registration.addRecipeCatalyst(new ItemStack(BlockInit.GINKGO_PALEONTOLOGY_TABLE), LostWorldsConstants.PALEONTOLOGY_TABLE_CATEGORY);
		registration.addRecipeCatalyst(new ItemStack(BlockInit.JUNGLE_PALEONTOLOGY_TABLE), LostWorldsConstants.PALEONTOLOGY_TABLE_CATEGORY);
		registration.addRecipeCatalyst(new ItemStack(BlockInit.OAK_PALEONTOLOGY_TABLE), LostWorldsConstants.PALEONTOLOGY_TABLE_CATEGORY);
		registration.addRecipeCatalyst(new ItemStack(BlockInit.SCORCHED_PALEONTOLOGY_TABLE), LostWorldsConstants.PALEONTOLOGY_TABLE_CATEGORY);
		registration.addRecipeCatalyst(new ItemStack(BlockInit.SPRUCE_PALEONTOLOGY_TABLE), LostWorldsConstants.PALEONTOLOGY_TABLE_CATEGORY);
		registration.addRecipeCatalyst(new ItemStack(BlockInit.SEQUOIA_PALEONTOLOGY_TABLE), LostWorldsConstants.PALEONTOLOGY_TABLE_CATEGORY);
		registration.addRecipeCatalyst(new ItemStack(BlockInit.WARPED_PALEONTOLOGY_TABLE), LostWorldsConstants.PALEONTOLOGY_TABLE_CATEGORY);
	}
	
	private static Collection<?> getRecipes(RecipeManager manager, IRecipeType<?> type)
	{
		return manager.getRecipes().parallelStream().filter(recipe -> recipe.getType() == type).collect(Collectors.toList());
	}
}
