package lostworlds.server.biome;

import static lostworlds.server.biome.LostWorldsConfiguredFeatures.register;

import lostworlds.server.feature.LostWorldsFeatures;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidWithNoiseConfig;

public class PlantPatchFeatures {
	public static final ConfiguredFeature<?, ?> ARCHAEFRUTUS_PATCH = register("archaefrutus_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.ARCHAEFRUTUS_CONFIG.get()).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(7));
	public static final ConfiguredFeature<?, ?> ALETHOPTERIS_PATCH = register("alethopteris_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.ALETHOPTERIS_CONFIG.get()).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(7));
	public static final ConfiguredFeature<?, ?> BRAZILEA_PATCH = register("brazilea_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.BRAZILEA_CONFIG.get()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(4));
	public static final ConfiguredFeature<?, ?> CALAMITES_SUCKOWII = register("calamites_suckwii", LostWorldsFeatures.CALAMITES_SUCKOWII.configured(new ProbabilityConfig(0.2F)).decorated(Features.Placements.HEIGHTMAP_WORLD_SURFACE).squared().decorated(Placement.COUNT_NOISE_BIASED.configured(new TopSolidWithNoiseConfig(160, 80.0D, 0.3D))));
	public static final ConfiguredFeature<?, ?> CYCAD_PATCH = register("cycad_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.CYCAD_CONFIG.get()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
	public static final ConfiguredFeature<?, ?> DUISBERGIA_PATCH = register("duisbergia_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.DUISBERGIA_CONFIG.get()).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(7));
	public static final ConfiguredFeature<?, ?> DICKSONIA_PATCH = register("dicksonia_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.DICKSONIA_CONFIG.get()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
	public static final ConfiguredFeature<?, ?> EUDICOTS_PATCH = register("eudicots_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.EUDICOTS_CONFIG.get()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
	public static final ConfiguredFeature<?, ?> FERN_PATCH = register("fern_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.FERN_CONFIG.get()));
	public static final ConfiguredFeature<?, ?> MAGNOLIA_PATCH = register("magnolia_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.MAGNOLIA_CONFIG.get()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
	public static final ConfiguredFeature<?, ?> OSMUNDA_PATCH = register("osmunda_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.OSMUNDA_CONFIG.get()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
	public static final ConfiguredFeature<?, ?> WILLIAMSONIA_PATCH = register("williamsonia_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.WILLIAMSONIA_CONFIG.get()).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(7));
	public static final ConfiguredFeature<?, ?> ZAMITES_PATCH = register("zamites_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.ZAMITES_CONFIG.get()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));

	public static final ConfiguredFeature<?, ?> PERMIAN_DESERT_SHRUB_PATCH = register("permian_desert_shrub_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.PERMIAN_DESERT_SHRUB_CONFIG.get()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
	public static final ConfiguredFeature<?, ?> PERMIAN_DESERT_FERNS_PATCH = register("permian_desert_ferns_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.PERMIAN_DESERT_FERNS_CONFIG.get()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));

	public static void init() {
	}
}
