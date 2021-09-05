package lostworlds.library.item;

import java.util.List;

import lostworlds.library.tab.ItemCategories;
import lostworlds.library.tab.ModItemGroup;
import lostworlds.library.util.ModRegistry;
import lostworlds.library.util.ModUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class FieldGuideItem extends Item implements ISortableItem
{
	protected FieldGuideItem() 
	{
		super(new Properties().tab(ModItemGroup.ITEMS).stacksTo(1));
	}

	@Override
	public void appendHoverText(ItemStack stack, World world, List<ITextComponent> toolTip, ITooltipFlag flag) 
	{
		toolTip.add(ModUtils.gTC("toolTip", "field_guide"));
	}
	
	@Override
	public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity playerEntity, LivingEntity entity, Hand hand) 
	{
		return ActionResultType.FAIL;
	}
	
	public static Item create()
	{
		Item item = new FieldGuideItem();
		ModRegistry.register("field_guide", item);
		return item;
	}

	@Override
	public ItemCategories getCategory(int meta) 
	{
		return ItemCategories.UTILITIES;
	}
}
