package lostworlds.content.jei.categories;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mojang.blaze3d.matrix.MatrixStack;

import lostworlds.content.ModUtils;
import lostworlds.content.jei.LostWorldsJeiConstants;
import lostworlds.content.server.init.BlockInit;
import lostworlds.library.container.recipes.FossilCleanerRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class FossilCleanerCategory implements IRecipeCategory<FossilCleanerRecipe> {
	public static final ResourceLocation TEXTURE_LOCATION = ModUtils.rL("textures/gui/lost_worlds_backgrounds.png");

	private final LoadingCache<Integer, IDrawableAnimated> cleanerProgessBar;

	private final IDrawable background;
	private final IDrawable icon;

	public FossilCleanerCategory(IGuiHelper helper) {
		ResourceLocation location = TEXTURE_LOCATION;
		this.background = helper.createDrawable(location, 0, 0, 82, 54);
		this.icon = helper.createDrawableIngredient(new ItemStack(BlockInit.FOSSIL_CLEANER));
		this.cleanerProgessBar = CacheBuilder.newBuilder().maximumSize(25).build(new CacheLoader<Integer, IDrawableAnimated>() {
			@Override
			public IDrawableAnimated load(Integer cookTime) {
				return helper.drawableBuilder(TEXTURE_LOCATION, 82, 18, 33, 16).buildAnimated(cookTime, IDrawableAnimated.StartDirection.LEFT, false);
			}
		});
	}

	protected IDrawableAnimated getCleaningProgessBar(FossilCleanerRecipe recipe) {
		int cookTime = recipe.getExtractingTime();
		if (cookTime <= 0) {
			cookTime = 1000;
		}
		return this.cleanerProgessBar.getUnchecked(cookTime);
	}

	@Override
	public ResourceLocation getUid() {
		return LostWorldsJeiConstants.FOSSIL_CLEANER_CATEGORY;
	}

	@Override
	public Class<? extends FossilCleanerRecipe> getRecipeClass() {
		return FossilCleanerRecipe.class;
	}

	@Override
	public String getTitle() {
		return ModUtils.tTC("jei", "fossil_cleaner.title").getString();
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
	public void setIngredients(FossilCleanerRecipe recipe, IIngredients ingredients) {
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutputs(VanillaTypes.ITEM, recipe.getOutputs());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, FossilCleanerRecipe recipe, IIngredients ingredients) {
		IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();

		itemStackGroup.init(0, true, 0, 0);
		itemStackGroup.init(1, false, 60, 18);

		itemStackGroup.set(ingredients);
	}

	@Override
	public void draw(FossilCleanerRecipe recipe, MatrixStack stack, double mouseX, double mouseY) {
		IDrawableAnimated arrow = getCleaningProgessBar(recipe);
		arrow.draw(stack, 21, 18);
	}
}
