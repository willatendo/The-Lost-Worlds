package lostworlds.content.server.setup;

import lostworlds.library.biome.ModConfiguredFeatures;
import lostworlds.library.util.ModUtils;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

/*
 * Author: Willatendo
 * Date: July 16, 2021
 */

@EventBusSubscriber(modid = ModUtils.ID, bus = Bus.MOD)
public class ServerSetup 
{
	@SubscribeEvent
	public static void onRegsterFeatures(Register<Feature<?>> feature)
	{
		ModUtils.LOGGER.debug("Loading: Mod Configured Features");
		
		ModConfiguredFeatures.init();

		ModUtils.LOGGER.debug("Finished: Mod Configured Features");
	}
}
