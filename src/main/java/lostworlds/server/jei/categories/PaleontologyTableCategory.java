package lostworlds.server.jei.categories;

import java.util.List;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.jei.LostWorldsJeiConstants;
import lostworlds.server.menu.recipes.PaleontologyTableRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.ICraftingGridHelper;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.Size2i;

public class PaleontologyTableCategory implements IRecipeCategory<PaleontologyTableRecipe> {
	public static final ResourceLocation TEXTURE_LOCATION = LostWorldsUtils.rL("textures/gui/lost_worlds_backgrounds.png");

	private final IDrawable background;
	private final IDrawable icon;

	private final ICraftingGridHelper craftingGridHelper;

	public PaleontologyTableCategory(IGuiHelper helper) {
		ResourceLocation location = TEXTURE_LOCATION;
		this.background = helper.createDrawable(location, 124, 0, 116, 54);
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, LostWorldsBlocks.PALEONTOLOGY_TABLE.asStack());
		this.craftingGridHelper = helper.createCraftingGridHelper(1);
	}

	@Override
	public ResourceLocation getUid() {
		return LostWorldsJeiConstants.PALEONTOLOGY_TABLE_CATEGORY.getUid();
	}

	@Override
	public Class<? extends PaleontologyTableRecipe> getRecipeClass() {
		return PaleontologyTableRecipe.class;
	}

	@Override
	public RecipeType<PaleontologyTableRecipe> getRecipeType() {
		return LostWorldsJeiConstants.PALEONTOLOGY_TABLE_CATEGORY;
	}

	@Override
	public Component getTitle() {
		return LostWorldsUtils.tTC("jei", "paleontology_table.title");
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
	public void setRecipe(IRecipeLayoutBuilder builder, PaleontologyTableRecipe recipe, IFocusGroup focuses) {
		List<List<ItemStack>> inputs = recipe.getIngredients().stream().map(ingredient -> List.of(ingredient.getItems())).toList();

		Size2i size = new Size2i(3, 3);
		if (size != null && size.width > 0 && size.height > 0) {
			this.craftingGridHelper.setInputs(builder, VanillaTypes.ITEM_STACK, inputs, size.width, size.height);
		} else {
			this.craftingGridHelper.setInputs(builder, VanillaTypes.ITEM_STACK, inputs, size.width, size.height);
			builder.setShapeless();
		}

		builder.addSlot(RecipeIngredientRole.OUTPUT, 95, 19).addItemStack(recipe.getOutputs().get(0));
	}
}
