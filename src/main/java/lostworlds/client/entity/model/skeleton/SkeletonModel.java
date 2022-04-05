package lostworlds.client.entity.model.skeleton;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.item.FossilItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.model.AnimatedGeoModel;

@OnlyIn(Dist.CLIENT)
public class SkeletonModel extends AnimatedGeoModel<FossilItem> {
//	private final String model;
//	private final String texture;

	public SkeletonModel(String model, String texture) {
//		this.model = model;
//		this.texture = texture;
	}

	public SkeletonModel(String modelAndTexture) {
//		this.model = modelAndTexture;
//		this.texture = modelAndTexture;
	}

	@Override
	public ResourceLocation getAnimationFileLocation(FossilItem animatable) {
		return LostWorldsUtils.rL("animations/skeleton.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(FossilItem item) {
		// return LostWorldsUtils.rL("geo/" + model + ".geo.json");
		return LostWorldsUtils.rL("geo/unfinished_cube.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(FossilItem item) {
		// return LostWorldsUtils.rL("textures/model/entity/fossil/" + texture +
		// "/skeleton.png");
		return LostWorldsUtils.rL("textures/model/entity/unfinished_cube.png");
	}
}
