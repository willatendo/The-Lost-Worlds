package lostworlds.jei;

import java.util.Collection;
import java.util.stream.Collectors;

import lostworlds.content.server.init.RecipeInit;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class TimeMachineJEIPlugin implements IModPlugin
{
	@Override
	public ResourceLocation getPluginUid() 
	{
		return LostWorldsConstants.TIME_MACHINE_PLUGIN;
	}
	
	@Override
	public void registerRecipes(IRecipeRegistration registration) 
	{
		RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();
		registration.addRecipes(getRecipes(manager, RecipeInit.TIME_MACHINE_RECIPE), LostWorldsConstants.TIME_MACHINE_CATEGORY);
	}
	
	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) 
	{
		IGuiHelper helper = registration.getJeiHelpers().getGuiHelper();
		
		registration.addRecipeCategories(new TimeMachineCategory(helper));
	}
	
	private static Collection<?> getRecipes(RecipeManager manager, IRecipeType<?> type)
	{
		return manager.getRecipes().parallelStream().filter(recipe -> recipe.getType() == type).collect(Collectors.toList());
	}
}
