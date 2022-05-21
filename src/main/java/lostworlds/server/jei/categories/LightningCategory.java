package lostworlds.server.jei.categories;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.jei.LostWorldsJeiConstants;
import lostworlds.server.jei.recipe.LightningRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

public class LightningCategory implements IRecipeCategory<LightningRecipe> {
	public static final ResourceLocation TEXTURE_LOCATION = LostWorldsUtils.rL("textures/gui/lost_worlds_backgrounds.png");

	private final IDrawable background;
	private final IDrawable icon;

	public LightningCategory(IGuiHelper helper) {
		ResourceLocation location = TEXTURE_LOCATION;
		this.background = helper.createDrawable(location, 124, 54, 72, 54);
		this.icon = helper.createDrawableIngredient(Items.REDSTONE.getDefaultInstance());
	}

	@Override
	public ResourceLocation getUid() {
		return LostWorldsJeiConstants.LIGHTNING_CATEGORY;
	}

	@Override
	public Class<? extends LightningRecipe> getRecipeClass() {
		return LightningRecipe.class;
	}

	@Override
	public String getTitle() {
		return LostWorldsUtils.tTC("jei", "lightning.title").getString();
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
	public void setIngredients(LightningRecipe recipe, IIngredients ingredients) {
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutputs(VanillaTypes.ITEM, recipe.getOutputs());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, LightningRecipe recipe, IIngredients ingredients) {
		IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();

		itemStackGroup.init(0, true, 18, 36);
		itemStackGroup.init(1, false, 54, 19);

		itemStackGroup.set(ingredients);
	}
}
