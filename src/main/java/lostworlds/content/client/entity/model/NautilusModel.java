package lostworlds.content.client.entity.model;

import lostworlds.content.ModUtils;
import lostworlds.library.entity.aquatic.modern.NautilusEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.tyrannomation.model.TyrannomatedTyrannomationModel;

@OnlyIn(Dist.CLIENT)
public class NautilusModel extends TyrannomatedTyrannomationModel<NautilusEntity> {
	@Override
	public ResourceLocation getAnimationFileLocation(NautilusEntity entity) {
		return ModUtils.rL("animations/nautilus.animations.json");
	}

	@Override
	public ResourceLocation getModelLocation(NautilusEntity entity) {
		return ModUtils.rL("geo/nautilus.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(NautilusEntity entity) {
		return ModUtils.rL("textures/model/entity/nautilus/texture.png");
	}
}
