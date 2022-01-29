package lostworlds.content.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import lostworlds.content.client.entity.model.KentrosaurusSkeletonModel;
import lostworlds.library.entity.fossil.FossilEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.tyrannomation.renderers.TyrannomationEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class KentrosaurusSkeletonRenderer extends TyrannomationEntityRenderer<FossilEntity> {
	public KentrosaurusSkeletonRenderer(EntityRendererManager renderManager) {
		super(renderManager, new KentrosaurusSkeletonModel());
		this.shadowRadius = 0.75F;
	}

	@Override
	public RenderType getRenderType(FossilEntity animatable, float partialTicks, MatrixStack stack, IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLight, ResourceLocation textureLocation) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
}
