package lostworlds.content.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import lostworlds.content.client.entity.model.KentrosaurusSkeletonModel;
import lostworlds.library.entity.fossil.FossilEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class KentrosaurusSkeletonRenderer extends GeoEntityRenderer<FossilEntity>
{
	public KentrosaurusSkeletonRenderer(EntityRendererManager renderManager) 
	{
		super(renderManager, new KentrosaurusSkeletonModel());
		this.shadowRadius = 0.75F;
	}
	@Override
	public RenderType getRenderType(FossilEntity animatable, float partialTicks, MatrixStack stack, IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) 
	{
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
}
