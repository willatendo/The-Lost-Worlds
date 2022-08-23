package lostworlds.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;

import lostworlds.client.entity.model.GreatAukModel;
import lostworlds.server.entity.semiaquatic.modern.GreatAukEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GreatAukRenderer extends GeoEntityRenderer<GreatAukEntity> {
	public GreatAukRenderer(EntityRendererManager renderManager) {
		super(renderManager, new GreatAukModel());
		this.shadowRadius = 0.5F;
	}

	@Override
	public void render(GreatAukEntity entity, float entityYaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer buffer, int packedLight) {
		if (entity.isBaby()) {
			stack.scale(0.15F, 0.15F, 0.15F);
		}
		stack.scale(1.0F, 1.0F, 1.0F);

		super.render(entity, entityYaw, partialTicks, stack, buffer, packedLight);
	}
}
