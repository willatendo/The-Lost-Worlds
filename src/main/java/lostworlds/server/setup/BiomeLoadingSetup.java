package lostworlds.server.setup;

import lostworlds.client.book.BookLoader;
import lostworlds.server.LostWorldsUtils;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = LostWorldsUtils.ID, bus = Bus.MOD)
public class BiomeLoadingSetup {
	@SubscribeEvent
	public static void listenersSetup(RegisterClientReloadListenersEvent event) {
		event.registerReloadListener(new BookLoader());
	}
}
