package lostworlds.content.client.setup;

import lostworlds.content.ModUtils;
import lostworlds.content.client.entity.render.ChilesaurusRenderer;
import lostworlds.content.client.entity.render.FossilPoacherRenderer;
import lostworlds.content.client.entity.render.KentrosaurusRenderer;
import lostworlds.content.client.entity.render.ModBoatRenderer;
import lostworlds.content.client.screen.AnalyzerScreen;
import lostworlds.content.client.screen.ArchaeologyTableScreen;
import lostworlds.content.client.screen.CultivatorScreen;
import lostworlds.content.client.screen.DNAExtractorScreen;
import lostworlds.content.client.screen.DNAInjectorScreen;
import lostworlds.content.client.screen.FossilCleanerScreen;
import lostworlds.content.client.screen.FossilGrinderScreen;
import lostworlds.content.client.screen.TimeMachineScreen;
import lostworlds.content.server.init.BlockInit;
import lostworlds.content.server.init.ContainerInit;
import lostworlds.content.server.init.EntityInit;
import lostworlds.content.server.init.TileEntityInit;
import lostworlds.library.block.ColouredGlassBlock;
import lostworlds.library.block.ColouredGlassPaneBlock;
import net.minecraft.block.Block;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = ModUtils.ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientSetup 
{
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event)
	{
		RenderTypeLookup.setRenderLayer(BlockInit.ALETHOPTERIS, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.CALAMITES_SUCKOWII, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.CALAMITES_SUCKOWII_SAPLING, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.CEPHALOTAXUS, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.DILLHOFFIA, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.POTTED_DILLHOFFIA, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.DUISBERGIA, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.GROUND_FERNS, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.OSMUNDA, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.PERMIAN_DESERT_FERNS, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.PERMIAN_DESERT_SHRUB, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.BRAZILEA, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.WILLIAMSONIA, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.ZAMITES, RenderType.cutout());
		
		RenderTypeLookup.setRenderLayer(BlockInit.FOSSIL_CLEANER, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.FOSSIL_GRINDER, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.DNA_EXTRACTOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.ANALYZER, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.DNA_INJECTOR, RenderType.translucent());		
		RenderTypeLookup.setRenderLayer(BlockInit.CULTIVATOR, RenderType.translucent());		
		
		RenderTypeLookup.setRenderLayer(BlockInit.ARAUCARIA_LEAVES, RenderType.translucent());	
		RenderTypeLookup.setRenderLayer(BlockInit.ARAUCARIA_DOOR, RenderType.translucent());	
		RenderTypeLookup.setRenderLayer(BlockInit.ARAUCARIA_TRAPDOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.ARAUCARIA_SAPLING, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.CALAMITES_LEAVES, RenderType.translucent());	
		RenderTypeLookup.setRenderLayer(BlockInit.CALAMITES_DOOR, RenderType.translucent());	
		RenderTypeLookup.setRenderLayer(BlockInit.CALAMITES_TRAPDOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.CALAMITES_SAPLING, RenderType.cutout());	
		RenderTypeLookup.setRenderLayer(BlockInit.CONIFER_LEAVES, RenderType.translucent());	
		RenderTypeLookup.setRenderLayer(BlockInit.CONIFER_DOOR, RenderType.translucent());	
		RenderTypeLookup.setRenderLayer(BlockInit.CONIFER_TRAPDOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.CONIFER_SAPLING, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.GINKGO_LEAVES, RenderType.translucent());	
		RenderTypeLookup.setRenderLayer(BlockInit.GINKGO_DOOR, RenderType.translucent());	
		RenderTypeLookup.setRenderLayer(BlockInit.GINKGO_TRAPDOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.GINKGO_SAPLING, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.SCORCHED_DOOR, RenderType.translucent());	
		RenderTypeLookup.setRenderLayer(BlockInit.SCORCHED_TRAPDOOR, RenderType.translucent());
		
		for(Block block : ColouredGlassPaneBlock.entries)
		{
			RenderTypeLookup.setRenderLayer(block, RenderType.translucent());
		}
		
		for(Block block : ColouredGlassBlock.entries)
		{
			RenderTypeLookup.setRenderLayer(block, RenderType.translucent());
		}	
		
		RenderTypeLookup.setRenderLayer(BlockInit.OUTDOOR_TOILET_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.GLASS_SHOP_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.VISITOR_CENTER_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.INNOVATION_CENTER_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.SECURITY_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.BACK_DOOR, RenderType.translucent());		
		
		ClientRegistry.bindTileEntityRenderer(TileEntityInit.SIGN_TILE_ENTITY, SignTileEntityRenderer::new);
		
		ScreenManager.register(ContainerInit.FOSSIL_CLEANER_CONTAINER, FossilCleanerScreen::new);
		ScreenManager.register(ContainerInit.FOSSIL_GRINDER_CONTAINER, FossilGrinderScreen::new);
		ScreenManager.register(ContainerInit.DNA_EXTRACTOR_CONTAINER, DNAExtractorScreen::new);
		ScreenManager.register(ContainerInit.ANALYZER_CONTAINER, AnalyzerScreen::new);
		ScreenManager.register(ContainerInit.DNA_INJECTOR_CONTAINER, DNAInjectorScreen::new);
		ScreenManager.register(ContainerInit.CULTIVATOR_CONTAINER, CultivatorScreen::new);

		ScreenManager.register(ContainerInit.TIME_MACHINE_CONTAINER, TimeMachineScreen::new);
		ScreenManager.register(ContainerInit.ARCHAEOLOGY_CONTAINER, ArchaeologyTableScreen::new);
		
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.FOSSIL_POACHER, manager -> new FossilPoacherRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.MOD_BOAT, manager -> new ModBoatRenderer(manager));

		RenderingRegistry.registerEntityRenderingHandler(EntityInit.CHILESAURUS, manager -> new ChilesaurusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.KENTROSAURUS, manager -> new KentrosaurusRenderer(manager));
	}
}
