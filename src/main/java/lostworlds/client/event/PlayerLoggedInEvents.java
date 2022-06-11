package lostworlds.client.event;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.item.LostWorldsItems;
import lostworlds.server.util.Version;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.items.ItemHandlerHelper;

@EventBusSubscriber(modid = LostWorldsUtils.ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class PlayerLoggedInEvents {
	@SubscribeEvent
	public static void onPlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
		// Book
		CompoundTag playerData = event.getPlayer().getPersistentData();
		CompoundTag data = playerData.getCompound(Player.PERSISTED_NBT_TAG);
		if (data != null && !data.getBoolean("has_lexicon")) {
			ItemHandlerHelper.giveItemToPlayer(event.getPlayer(), LostWorldsItems.LOST_WORLDS_LEXICON.asStack());
			data.putBoolean("has_lexicon", true);
			playerData.put(Player.PERSISTED_NBT_TAG, data);
		}

		// Message
		Player player = event.getPlayer();
		player.sendMessage(Version.getMessage(LostWorldsUtils.VERSION_PARSER, Version.toStringVersion(LostWorldsUtils.VERSION)), player.getUUID());
	}
}
