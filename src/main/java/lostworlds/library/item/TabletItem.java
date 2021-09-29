package lostworlds.library.item;

import lostworlds.content.ModUtils;
import lostworlds.content.client.screen.tablet.HerbivoreTabletScreen;
import lostworlds.library.entity.terrestrial.HerbivoreEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;

public class TabletItem extends ModItem
{
	public TabletItem() 
	{
		super(new Properties().tab(ModUtils.ITEMS).stacksTo(1));
	}
	
	@Override
	public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity playerEntity, LivingEntity entity, Hand hand) 
	{
		Minecraft instance = Minecraft.getInstance();
		
		if(entity instanceof HerbivoreEntity)
		{	
			HerbivoreEntity herbivore = (HerbivoreEntity) entity;
			if(herbivore.isTagged())
			{
				instance.setScreen(new HerbivoreTabletScreen(herbivore));
			}
			
			return ActionResultType.SUCCESS;
		}
		
		return ActionResultType.FAIL;
	}
}
