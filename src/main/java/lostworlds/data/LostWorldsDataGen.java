package lostworlds.data;

import lostworlds.LostWorldsMod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@EventBusSubscriber(bus = Bus.MOD, modid = LostWorldsMod.ID)
public class LostWorldsDataGen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator dataGenerator = event.getGenerator();
		ExistingFileHelper fileHelper = event.getExistingFileHelper();
		dataGenerator.addProvider(new LostWorldsRecipeProvider(dataGenerator));
		dataGenerator.addProvider(new LostWorldsBookProvider(dataGenerator));
		dataGenerator.addProvider(new LostWorldsAdvancementProvider(dataGenerator, fileHelper));
		dataGenerator.addProvider(new LostWorldsSoundProvider(dataGenerator, fileHelper));
	}
}
