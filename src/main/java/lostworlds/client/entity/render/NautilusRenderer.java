package lostworlds.client.entity.render;

import lostworlds.client.entity.model.NautilusModel;
import lostworlds.server.entity.aquatic.modern.NautilusEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class NautilusRenderer extends GeoEntityRenderer<NautilusEntity> {
	public NautilusRenderer(EntityRendererManager renderManager) {
		super(renderManager, new NautilusModel());
		this.shadowRadius = 0.3F;
	}
}
