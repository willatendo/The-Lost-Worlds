package lostworlds.integration.jei;

import java.util.Collection;
import java.util.stream.Collectors;

import lostworlds.content.client.screen.FossilGrinderScreen;
import lostworlds.content.server.init.RecipeInit;
import lostworlds.library.util.ModUtil;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class FossilGrinderJEIPlugin implements IModPlugin
{
	private static final ResourceLocation ID = ModUtil.rL("fossil_grinder_plugin");
	
	@Override
	public ResourceLocation getPluginUid() 
	{
		return ID;
	}
	
	@Override
	public void registerRecipes(IRecipeRegistration registration) 
	{
		RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();
		registration.addRecipes(getRecipes(manager, RecipeInit.FOSSIL_GRINDER_RECIPE), FossilGrinderCategory.ID);
	}
	
	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) 
	{
		IGuiHelper helper = registration.getJeiHelpers().getGuiHelper();
		
		registration.addRecipeCategories(new FossilGrinderCategory(helper));
	}
	
	private static Collection<?> getRecipes(RecipeManager manager, IRecipeType<?> type)
	{
		return manager.getRecipes().parallelStream().filter(recipe -> recipe.getType() == type).collect(Collectors.toList());
	}
	
	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration) 
	{
		registration.addRecipeClickArea(FossilGrinderScreen.class, 94, 38, 35, 15, ID);
	}
}
