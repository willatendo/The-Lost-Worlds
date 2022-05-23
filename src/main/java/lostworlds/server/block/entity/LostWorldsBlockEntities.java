package lostworlds.server.block.entity;

import static lostworlds.LostWorldsMod.getRegistrate;

import com.tterrag.registrate.util.entry.BlockEntityEntry;

import lostworlds.client.entity.render.block.DisplayCaseRenderer;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.util.registrate.LostWorldsRegistrate;

public class LostWorldsBlockEntities {
	public static final LostWorldsRegistrate REGISTRATE = getRegistrate();

	public static final BlockEntityEntry<FossilCleanerBlockEntity> FOSSIL_CLEANER_BLOCK_ENTITY = REGISTRATE.blockEntity("fossil_cleaner", FossilCleanerBlockEntity::new).validBlock(() -> LostWorldsBlocks.FOSSIL_CLEANER.get()).register();
	public static final BlockEntityEntry<FossilGrinderBlockEntity> FOSSIL_GRINDER_BLOCK_ENTITY = REGISTRATE.blockEntity("fossil_grinder", FossilGrinderBlockEntity::new).validBlock(() -> LostWorldsBlocks.FOSSIL_GRINDER.get()).register();
	public static final BlockEntityEntry<DNAExtractorBlockEntity> DNA_EXTRACTOR_BLOCK_ENTITY = REGISTRATE.blockEntity("dna_extractor", DNAExtractorBlockEntity::new).validBlock(() -> LostWorldsBlocks.DNA_EXTRACTOR.get()).register();
	public static final BlockEntityEntry<AnalyzerBlockEntity> ANALYZER_BLOCK_ENTITY = REGISTRATE.blockEntity("analyzer", AnalyzerBlockEntity::new).validBlock(() -> LostWorldsBlocks.ANALYZER.get()).register();
	public static final BlockEntityEntry<DNAInjectorBlockEntity> DNA_INJECTOR_BLOCK_ENTITY = REGISTRATE.blockEntity("dna_injector", DNAInjectorBlockEntity::new).validBlock(() -> LostWorldsBlocks.DNA_INJECTOR.get()).register();
	public static final BlockEntityEntry<CultivatorBlockEntity> CULTIVATOR_BLOCK_ENTITY = REGISTRATE.blockEntity("cultivator", CultivatorBlockEntity::new).validBlock(() -> LostWorldsBlocks.CULTIVATOR.get()).register();

	public static final BlockEntityEntry<FeedingTroughBlockEntity> FEEDING_TROUGH_BLOCK_ENTITY = REGISTRATE.blockEntity("feeding_trough", FeedingTroughBlockEntity::new).validBlock(() -> LostWorldsBlocks.FEEDING_TROUGH.get()).register();

	public static final BlockEntityEntry<DisplayCaseBlockEntity> DISPLAY_CASE_BLOCK_ENTITY = REGISTRATE.blockEntity("display_case", DisplayCaseBlockEntity::new).validBlock(() -> LostWorldsBlocks.DISPLAY_CASE.get()).renderer(() -> DisplayCaseRenderer::new).register();

	public static void registrate() {
	}
}
