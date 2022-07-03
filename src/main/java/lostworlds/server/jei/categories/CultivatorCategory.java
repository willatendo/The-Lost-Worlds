package lostworlds.server.jei.categories;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mojang.blaze3d.vertex.PoseStack;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.jei.LostWorldsJeiConstants;
import lostworlds.server.menu.recipes.CultivatorRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class CultivatorCategory implements IRecipeCategory<CultivatorRecipe> {
	public static final ResourceLocation TEXTURE_LOCATION = LostWorldsUtils.rL("textures/gui/lost_worlds_backgrounds.png");

	private final LoadingCache<Integer, IDrawableAnimated> cultivatorProgessBar;

	private final IDrawable background;
	private final IDrawable icon;

	public CultivatorCategory(IGuiHelper helper) {
		ResourceLocation location = TEXTURE_LOCATION;
		this.background = helper.createDrawable(location, 0, 230, 94, 26);
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, LostWorldsBlocks.CULTIVATOR.asStack());
		this.cultivatorProgessBar = CacheBuilder.newBuilder().maximumSize(25).build(new CacheLoader<Integer, IDrawableAnimated>() {
			@Override
			public IDrawableAnimated load(Integer cookTime) {
				return helper.drawableBuilder(TEXTURE_LOCATION, 95, 230, 33, 17).buildAnimated(cookTime, IDrawableAnimated.StartDirection.LEFT, false);
			}
		});
	}

	protected IDrawableAnimated getCultivatingProgessBar(CultivatorRecipe recipe) {
		int cookTime = recipe.getCultivatingTime();
		if (cookTime <= 0) {
			cookTime = 4000;
		}
		return this.cultivatorProgessBar.getUnchecked(cookTime);
	}

	@Override
	public ResourceLocation getUid() {
		return LostWorldsJeiConstants.CULTIVATOR_CATEGORY.getUid();
	}

	@Override
	public Class<? extends CultivatorRecipe> getRecipeClass() {
		return CultivatorRecipe.class;
	}

	@Override
	public Component getTitle() {
		return LostWorldsUtils.tTC("jei", "cultivator.title");
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
	public void setRecipe(IRecipeLayoutBuilder builder, CultivatorRecipe recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 5, 5).addIngredients(recipe.getIngredients().get(0));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 73, 5).addItemStack(recipe.getOutputs().get(0));
	}

	@Override
	public void draw(CultivatorRecipe recipe, PoseStack stack, double mouseX, double mouseY) {
		IDrawableAnimated arrow = getCultivatingProgessBar(recipe);
		arrow.draw(stack, 31, 5);
	}
}
