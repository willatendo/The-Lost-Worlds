package lostworlds.server.jei.categories;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mojang.blaze3d.vertex.PoseStack;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.jei.LostWorldsJeiConstants;
import lostworlds.server.menu.recipes.FossilGrinderRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;

public class FossilGrinderCategory implements IRecipeCategory<FossilGrinderRecipe> {
	public static final ResourceLocation TEXTURE_LOCATION = LostWorldsUtils.rL("textures/gui/lost_worlds_backgrounds.png");

	private final LoadingCache<Integer, IDrawableAnimated> grinderProgessBar;

	private final IDrawable background;
	private final IDrawable icon;

	public FossilGrinderCategory(IGuiHelper helper) {
		ResourceLocation location = TEXTURE_LOCATION;
		this.background = helper.createDrawable(location, 0, 54, 89, 46);
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM, LostWorldsBlocks.FOSSIL_GRINDER.asStack());
		this.grinderProgessBar = CacheBuilder.newBuilder().maximumSize(25).build(new CacheLoader<Integer, IDrawableAnimated>() {
			@Override
			public IDrawableAnimated load(Integer cookTime) {
				return helper.drawableBuilder(TEXTURE_LOCATION, 89, 54, 35, 15).buildAnimated(cookTime, IDrawableAnimated.StartDirection.LEFT, false);
			}
		});
	}

	protected IDrawableAnimated getGrinderProgessBar(FossilGrinderRecipe recipe) {
		int cookTime = recipe.getGrindTime();
		if (cookTime <= 0) {
			cookTime = 300;
		}
		return this.grinderProgessBar.getUnchecked(cookTime);
	}

	@Override
	public ResourceLocation getUid() {
		return LostWorldsJeiConstants.FOSSIL_GRINDER_CATEGORY.getUid();
	}

	@Override
	public Class<? extends FossilGrinderRecipe> getRecipeClass() {
		return FossilGrinderRecipe.class;
	}

	@Override
	public Component getTitle() {
		return LostWorldsUtils.tTC("jei", "fossil_grinder.title");
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
//	public void setIngredients(FossilGrinderRecipe recipe, IIngredients ingredients) {
//		ingredients.setInputIngredients(recipe.getIngredients());
//		ingredients.setOutputs(VanillaTypes.ITEM, recipe.getOutputs());
//	}
//
//	@Override
//	public void setRecipe(IRecipeLayout recipeLayout, FossilGrinderRecipe recipe, IIngredients ingredients) {
//		IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();
//
//		itemStackGroup.init(0, true, 4, 4);
//		itemStackGroup.init(1, false, 67, 4);
//		itemStackGroup.init(2, false, 51, 27);
//		itemStackGroup.init(3, false, 70, 27);
//
//		itemStackGroup.set(ingredients);
//	}

	@Override
	public void draw(FossilGrinderRecipe recipe, PoseStack stack, double mouseX, double mouseY) {
		IDrawableAnimated arrow = getGrinderProgessBar(recipe);
		arrow.draw(stack, 27, 7);

		drawChance(recipe, stack, 32);
	}

	public void drawChance(FossilGrinderRecipe recipe, PoseStack stack, int y) {
		TranslatableComponent name = LostWorldsUtils.tTC("jei", "fossil_grinder.chance");
		Minecraft minecraft = Minecraft.getInstance();
		Font fontRenderer = minecraft.font;
		fontRenderer.draw(stack, name, 4, y, 0xFF808080);
	}
}
