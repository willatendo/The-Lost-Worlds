package lostworlds.library.item;

import lostworlds.content.ModUtils;
import lostworlds.content.client.screen.tablet.TabletScreen;
import lostworlds.library.entity.terrestrial.EggLayingEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TabletItem extends ModItem
{
	public TabletItem() 
	{
		super(new Properties().tab(ModUtils.ITEMS).stacksTo(1));
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity playerEntity, LivingEntity entity, Hand hand) 
	{
		Minecraft instance = Minecraft.getInstance();
		
		if(entity instanceof EggLayingEntity)
		{	
			EggLayingEntity herbivore = (EggLayingEntity) entity;
			instance.setScreen(new TabletScreen(herbivore));
			
			return ActionResultType.SUCCESS;
		}
		
		return ActionResultType.FAIL;
	}
}
