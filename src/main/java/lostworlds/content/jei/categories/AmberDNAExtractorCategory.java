package lostworlds.content.jei.categories;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mojang.blaze3d.matrix.MatrixStack;

import lostworlds.content.ModUtils;
import lostworlds.content.jei.LostWorldsJeiConstants;
import lostworlds.content.jei.recipe.AmberDNAExtractorRecipe;
import lostworlds.content.server.init.BlockInit;
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

public class AmberDNAExtractorCategory implements IRecipeCategory<AmberDNAExtractorRecipe> {
	public static final ResourceLocation TEXTURE_LOCATION = ModUtils.rL("textures/gui/lost_worlds_backgrounds.png");

	private final LoadingCache<Integer, IDrawableAnimated> extractionProgessBar;

	private final IDrawable background;
	private final IDrawable icon;

	public AmberDNAExtractorCategory(IGuiHelper helper) {
		ResourceLocation location = TEXTURE_LOCATION;
		this.background = helper.createDrawable(location, 0, 100, 82, 38);
		this.icon = helper.createDrawableIngredient(new ItemStack(BlockInit.DNA_EXTRACTOR));
		this.extractionProgessBar = CacheBuilder.newBuilder().maximumSize(25).build(new CacheLoader<Integer, IDrawableAnimated>() {
			@Override
			public IDrawableAnimated load(Integer cookTime) {
				return helper.drawableBuilder(TEXTURE_LOCATION, 82, 100, 34, 10).buildAnimated(cookTime, IDrawableAnimated.StartDirection.LEFT, false);
			}
		});
	}

	protected IDrawableAnimated getExtractionProgessBar(AmberDNAExtractorRecipe recipe) {
		int cookTime = recipe.getExtractingTime();
		if (cookTime <= 0) {
			cookTime = 60;
		}
		return this.extractionProgessBar.getUnchecked(cookTime);
	}

	@Override
	public ResourceLocation getUid() {
		return LostWorldsJeiConstants.AMBER_DNA_EXTRACTOR_CATEGORY;
	}

	@Override
	public Class<? extends AmberDNAExtractorRecipe> getRecipeClass() {
		return AmberDNAExtractorRecipe.class;
	}

	@Override
	public String getTitle() {
		return ModUtils.tTC("jei", "dna_extractor.title").getString();
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
	public void setIngredients(AmberDNAExtractorRecipe recipe, IIngredients ingredients) {
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutputs(VanillaTypes.ITEM, recipe.getOutputs());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, AmberDNAExtractorRecipe recipe, IIngredients ingredients) {
		IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();

		itemStackGroup.init(0, true, 0, 0);
		itemStackGroup.init(1, true, 0, 20);
		itemStackGroup.init(2, false, 60, 10);

		itemStackGroup.set(ingredients);
	}

	@Override
	public void draw(AmberDNAExtractorRecipe recipe, MatrixStack stack, double mouseX, double mouseY) {
		IDrawableAnimated arrow = getExtractionProgessBar(recipe);
		arrow.draw(stack, 20, 14);
	}
}
