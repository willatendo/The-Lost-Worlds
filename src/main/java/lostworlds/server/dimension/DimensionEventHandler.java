package lostworlds.server.dimension;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.storage.DerivedLevelData;
import net.minecraftforge.event.world.SleepFinishedTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = LostWorldsUtils.ID)
public class DimensionEventHandler {
	@SubscribeEvent
	public static void onSleepFinished(SleepFinishedTimeEvent event) {
		LevelAccessor level = event.getWorld();
		if (level instanceof ServerLevel serverlevel) {
			if (serverlevel.dimension() == LostWorldsDimensions.CRETACEOUS_LEVEL) {
				if (level.getLevelData() instanceof DerivedLevelData) {
					((DerivedLevelData) level.getLevelData()).wrapped.setDayTime(event.getNewTime());
				}
			}
		}
	}
}
