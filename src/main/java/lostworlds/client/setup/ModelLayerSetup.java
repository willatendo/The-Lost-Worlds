package lostworlds.client.setup;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsWoodTypes;
import lostworlds.server.entity.utils.enums.ModBoatType;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(bus = Bus.MOD, modid = LostWorldsUtils.ID, value = Dist.CLIENT)
public class ModelLayerSetup {
	public static final ModelLayerLocation FOSSIL_POACHER = new ModelLayerLocation(LostWorldsUtils.rL("fossil_poacher"), "main");

	@SubscribeEvent
	public static void layerSetup(RegisterLayerDefinitions event) {
		for (ModBoatType types : ModBoatType.values()) {
			event.registerLayerDefinition(types.getLayer(), BoatModel::createBodyModel);
		}

		for (WoodType types : LostWorldsWoodTypes.WOOD_TYPES) {
			event.registerLayerDefinition(ModelLayers.createSignModelName(types), SignRenderer::createSignLayer);
		}

		event.registerLayerDefinition(FOSSIL_POACHER, IllagerModel::createBodyLayer);
	}
}
