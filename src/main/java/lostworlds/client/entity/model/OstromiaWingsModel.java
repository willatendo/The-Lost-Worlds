package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.terrestrial.jurassic.Ostromia;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.model.AnimatedGeoModel;

@OnlyIn(Dist.CLIENT)
public class OstromiaWingsModel extends AnimatedGeoModel<Ostromia> {
	@Override
	public ResourceLocation getAnimationFileLocation(Ostromia entity) {
		return LostWorldsUtils.rL("animations/ostromia_wing.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(Ostromia entity) {
		return LostWorldsUtils.rL("geo/ostromia_wings.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(Ostromia entity) {
		return LostWorldsUtils.rL("textures/model/entity/ostromia/wings_texture.png");
	}
}
