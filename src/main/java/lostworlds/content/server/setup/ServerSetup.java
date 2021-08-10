package lostworlds.content.server.setup;

import lostworlds.library.biome.ModConfiguredFeatures;
import lostworlds.library.util.ModUtil;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

/*
 * Author: Willatendo
 * Date: July 16, 2021
 */

@EventBusSubscriber(modid = ModUtil.ID, bus = Bus.MOD)
public class ServerSetup 
{
	@SubscribeEvent
	public static void onRegsterFeatures(Register<Feature<?>> feature)
	{
		ModUtil.LOGGER.debug("Loading: Mod Configured Features");
		
		ModConfiguredFeatures.init();

		ModUtil.LOGGER.debug("Finished: Mod Configured Features");
	}
}
