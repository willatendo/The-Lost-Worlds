package lostworlds.client.entity.render;

import lostworlds.client.entity.model.PalaeoniscumModel;
import lostworlds.server.entity.aquatic.permian.PalaeoniscumEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class PalaeoniscumRenderer extends GeoEntityRenderer<PalaeoniscumEntity> {
	public PalaeoniscumRenderer(EntityRendererManager renderManager) {
		super(renderManager, new PalaeoniscumModel());
		this.shadowRadius = 0.3F;
	}
}
