package lostworlds.library.dimension;

import lostworlds.content.server.init.DimensionInit;
import lostworlds.library.util.ModUtils;
import net.minecraft.world.IWorld;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DerivedWorldInfo;
import net.minecraftforge.event.world.SleepFinishedTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = ModUtils.ID)
public class DimensionSetup 
{
	@SubscribeEvent
	public static void onSleepFinished(SleepFinishedTimeEvent event) 
	{
		IWorld world = event.getWorld();
		if(world instanceof ServerWorld) 
		{
			ServerWorld serverworld = (ServerWorld) world;
			if(serverworld.dimension() == DimensionInit.PERMIAN_WORLD) 
			{
				if(world.getLevelData() instanceof DerivedWorldInfo) 
				{
					((DerivedWorldInfo) world.getLevelData()).wrapped.setDayTime(event.getNewTime());
				}
			}
		}
	}
}
