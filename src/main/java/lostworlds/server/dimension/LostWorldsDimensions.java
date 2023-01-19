package lostworlds.server.dimension;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;

public class LostWorldsDimensions {
	public static final ResourceKey<Level> CRETACEOUS_LEVEL = ResourceKey.create(Registry.DIMENSION_REGISTRY, LostWorldsUtils.rL("cretaceous"));
	public static final ResourceKey<LevelStem> CRETACEOUS_DIMENSION = ResourceKey.create(Registry.LEVEL_STEM_REGISTRY, LostWorldsUtils.rL("cretaceous"));
	public static final ResourceKey<DimensionType> CRETACEOUS_DIMENSION_TYPE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, LostWorldsUtils.rL("cretaceous"));

	public static final ResourceKey<Level> JURASSIC_LEVEL = ResourceKey.create(Registry.DIMENSION_REGISTRY, LostWorldsUtils.rL("jurassic"));
	public static final ResourceKey<LevelStem> JURASSIC_DIMENSION = ResourceKey.create(Registry.LEVEL_STEM_REGISTRY, LostWorldsUtils.rL("jurassic"));
	public static final ResourceKey<DimensionType> JURASSIC_DIMENSION_TYPE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, LostWorldsUtils.rL("jurassic"));

	public static final ResourceKey<Level> PERMIAN_LEVEL = ResourceKey.create(Registry.DIMENSION_REGISTRY, LostWorldsUtils.rL("permian"));
	public static final ResourceKey<LevelStem> PERMIAN_DIMENSION = ResourceKey.create(Registry.LEVEL_STEM_REGISTRY, LostWorldsUtils.rL("permian"));
	public static final ResourceKey<DimensionType> PERMIAN_DIMENSION_TYPE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, LostWorldsUtils.rL("permian"));

	public static void init() {
	}
}
