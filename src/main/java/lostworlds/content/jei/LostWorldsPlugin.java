package lostworlds.content.jei;

import java.util.Collection;
import java.util.stream.Collectors;

import lostworlds.content.client.screen.AnalyzerScreen;
import lostworlds.content.client.screen.CultivatorScreen;
import lostworlds.content.client.screen.DNAExtractorScreen;
import lostworlds.content.client.screen.FossilGrinderScreen;
import lostworlds.content.jei.categories.AnalyzerCategory;
import lostworlds.content.jei.categories.CultivatorCategory;
import lostworlds.content.jei.categories.DNAExtractorCategory;
import lostworlds.content.jei.categories.FossilGrinderCategory;
import lostworlds.content.jei.categories.TimeMachineCategory;
import lostworlds.content.server.init.BlockInit;
import lostworlds.content.server.init.RecipeInit;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
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
		registration.addRecipes(getRecipes(manager, RecipeInit.FOSSIL_GRINDER_RECIPE), LostWorldsConstants.FOSSIL_GRINDER_CATEGORY);
		registration.addRecipes(getRecipes(manager, RecipeInit.DNA_EXTRACTOR_RECIPE), LostWorldsConstants.DNA_EXTRACTOR_CATEGORY);
		registration.addRecipes(getRecipes(manager, RecipeInit.ANALYZER_RECIPE), LostWorldsConstants.ANALYZER_CATEGORY);
		registration.addRecipes(getRecipes(manager, RecipeInit.CULTIVATOR_RECIPE), LostWorldsConstants.CULTIVATOR_CATEGORY);
		registration.addRecipes(getRecipes(manager, RecipeInit.TIME_MACHINE_RECIPE), LostWorldsConstants.TIME_MACHINE_CATEGORY);
	}
	
	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) 
	{
		IGuiHelper helper = registration.getJeiHelpers().getGuiHelper();
		
		registration.addRecipeCategories(new FossilGrinderCategory(helper));
		registration.addRecipeCategories(new DNAExtractorCategory(helper));
		registration.addRecipeCategories(new AnalyzerCategory(helper));
		registration.addRecipeCategories(new CultivatorCategory(helper));
		registration.addRecipeCategories(new TimeMachineCategory(helper));
	}
	
	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration) 
	{
		registration.addRecipeClickArea(FossilGrinderScreen.class, 75, 37, 35, 15, LostWorldsConstants.FOSSIL_GRINDER_CATEGORY);
		registration.addRecipeClickArea(DNAExtractorScreen.class, 75, 38, 34, 10, LostWorldsConstants.DNA_EXTRACTOR_CATEGORY);
		registration.addRecipeClickArea(AnalyzerScreen.class, 75, 38, 34, 12, LostWorldsConstants.ANALYZER_CATEGORY);
		registration.addRecipeClickArea(CultivatorScreen.class, 76, 34, 33, 17, LostWorldsConstants.CULTIVATOR_CATEGORY);
	}
	
	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) 
	{
		registration.addRecipeCatalyst(new ItemStack(BlockInit.FOSSIL_GRINDER), LostWorldsConstants.FOSSIL_GRINDER_CATEGORY);
		registration.addRecipeCatalyst(new ItemStack(BlockInit.DNA_EXTRACTOR), LostWorldsConstants.DNA_EXTRACTOR_CATEGORY);
		registration.addRecipeCatalyst(new ItemStack(BlockInit.ANALYZER), LostWorldsConstants.ANALYZER_CATEGORY);
		registration.addRecipeCatalyst(new ItemStack(BlockInit.CULTIVATOR), LostWorldsConstants.CULTIVATOR_CATEGORY);
		registration.addRecipeCatalyst(new ItemStack(BlockInit.TIME_MACHINE), LostWorldsConstants.TIME_MACHINE_CATEGORY);
	}
	
	private static Collection<?> getRecipes(RecipeManager manager, IRecipeType<?> type)
	{
		return manager.getRecipes().parallelStream().filter(recipe -> recipe.getType() == type).collect(Collectors.toList());
	}
}
