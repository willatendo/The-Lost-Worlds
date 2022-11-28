package lostworlds.data;

import lostworlds.data.tag.LostWorldsBiomeTagProvider;
import lostworlds.data.tag.LostWorldsConfiguredStructureTagProvider;
import lostworlds.data.tag.LostWorldsSpeciesTypeTagProvider;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(bus = Bus.MOD, modid = LostWorldsUtils.ID)
public class LostWorldsDataGen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator dataGenerator = event.getGenerator();
		ExistingFileHelper fileHelper = event.getExistingFileHelper();
		dataGenerator.addProvider(event.includeServer(), new LostWorldsAdvancementProvider(dataGenerator, fileHelper));
		dataGenerator.addProvider(event.includeServer(), new LostWorldsRecipeProvider(dataGenerator));
		dataGenerator.addProvider(event.includeServer(), new LostWorldsBiomeTagProvider(dataGenerator, fileHelper));
		dataGenerator.addProvider(event.includeServer(), new LostWorldsConfiguredStructureTagProvider(dataGenerator, fileHelper));
		dataGenerator.addProvider(event.includeServer(), new LostWorldsLootProvider(dataGenerator));
		dataGenerator.addProvider(event.includeServer(), new LostWorldsDimensionProvider(dataGenerator));
		dataGenerator.addProvider(event.includeServer(), new LostWorldsSpeciesTypeTagProvider(dataGenerator, fileHelper));
		dataGenerator.addProvider(event.includeServer(), new LostWorldsGlobalLootModiferProvider(dataGenerator));
		/*
		 * dataGenerator.addProvider(new
		 * LostWorldsTranslationLanguageProvider(dataGenerator, "sv_se", provider -> {
		 * provider.addAdvancement("a_terrible_market", "En hemsk marknad",
		 * "Finna in svart marknad. En plats full av girig plundringer, men viktigt en hammare."
		 * ); provider.addAdvancement("ancient_seeds", "Antik frö",
		 * "Efter högar av arbeta, du fått några frö!");
		 * provider.addAdvancement("basic_explorer", "Basisk upptäcktsresande",
		 * "Precis den starta av din utforskar resa."); })); //
		 */
	}
}
