package lostworlds.content.client.setup;

import lostworlds.content.client.screen.AnalyserScreen;
import lostworlds.content.client.screen.ArchaeologyTableScreen;
import lostworlds.content.client.screen.DNAExtractorScreen;
import lostworlds.content.client.screen.DNAInjectorScreen;
import lostworlds.content.client.screen.FossilCleanerScreen;
import lostworlds.content.client.screen.FossilGrinderScreen;
import lostworlds.content.client.screen.TimeMachineScreen;
import lostworlds.content.server.init.BlockInit;
import lostworlds.content.server.init.ContainerInit;
import lostworlds.content.server.init.TileEntityInit;
import lostworlds.library.util.ModUtils;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/*
 * Author: Willatendo
 * Date: July 8, 2021
 */

@EventBusSubscriber(modid = ModUtils.ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientSetup 
{
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event)
	{
		RenderTypeLookup.setRenderLayer(BlockInit.ALETHOPTERIS, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.ASHY_SHRUB, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.CEPHALOTAXUS, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.CYCAD, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.DICKSONIA, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.TALL_DICKSONIA, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.DILLHOFFIA, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.POTTED_DILLHOFFIA, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.DUISBERGIA, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.GROUND_FERNS, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.HORSETAIL, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.LYCOPHYTA, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.OSMUNDA, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.PERMIAN_DESERT_FERNS, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.PERMIAN_DESERT_SHRUB, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.TEMPSKYA, RenderType.cutout());
		
		RenderTypeLookup.setRenderLayer(BlockInit.FOSSIL_CLEANER, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.FOSSIL_GRINDER, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.DNA_EXTRACTOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.ANALYSER, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.DNA_INJECTOR, RenderType.translucent());		
		
		RenderTypeLookup.setRenderLayer(BlockInit.ARAUCARIA_LEAVES, RenderType.translucent());	
		RenderTypeLookup.setRenderLayer(BlockInit.ARAUCARIA_DOOR, RenderType.translucent());	
		RenderTypeLookup.setRenderLayer(BlockInit.ARAUCARIA_TRAPDOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.ARAUCARIA_SAPLING, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.CONIFER_LEAVES, RenderType.translucent());	
		RenderTypeLookup.setRenderLayer(BlockInit.CONIFER_DOOR, RenderType.translucent());	
		RenderTypeLookup.setRenderLayer(BlockInit.CONIFER_TRAPDOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.CONIFER_SAPLING, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.GINKGO_LEAVES, RenderType.translucent());	
		RenderTypeLookup.setRenderLayer(BlockInit.GINKGO_DOOR, RenderType.translucent());	
		RenderTypeLookup.setRenderLayer(BlockInit.GINKGO_TRAPDOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.GINKGO_SAPLING, RenderType.cutout());

		RenderTypeLookup.setRenderLayer(BlockInit.TINTED_GLASS, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.TINTED_GLASS_PANE, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.CLEAR_GLASS, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.CLEAR_GLASS_PANE, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.WHITE_GLASS, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.WHITE_GLASS_PANE, RenderType.translucent());		
		RenderTypeLookup.setRenderLayer(BlockInit.LIGHT_GREY_GLASS, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.LIGHT_GREY_GLASS_PANE, RenderType.translucent());	
		RenderTypeLookup.setRenderLayer(BlockInit.GREY_GLASS, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.GREY_GLASS_PANE, RenderType.translucent());	
		RenderTypeLookup.setRenderLayer(BlockInit.BLACK_GLASS, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.BLACK_GLASS_PANE, RenderType.translucent());	
		RenderTypeLookup.setRenderLayer(BlockInit.RED_GLASS, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.RED_GLASS_PANE, RenderType.translucent());	
		RenderTypeLookup.setRenderLayer(BlockInit.ORANGE_GLASS, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.ORANGE_GLASS_PANE, RenderType.translucent());	
		RenderTypeLookup.setRenderLayer(BlockInit.YELLOW_GLASS, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.YELLOW_GLASS_PANE, RenderType.translucent());	
		RenderTypeLookup.setRenderLayer(BlockInit.LIME_GLASS, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.LIME_GLASS_PANE, RenderType.translucent());	
		RenderTypeLookup.setRenderLayer(BlockInit.GREEN_GLASS, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.GREEN_GLASS_PANE, RenderType.translucent());	
		RenderTypeLookup.setRenderLayer(BlockInit.CYAN_GLASS, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.CYAN_GLASS_PANE, RenderType.translucent());	
		RenderTypeLookup.setRenderLayer(BlockInit.LIGHT_BLUE_GLASS, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.LIGHT_BLUE_GLASS_PANE, RenderType.translucent());	
		RenderTypeLookup.setRenderLayer(BlockInit.BLUE_GLASS, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.BLUE_GLASS_PANE, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.PURPLE_GLASS, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.PURPLE_GLASS_PANE, RenderType.translucent());	
		RenderTypeLookup.setRenderLayer(BlockInit.MAGENTA_GLASS, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.MAGENTA_GLASS_PANE, RenderType.translucent());	
		RenderTypeLookup.setRenderLayer(BlockInit.PINK_GLASS, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.PINK_GLASS_PANE, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.BROWN_GLASS, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.BROWN_GLASS_PANE, RenderType.translucent());	
		RenderTypeLookup.setRenderLayer(BlockInit.SHADED_GLASS, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.SHADED_GLASS_PANE, RenderType.translucent());
		
		RenderTypeLookup.setRenderLayer(BlockInit.OUTDOOR_TOILET_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.GLASS_SHOP_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.VISITOR_CENTRE_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.INNOVATION_CENTRE_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.SECURITY_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.BACK_DOOR, RenderType.translucent());		
		
		ClientRegistry.bindTileEntityRenderer(TileEntityInit.SIGN_TILE_ENTITY.get(), SignTileEntityRenderer::new);
		
		ScreenManager.register(ContainerInit.FOSSIL_CLEANER_CONTAINER.get(), FossilCleanerScreen::new);
		ScreenManager.register(ContainerInit.FOSSIL_GRINDER_CONTAINER.get(), FossilGrinderScreen::new);
		ScreenManager.register(ContainerInit.DNA_EXTRACTOR_CONTAINER.get(), DNAExtractorScreen::new);
		ScreenManager.register(ContainerInit.ANALYSER_CONTAINER.get(), AnalyserScreen::new);
		ScreenManager.register(ContainerInit.DNA_INJECTOR_CONTAINER.get(), DNAInjectorScreen::new);

		ScreenManager.register(ContainerInit.TIME_MACHINE_CONTAINER.get(), TimeMachineScreen::new);
		ScreenManager.register(ContainerInit.ARCHAEOLOGY_CONTAINER.get(), ArchaeologyTableScreen::new);
	}
}
