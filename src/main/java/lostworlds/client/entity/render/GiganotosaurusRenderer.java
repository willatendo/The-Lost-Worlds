package lostworlds.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;

import lostworlds.client.entity.model.GiganotosaurusModel;
import lostworlds.server.entity.terrestrial.cretaceous.GiganotosaurusEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GiganotosaurusRenderer extends GeoEntityRenderer<GiganotosaurusEntity> {
	public GiganotosaurusRenderer(EntityRendererManager renderManager) {
		super(renderManager, new GiganotosaurusModel());
		this.shadowRadius = 0.5F;
	}

	@Override
	public void render(GiganotosaurusEntity entity, float entityYaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer buffer, int packedLight) {
		if (entity.isBaby()) {
			stack.scale(0.15F, 0.15F, 0.15F);
		}
		stack.scale(1.5F, 1.5F, 1.5F);

		super.render(entity, entityYaw, partialTicks, stack, buffer, packedLight);
	}
}
