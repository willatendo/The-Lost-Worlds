package lostworlds.server.dimension;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class LostWorldsDimensions {
	public static final ResourceKey<Level> CRETACEOUS_LEVEL = ResourceKey.create(Registry.DIMENSION_REGISTRY, LostWorldsUtils.rL("cretaceous"));
	public static final ResourceKey<DimensionType> CRETACEOUS_DIMENSION_TYPE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, LostWorldsUtils.rL("cretaceous"));
}
