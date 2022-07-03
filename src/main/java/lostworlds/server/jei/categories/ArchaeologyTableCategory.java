package lostworlds.server.jei.categories;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.jei.LostWorldsJeiConstants;
import lostworlds.server.menu.recipes.ArchaeologyTableRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ArchaeologyTableCategory implements IRecipeCategory<ArchaeologyTableRecipe> {
	public static final ResourceLocation TEXTURE_LOCATION = LostWorldsUtils.rL("textures/gui/lost_worlds_backgrounds.png");

	private final IDrawable background;
	private final IDrawable icon;

	public ArchaeologyTableCategory(IGuiHelper helper) {
		ResourceLocation location = TEXTURE_LOCATION;
		this.background = helper.createDrawable(location, 124, 0, 116, 54);
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, LostWorldsBlocks.ARCHAEOLOGY_TABLE.asStack());
	}

	@Override
	public ResourceLocation getUid() {
		return LostWorldsJeiConstants.ARCHAEOLOGY_TABLE_CATEGORY.getUid();
	}

	@Override
	public Class<? extends ArchaeologyTableRecipe> getRecipeClass() {
		return ArchaeologyTableRecipe.class;
	}

	@Override
	public Component getTitle() {
		return LostWorldsUtils.tTC("jei", "archaeology_table.title");
	}

	@Override
	public IDrawable getBackground() {
		return this.background;
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}
}
