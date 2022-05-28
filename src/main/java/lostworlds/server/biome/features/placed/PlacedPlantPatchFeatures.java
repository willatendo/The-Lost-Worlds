package lostworlds.server.biome.features.placed;

import static lostworlds.server.biome.features.placed.LostWorldsPlacedFeatures.register;

import lostworlds.server.biome.features.configured.PlantPatchFeatures;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

public class PlacedPlantPatchFeatures {
	public static final Holder<PlacedFeature> ARCHAEFRUTUS_PATCH = register("archaefrutus_patch", PlantPatchFeatures.ARCHAEFRUTUS_PATCH, RarityFilter.onAverageOnceEvery(7), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
	public static final Holder<PlacedFeature> ALETHOPTERIS_PATCH = register("alethopteris_patch", PlantPatchFeatures.ALETHOPTERIS_PATCH, RarityFilter.onAverageOnceEvery(7), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
	public static final Holder<PlacedFeature> BRAZILEA_PATCH = register("brazilea_patch", PlantPatchFeatures.BRAZILEA_PATCH, VegetationPlacements.worldSurfaceSquaredWithCount(4));
	public static final Holder<PlacedFeature> CALAMITES_SUCKOWII = register("calamites_suckwii", PlantPatchFeatures.CALAMITES_SUCKOWII, RarityFilter.onAverageOnceEvery(12), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
	public static final Holder<PlacedFeature> CYCAD_PATCH = register("cycad_patch", PlantPatchFeatures.CYCAD_PATCH, RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
	public static final Holder<PlacedFeature> DUISBERGIA_PATCH = register("duisbergia_patch", PlantPatchFeatures.DUISBERGIA_PATCH, RarityFilter.onAverageOnceEvery(7), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
	public static final Holder<PlacedFeature> DICKSONIA_PATCH = register("dicksonia_patch", PlantPatchFeatures.DICKSONIA_PATCH, RarityFilter.onAverageOnceEvery(7), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
	public static final Holder<PlacedFeature> EUDICOTS_PATCH = register("eudicots_patch", PlantPatchFeatures.EUDICOTS_PATCH, RarityFilter.onAverageOnceEvery(7), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
	public static final Holder<PlacedFeature> FERN_PATCH = register("fern_patch", PlantPatchFeatures.FERN_PATCH, RarityFilter.onAverageOnceEvery(7), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
	public static final Holder<PlacedFeature> MAGNOLIA_PATCH = register("magnolia_patch", PlantPatchFeatures.MAGNOLIA_PATCH, RarityFilter.onAverageOnceEvery(7), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
	public static final Holder<PlacedFeature> OSMUNDA_PATCH = register("osmunda_patch", PlantPatchFeatures.OSMUNDA_PATCH, RarityFilter.onAverageOnceEvery(7), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
	public static final Holder<PlacedFeature> WILLIAMSONIA_PATCH = register("williamsonia_patch", PlantPatchFeatures.WILLIAMSONIA_PATCH, RarityFilter.onAverageOnceEvery(7), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
	public static final Holder<PlacedFeature> ZAMITES_PATCH = register("zamites_patch", PlantPatchFeatures.ZAMITES_PATCH, RarityFilter.onAverageOnceEvery(7), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

	public static final Holder<PlacedFeature> PERMIAN_DESERT_SHRUB_PATCH = register("permian_desert_shrub_patch", PlantPatchFeatures.PERMIAN_DESERT_SHRUB_PATCH, RarityFilter.onAverageOnceEvery(7), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
	public static final Holder<PlacedFeature> PERMIAN_DESERT_FERNS_PATCH = register("permian_desert_ferns_patch", PlantPatchFeatures.PERMIAN_DESERT_FERNS_PATCH, RarityFilter.onAverageOnceEvery(7), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

	public static void init() {
	}
}
