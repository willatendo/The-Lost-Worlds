package lostworlds.content.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import lostworlds.content.client.entity.model.ZephyrosaurusModel;
import lostworlds.library.entity.terrestrial.cretaceous.ZephyrosaurusEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.tyrannomation.renderers.TyrannomationEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class ZephyrosaurusRenderer extends TyrannomationEntityRenderer<ZephyrosaurusEntity> {
	public ZephyrosaurusRenderer(EntityRendererManager renderManager) {
		super(renderManager, new ZephyrosaurusModel());
		this.shadowRadius = 0.25F;
	}

	@Override
	public RenderType getRenderType(ZephyrosaurusEntity animatable, float partialTicks, MatrixStack stack, IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}

	@Override
	public void render(ZephyrosaurusEntity entity, float entityYaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer bufferIn, int packedLightIn) {
		stack.pushPose();

		if (entity.isBaby()) {
			stack.scale(0.15F, 0.15F, 0.15F);
		}
		stack.scale(1.0F, 1.0F, 1.0F);

		stack.popPose();

		super.render(entity, entityYaw, partialTicks, stack, bufferIn, packedLightIn);
	}
}
