package lostworlds.server.setup;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.species.SpeciesTypeCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = LostWorldsUtils.ID, bus = Bus.FORGE)
public class CommandSetup {
	@SubscribeEvent
	public static void commandSetup(RegisterCommandsEvent event) {
		SpeciesTypeCommand.register(event.getDispatcher());
	}
}
