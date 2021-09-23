package lostworlds.library.item;

import java.util.List;

import lostworlds.content.ModUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class FieldGuideItem extends ModItem
{
	public FieldGuideItem() 
	{
		super(new Properties().tab(ModUtils.ITEMS).stacksTo(1));
	}
	
	@Override
	public void appendHoverText(ItemStack stack, World world, List<ITextComponent> toolTip, ITooltipFlag flag) 
	{
		toolTip.add(ModUtils.gTC("item", "field_guide.desc"));
	}
	
	@Override
	public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity playerEntity, LivingEntity entity, Hand hand) 
	{
		return ActionResultType.FAIL;
	}
}
