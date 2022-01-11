package lostworlds.content.server.init;

import lostworlds.content.ModRegistry;
import lostworlds.content.ModUtils;
import lostworlds.library.block.entity.AnalyzerTileEntity;
import lostworlds.library.block.entity.CultivatorTileEntity;
import lostworlds.library.block.entity.DNAExtractorTileEntity;
import lostworlds.library.block.entity.DNAInjectorTileEntity;
import lostworlds.library.block.entity.DisplayCaseTileEntity;
import lostworlds.library.block.entity.FeedingTroughTileEntity;
import lostworlds.library.block.entity.FossilCleanerTileEntity;
import lostworlds.library.block.entity.FossilGrinderTileEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityInit {
	public static final TileEntityType<FossilCleanerTileEntity> FOSSIL_CLEANER_TILE_ENTITY = ModRegistry.register("fossil_cleaner_tile_entity", TileEntityType.Builder.of(FossilCleanerTileEntity::new, BlockInit.FOSSIL_CLEANER).build(null));
	public static final TileEntityType<FossilGrinderTileEntity> FOSSIL_GRINDER_TILE_ENTITY = ModRegistry.register("fossil_grinder_tile_entity", TileEntityType.Builder.of(FossilGrinderTileEntity::new, BlockInit.FOSSIL_GRINDER).build(null));
	public static final TileEntityType<DNAExtractorTileEntity> DNA_EXTRACTOR_TILE_ENTITY = ModRegistry.register("dna_extractor_tile_entity", TileEntityType.Builder.of(DNAExtractorTileEntity::new, BlockInit.DNA_EXTRACTOR).build(null));
	public static final TileEntityType<AnalyzerTileEntity> ANALYZER_TILE_ENTITY = ModRegistry.register("analyzer_tile_entity", TileEntityType.Builder.of(AnalyzerTileEntity::new, BlockInit.ANALYZER).build(null));
	public static final TileEntityType<DNAInjectorTileEntity> DNA_INJECTOR_TILE_ENTITY = ModRegistry.register("dna_injector_tile_entity", TileEntityType.Builder.of(DNAInjectorTileEntity::new, BlockInit.DNA_INJECTOR).build(null));
	public static final TileEntityType<CultivatorTileEntity> CULTIVATOR_TILE_ENTITY = ModRegistry.register("cultivator_tile_entity", TileEntityType.Builder.of(CultivatorTileEntity::new, BlockInit.CULTIVATOR).build(null));

	public static final TileEntityType<FeedingTroughTileEntity> FEEDING_TROUGH_TILE_ENTITY = ModRegistry.register("feeding_trough_tile_entity", TileEntityType.Builder.of(FeedingTroughTileEntity::new, BlockInit.FEEDING_TROUGH).build(null));

	public static final TileEntityType<DisplayCaseTileEntity> DISPLAY_CASE_TILE_ENTITY = ModRegistry.register("display_case_tile_entity", TileEntityType.Builder.of(DisplayCaseTileEntity::new, BlockInit.DISPLAY_CASE).build(null));

	// Registry
	public static void init() {
		ModUtils.LOGGER.debug("Registering Mod Tile Entities");
	}

	public static Block[] collectBlocks(Class<?> blockClass) {
		return ForgeRegistries.BLOCKS.getValues().stream().filter(blockClass::isInstance).toArray(Block[]::new);
	}
}
