package lostworlds.client.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;

import lostworlds.client.entity.model.ProtosuchusModel;
import lostworlds.server.entity.semiaquatic.jurassic.Protosuchus;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ProtosuchusRenderer extends GeoEntityRenderer<Protosuchus> {
	public ProtosuchusRenderer(Context context) {
		super(context, new ProtosuchusModel());
		this.shadowRadius = 0.25F;
	}

	@Override
	public void render(Protosuchus entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight) {
		if (entity.isBaby()) {
			stack.scale(0.15F, 0.15F, 0.15F);
		}
		stack.scale(1.0F, 1.0F, 1.0F);

		super.render(entity, entityYaw, partialTicks, stack, buffer, packedLight);
	}
}
