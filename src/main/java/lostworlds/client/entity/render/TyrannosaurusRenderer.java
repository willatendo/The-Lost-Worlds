package lostworlds.client.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;

import lostworlds.client.entity.model.TyrannosaurusModel;
import lostworlds.server.entity.terrestrial.cretaceous.Tyrannosaurus;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class TyrannosaurusRenderer extends GeoEntityRenderer<Tyrannosaurus> {
	public TyrannosaurusRenderer(Context context) {
		super(context, new TyrannosaurusModel());
		this.shadowRadius = 0.25F;
	}

	@Override
	public void render(Tyrannosaurus entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight) {
		if (entity.isBaby()) {
			stack.scale(0.15F, 0.15F, 0.15F);
		}
		stack.scale(1.5F, 1.5F, 1.5F);

		super.render(entity, entityYaw, partialTicks, stack, buffer, packedLight);
	}
}
