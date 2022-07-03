package lostworlds.server.jei.categories;

import com.mojang.blaze3d.vertex.PoseStack;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.jei.LostWorldsJeiConstants;
import lostworlds.server.jei.recipe.WaterFuelRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class WaterFuelCategory implements IRecipeCategory<WaterFuelRecipe> {
	public static final ResourceLocation TEXTURE_LOCATION = LostWorldsUtils.rL("textures/gui/lost_worlds_backgrounds.png");

	private final IDrawableStatic background;
	private final IDrawable icon;
	protected final IDrawableStatic bucket;
	protected final IDrawableAnimated animatedBucket;

	public WaterFuelCategory(IGuiHelper helper) {
		Minecraft minecraft = Minecraft.getInstance();
		Font fontRenderer = minecraft.font;
		Component smeltCountText = WaterFuelRecipe.createSmeltCountText(100000);
		int stringWidth = fontRenderer.width(smeltCountText.getString());
		this.background = helper.drawableBuilder(TEXTURE_LOCATION, 0, 18, 18, 35).addPadding(0, 0, 0, stringWidth + 20).build();
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(Items.WATER_BUCKET));
		this.bucket = helper.createDrawable(TEXTURE_LOCATION, 82, 0, 22, 22);
		this.animatedBucket = helper.createAnimatedDrawable(this.bucket, 300, IDrawableAnimated.StartDirection.TOP, true);
	}

	@Override
	public ResourceLocation getUid() {
		return LostWorldsJeiConstants.WATER_FUEL_CATEGORY.getUid();
	}

	@Override
	public Class<? extends WaterFuelRecipe> getRecipeClass() {
		return WaterFuelRecipe.class;
	}

	@Override
	public Component getTitle() {
		return LostWorldsUtils.tTC("jei", "fossil_cleaner_fuel.title");
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
	public void setRecipe(IRecipeLayoutBuilder builder, WaterFuelRecipe recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 1, 19).addItemStack(recipe.getInputs().get(0));
	}

	@Override
	public void draw(WaterFuelRecipe recipe, PoseStack matrixStack, double mouseX, double mouseY) {
		IDrawableAnimated flame = recipe.getBucket();
		flame.draw(matrixStack, 0, 0);
		Minecraft minecraft = Minecraft.getInstance();
		Component smeltCountText = recipe.getExtractCountText();
		minecraft.font.draw(matrixStack, smeltCountText, 24, 13, 0xFF808080);
	}
}
