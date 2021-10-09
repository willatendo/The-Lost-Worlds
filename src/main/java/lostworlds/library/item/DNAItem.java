package lostworlds.library.item;

import java.util.List;

import lostworlds.content.ModUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class DNAItem extends ModItem
{
	private final String name;
	
	public DNAItem(String name) 
	{
		this.name = name;
	}
	
	@Override
	public void appendHoverText(ItemStack stack, World world, List<ITextComponent> toolTip, ITooltipFlag flag)
	{
		toolTip.add(ModUtils.gTC("item", this.name + "_dna.desc"));
	}
}