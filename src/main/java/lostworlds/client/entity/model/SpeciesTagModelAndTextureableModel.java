package lostworlds.client.entity.model;

import java.util.List;

import lostworlds.server.LostWorldsRegistries;
import lostworlds.server.entity.SpeciesTagModelAndTextureable;
import lostworlds.server.species.SpeciesType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public abstract class SpeciesTagModelAndTextureableModel<T extends SpeciesTagModelAndTextureable & IAnimatable> extends AnimatedGeoModel<T> {
	@Override
	public ResourceLocation getModelLocation(T entity) {
		List<SpeciesType> types = LostWorldsRegistries.SPECIES_TYPES_REGISTRY.get().tags().getTag(entity.getTagToUse()).stream().toList();
		return types.get((int) entity.getVarientData()).getModel();
	}

	@Override
	public ResourceLocation getTextureLocation(T entity) {
		List<SpeciesType> types = LostWorldsRegistries.SPECIES_TYPES_REGISTRY.get().tags().getTag(entity.getTagToUse()).stream().toList();
		return types.get((int) entity.getVarientData()).getTexture();
	}
}
