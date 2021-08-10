package lostworlds.library.dimension;

import java.util.OptionalLong;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.FuzzedBiomeMagnifier;

public class ModDimensionType extends DimensionType
{
	public ModDimensionType(OptionalLong fixedTime, boolean hasSkylight, boolean hasCeiling, boolean ultraWarm, boolean natural, double coordinateScale, boolean piglinSafe, boolean bedWorks, boolean respawnAnchorWorks, boolean hasRaids, int logicalHeight, ResourceLocation infiniburn, ResourceLocation effectsLocation, float ambientLight) 
	{
		super(fixedTime, hasSkylight, hasCeiling, ultraWarm, natural, coordinateScale, false, piglinSafe, bedWorks, respawnAnchorWorks, hasRaids, logicalHeight, FuzzedBiomeMagnifier.INSTANCE, infiniburn, effectsLocation, ambientLight);
	}
}
