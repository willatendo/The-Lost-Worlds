package lostworlds.content.jei.categories;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mojang.blaze3d.matrix.MatrixStack;

import lostworlds.content.ModUtils;
import lostworlds.content.jei.LostWorldsConstants;
import lostworlds.content.server.init.BlockInit;
import lostworlds.library.container.recipes.CultivatorRecipe;
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

public class CultivatorCategory implements IRecipeCategory<CultivatorRecipe>
{
public static final ResourceLocation TEXTURE_LOCATION = ModUtils.rL("textures/gui/lost_worlds_backgrounds.png");
	
	private final LoadingCache<Integer, IDrawableAnimated> cultivatorProgessBar;
	
	private final IDrawable background;
	private final IDrawable icon;

	public CultivatorCategory(IGuiHelper helper) 
	{
		ResourceLocation location = TEXTURE_LOCATION;
		this.background = helper.createDrawable(location, 0, 230, 94, 26);
		this.icon = helper.createDrawableIngredient(new ItemStack(BlockInit.CULTIVATOR));
		this.cultivatorProgessBar = CacheBuilder.newBuilder().maximumSize(25).build(new CacheLoader<Integer, IDrawableAnimated>() 
		{
			@Override
			public IDrawableAnimated load(Integer cookTime) 
			{
				return helper.drawableBuilder(TEXTURE_LOCATION, 95, 230, 33, 17).buildAnimated(cookTime, IDrawableAnimated.StartDirection.LEFT, false);
			}
		});
	}
	
	protected IDrawableAnimated getCultivatingProgessBar(CultivatorRecipe recipe) 
	{
		int cookTime = recipe.getCultivatingTime();
		if(cookTime <= 0) 
		{
			cookTime = 4000;
		}
		return this.cultivatorProgessBar.getUnchecked(cookTime);
	}
	
	@Override
	public ResourceLocation getUid() 
	{
		return LostWorldsConstants.CULTIVATOR_CATEGORY;
	}

	@Override
	public Class<? extends CultivatorRecipe> getRecipeClass() 
	{
		return CultivatorRecipe.class;
	}

	@Override
	public String getTitle() 
	{
		return ModUtils.tTC("jei", "cultivator.title").getString();
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
	public void setIngredients(CultivatorRecipe recipe, IIngredients ingredients) 
	{
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutputs(VanillaTypes.ITEM, recipe.getOutputs());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, CultivatorRecipe recipe, IIngredients ingredients) 
	{
		IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();
		
		itemStackGroup.init(0, true, 4, 4);
		itemStackGroup.init(1, false, 72, 4);
		
		itemStackGroup.set(ingredients);
	}
	
	@Override
	public void draw(CultivatorRecipe recipe, MatrixStack stack, double mouseX, double mouseY) 
	{
		IDrawableAnimated arrow = getCultivatingProgessBar(recipe);
		arrow.draw(stack, 31, 5);
	}
}
