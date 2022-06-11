package lostworlds.server.setup;

import lostworlds.client.LostWorldsConfig;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.util.JigsawUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ServerAboutToStartSetup {
	@SubscribeEvent
	public static void onServerAboutToStartEvent(ServerAboutToStartEvent event) {
		if (LostWorldsConfig.COMMON_CONFIG.villageStructures.get()) {
			JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/plains/houses"), LostWorldsUtils.rL("village/plains/plains_archaeologist_hut"), LostWorldsConfig.COMMON_CONFIG.villageStructureWeights.get());
			JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/plains/houses"), LostWorldsUtils.rL("village/plains/plains_paleontology_hut"), LostWorldsConfig.COMMON_CONFIG.villageStructureWeights.get());

			JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/taiga/houses"), LostWorldsUtils.rL("village/taiga/taiga_archaeologist_hut"), LostWorldsConfig.COMMON_CONFIG.villageStructureWeights.get());
			JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/taiga/houses"), LostWorldsUtils.rL("village/taiga/taiga_paleontology_hut"), LostWorldsConfig.COMMON_CONFIG.villageStructureWeights.get());

			JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/savanna/houses"), LostWorldsUtils.rL("village/savanna/savanna_archaeologist_hut"), LostWorldsConfig.COMMON_CONFIG.villageStructureWeights.get());
			JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/savanna/houses"), LostWorldsUtils.rL("village/savanna/savanna_paleontology_hut"), LostWorldsConfig.COMMON_CONFIG.villageStructureWeights.get());

			JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/snowy/houses"), LostWorldsUtils.rL("village/snowy/snowy_archaeologist_hut"), LostWorldsConfig.COMMON_CONFIG.villageStructureWeights.get());
			JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/snowy/houses"), LostWorldsUtils.rL("village/snowy/snowy_paleontology_hut"), LostWorldsConfig.COMMON_CONFIG.villageStructureWeights.get());

			JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/desert/houses"), LostWorldsUtils.rL("village/desert/desert_archaeologist_hut"), LostWorldsConfig.COMMON_CONFIG.villageStructureWeights.get());
			JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/desert/houses"), LostWorldsUtils.rL("village/desert/desert_paleontology_hut"), LostWorldsConfig.COMMON_CONFIG.villageStructureWeights.get());
		}
	}
}
