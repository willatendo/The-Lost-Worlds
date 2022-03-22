package lostworlds.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import lostworlds.client.entity.model.CarnotaurusModel;
import lostworlds.server.entity.terrestrial.cretaceous.CarnotaurusEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.tyrannomation.renderers.TyrannomationEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class CarnotaurusRenderer extends TyrannomationEntityRenderer<CarnotaurusEntity> {
	public CarnotaurusRenderer(EntityRendererManager renderManager) {
		super(renderManager, new CarnotaurusModel());
		this.shadowRadius = 0.5F;
	}

	@Override
	public RenderType getRenderType(CarnotaurusEntity animatable, float partialTicks, MatrixStack stack, IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}

	@Override
	public void render(CarnotaurusEntity entity, float entityYaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer buffer, int packedLightIn) {
		stack.pushPose();

		// Todo: Fix Adult Model So We Don't Scale it up
		if (entity.isBaby()) {
			stack.scale(0.15F, 0.15F, 0.15F);
		}
		stack.scale(1.5F, 1.5F, 1.5F);

		stack.popPose();

		super.render(entity, entityYaw, partialTicks, stack, buffer, packedLightIn);
	}
}