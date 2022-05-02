package lostworlds.server.biome;

import java.util.function.Supplier;

import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.blockplacer.DoublePlantBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;

public class ModBlockClusterFeatureConfig {
	public static final Supplier<BlockClusterFeatureConfig> ARCHAEFRUTUS_CONFIG = () -> (new BlockClusterFeatureConfig.Builder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.ARCHAEFRUTUS.getDefaultState()), SimpleBlockPlacer.INSTANCE)).tries(16).build();
	public static final Supplier<BlockClusterFeatureConfig> ALETHOPTERIS_CONFIG = () -> (new BlockClusterFeatureConfig.Builder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.ALETHOPTERIS.getDefaultState()), new DoublePlantBlockPlacer())).tries(64).noProjection().build();
	public static final Supplier<BlockClusterFeatureConfig> BRAZILEA_CONFIG = () -> (new BlockClusterFeatureConfig.Builder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.BRAZILEA.getDefaultState()), SimpleBlockPlacer.INSTANCE)).tries(10).build();
	public static final Supplier<BlockClusterFeatureConfig> DUISBERGIA_CONFIG = () -> (new BlockClusterFeatureConfig.Builder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.DUISBERGIA.getDefaultState()), new DoublePlantBlockPlacer())).tries(64).noProjection().build();
	public static final Supplier<BlockClusterFeatureConfig> WILLIAMSONIA_CONFIG = () -> (new BlockClusterFeatureConfig.Builder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.WILLIAMSONIA.getDefaultState()), new DoublePlantBlockPlacer())).tries(64).noProjection().build();

	public static final Supplier<BlockClusterFeatureConfig> PERMIAN_DESERT_SHRUB_CONFIG = () -> (new BlockClusterFeatureConfig.Builder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.PERMIAN_DESERT_SHRUB.getDefaultState()), SimpleBlockPlacer.INSTANCE)).tries(9).build();
	public static final Supplier<BlockClusterFeatureConfig> PERMIAN_DESERT_FERNS_CONFIG = () -> (new BlockClusterFeatureConfig.Builder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.PERMIAN_DESERT_FERNS.getDefaultState()), SimpleBlockPlacer.INSTANCE)).tries(4).build();

	public static final Supplier<BlockClusterFeatureConfig> GEYSER_BLOCK = () -> (new BlockClusterFeatureConfig.Builder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.GEYSER_BLOCK.getDefaultState()), SimpleBlockPlacer.INSTANCE)).tries(1).build();

	public static final Supplier<BlockClusterFeatureConfig> FERN_CONFIG = () -> (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(Blocks.FERN.defaultBlockState(), 4), SimpleBlockPlacer.INSTANCE)).tries(32).build();

	public static final Supplier<BlockClusterFeatureConfig> CYCAD_CONFIG = () -> (new BlockClusterFeatureConfig.Builder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.CYCAD.getDefaultState()), SimpleBlockPlacer.INSTANCE)).tries(16).build();
	public static final Supplier<BlockClusterFeatureConfig> DICKSONIA_CONFIG = () -> (new BlockClusterFeatureConfig.Builder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.DICKSONIA.getDefaultState()), SimpleBlockPlacer.INSTANCE)).tries(16).build();
	public static final Supplier<BlockClusterFeatureConfig> EUDICOTS_CONFIG = () -> (new BlockClusterFeatureConfig.Builder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.EUDICOTS.getDefaultState()), SimpleBlockPlacer.INSTANCE)).tries(16).build();
	public static final Supplier<BlockClusterFeatureConfig> MAGNOLIA_CONFIG = () -> (new BlockClusterFeatureConfig.Builder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.MAGNOLIA.getDefaultState()), SimpleBlockPlacer.INSTANCE)).tries(16).build();
	public static final Supplier<BlockClusterFeatureConfig> OSMUNDA_CONFIG = () -> (new BlockClusterFeatureConfig.Builder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.OSMUNDA.getDefaultState()), SimpleBlockPlacer.INSTANCE)).tries(16).build();
	public static final Supplier<BlockClusterFeatureConfig> ZAMITES_CONFIG = () -> (new BlockClusterFeatureConfig.Builder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.ZAMITES.getDefaultState()), SimpleBlockPlacer.INSTANCE)).tries(16).build();
}
