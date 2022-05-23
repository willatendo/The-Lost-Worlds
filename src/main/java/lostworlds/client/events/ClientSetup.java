package lostworlds.client.events;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.utils.enums.ModBoatType;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientSetup {
	public static final ModelLayerLocation FOSSIL_POACHER = new ModelLayerLocation(LostWorldsUtils.rL("fossil_poacher"), "main");

	@SubscribeEvent
	public static void layerSetup(RegisterLayerDefinitions event) {
		for (ModBoatType types : ModBoatType.values()) {
			event.registerLayerDefinition(types.getLayer(), BoatModel::createBodyModel);
		}

		event.registerLayerDefinition(FOSSIL_POACHER, IllagerModel::createBodyLayer);
	}
}
