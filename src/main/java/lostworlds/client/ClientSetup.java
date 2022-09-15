package lostworlds.client;

import lostworlds.client.books.TyrannibookHelper;
import lostworlds.client.books.lostworlds.LostWorldsBooks;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.entity.LostWorldsBlockEntities;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(bus = Bus.MOD, modid = LostWorldsUtils.ID, value = Dist.CLIENT)
public class ClientSetup {
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		TyrannibookHelper.listenersSetup(event);

		LostWorldsBooks.initBooks();

		ClientRegistry.bindTileEntityRenderer(LostWorldsBlockEntities.LOST_WORLDS_SIGN.get(), SignTileEntityRenderer::new);

		DimensionRenderInfo.EFFECTS.put(LostWorldsUtils.rL("permian_render"), new StandardDimensionRenderInfo());
		DimensionRenderInfo.EFFECTS.put(LostWorldsUtils.rL("jurassic_render"), new StandardDimensionRenderInfo());
		DimensionRenderInfo.EFFECTS.put(LostWorldsUtils.rL("cretaceous_render"), new StandardDimensionRenderInfo());
	}
}
