package lostworlds.content.client.event;

import lostworlds.library.util.ModUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.loading.FMLEnvironment;

@EventBusSubscriber(modid = ModUtils.ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class OnLoadEvent 
{
	@SubscribeEvent
	public static void onLoadEvent(final PlayerEvent.PlayerLoggedInEvent event)
	{
		if(!FMLEnvironment.production)
		{
			PlayerEntity player = event.getPlayer();
			player.sendMessage(ModUtils.gTC("event", "dev_load"), player.getUUID());
		}
		else
		{
			PlayerEntity player = event.getPlayer();
			player.sendMessage(ModUtils.cTC("event", "player_load", TextFormatting.GOLD), player.getUUID());
		}
	}
}
