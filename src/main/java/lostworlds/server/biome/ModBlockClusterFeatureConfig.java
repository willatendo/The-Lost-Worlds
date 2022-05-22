package lostworlds.server.biome;

import java.util.function.Supplier;

import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.blockplacers.DoublePlantPlacer;
import net.minecraft.world.level.levelgen.feature.blockplacers.SimpleBlockPlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;

public class ModBlockClusterFeatureConfig {
	public static final Supplier<RandomPatchConfiguration> ARCHAEFRUTUS_CONFIG = () -> (new RandomPatchConfiguration.GrassConfigurationBuilder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.ARCHAEFRUTUS.getDefaultState()), SimpleBlockPlacer.INSTANCE)).tries(16).build();
	public static final Supplier<RandomPatchConfiguration> ALETHOPTERIS_CONFIG = () -> (new RandomPatchConfiguration.GrassConfigurationBuilder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.ALETHOPTERIS.getDefaultState()), new DoublePlantPlacer())).tries(64).noProjection().build();
	public static final Supplier<RandomPatchConfiguration> BRAZILEA_CONFIG = () -> (new RandomPatchConfiguration.GrassConfigurationBuilder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.BRAZILEA.getDefaultState()), SimpleBlockPlacer.INSTANCE)).tries(10).build();
	public static final Supplier<RandomPatchConfiguration> DUISBERGIA_CONFIG = () -> (new RandomPatchConfiguration.GrassConfigurationBuilder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.DUISBERGIA.getDefaultState()), new DoublePlantPlacer())).tries(64).noProjection().build();
	public static final Supplier<RandomPatchConfiguration> WILLIAMSONIA_CONFIG = () -> (new RandomPatchConfiguration.GrassConfigurationBuilder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.WILLIAMSONIA.getDefaultState()), new DoublePlantPlacer())).tries(64).noProjection().build();

	public static final Supplier<RandomPatchConfiguration> PERMIAN_DESERT_SHRUB_CONFIG = () -> (new RandomPatchConfiguration.GrassConfigurationBuilder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.PERMIAN_DESERT_SHRUB.getDefaultState()), SimpleBlockPlacer.INSTANCE)).tries(9).build();
	public static final Supplier<RandomPatchConfiguration> PERMIAN_DESERT_FERNS_CONFIG = () -> (new RandomPatchConfiguration.GrassConfigurationBuilder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.PERMIAN_DESERT_FERNS.getDefaultState()), SimpleBlockPlacer.INSTANCE)).tries(4).build();

	public static final Supplier<RandomPatchConfiguration> GEYSER_BLOCK = () -> (new RandomPatchConfiguration.GrassConfigurationBuilder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.GEYSER_BLOCK.getDefaultState()), SimpleBlockPlacer.INSTANCE)).tries(1).build();

	public static final Supplier<RandomPatchConfiguration> FERN_CONFIG = () -> (new RandomPatchConfiguration.GrassConfigurationBuilder((new WeightedStateProvider()).add(Blocks.FERN.defaultBlockState(), 4), SimpleBlockPlacer.INSTANCE)).tries(32).build();

	public static final Supplier<RandomPatchConfiguration> CYCAD_CONFIG = () -> (new RandomPatchConfiguration.GrassConfigurationBuilder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.CYCAD.getDefaultState()), SimpleBlockPlacer.INSTANCE)).tries(16).build();
	public static final Supplier<RandomPatchConfiguration> DICKSONIA_CONFIG = () -> (new RandomPatchConfiguration.GrassConfigurationBuilder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.DICKSONIA.getDefaultState()), SimpleBlockPlacer.INSTANCE)).tries(16).build();
	public static final Supplier<RandomPatchConfiguration> EUDICOTS_CONFIG = () -> (new RandomPatchConfiguration.GrassConfigurationBuilder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.EUDICOTS.getDefaultState()), SimpleBlockPlacer.INSTANCE)).tries(16).build();
	public static final Supplier<RandomPatchConfiguration> MAGNOLIA_CONFIG = () -> (new RandomPatchConfiguration.GrassConfigurationBuilder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.MAGNOLIA.getDefaultState()), SimpleBlockPlacer.INSTANCE)).tries(16).build();
	public static final Supplier<RandomPatchConfiguration> OSMUNDA_CONFIG = () -> (new RandomPatchConfiguration.GrassConfigurationBuilder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.OSMUNDA.getDefaultState()), SimpleBlockPlacer.INSTANCE)).tries(16).build();
	public static final Supplier<RandomPatchConfiguration> ZAMITES_CONFIG = () -> (new RandomPatchConfiguration.GrassConfigurationBuilder(new SuppliedBlockStateProvider(() -> LostWorldsBlocks.ZAMITES.getDefaultState()), SimpleBlockPlacer.INSTANCE)).tries(16).build();
}
