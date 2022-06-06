package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.aquatic.cambrian.Anomalocaris;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AnomalocarisModel extends SpeciesTagModelAndTextureableModel<Anomalocaris> {
	@Override
	public ResourceLocation getAnimationFileLocation(Anomalocaris entity) {
		return LostWorldsUtils.rL("animations/anomalocaris.animations.json");
	}
}
