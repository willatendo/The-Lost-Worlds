package lostworlds.content.client.entity.render.bone;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import lostworlds.content.client.entity.model.skeleton.CustomisableModel;
import lostworlds.library.entity.fossil.FossilEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.tyrannomation.renderers.TyrannomationEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class CustomisableRenderer extends TyrannomationEntityRenderer<FossilEntity> {
	public CustomisableRenderer(EntityRendererManager renderManager, String model, String texture, float shadow) {
		super(renderManager, new CustomisableModel(model, texture));
		this.shadowRadius = shadow;
	}

	@Override
	public RenderType getRenderType(FossilEntity animatable, float partialTicks, MatrixStack stack, IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
}
