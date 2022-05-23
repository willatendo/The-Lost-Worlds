package lostworlds.client.entity.render;

import lostworlds.client.entity.model.CustomisableModel;
import lostworlds.server.entity.fossil.Fossil;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class CustomisableRenderer extends GeoEntityRenderer<Fossil> {
	public CustomisableRenderer(Context context, String model, String texture, float shadow) {
		super(context, new CustomisableModel(model, texture));
		this.shadowRadius = shadow;
	}
}
