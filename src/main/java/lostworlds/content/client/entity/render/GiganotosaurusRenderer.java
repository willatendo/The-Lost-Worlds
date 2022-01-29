package lostworlds.content.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import lostworlds.content.client.entity.model.GiganotosaurusModel;
import lostworlds.library.entity.terrestrial.cretaceous.GiganotosaurusEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.tyrannomation.renderers.TyrannomationEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class GiganotosaurusRenderer extends TyrannomationEntityRenderer<GiganotosaurusEntity> {
	public GiganotosaurusRenderer(EntityRendererManager renderManager) {
		super(renderManager, new GiganotosaurusModel());
		this.shadowRadius = 0.5F;
	}

	@Override
	public RenderType getRenderType(GiganotosaurusEntity animatable, float partialTicks, MatrixStack stack, IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLight, ResourceLocation textureLocation) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}

	@Override
	public void render(GiganotosaurusEntity entity, float entityYaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer buffer, int packedLight) {
		stack.pushPose();

		// Todo: Fix Adult Model So We Don't Scale it up
		if (entity.isBaby()) {
			stack.scale(0.15F, 0.15F, 0.15F);
		}
		stack.scale(1.5F, 1.5F, 1.5F);

		stack.popPose();

		super.render(entity, entityYaw, partialTicks, stack, buffer, packedLight);
	}
}
