package lostworlds.content.jei.categories;

import com.mojang.blaze3d.matrix.MatrixStack;

import lostworlds.content.ModUtils;
import lostworlds.content.jei.LostWorldsConstants;
import lostworlds.content.jei.recipe.WaterFuelRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

public class WaterFuelCategory implements IRecipeCategory<WaterFuelRecipe>
{
	public static final ResourceLocation TEXTURE_LOCATION = ModUtils.rL("textures/gui/lost_worlds_backgrounds.png");

	protected static final int fuelSlot = 1;
	private final IDrawableStatic background;
	private final IDrawable icon;
	protected final IDrawableStatic bucket;
	protected final IDrawableAnimated animatedBucket;
	
	public WaterFuelCategory(IGuiHelper helper) 
	{
		this.background = helper.drawableBuilder(TEXTURE_LOCATION, 0, 18, 18, 35).build();
		this.icon = helper.createDrawableIngredient(new ItemStack(Items.WATER_BUCKET));
		this.bucket = helper.createDrawable(TEXTURE_LOCATION, 82, 0, 22, 22);
		this.animatedBucket = helper.createAnimatedDrawable(this.bucket, 300, IDrawableAnimated.StartDirection.TOP, true);
	}
	
	@Override
	public ResourceLocation getUid() 
	{
		return LostWorldsConstants.WATER_FUEL_CATEGORY;
	}

	@Override
	public Class<? extends WaterFuelRecipe> getRecipeClass() 
	{
		return WaterFuelRecipe.class;
	}

	@Override
	public String getTitle() 
	{
		return ModUtils.tTC("jei", "fossil_cleaner_fuel.title").getString();
	}

	@Override
	public IDrawable getBackground() 
	{
		return this.background;
	}

	@Override
	public IDrawable getIcon() 
	{
		return this.icon;
	}

	@Override
	public void setIngredients(WaterFuelRecipe recipe, IIngredients ingredients) 
	{
		ingredients.setInputs(VanillaTypes.ITEM, recipe.getInputs());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, WaterFuelRecipe recipe, IIngredients ingredients) 
	{
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

		guiItemStacks.init(fuelSlot, true, 0, 18);
		guiItemStacks.set(ingredients);
	}
	
	@Override
	public void draw(WaterFuelRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) 
	{
		IDrawableAnimated flame = recipe.getBucket();
		flame.draw(matrixStack, 0, 0);
	}
}
