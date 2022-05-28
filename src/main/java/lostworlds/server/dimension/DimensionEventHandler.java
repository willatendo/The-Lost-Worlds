package lostworlds.server.dimension;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.storage.DerivedLevelData;
import net.minecraftforge.event.world.SleepFinishedTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DimensionEventHandler {
	@SubscribeEvent
	public static void onSleepFinished(SleepFinishedTimeEvent event) {
		LevelAccessor world = event.getWorld();
		if (world instanceof ServerLevel) {
			ServerLevel serverWorld = (ServerLevel) world;
			if (serverWorld.dimension() == LostWorldsDimensions.CRETACEOUS_LEVEL) {
				if (world.getLevelData() instanceof DerivedLevelData) {
					((DerivedLevelData) world.getLevelData()).wrapped.setDayTime(event.getNewTime());
				}
			}
		}
	}
}
