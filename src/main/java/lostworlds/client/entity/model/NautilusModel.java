package lostworlds.client.entity.model;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.aquatic.modern.Nautilus;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NautilusModel extends SpeciesTagModelAndTextureableModel<Nautilus> {
	@Override
	public ResourceLocation getAnimationFileLocation(Nautilus entity) {
		return LostWorldsUtils.rL("animations/nautilus.animations.json");
	}
}
