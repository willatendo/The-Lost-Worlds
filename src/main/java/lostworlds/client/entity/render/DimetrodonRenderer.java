package lostworlds.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;

import lostworlds.client.entity.model.DimetrodonModel;
import lostworlds.server.entity.terrestrial.permian.DimetrodonEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DimetrodonRenderer extends GeoEntityRenderer<DimetrodonEntity> {
	public DimetrodonRenderer(EntityRendererManager renderManager) {
		super(renderManager, new DimetrodonModel());
		this.shadowRadius = 0.5F;
	}

	@Override
	public void render(DimetrodonEntity entity, float entityYaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer buffer, int packedLight) {
		if (entity.isBaby()) {
			stack.scale(0.15F, 0.15F, 0.15F);
		}
		stack.scale(1.0F, 1.0F, 1.0F);

		super.render(entity, entityYaw, partialTicks, stack, buffer, packedLight);
	}
}
