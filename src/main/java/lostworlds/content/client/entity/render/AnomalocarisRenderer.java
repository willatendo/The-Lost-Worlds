package lostworlds.content.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import lostworlds.content.client.entity.model.AnomalocarisModel;
import lostworlds.library.entity.aquatic.cambrian.AnomalocarisEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.tyrannomation.renderers.TyrannomationEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class AnomalocarisRenderer extends TyrannomationEntityRenderer<AnomalocarisEntity>
{
	public AnomalocarisRenderer(EntityRendererManager renderManager) 
	{
		super(renderManager, new AnomalocarisModel());
		this.shadowRadius = 0.5F;
	}
	
	@Override
	public RenderType getRenderType(AnomalocarisEntity animatable, float partialTicks, MatrixStack stack, IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) 
	{
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
}
