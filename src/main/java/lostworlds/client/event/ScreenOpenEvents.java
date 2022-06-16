package lostworlds.client.event;

import lostworlds.client.screen.LostWorldsTitleScreen;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenOpenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = LostWorldsUtils.ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class ScreenOpenEvents {
	@SubscribeEvent
	public static void onPlayerLoggedInEvent(ScreenOpenEvent event) {
		if (event.getScreen() instanceof TitleScreen && !(event.getScreen() instanceof LostWorldsTitleScreen)) {
			event.setScreen(new LostWorldsTitleScreen());
		}
	}
}
