package lostworlds.client.jei.categories;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mojang.blaze3d.matrix.MatrixStack;

import lostworlds.client.jei.LostWorldsJeiConstants;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.container.recipes.DNAExtractorRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.util.ResourceLocation;

public class DNAExtractorCategory implements IRecipeCategory<DNAExtractorRecipe> {
	public static final ResourceLocation TEXTURE_LOCATION = LostWorldsUtils.rL("textures/gui/lost_worlds_backgrounds.png");

	private final LoadingCache<Integer, IDrawableAnimated> extractionProgessBar;

	private final IDrawable background;
	private final IDrawable icon;

	public DNAExtractorCategory(IGuiHelper helper) {
		ResourceLocation location = TEXTURE_LOCATION;
		this.background = helper.createDrawable(location, 0, 100, 82, 38);
		this.icon = helper.createDrawableIngredient(LostWorldsBlocks.DNA_EXTRACTOR.asStack());
		this.extractionProgessBar = CacheBuilder.newBuilder().maximumSize(25).build(new CacheLoader<Integer, IDrawableAnimated>() {
			@Override
			public IDrawableAnimated load(Integer cookTime) {
				return helper.drawableBuilder(TEXTURE_LOCATION, 82, 100, 34, 10).buildAnimated(cookTime, IDrawableAnimated.StartDirection.LEFT, false);
			}
		});
	}

	protected IDrawableAnimated getExtractionProgessBar(DNAExtractorRecipe recipe) {
		int cookTime = recipe.getExtractingTime();
		if (cookTime <= 0) {
			cookTime = 60;
		}
		return this.extractionProgessBar.getUnchecked(cookTime);
	}

	@Override
	public ResourceLocation getUid() {
		return LostWorldsJeiConstants.DNA_EXTRACTOR_CATEGORY;
	}

	@Override
	public Class<? extends DNAExtractorRecipe> getRecipeClass() {
		return DNAExtractorRecipe.class;
	}

	@Override
	public String getTitle() {
		return LostWorldsUtils.tTC("jei", "dna_extractor.title").getString();
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
	public void setIngredients(DNAExtractorRecipe recipe, IIngredients ingredients) {
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutputs(VanillaTypes.ITEM, recipe.getOutputs());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, DNAExtractorRecipe recipe, IIngredients ingredients) {
		IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();

		itemStackGroup.init(0, true, 0, 0);
		itemStackGroup.init(1, true, 0, 20);
		itemStackGroup.init(2, false, 60, 10);

		itemStackGroup.set(ingredients);
	}

	@Override
	public void draw(DNAExtractorRecipe recipe, MatrixStack stack, double mouseX, double mouseY) {
		IDrawableAnimated arrow = getExtractionProgessBar(recipe);
		arrow.draw(stack, 20, 14);
	}
}
