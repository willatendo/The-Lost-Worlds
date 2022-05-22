package lostworlds.server.jei.categories;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.container.recipes.TimeMachineRecipe;
import lostworlds.server.jei.LostWorldsJeiConstants;
import mezz.jei.api.constants.ModIds;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class TimeMachineCategory implements IRecipeCategory<TimeMachineRecipe> {
	public static final String TEXTURE = "textures/gui/gui_vanilla.png";
	public static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(ModIds.JEI_ID, TEXTURE);

	private final IDrawable background;
	private final IDrawable icon;

	public TimeMachineCategory(IGuiHelper helper) {
		ResourceLocation location = TEXTURE_LOCATION;
		this.background = helper.createDrawable(location, 0, 168, 125, 18);
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM, LostWorldsBlocks.TIME_MACHINE.asStack());
	}

	@Override
	public ResourceLocation getUid() {
		return LostWorldsJeiConstants.TIME_MACHINE_CATEGORY.getUid();
	}

	@Override
	public Class<? extends TimeMachineRecipe> getRecipeClass() {
		return TimeMachineRecipe.class;
	}

	@Override
	public Component getTitle() {
		return LostWorldsUtils.tTC("jei", "time_machine.title");
	}

	@Override
	public IDrawable getBackground() {
		return this.background;
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

//	@Override
//	public void setIngredients(TimeMachineRecipe recipe, IIngredients ingredients) {
//		ingredients.setInputIngredients(recipe.getIngredients());
//		ingredients.setOutputs(VanillaTypes.ITEM, recipe.getOutput());
//	}
//
//	@Override
//	public void setRecipe(IRecipeLayout recipeLayout, TimeMachineRecipe recipe, IIngredients ingredients) {
//		IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();
//
//		itemStackGroup.init(0, true, 0, 0);
//		itemStackGroup.init(1, true, 49, 0);
//		itemStackGroup.init(2, false, 107, 0);
//
//		itemStackGroup.set(ingredients);
//	}
}
