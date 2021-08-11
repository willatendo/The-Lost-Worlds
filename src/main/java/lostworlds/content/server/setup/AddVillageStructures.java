package lostworlds.content.server.setup;

import lostworlds.library.util.JigsawUtil;
import lostworlds.library.util.ModUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;

/*
 * Author: Willatendo
 * Date: July 3, 2021
 */

@EventBusSubscriber
public class AddVillageStructures 
{
	@SubscribeEvent
	public static void onServerAboutToStartEvent(FMLServerAboutToStartEvent event)
	{
		ModUtils.LOGGER.debug("Loading: Village Structures");

		//Plains Village Structures
		JigsawUtil.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/plains/houses"), ModUtils.rL("village/plains/plains_archaeologist_hut"), 25);
		
		//Taiga Village Structures
		JigsawUtil.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/taiga/houses"), ModUtils.rL("village/taiga/taiga_archaeologist_hut"), 25);

		//Savanna Village Structures
		JigsawUtil.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/savanna/houses"), ModUtils.rL("village/savanna/savanna_archaeologist_hut"), 25);
		
		//Snowy Village Structures
		JigsawUtil.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/snowy/houses"), ModUtils.rL("village/savanna/snowy_archaeologist_hut"), 25);
		
		//Desert Village Structures
		JigsawUtil.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/desert/houses"), ModUtils.rL("village/desert/desert_archaeologist_hut"), 25);
	
		ModUtils.LOGGER.debug("Finished: Village Structures");
	}
}
