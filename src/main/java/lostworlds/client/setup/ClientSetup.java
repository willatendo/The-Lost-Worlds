package lostworlds.client.setup;

import lostworlds.client.LostWorldsBooks;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.dimension.LostWorldsDimensionRenderInfo;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(bus = Bus.MOD, modid = LostWorldsUtils.ID, value = Dist.CLIENT)
public class ClientSetup {
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		LostWorldsDimensionRenderInfo.initClient();
		LostWorldsBooks.initBooks();
	}
}
