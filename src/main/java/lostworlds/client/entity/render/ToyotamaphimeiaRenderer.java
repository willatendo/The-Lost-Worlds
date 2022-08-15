package lostworlds.client.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;

import lostworlds.client.entity.model.ToyotamaphimeiaModel;
import lostworlds.server.entity.semiaquatic.pleistocene.Toyotamaphimeia;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class ToyotamaphimeiaRenderer extends GeoEntityRenderer<Toyotamaphimeia> {
	public ToyotamaphimeiaRenderer(Context context) {
		super(context, new ToyotamaphimeiaModel());
		this.shadowRadius = 0.25F;
	}

	@Override
	public void render(Toyotamaphimeia entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight) {
		if (entity.isBaby()) {
			stack.scale(0.15F, 0.15F, 0.15F);
		}
		stack.scale(1.0F, 1.0F, 1.0F);

		super.render(entity, entityYaw, partialTicks, stack, buffer, packedLight);
	}
}
