package lostworlds.client.entity.model.skeleton;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.fossil.FossilEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.tyrannomation.model.TyrannomatedTyrannomationModel;

@OnlyIn(Dist.CLIENT)
public class CustomisableModel extends TyrannomatedTyrannomationModel<FossilEntity> {
//	private final String model;
//	private final String texture;

	public CustomisableModel(String model, String texture) {
//		this.model = model;
//		this.texture = texture;
	}

	public CustomisableModel(String modelAndTexture) {
//		this.model = modelAndTexture;
//		this.texture = modelAndTexture;
	}

	@Override
	public ResourceLocation getAnimationFileLocation(FossilEntity animatable) {
		return LostWorldsUtils.rL("animations/skeleton.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(FossilEntity item) {
		// return LostWorldsUtils.rL("geo/" + model + ".geo.json");
		return LostWorldsUtils.rL("geo/unfinished_cube.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(FossilEntity item) {
		// return LostWorldsUtils.rL("textures/model/entity/fossil/" + texture + "/skeleton.png");
		return LostWorldsUtils.rL("textures/model/entity/unfinished_cube.png");
	}
}
