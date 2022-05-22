package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;

@OnlyIn(Dist.CLIENT)
public class CustomisableModel<T extends IAnimatable> extends AnimatedGeoModel<T> {
	private final String model;
	private final String texture;

	public CustomisableModel(String model, String texture) {
		this.model = model;
		this.texture = texture;
	}

	public CustomisableModel(String modelAndTexture) {
		this(modelAndTexture, modelAndTexture);
	}

	@Override
	public ResourceLocation getAnimationFileLocation(T animatable) {
		return LostWorldsUtils.rL("animations/skeleton.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(T animatable) {
		return LostWorldsUtils.rL("geo/" + this.model + ".geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(T animatable) {
		return LostWorldsUtils.rL("textures/model/entity/fossil/" + this.texture + "/skeleton.png");
	}
}
