package lostworlds.server.setup;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.world.FeatureGen;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = LostWorldsUtils.ID, bus = Bus.FORGE)
public class BiomeLoadingEventSetup {
	public static void biomeStuff(BiomeLoadingEvent event) {
//		EntitySpawns.init(event);

		FeatureGen.init(event);
	}
}
