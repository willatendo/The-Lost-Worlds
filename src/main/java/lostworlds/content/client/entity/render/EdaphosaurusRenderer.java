package lostworlds.content.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import lostworlds.content.client.entity.model.EdaphosaurusModel;
import lostworlds.library.entity.terrestrial.permian.EdaphosaurusEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.tyrannomation.renderers.TyrannomationEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class EdaphosaurusRenderer extends TyrannomationEntityRenderer<EdaphosaurusEntity>
{
	public EdaphosaurusRenderer(EntityRendererManager renderManager) 
	{
		super(renderManager, new EdaphosaurusModel());
		this.shadowRadius = 0.5F;
	}
	
	@Override
	public RenderType getRenderType(EdaphosaurusEntity animatable, float partialTicks, MatrixStack stack, IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) 
	{
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
	
	@Override
	public void render(EdaphosaurusEntity entity, float entityYaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer bufferIn, int packedLightIn) 
	{
		if(entity.isBaby())
		{
			stack.scale(0.15F, 0.15F, 0.15F);
		}
		stack.scale(1.0F, 1.0F, 1.0F);
		
		super.render(entity, entityYaw, partialTicks, stack, bufferIn, packedLightIn);
	}
}