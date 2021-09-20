package lostworlds.library.item;

import lostworlds.content.client.screen.tablet.HerbivoreTabletScreen;
import lostworlds.library.entity.terrestrial.HerbivoreEntity;
import lostworlds.library.entity.terrestrial.jurassic.ChilesaurusEntity;
import lostworlds.library.tab.ModItemGroup;
import lostworlds.library.util.ModRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;

public class TabletItem extends Item
{
	protected TabletItem(Properties properties) 
	{
		super(properties);
	}
	
	@Override
	public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity playerEntity, LivingEntity entity, Hand hand) 
	{
		Minecraft instance = Minecraft.getInstance();
		
		HerbivoreEntity herbivore = (HerbivoreEntity) entity;
		if(entity instanceof HerbivoreEntity)
		{	
			if(herbivore.isTagged())
			{
				if(entity instanceof ChilesaurusEntity)
				{
					instance.setScreen(new HerbivoreTabletScreen(herbivore));
				}
			}
			
			return ActionResultType.SUCCESS;
		}
		
		return ActionResultType.FAIL;
	}
	
	
	
	public static Item create()
	{
		Item item = new TabletItem(new Properties().tab(ModItemGroup.ITEMS).stacksTo(1));
		ModRegistry.register("tablet", item);
		return item;
	}
}
