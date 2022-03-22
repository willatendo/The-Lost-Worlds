package lostworlds.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import lostworlds.client.entity.model.UtahraptorModel;
import lostworlds.server.entity.terrestrial.cretaceous.UtahraptorEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.tyrannomation.renderers.TyrannomationEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class UtahraptorRenderer extends TyrannomationEntityRenderer<UtahraptorEntity> {
	public UtahraptorRenderer(EntityRendererManager renderManager) {
		super(renderManager, new UtahraptorModel());
		this.shadowRadius = 0.25F;
	}

	@Override
	public RenderType getRenderType(UtahraptorEntity animatable, float partialTicks, MatrixStack stack, IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLight, ResourceLocation textureLocation) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}

	@Override
	public void render(UtahraptorEntity entity, float entityYaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer buffer, int packedLight) {
		stack.pushPose();

		if (entity.isBaby()) {
			stack.scale(0.15F, 0.15F, 0.15F);
		}
		stack.scale(1.0F, 1.0F, 1.0F);

		stack.popPose();

		super.render(entity, entityYaw, partialTicks, stack, buffer, packedLight);
	}
}