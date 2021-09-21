package lostworlds.content.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import lostworlds.content.client.entity.model.KentrosaurusModel;
import lostworlds.library.entity.terrestrial.jurassic.KentrosaurusEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class KentrosaurusRenderer extends GeoEntityRenderer<KentrosaurusEntity>
{
	public KentrosaurusRenderer(EntityRendererManager renderManager) 
	{
		super(renderManager, new KentrosaurusModel());
		this.shadowRadius = 1.0F;
	}
	@Override
	public RenderType getRenderType(KentrosaurusEntity animatable, float partialTicks, MatrixStack stack, IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) 
	{
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
	
	@Override
	public void render(KentrosaurusEntity entity, float entityYaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer bufferIn, int packedLightIn) 
	{
		if(entity.isBaby())
		{
			stack.scale(0.15F, 0.15F, 0.15F);
		}
		stack.scale(1.0F, 1.0F, 1.0F);
		
		super.render(entity, entityYaw, partialTicks, stack, bufferIn, packedLightIn);
	}
}