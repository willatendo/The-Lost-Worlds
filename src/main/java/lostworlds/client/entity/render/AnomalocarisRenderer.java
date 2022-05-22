package lostworlds.client.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import lostworlds.client.entity.model.AnomalocarisModel;
import lostworlds.server.entity.aquatic.cambrian.AnomalocarisEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class AnomalocarisRenderer extends GeoEntityRenderer<AnomalocarisEntity> {
	public AnomalocarisRenderer(EntityRenderDispatcher renderManager) {
		super(renderManager, new AnomalocarisModel());
		this.shadowRadius = 0.5F;
	}

	@Override
	public RenderType getRenderType(AnomalocarisEntity animatable, float partialTicks, PoseStack stack, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLight, ResourceLocation textureLocation) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
}
