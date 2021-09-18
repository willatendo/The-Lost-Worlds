package lostworlds.jei.categories;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mojang.blaze3d.matrix.MatrixStack;

import lostworlds.content.server.init.BlockInit;
import lostworlds.jei.LostWorldsConstants;
import lostworlds.library.recipe.AnalyserRecipe;
import lostworlds.library.util.ModUtils;
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

public class AnalyserCategory implements IRecipeCategory<AnalyserRecipe>
{
	public static final ResourceLocation TEXTURE_LOCATION = ModUtils.rL("textures/gui/lost_worlds_backgrounds.png");
	
	private final LoadingCache<Integer, IDrawableAnimated> analyseProgessBar;
	
	private final IDrawable background;
	private final IDrawable icon;

	public AnalyserCategory(IGuiHelper helper) 
	{
		ResourceLocation location = TEXTURE_LOCATION;
		this.background = helper.createDrawable(location, 0, 138, 82, 38);
		this.icon = helper.createDrawableIngredient(new ItemStack(BlockInit.DNA_EXTRACTOR));
		this.analyseProgessBar = CacheBuilder.newBuilder().maximumSize(25).build(new CacheLoader<Integer, IDrawableAnimated>() 
		{
			@Override
			public IDrawableAnimated load(Integer cookTime) 
			{
				return helper.drawableBuilder(TEXTURE_LOCATION, 82, 138, 34, 16).buildAnimated(cookTime, IDrawableAnimated.StartDirection.LEFT, false);
			}
		});
	}
	
	protected IDrawableAnimated getAnalyseProgessBar(AnalyserRecipe recipe) 
	{
		int cookTime = recipe.getAnalysingTime();
		if(cookTime <= 0) 
		{
			cookTime = 60;
		}
		return this.analyseProgessBar.getUnchecked(cookTime);
	}
	
	@Override
	public ResourceLocation getUid() 
	{
		return LostWorldsConstants.ANALYSER_CATEGORY;
	}

	@Override
	public Class<? extends AnalyserRecipe> getRecipeClass() 
	{
		return AnalyserRecipe.class;
	}

	@Override
	public String getTitle() 
	{
		return ModUtils.tTC("jei", "analyser.title").getString();
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
	public void setIngredients(AnalyserRecipe recipe, IIngredients ingredients) 
	{
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutputs(VanillaTypes.ITEM, recipe.getOutputs());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, AnalyserRecipe recipe, IIngredients ingredients) 
	{
		IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();
		
		itemStackGroup.init(0, true, 0, 0);
		itemStackGroup.init(1, true, 0, 20);
		itemStackGroup.init(2, false, 60, 10);
		
		itemStackGroup.set(ingredients);
	}
	
	@Override
	public void draw(AnalyserRecipe recipe, MatrixStack stack, double mouseX, double mouseY) 
	{
		IDrawableAnimated arrow = getAnalyseProgessBar(recipe);
		arrow.draw(stack, 20, 11);
	}
}
