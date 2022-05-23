package lostworlds.client.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;

import lostworlds.client.entity.model.TetraceratopsModel;
import lostworlds.server.entity.terrestrial.permian.Tetraceratops;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class TetraceratopsRenderer extends GeoEntityRenderer<Tetraceratops> {
	public TetraceratopsRenderer(Context context) {
		super(context, new TetraceratopsModel());
		this.shadowRadius = 0.25F;
	}

	@Override
	public void render(Tetraceratops entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource bufferIn, int packedLightIn) {
		if (entity.isBaby()) {
			stack.scale(0.15F, 0.15F, 0.15F);
		}
		stack.scale(1.0F, 1.0F, 1.0F);

		super.render(entity, entityYaw, partialTicks, stack, bufferIn, packedLightIn);
	}
}
