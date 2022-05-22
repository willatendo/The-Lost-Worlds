package lostworlds.client.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;

import lostworlds.client.entity.model.OstromiaWingsModel;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.terrestrial.jurassic.OstromiaEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

@OnlyIn(Dist.CLIENT)
public class OstromiaWingsRenderer extends GeoLayerRenderer<OstromiaEntity> {
	public OstromiaWingsRenderer(IGeoRenderer<OstromiaEntity> renderer) {
		super(renderer);
	}

	@Override
	public void render(PoseStack stack, MultiBufferSource buffer, int packedLight, OstromiaEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (entity.isGliding()) {
			this.renderModel(new OstromiaWingsModel(), LostWorldsUtils.rL("textures/model/entity/ostromia/wings_texture.png"), stack, buffer, packedLight, entity, partialTicks, 1.0F, 1.0F, 1.0F);
		}
	}
}
