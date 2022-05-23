package lostworlds.server.jei.categories;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mojang.blaze3d.vertex.PoseStack;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.jei.LostWorldsJeiConstants;
import lostworlds.server.menu.recipes.DNAInjectorRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class DNAInjectorCategory implements IRecipeCategory<DNAInjectorRecipe> {
	public static final ResourceLocation TEXTURE_LOCATION = LostWorldsUtils.rL("textures/gui/lost_worlds_backgrounds.png");

	private final LoadingCache<Integer, IDrawableAnimated> injectionProgessBar;

	private final IDrawable background;
	private final IDrawable icon;

	public DNAInjectorCategory(IGuiHelper helper) {
		ResourceLocation location = TEXTURE_LOCATION;
		this.background = helper.createDrawable(location, 0, 100, 82, 38);
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM, LostWorldsBlocks.DNA_INJECTOR.asStack());
		this.injectionProgessBar = CacheBuilder.newBuilder().maximumSize(25).build(new CacheLoader<Integer, IDrawableAnimated>() {
			@Override
			public IDrawableAnimated load(Integer cookTime) {
				return helper.drawableBuilder(TEXTURE_LOCATION, 82, 100, 34, 10).buildAnimated(cookTime, IDrawableAnimated.StartDirection.LEFT, false);
			}
		});
	}

	protected IDrawableAnimated getInjectionProgessBar(DNAInjectorRecipe recipe) {
		int cookTime = recipe.getInjectingTime();
		if (cookTime <= 0) {
			cookTime = 60;
		}
		return this.injectionProgessBar.getUnchecked(cookTime);
	}

	@Override
	public ResourceLocation getUid() {
		return LostWorldsJeiConstants.DNA_INJECTOR_CATEGORY.getUid();
	}

	@Override
	public Class<? extends DNAInjectorRecipe> getRecipeClass() {
		return DNAInjectorRecipe.class;
	}

	@Override
	public Component getTitle() {
		return LostWorldsUtils.tTC("jei", "dna_injector.title");
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
//	public void setIngredients(DNAInjectorRecipe recipe, IIngredients ingredients) {
//		ingredients.setInputIngredients(recipe.getIngredients());
//		ingredients.setOutputs(VanillaTypes.ITEM, recipe.getOutputs());
//	}
//
//	@Override
//	public void setRecipe(IRecipeLayout recipeLayout, DNAInjectorRecipe recipe, IIngredients ingredients) {
//		IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();
//
//		itemStackGroup.init(0, true, 0, 0);
//		itemStackGroup.init(1, true, 0, 20);
//		itemStackGroup.init(2, false, 60, 10);
//
//		itemStackGroup.set(ingredients);
//	}

	@Override
	public void draw(DNAInjectorRecipe recipe, PoseStack stack, double mouseX, double mouseY) {
		IDrawableAnimated arrow = getInjectionProgessBar(recipe);
		arrow.draw(stack, 20, 14);
	}
}
