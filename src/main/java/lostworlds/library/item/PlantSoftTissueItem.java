package lostworlds.library.item;

import java.util.List;

import lostworlds.content.ModUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class PlantSoftTissueItem extends ModItem
{
	private final String name;
	
	public PlantSoftTissueItem(String name) 
	{
		this.name = name;
	}
	
	@Override
	public void appendHoverText(ItemStack stack, World world, List<ITextComponent> toolTip, ITooltipFlag flag)
	{
		toolTip.add(ModUtils.gTC("item", this.name + "_soft_tissue.desc"));
	}
}
