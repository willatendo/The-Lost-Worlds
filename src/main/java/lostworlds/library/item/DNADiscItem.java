package lostworlds.library.item;

import java.util.List;

import lostworlds.content.ModUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class DNADiscItem extends ModItem
{
	private final ITextComponent name;
	
	public DNADiscItem(ITextComponent name) 
	{
		this.name = name;
	}
	
	@Override
	public void appendHoverText(ItemStack stack, World world, List<ITextComponent> toolTip, ITooltipFlag flag)
	{
		TranslationTextComponent text = new TranslationTextComponent("item.lostworlds.dna_disc.desc", this.name);
		text.withStyle(TextFormatting.GOLD);
		toolTip.add(text);
	}
	
	@Override
	public ITextComponent getName(ItemStack stack) 
	{
		return ModUtils.tTC("item", "dna_disc");	
	}
}
