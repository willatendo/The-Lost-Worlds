package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.aquatic.modern.Nautilus;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.model.AnimatedGeoModel;

@OnlyIn(Dist.CLIENT)
public class NautilusModel extends AnimatedGeoModel<Nautilus> {
	@Override
	public ResourceLocation getAnimationFileLocation(Nautilus entity) {
		return LostWorldsUtils.rL("animations/nautilus.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(Nautilus entity) {
		return LostWorldsUtils.rL("geo/nautilus.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(Nautilus entity) {
		return LostWorldsUtils.rL("textures/model/entity/nautilus/texture.png");
	}
}
