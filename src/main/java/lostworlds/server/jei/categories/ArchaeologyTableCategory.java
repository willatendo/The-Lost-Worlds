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

	public static final int width = 116;
	public static final int height = 54;

	private final IDrawable background;
	private final IDrawable icon;

	public ArchaeologyTableCategory(IGuiHelper helper) {
		ResourceLocation location = TEXTURE_LOCATION;
		this.background = helper.createDrawable(location, 124, 0, width, height);
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM, LostWorldsBlocks.ARCHAEOLOGY_TABLE.asStack());
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

//	@Override
//	public void setIngredients(ArchaeologyTableRecipe recipe, IIngredients ingredients) {
//		ingredients.setInputIngredients(recipe.getIngredients());
//		ingredients.setOutputs(VanillaTypes.ITEM, recipe.getOutput());
//	}
//
//	@Override
//	public void setRecipe(IRecipeLayout recipeLayout, ArchaeologyTableRecipe recipe, IIngredients ingredients) {
//		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
//
//		guiItemStacks.init(craftOutputSlot, false, 94, 18);
//
//		for (int y = 0; y < 3; ++y) {
//			for (int x = 0; x < 3; ++x) {
//				int index = craftInputSlot1 + x + (y * 3);
//				guiItemStacks.init(index, true, x * 18, y * 18);
//			}
//		}
//
//		List<List<ItemStack>> inputs = ingredients.getInputs(VanillaTypes.ITEM);
//		List<List<ItemStack>> outputs = ingredients.getOutputs(VanillaTypes.ITEM);
//
//		Size2i size = new Size2i(3, 3);
//		if (size != null && size.width > 0 && size.height > 0) {
//			craftingGridHelper.setInputs(guiItemStacks, inputs, size.width, size.height);
//		} else {
//			craftingGridHelper.setInputs(guiItemStacks, inputs);
//			recipeLayout.setShapeless();
//		}
//
//		guiItemStacks.set(craftOutputSlot, outputs.get(0));
//	}
}
