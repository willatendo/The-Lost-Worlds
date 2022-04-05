package lostworlds.server.block.entity;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsBlockEntities {
	public static final TileEntityType<FossilCleanerTileEntity> FOSSIL_CLEANER_TILE_ENTITY = register("fossil_cleaner_tile_entity", TileEntityType.Builder.of(FossilCleanerTileEntity::new, LostWorldsBlocks.FOSSIL_CLEANER).build(null));
	public static final TileEntityType<FossilGrinderTileEntity> FOSSIL_GRINDER_TILE_ENTITY = register("fossil_grinder_tile_entity", TileEntityType.Builder.of(FossilGrinderTileEntity::new, LostWorldsBlocks.FOSSIL_GRINDER).build(null));
	public static final TileEntityType<DNAExtractorTileEntity> DNA_EXTRACTOR_TILE_ENTITY = register("dna_extractor_tile_entity", TileEntityType.Builder.of(DNAExtractorTileEntity::new, LostWorldsBlocks.DNA_EXTRACTOR).build(null));
	public static final TileEntityType<AnalyzerTileEntity> ANALYZER_TILE_ENTITY = register("analyzer_tile_entity", TileEntityType.Builder.of(AnalyzerTileEntity::new, LostWorldsBlocks.ANALYZER).build(null));
	public static final TileEntityType<DNAInjectorTileEntity> DNA_INJECTOR_TILE_ENTITY = register("dna_injector_tile_entity", TileEntityType.Builder.of(DNAInjectorTileEntity::new, LostWorldsBlocks.DNA_INJECTOR).build(null));
	public static final TileEntityType<CultivatorTileEntity> CULTIVATOR_TILE_ENTITY = register("cultivator_tile_entity", TileEntityType.Builder.of(CultivatorTileEntity::new, LostWorldsBlocks.CULTIVATOR).build(null));

	public static final TileEntityType<FeedingTroughTileEntity> FEEDING_TROUGH_TILE_ENTITY = register("feeding_trough_tile_entity", TileEntityType.Builder.of(FeedingTroughTileEntity::new, LostWorldsBlocks.FEEDING_TROUGH).build(null));

	public static final TileEntityType<DisplayCaseTileEntity> DISPLAY_CASE_TILE_ENTITY = register("display_case_tile_entity", TileEntityType.Builder.of(DisplayCaseTileEntity::new, LostWorldsBlocks.DISPLAY_CASE).build(null));

	public static <T extends TileEntity> TileEntityType<T> register(String id, TileEntityType<T> tileEntity) {
		tileEntity.setRegistryName(LostWorldsUtils.rL(id));
		ForgeRegistries.TILE_ENTITIES.register(tileEntity);
		return tileEntity;
	}

	public static void init() {
		LostWorldsUtils.LOGGER.debug("Registering Mod Tile Entities");
	}

	public static Block[] collectBlocks(Class<?> blockClass) {
		return ForgeRegistries.BLOCKS.getValues().stream().filter(blockClass::isInstance).toArray(Block[]::new);
	}
}
