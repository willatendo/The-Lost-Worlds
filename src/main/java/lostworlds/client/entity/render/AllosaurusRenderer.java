package lostworlds.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import lostworlds.client.entity.model.AllosaurusModel;
import lostworlds.server.entity.terrestrial.jurassic.AllosaurusEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class AllosaurusRenderer extends GeoEntityRenderer<AllosaurusEntity> {
	public AllosaurusRenderer(EntityRendererManager renderManager) {
		super(renderManager, new AllosaurusModel());
		this.shadowRadius = 0.75F;
	}

	@Override
	public RenderType getRenderType(AllosaurusEntity animatable, float partialTicks, MatrixStack stack, IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLight, ResourceLocation textureLocation) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}

	@Override
	public void render(AllosaurusEntity entity, float entityYaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer buffer, int packedLight) {
		stack.pushPose();

		// Todo: Fix Adult Model So We Don't Scale it up
		if (entity.isBaby()) {
			stack.scale(0.15F, 0.15F, 0.15F);
		}
		stack.scale(1.3F, 1.3F, 1.3F);

		stack.popPose();

		super.render(entity, entityYaw, partialTicks, stack, buffer, packedLight);
	}
}
