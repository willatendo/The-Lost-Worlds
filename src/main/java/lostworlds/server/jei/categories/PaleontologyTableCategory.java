package lostworlds.server.jei.categories;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import org.jetbrains.annotations.Nullable;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.vertex.PoseStack;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.jei.LostWorldsJeiConstants;
import lostworlds.server.menu.recipes.PaleontologyTableRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.ICraftingGridHelper;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.extensions.IExtendableRecipeCategory;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICraftingCategoryExtension;
import mezz.jei.gui.recipes.builder.RecipeLayoutBuilder;
import mezz.jei.ingredients.Ingredients;
import mezz.jei.recipes.ExtendableRecipeCategoryHelper;
import mezz.jei.util.ErrorUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;

public class PaleontologyTableCategory implements IExtendableRecipeCategory<PaleontologyTableRecipe, ICraftingCategoryExtension> {
	public static final ResourceLocation TEXTURE_LOCATION = LostWorldsUtils.rL("textures/gui/lost_worlds_backgrounds.png");

	private final IDrawable background;
	private final IDrawable icon;

	private final ICraftingGridHelper craftingGridHelper;
	private final ExtendableRecipeCategoryHelper<Recipe<?>, ICraftingCategoryExtension> extendableHelper = new ExtendableRecipeCategoryHelper<>(PaleontologyTableRecipe.class);

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
		ICraftingCategoryExtension recipeExtension = this.extendableHelper.getRecipeExtension(recipe);
		recipeExtension.setRecipe(builder, craftingGridHelper, focuses);

		if (builder instanceof RecipeLayoutBuilder b && b.isUsed()) {
			return;
		}
		this.legacySetRecipe(builder, recipeExtension);
	}

	@SuppressWarnings({ "removal" })
	private void legacySetRecipe(IRecipeLayoutBuilder builder, ICraftingCategoryExtension recipeExtension) {
		Ingredients ingredients = new Ingredients();
		recipeExtension.setIngredients(ingredients);
		List<@Nullable List<@Nullable ItemStack>> inputs = ingredients.getInputs(VanillaTypes.ITEM_STACK);
		List<@Nullable List<@Nullable ItemStack>> outputs = ingredients.getOutputs(VanillaTypes.ITEM_STACK);
		List<@Nullable ItemStack> output;
		if (outputs.isEmpty()) {
			output = List.of();
		} else {
			output = outputs.get(0);
		}

		int width = recipeExtension.getWidth();
		int height = recipeExtension.getHeight();
		this.craftingGridHelper.setOutputs(builder, VanillaTypes.ITEM_STACK, output);
		this.craftingGridHelper.setInputs(builder, VanillaTypes.ITEM_STACK, inputs, width, height);
	}

	@Override
	public void draw(PaleontologyTableRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX, double mouseY) {
		ICraftingCategoryExtension extension = this.extendableHelper.getRecipeExtension(recipe);
		int recipeWidth = this.background.getWidth();
		int recipeHeight = this.background.getHeight();
		extension.drawInfo(recipeWidth, recipeHeight, poseStack, mouseX, mouseY);
	}

	@Override
	public List<Component> getTooltipStrings(PaleontologyTableRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
		ICraftingCategoryExtension extension = this.extendableHelper.getRecipeExtension(recipe);
		return extension.getTooltipStrings(mouseX, mouseY);
	}

	@Override
	public boolean handleInput(PaleontologyTableRecipe recipe, double mouseX, double mouseY, InputConstants.Key input) {
		ICraftingCategoryExtension extension = this.extendableHelper.getRecipeExtension(recipe);
		return extension.handleInput(mouseX, mouseY, input);
	}

	@Override
	public boolean isHandled(PaleontologyTableRecipe recipe) {
		return this.extendableHelper.getOptionalRecipeExtension(recipe).isPresent();
	}

	@Override
	public <R extends PaleontologyTableRecipe> void addCategoryExtension(Class<? extends R> recipeClass, Function<R, ? extends ICraftingCategoryExtension> extensionFactory) {
		ErrorUtil.checkNotNull(recipeClass, "recipeClass");
		ErrorUtil.checkNotNull(extensionFactory, "extensionFactory");
		this.extendableHelper.addRecipeExtensionFactory(recipeClass, null, extensionFactory);
	}

	@Override
	public <R extends PaleontologyTableRecipe> void addCategoryExtension(Class<? extends R> recipeClass, Predicate<R> extensionFilter, Function<R, ? extends ICraftingCategoryExtension> extensionFactory) {
		ErrorUtil.checkNotNull(recipeClass, "recipeClass");
		ErrorUtil.checkNotNull(extensionFilter, "extensionFilter");
		ErrorUtil.checkNotNull(extensionFactory, "extensionFactory");
		this.extendableHelper.addRecipeExtensionFactory(recipeClass, extensionFilter, extensionFactory);
	}

	@Override
	public ResourceLocation getRegistryName(PaleontologyTableRecipe recipe) {
		ErrorUtil.checkNotNull(recipe, "recipe");
		return this.extendableHelper.getOptionalRecipeExtension(recipe).flatMap(extension -> Optional.ofNullable(extension.getRegistryName())).orElseGet(recipe::getId);
	}
}
