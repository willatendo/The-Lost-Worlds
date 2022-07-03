package lostworlds.server.jei.categories;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.jei.LostWorldsJeiConstants;
import lostworlds.server.menu.recipes.TimeMachineRecipe;
import mezz.jei.api.constants.ModIds;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
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
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, LostWorldsBlocks.TIME_MACHINE.asStack());
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

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, TimeMachineRecipe recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 1, 1).addIngredients(recipe.getIngredients().get(0));
		builder.addSlot(RecipeIngredientRole.INPUT, 50, 1).addIngredients(recipe.getIngredients().get(1));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 108, 1).addItemStack(recipe.getOutputs().get(0));
	}
}
