package lostworlds.content.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import lostworlds.content.client.entity.model.ChilesaurusModel;
import lostworlds.library.entity.prehistoric.jurassic.ChilesaurusEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ChilesaurusRenderer extends GeoEntityRenderer<ChilesaurusEntity>
{
	public ChilesaurusRenderer(EntityRendererManager renderManager) 
	{
		super(renderManager, new ChilesaurusModel());
		this.shadowRadius = 0.5F;
	}
	@Override
	public RenderType getRenderType(ChilesaurusEntity animatable, float partialTicks, MatrixStack stack, IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) 
	{
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
	
	@Override
	public void render(ChilesaurusEntity entity, float entityYaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer bufferIn, int packedLightIn) 
	{
		if(entity.isBaby())
		{
			stack.scale(0.15F, 0.15F, 0.15F);
		}
		stack.scale(1.0F, 1.0F, 1.0F);
		
		super.render(entity, entityYaw, partialTicks, stack, bufferIn, packedLightIn);
	}
}