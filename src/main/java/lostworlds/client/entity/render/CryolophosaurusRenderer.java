package lostworlds.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;

import lostworlds.client.entity.model.CryolophosaurusModel;
import lostworlds.server.entity.terrestrial.jurassic.CryolophosaurusEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class CryolophosaurusRenderer extends GeoEntityRenderer<CryolophosaurusEntity> {
	public CryolophosaurusRenderer(EntityRendererManager renderManager) {
		super(renderManager, new CryolophosaurusModel());
		this.shadowRadius = 0.5F;
	}

	@Override
	public void render(CryolophosaurusEntity entity, float entityYaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer buffer, int packedLight) {
		if (entity.isBaby()) {
			stack.scale(0.15F, 0.15F, 0.15F);
		}
		stack.scale(1.0F, 1.0F, 1.0F);

		super.render(entity, entityYaw, partialTicks, stack, buffer, packedLight);
	}
}
