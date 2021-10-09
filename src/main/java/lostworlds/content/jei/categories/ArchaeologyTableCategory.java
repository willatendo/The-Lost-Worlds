package lostworlds.content.jei.categories;

import java.util.List;

import lostworlds.content.ModUtils;
import lostworlds.content.jei.LostWorldsConstants;
import lostworlds.content.server.init.BlockInit;
import lostworlds.library.container.recipes.ArchaeologyTableRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.ICraftingGridHelper;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.Size2i;

public class ArchaeologyTableCategory implements IRecipeCategory<ArchaeologyTableRecipe>
{
	public static final ResourceLocation TEXTURE_LOCATION = ModUtils.rL("textures/gui/lost_worlds_backgrounds.png");
	
	private static final int craftOutputSlot = 0;
	private static final int craftInputSlot1 = 1;
	
	public static final int width = 116;
	public static final int height = 54;
	
	private final IDrawable background;
	private final IDrawable icon;
	
	private final ICraftingGridHelper craftingGridHelper;
	
	public ArchaeologyTableCategory(IGuiHelper helper) 
	{
		ResourceLocation location = TEXTURE_LOCATION;
		this.background = helper.createDrawable(location, 124, 0, width, height);
		this.icon = helper.createDrawableIngredient(new ItemStack(BlockInit.ARCHAEOLOGY_TABLE));
		craftingGridHelper = helper.createCraftingGridHelper(craftInputSlot1);
	}
	
	@Override
	public ResourceLocation getUid() 
	{
		return LostWorldsConstants.ARCHAEOLOGY_TABLE_CATEGORY;
	}

	@Override
	public Class<? extends ArchaeologyTableRecipe> getRecipeClass() 
	{
		return ArchaeologyTableRecipe.class;
	}

	@Override
	public String getTitle() 
	{
		return ModUtils.tTC("jei", "archaeology_table.title").getString();
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
	public void setIngredients(ArchaeologyTableRecipe recipe, IIngredients ingredients) 
	{
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutputs(VanillaTypes.ITEM, recipe.getOutput());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, ArchaeologyTableRecipe recipe, IIngredients ingredients) 
	{
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

		guiItemStacks.init(craftOutputSlot, false, 94, 18);

		for(int y = 0; y < 3; ++y) 
		{
			for(int x = 0; x < 3; ++x) 
			{
				int index = craftInputSlot1 + x + (y * 3);
				guiItemStacks.init(index, true, x * 18, y * 18);
			}
		}
				
		List<List<ItemStack>> inputs = ingredients.getInputs(VanillaTypes.ITEM);
		List<List<ItemStack>> outputs = ingredients.getOutputs(VanillaTypes.ITEM);
		
		Size2i size = new Size2i(3, 3);
		if(size != null && size.width > 0 && size.height > 0) 
		{
			craftingGridHelper.setInputs(guiItemStacks, inputs, size.width, size.height);
		} 
		else 
		{
			craftingGridHelper.setInputs(guiItemStacks, inputs);
			recipeLayout.setShapeless();
		}
		
		guiItemStacks.set(craftOutputSlot, outputs.get(0));
	}
}
