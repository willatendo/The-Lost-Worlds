package lostworlds.jei;

import lostworlds.content.server.init.BlockInit;
import lostworlds.library.recipe.TimeMachineRecipe;
import lostworlds.library.util.ModUtils;
import mezz.jei.api.constants.ModIds;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class TimeMachineCategory implements IRecipeCategory<TimeMachineRecipe>
{		
	public static final String TEXTURE_GUI_PATH = "textures/gui/";
	public static final String TEXTURE_GUI_VANILLA = TEXTURE_GUI_PATH + "gui_vanilla.png";
	public static final ResourceLocation RECIPE_GUI_VANILLA = new ResourceLocation(ModIds.JEI_ID, TEXTURE_GUI_VANILLA);
	
	private final IDrawable background;
	private final IDrawable icon;
	
	public TimeMachineCategory(IGuiHelper helper) 
	{
		ResourceLocation location = RECIPE_GUI_VANILLA;
		this.background = helper.createDrawable(location, 0, 168, 125, 18);
		this.icon = helper.createDrawableIngredient(new ItemStack(BlockInit.TIME_MACHINE.asItem()));
	}
	
	@Override
	public ResourceLocation getUid() 
	{
		return LostWorldsConstants.TIME_MACHINE_CATEGORY;
	}

	@Override
	public Class<? extends TimeMachineRecipe> getRecipeClass() 
	{
		return TimeMachineRecipe.class;
	}

	@Override
	public String getTitle() 
	{
		return ModUtils.tTC("jei", "time_machine.title").getString();
	}

	@Override
	public IDrawable getBackground() 
	{
		return background;
	}

	@Override
	public IDrawable getIcon() 
	{
		return icon;
	}

	@Override
	public void setIngredients(TimeMachineRecipe recipe, IIngredients ingredients) 
	{
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutputs(VanillaTypes.ITEM, recipe.getOutput());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, TimeMachineRecipe recipe, IIngredients ingredients) 
	{
		IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();
		
		itemStackGroup.init(0, true, 0, 0);
		itemStackGroup.init(1, true, 49, 0);
		itemStackGroup.init(2, false, 107, 0);
		
		itemStackGroup.set(ingredients);
	}
}
