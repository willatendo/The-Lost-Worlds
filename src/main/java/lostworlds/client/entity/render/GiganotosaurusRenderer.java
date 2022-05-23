package lostworlds.client.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;

import lostworlds.client.entity.model.GiganotosaurusModel;
import lostworlds.server.entity.terrestrial.cretaceous.Giganotosaurus;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class GiganotosaurusRenderer extends GeoEntityRenderer<Giganotosaurus> {
	public GiganotosaurusRenderer(Context context) {
		super(context, new GiganotosaurusModel());
		this.shadowRadius = 0.5F;
	}

	@Override
	public void render(Giganotosaurus entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight) {
		if (entity.isBaby()) {
			stack.scale(0.15F, 0.15F, 0.15F);
		}
		stack.scale(1.5F, 1.5F, 1.5F);

		super.render(entity, entityYaw, partialTicks, stack, buffer, packedLight);
	}
}
