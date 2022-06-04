package lostworlds.server.entity;

import lostworlds.server.species.SpeciesType;
import net.minecraft.tags.TagKey;

public interface SpeciesTagModelAndTextureable {
	TagKey<SpeciesType> getTagToUse();

	byte getVarientData();
}
