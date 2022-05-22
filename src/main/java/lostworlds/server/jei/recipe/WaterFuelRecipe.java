package lostworlds.server.jei.recipe;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.base.Preconditions;

import lostworlds.server.LostWorldsUtils;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.helpers.IGuiHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class WaterFuelRecipe {
	public static final ResourceLocation TEXTURE_LOCATION = LostWorldsUtils.rL("textures/gui/lost_worlds_backgrounds.png");
	private final List<ItemStack> inputs;
	private final Component extractCountText;
	private final IDrawableAnimated bucket;

	public WaterFuelRecipe(IGuiHelper guiHelper, Collection<ItemStack> input, int fuelTime) {
		Preconditions.checkArgument(fuelTime > 0, "burn time must be greater than 0");
		this.inputs = new ArrayList<>(input);
		this.extractCountText = createSmeltCountText(fuelTime);
		this.bucket = guiHelper.drawableBuilder(TEXTURE_LOCATION, 82, 0, 18, 18).buildAnimated(fuelTime, IDrawableAnimated.StartDirection.TOP, true);
	}

	public List<ItemStack> getInputs() {
		return this.inputs;
	}

	public Component getExtractCountText() {
		return extractCountText;
	}

	public IDrawableAnimated getBucket() {
		return this.bucket;
	}

	public static Component createSmeltCountText(int burnTime) {
		if (burnTime == 1000) {
			return LostWorldsUtils.tTC("jei", "cleanCount.single");
		} else {
			NumberFormat numberInstance = NumberFormat.getNumberInstance();
			numberInstance.setMaximumFractionDigits(2);
			String smeltCount = numberInstance.format(burnTime / 1000.0f);
			return new TranslatableComponent("jei.lostworlds.cleanCount.multi", smeltCount);
		}
	}
}
