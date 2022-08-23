package lostworlds.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;

import lostworlds.client.entity.model.AllosaurusModel;
import lostworlds.server.entity.terrestrial.jurassic.AllosaurusEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class AllosaurusRenderer extends GeoEntityRenderer<AllosaurusEntity> {
	public AllosaurusRenderer(EntityRendererManager renderManager) {
		super(renderManager, new AllosaurusModel());
		this.shadowRadius = 0.75F;
	}

	@Override
	public void render(AllosaurusEntity entity, float entityYaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer buffer, int packedLight) {
		if (entity.isBaby()) {
			stack.scale(0.15F, 0.15F, 0.15F);
		}
		stack.scale(1.3F, 1.3F, 1.3F);

		super.render(entity, entityYaw, partialTicks, stack, buffer, packedLight);
	}
}
