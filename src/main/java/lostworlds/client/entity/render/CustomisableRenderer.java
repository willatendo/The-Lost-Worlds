package lostworlds.client.entity.render;

import lostworlds.client.entity.model.CustomisableModel;
import lostworlds.server.entity.fossil.FossilEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class CustomisableRenderer extends GeoEntityRenderer<FossilEntity> {
	public CustomisableRenderer(EntityRendererManager renderManager, String model, String texture, float shadow) {
		super(renderManager, new CustomisableModel<FossilEntity>(model, texture));
		this.shadowRadius = shadow;
	}
}
