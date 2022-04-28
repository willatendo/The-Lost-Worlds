package lostworlds.server.biome;

import static lostworlds.server.biome.ModConfiguredFeatures.register;
import static lostworlds.server.util.GeneralGetter.getStateWhenCan;

import java.util.function.Supplier;

import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.feature.LostWorldsFeatures;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.blockplacer.DoublePlantBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidWithNoiseConfig;

public class PlantPatchFeatures {
	public static final Supplier<ConfiguredFeature<?, ?>> ARCHAEFRUTUS_PATCH = () -> register("archaefrutus_patch", Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(getStateWhenCan(LostWorldsBlocks.ARCHAEFRUTUS)), new DoublePlantBlockPlacer())).tries(64).noProjection().build()).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(7));
	public static final Supplier<ConfiguredFeature<?, ?>> ALETHOPTERIS_PATCH = () -> register("alethopteris_patch", Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(getStateWhenCan(LostWorldsBlocks.ALETHOPTERIS)), new DoublePlantBlockPlacer())).tries(64).noProjection().build()).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(7));
	public static final Supplier<ConfiguredFeature<?, ?>> BRAZILEA_PATCH = () -> register("brazilea_patch", Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(getStateWhenCan(LostWorldsBlocks.BRAZILEA)), SimpleBlockPlacer.INSTANCE)).tries(10).build()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(4));
	public static final Supplier<ConfiguredFeature<?, ?>> CALAMITES_SUCKOWII = () -> register("calamites_suckwii", LostWorldsFeatures.CALAMITES_SUCKOWII.configured(new ProbabilityConfig(0.2F)).decorated(Features.Placements.HEIGHTMAP_WORLD_SURFACE).squared().decorated(Placement.COUNT_NOISE_BIASED.configured(new TopSolidWithNoiseConfig(160, 80.0D, 0.3D))));
	public static final Supplier<ConfiguredFeature<?, ?>> CYCAD_PATCH = () -> register("cycad_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.CYCAD_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
	public static final Supplier<ConfiguredFeature<?, ?>> DUISBERGIA_PATCH = () -> register("duisbergia_patch", Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(getStateWhenCan(LostWorldsBlocks.DUISBERGIA)), new DoublePlantBlockPlacer())).tries(64).noProjection().build()).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(7));
	public static final Supplier<ConfiguredFeature<?, ?>> DICKSONIA_PATCH = () -> register("dicksonia_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.DICKSONIA_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
	public static final Supplier<ConfiguredFeature<?, ?>> EUDICOTS_PATCH = () -> register("eudicots_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.EUDICOTS_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
	public static final Supplier<ConfiguredFeature<?, ?>> FERN_PATCH = () -> register("fern_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.FERN_CONFIG));
	public static final Supplier<ConfiguredFeature<?, ?>> MAGNOLIA_PATCH = () -> register("magnolia_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.MAGNOLIA_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
	public static final Supplier<ConfiguredFeature<?, ?>> OSMUNDA_PATCH = () -> register("osmunda_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.OSMUNDA_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
	public static final Supplier<ConfiguredFeature<?, ?>> WILLIAMSONIA_PATCH = () -> register("williamsonia_patch", Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(getStateWhenCan(LostWorldsBlocks.WILLIAMSONIA)), new DoublePlantBlockPlacer())).tries(64).noProjection().build()).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(7));
	public static final Supplier<ConfiguredFeature<?, ?>> ZAMITES_PATCH = () -> register("zamites_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.ZAMITES_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));

	public static final Supplier<ConfiguredFeature<?, ?>> PERMIAN_DESERT_SHRUB_PATCH = () -> register("permian_desert_shrub_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.PERMIAN_DESERT_SHRUB_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
	public static final Supplier<ConfiguredFeature<?, ?>> PERMIAN_DESERT_FERNS_PATCH = () -> register("permian_desert_ferns_patch", Feature.RANDOM_PATCH.configured(ModBlockClusterFeatureConfig.PERMIAN_DESERT_FERNS_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));

	static class ModBlockClusterFeatureConfig {
		public static final BlockClusterFeatureConfig PERMIAN_DESERT_SHRUB_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(getStateWhenCan(LostWorldsBlocks.PERMIAN_DESERT_SHRUB)), SimpleBlockPlacer.INSTANCE)).tries(9).build();
		public static final BlockClusterFeatureConfig PERMIAN_DESERT_FERNS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(getStateWhenCan(LostWorldsBlocks.PERMIAN_DESERT_FERNS)), SimpleBlockPlacer.INSTANCE)).tries(4).build();

		public static final BlockClusterFeatureConfig GEYSER_BLOCK = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(getStateWhenCan(LostWorldsBlocks.GEYSER_BLOCK)), SimpleBlockPlacer.INSTANCE)).tries(1).build();

		public static final BlockClusterFeatureConfig FERN_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(Blocks.FERN.defaultBlockState(), 4), SimpleBlockPlacer.INSTANCE)).tries(32).build();

		public static final BlockClusterFeatureConfig CYCAD_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(getStateWhenCan(LostWorldsBlocks.CYCAD)), SimpleBlockPlacer.INSTANCE)).tries(16).build();
		public static final BlockClusterFeatureConfig DICKSONIA_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(getStateWhenCan(LostWorldsBlocks.DICKSONIA)), SimpleBlockPlacer.INSTANCE)).tries(16).build();
		public static final BlockClusterFeatureConfig EUDICOTS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(getStateWhenCan(LostWorldsBlocks.EUDICOTS)), SimpleBlockPlacer.INSTANCE)).tries(16).build();
		public static final BlockClusterFeatureConfig MAGNOLIA_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(getStateWhenCan(LostWorldsBlocks.MAGNOLIA)), SimpleBlockPlacer.INSTANCE)).tries(16).build();
		public static final BlockClusterFeatureConfig OSMUNDA_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(getStateWhenCan(LostWorldsBlocks.OSMUNDA)), SimpleBlockPlacer.INSTANCE)).tries(16).build();
		public static final BlockClusterFeatureConfig ZAMITES_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(getStateWhenCan(LostWorldsBlocks.ZAMITES)), SimpleBlockPlacer.INSTANCE)).tries(16).build();
	}
}
