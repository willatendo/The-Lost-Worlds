package lostworlds.content.event;

import lostworlds.content.ModUtils;
import lostworlds.content.server.init.ItemInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.items.ItemHandlerHelper;

@EventBusSubscriber(modid = ModUtils.ID, bus = Bus.FORGE)
public class CommonForgeBus 
{
	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) 
	{
		CompoundNBT playerData = event.getPlayer().getPersistentData();
		CompoundNBT data = playerData.getCompound(PlayerEntity.PERSISTED_NBT_TAG);
		if(data != null && !data.getBoolean("has_lexicon")) 
		{
			ItemHandlerHelper.giveItemToPlayer(event.getPlayer(), new ItemStack(ItemInit.LOST_WORLDS_LEXICON));
			data.putBoolean("has_lexicon", true);
			playerData.put(PlayerEntity.PERSISTED_NBT_TAG, data);
		}
	}
}
