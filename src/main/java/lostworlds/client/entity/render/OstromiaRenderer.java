package lostworlds.client.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;

import lostworlds.client.entity.model.OstromiaModel;
import lostworlds.server.entity.terrestrial.jurassic.Ostromia;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class OstromiaRenderer extends GeoEntityRenderer<Ostromia> {
	public OstromiaRenderer(Context context) {
		super(context, new OstromiaModel());
		this.layerRenderers.add(new OstromiaWingsRenderer(this));
		this.shadowRadius = 0.25F;
	}

	@Override
	public void render(Ostromia entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight) {
		if (entity.isBaby()) {
			stack.scale(0.15F, 0.15F, 0.15F);
		}
		stack.scale(1.0F, 1.0F, 1.0F);

		super.render(entity, entityYaw, partialTicks, stack, buffer, packedLight);
	}
}
