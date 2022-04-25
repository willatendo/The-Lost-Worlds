package lostworlds.server.block.entity;

import static lostworlds.LostWorldsMod.getRegistrate;

import com.tterrag.registrate.util.entry.TileEntityEntry;

import lostworlds.client.entity.render.block.DisplayCaseRenderer;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.util.LostWorldsRegistrate;

public class LostWorldsBlockEntities {
	public static final LostWorldsRegistrate REGISTRATE = getRegistrate();

	public static final TileEntityEntry<FossilCleanerTileEntity> FOSSIL_CLEANER_TILE_ENTITY = REGISTRATE.tileEntity("fossil_cleaner_tile_entity", FossilCleanerTileEntity::new).validBlock(() -> LostWorldsBlocks.FOSSIL_CLEANER.get()).register();
	public static final TileEntityEntry<FossilGrinderTileEntity> FOSSIL_GRINDER_TILE_ENTITY = REGISTRATE.tileEntity("fossil_grinder_tile_entity", FossilGrinderTileEntity::new).validBlock(() -> LostWorldsBlocks.FOSSIL_GRINDER.get()).register();
	public static final TileEntityEntry<DNAExtractorTileEntity> DNA_EXTRACTOR_TILE_ENTITY = REGISTRATE.tileEntity("dna_extractor_tile_entity", DNAExtractorTileEntity::new).validBlock(() -> LostWorldsBlocks.DNA_EXTRACTOR.get()).register();
	public static final TileEntityEntry<AnalyzerTileEntity> ANALYZER_TILE_ENTITY = REGISTRATE.tileEntity("analyzer_tile_entity", AnalyzerTileEntity::new).validBlock(() -> LostWorldsBlocks.ANALYZER.get()).register();
	public static final TileEntityEntry<DNAInjectorTileEntity> DNA_INJECTOR_TILE_ENTITY = REGISTRATE.tileEntity("dna_injector_tile_entity", DNAInjectorTileEntity::new).validBlock(() -> LostWorldsBlocks.DNA_INJECTOR.get()).register();
	public static final TileEntityEntry<CultivatorTileEntity> CULTIVATOR_TILE_ENTITY = REGISTRATE.tileEntity("cultivator_tile_entity", CultivatorTileEntity::new).validBlock(() -> LostWorldsBlocks.CULTIVATOR.get()).register();

	public static final TileEntityEntry<FeedingTroughTileEntity> FEEDING_TROUGH_TILE_ENTITY = REGISTRATE.tileEntity("feeding_trough_tile_entity", FeedingTroughTileEntity::new).validBlock(() -> LostWorldsBlocks.FEEDING_TROUGH.get()).register();

	public static final TileEntityEntry<DisplayCaseTileEntity> DISPLAY_CASE_TILE_ENTITY = REGISTRATE.tileEntity("display_case_tile_entity", DisplayCaseTileEntity::new).validBlock(() -> LostWorldsBlocks.DISPLAY_CASE.get()).renderer(() -> DisplayCaseRenderer::new).register();

	public static void init() {
	}
}
