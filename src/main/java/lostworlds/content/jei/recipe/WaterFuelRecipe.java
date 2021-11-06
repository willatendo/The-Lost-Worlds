package lostworlds.content.jei.recipe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.base.Preconditions;

import lostworlds.content.ModUtils;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.helpers.IGuiHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class WaterFuelRecipe 
{
	public static final ResourceLocation TEXTURE_LOCATION = ModUtils.rL("textures/gui/lost_worlds_backgrounds.png");
	private final List<ItemStack> inputs;
	private final IDrawableAnimated bucket;

	public WaterFuelRecipe(IGuiHelper guiHelper, Collection<ItemStack> input, int fuelTime) 
	{
		Preconditions.checkArgument(fuelTime > 0, "burn time must be greater than 0");
		this.inputs = new ArrayList<>(input);
		this.bucket = guiHelper.drawableBuilder(TEXTURE_LOCATION, 82, 0, 18, 18).buildAnimated(fuelTime, IDrawableAnimated.StartDirection.TOP, true);
	}

	public List<ItemStack> getInputs() 
	{
		return this.inputs;
	}

	public IDrawableAnimated getBucket() 
	{
		return this.bucket;
	}
}
