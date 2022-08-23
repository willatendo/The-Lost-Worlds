package lostworlds.client.entity.render;

import lostworlds.client.entity.model.AnomalocarisModel;
import lostworlds.server.entity.aquatic.cambrian.AnomalocarisEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class AnomalocarisRenderer extends GeoEntityRenderer<AnomalocarisEntity> {
	public AnomalocarisRenderer(EntityRendererManager renderManager) {
		super(renderManager, new AnomalocarisModel());
		this.shadowRadius = 0.5F;
	}
}
