package lostworlds.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import lostworlds.client.entity.model.OstromiaModel;
import lostworlds.server.entity.terrestrial.jurassic.OstromiaEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class OstromiaRenderer extends GeoEntityRenderer<OstromiaEntity> {
	public OstromiaRenderer(EntityRendererManager renderManager) {
		super(renderManager, new OstromiaModel());
		this.layerRenderers.add(new OstromiaWingsRenderer(this));
		this.shadowRadius = 0.25F;
	}

	@Override
	public RenderType getRenderType(OstromiaEntity animatable, float partialTicks, MatrixStack stack, IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLight, ResourceLocation textureLocation) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}

	@Override
	public void render(OstromiaEntity entity, float entityYaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer buffer, int packedLight) {
		stack.pushPose();

		if (entity.isBaby()) {
			stack.scale(0.15F, 0.15F, 0.15F);
		}
		stack.scale(1.0F, 1.0F, 1.0F);

		stack.popPose();

		super.render(entity, entityYaw, partialTicks, stack, buffer, packedLight);
	}
}
