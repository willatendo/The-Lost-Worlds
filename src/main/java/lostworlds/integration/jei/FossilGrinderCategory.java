package lostworlds.integration.jei;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mojang.blaze3d.matrix.MatrixStack;

import lostworlds.content.server.init.BlockInit;
import lostworlds.library.recipe.FossilGrinderRecipe;
import lostworlds.library.util.ModUtil;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class FossilGrinderCategory implements IRecipeCategory<FossilGrinderRecipe>
{
	public static final ResourceLocation ID = ModUtil.rL("fossil_grinder_category");
	public static final ResourceLocation DISPLAY = ModUtil.rL("textures/gui/jei/lostworlds_backgrounds.png");
	
	private final LoadingCache<Integer, IDrawableAnimated> grinderProgessBar;
	
	private final IDrawable background;
	private final IDrawable icon;
	
	public FossilGrinderCategory(IGuiHelper helper) 
	{
		this.background = helper.createDrawable(DISPLAY, 0, 38, 94, 46);
		this.icon = helper.createDrawableIngredient(new ItemStack(BlockInit.FOSSIL_GRINDER.asItem()));
		this.grinderProgessBar = CacheBuilder.newBuilder().maximumSize(25).build(new CacheLoader<Integer, IDrawableAnimated>() 
		{
			@Override
			public IDrawableAnimated load(Integer cookTime) 
			{
				return helper.drawableBuilder(DISPLAY, 94, 38, 35, 15).buildAnimated(cookTime, IDrawableAnimated.StartDirection.LEFT, false);
			}
		});
	}
	
	protected IDrawableAnimated getGrinderProgessBar(FossilGrinderRecipe recipe) 
	{
		int cookTime = recipe.getGrindTime();
		if(cookTime <= 0) 
		{
			cookTime = 300;
		}
		return this.grinderProgessBar.getUnchecked(cookTime);
	}
	
	@Override
	public ResourceLocation getUid() 
	{
		return ID;
	}

	@Override
	public Class<? extends FossilGrinderRecipe> getRecipeClass() 
	{
		return FossilGrinderRecipe.class;
	}

	@Override
	public String getTitle() 
	{
		return ModUtil.tTC("jei", "fossil_grinder.title").getString();
	}

	@Override
	public IDrawable getBackground() 
	{
		return background;
	}

	@Override
	public IDrawable getIcon() 
	{
		return icon;
	}

	@Override
	public void setIngredients(FossilGrinderRecipe recipe, IIngredients ingredients) 
	{
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutputs(VanillaTypes.ITEM, recipe.getOutputs());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, FossilGrinderRecipe recipe, IIngredients ingredients) 
	{
		IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();
		
		itemStackGroup.init(0, true, 4, 4);
		itemStackGroup.init(1, false, 71, 4);
		
		itemStackGroup.set(ingredients);
	}
	
	@Override
	public void draw(FossilGrinderRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) 
	{
		IDrawableAnimated arrow = getGrinderProgessBar(recipe);
		arrow.draw(matrixStack, 29, 6);
		
		drawChance(recipe, matrixStack, 30);
	}
	
	public void drawChance(FossilGrinderRecipe recipe, MatrixStack matrixStack, int y)
	{
		TranslationTextComponent name = ModUtil.tTC("jei", "fossil_grinder.chance");
		Minecraft minecraft = Minecraft.getInstance();
		FontRenderer fontRenderer = minecraft.font;
		int stringWidth = fontRenderer.width(name);
		fontRenderer.draw(matrixStack, name, background.getWidth() - stringWidth, y, 0xFF808080);
	}
}
