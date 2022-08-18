package lostworlds.client.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;

import lostworlds.client.entity.model.ZephyrosaurusModel;
import lostworlds.server.entity.terrestrial.cretaceous.Zephyrosaurus;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ZephyrosaurusRenderer extends GeoEntityRenderer<Zephyrosaurus> {
	public ZephyrosaurusRenderer(Context context) {
		super(context, new ZephyrosaurusModel());
		this.shadowRadius = 0.25F;
	}

	@Override
	public void render(Zephyrosaurus entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource bufferIn, int packedLightIn) {
		if (entity.isBaby()) {
			stack.scale(0.15F, 0.15F, 0.15F);
		}
		stack.scale(1.0F, 1.0F, 1.0F);

		super.render(entity, entityYaw, partialTicks, stack, bufferIn, packedLightIn);
	}
}
