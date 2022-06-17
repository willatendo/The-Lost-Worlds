package lostworlds.client.setup;

import lostworlds.client.LostWorldsBooks;
import lostworlds.client.entity.render.LostWorldsSignRenderer;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsWoodTypes;
import lostworlds.server.block.entity.LostWorldsBlockEntities;
import lostworlds.server.dimension.LostWorldsDimensionRenderInfo;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(bus = Bus.MOD, modid = LostWorldsUtils.ID, value = Dist.CLIENT)
public class ClientSetup {
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		LostWorldsDimensionRenderInfo.initClient();
		LostWorldsBooks.initBooks();

		BlockEntityRenderers.register(LostWorldsBlockEntities.LOST_WORLDS_SIGN.get(), LostWorldsSignRenderer::new);

		event.enqueueWork(() -> {
			LostWorldsWoodTypes.getWoodTypes().forEach(woodType -> Sheets.addWoodType(woodType));
		});
	}
}
