package lostworlds.integration.quark.client;

import lostworlds.integration.quark.init.QuarkBlocks;
import lostworlds.library.util.ModUtils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = ModUtils.ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientSetup 
{
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event)
	{
		RenderTypeLookup.setRenderLayer(QuarkBlocks.ARAUCARIA_LEAF_CARPET, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(QuarkBlocks.CONIFER_LEAF_CARPET, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(QuarkBlocks.GINKGO_LEAF_CARPET, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(QuarkBlocks.ARAUCARIA_LADDER, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(QuarkBlocks.CONIFER_LADDER, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(QuarkBlocks.GINKGO_LADDER, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(QuarkBlocks.SCORCHED_LADDER, RenderType.cutout());
	}
}
