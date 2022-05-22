package lostworlds.client.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import lostworlds.client.entity.model.PalaeoniscumModel;
import lostworlds.server.entity.aquatic.permian.PalaeoniscumEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class PalaeoniscumRenderer extends GeoEntityRenderer<PalaeoniscumEntity> {
	public PalaeoniscumRenderer(EntityRenderDispatcher renderManager) {
		super(renderManager, new PalaeoniscumModel());
		this.shadowRadius = 0.3F;
	}

	@Override
	public RenderType getRenderType(PalaeoniscumEntity animatable, float partialTicks, PoseStack stack, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLight, ResourceLocation textureLocation) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
}
