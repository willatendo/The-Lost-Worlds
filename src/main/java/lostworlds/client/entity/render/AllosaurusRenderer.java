package lostworlds.client.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;

import lostworlds.client.entity.model.AllosaurusModel;
import lostworlds.server.entity.terrestrial.jurassic.Allosaurus;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class AllosaurusRenderer extends GeoEntityRenderer<Allosaurus> {
	public AllosaurusRenderer(Context context) {
		super(context, new AllosaurusModel());
		this.shadowRadius = 0.75F;
	}

	@Override
	public void render(Allosaurus entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight) {
		if (entity.isBaby()) {
			stack.scale(0.15F, 0.15F, 0.15F);
		}
		stack.scale(1.3F, 1.3F, 1.3F);

		super.render(entity, entityYaw, partialTicks, stack, buffer, packedLight);
	}
}
