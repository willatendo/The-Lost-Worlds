package lostworlds.client.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import lostworlds.client.entity.model.AnomalocarisModel;
import lostworlds.server.entity.aquatic.cambrian.Anomalocaris;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class AnomalocarisRenderer extends GeoEntityRenderer<Anomalocaris> {
	public AnomalocarisRenderer(Context context) {
		super(context, new AnomalocarisModel());
		this.shadowRadius = 0.5F;
	}

	@Override
	public RenderType getRenderType(Anomalocaris animatable, float partialTicks, PoseStack stack, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLight, ResourceLocation textureLocation) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
}
