package lostworlds.server.setup;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.species.SpeciesTypeCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.loading.FMLEnvironment;

@EventBusSubscriber(modid = LostWorldsUtils.ID, bus = Bus.FORGE)
public class CommandSetup {
	@SubscribeEvent
	public static void commandSetup(RegisterCommandsEvent event) {
		if (!FMLEnvironment.production) {
			SpeciesTypeCommand.register(event.getDispatcher());
		}
	}
}
