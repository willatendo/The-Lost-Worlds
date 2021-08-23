package lostworlds.integration.quark.init;

public class QuarkBlocks 
{
//	public static final Block PERMIAN_STONE_VERTICAL_SLAB = QuarkBlockAndItemBuilder.create("permian_stone_vertical_slab", new VerticalSlabBlock(AbstractBlock.Properties.copy(BlockInit.PERMIAN_STONE_SLAB)));
//	public static final Block PERMIAN_COBBLESTONE_VERTICAL_SLAB = QuarkBlockAndItemBuilder.create("permian_cobblestone_vertical_slab", new VerticalSlabBlock(AbstractBlock.Properties.copy(BlockInit.PERMIAN_COBBLESTONE_SLAB)));
//	public static final Block PERMIAN_STONE_BRICK_VERTICAL_SLAB = QuarkBlockAndItemBuilder.create("permian_stone_brick_vertical_slab", new VerticalSlabBlock(AbstractBlock.Properties.copy(BlockInit.PERMIAN_STONE_BRICK_SLAB)));
//	public static final Block VOLCANIC_ROCK_VERTICAL_SLAB = QuarkBlockAndItemBuilder.create("volcanic_rock_vertical_slab", new VerticalSlabBlock(AbstractBlock.Properties.copy(BlockInit.VOLCANIC_ROCK_SLAB)));
//	public static final Block ARAUCARIA_VERTICAL_SLAB = QuarkBlockAndItemBuilder.create("araucaria_vertical_slab", new VerticalSlabBlock(AbstractBlock.Properties.copy(BlockInit.ARAUCARIA_SLAB)));
//	public static final Block CONIFER_VERTICAL_SLAB = QuarkBlockAndItemBuilder.create("conifer_vertical_slab", new VerticalSlabBlock(AbstractBlock.Properties.copy(BlockInit.CONIFER_SLAB)));
//	public static final Block GINKGO_VERTICAL_SLAB = QuarkBlockAndItemBuilder.create("ginkgo_vertical_slab", new VerticalSlabBlock(AbstractBlock.Properties.copy(BlockInit.GINKGO_SLAB)));
//	public static final Block SCORCHED_VERTICAL_SLAB = QuarkBlockAndItemBuilder.create("scorched_vertical_slab", new VerticalSlabBlock(AbstractBlock.Properties.copy(BlockInit.SCORCHED_SLAB)));
//	public static final Block LIGHT_CONCRETE_VERTICAL_SLAB = QuarkBlockAndItemBuilder.create("light_concrete_vertical_slab", new VerticalSlabBlock(AbstractBlock.Properties.copy(BlockInit.LIGHT_CONCRETE_SLAB)));
//	public static final Block POLISHED_LIGHT_CONCRETE_VERTICAL_SLAB = QuarkBlockAndItemBuilder.create("polished_light_concrete_vertical_slab", new VerticalSlabBlock(AbstractBlock.Properties.copy(BlockInit.POLISHED_LIGHT_CONCRETE_SLAB)));
//	public static final Block WOODEN_PLANKS_VERTICAL_SLAB = QuarkBlockAndItemBuilder.create("wooden_planks_vertical_slab", new VerticalSlabBlock(AbstractBlock.Properties.copy(BlockInit.WOODEN_PLANKS_SLAB)));
//	public static final Block DARK_CONCRETE_VERTICAL_SLAB = QuarkBlockAndItemBuilder.create("dark_concrete_vertical_slab", new VerticalSlabBlock(AbstractBlock.Properties.copy(BlockInit.DARK_CONCRETE_SLAB)));
//	public static final Block POLISHED_DARK_CONCRETE_VERTICAL_SLAB = QuarkBlockAndItemBuilder.create("polished_dark_concrete_vertical_slab", new VerticalSlabBlock(AbstractBlock.Properties.copy(BlockInit.POLISHED_DARK_CONCRETE_SLAB)));
//	public static final Block REFINED_WOODEN_PLANKS_VERTICAL_SLAB = QuarkBlockAndItemBuilder.create("refined_wooden_planks_vertical_slab", new VerticalSlabBlock(AbstractBlock.Properties.copy(BlockInit.REFINED_WOODEN_PLANKS_SLAB)));
//	public static final Block TILE_VERTICAL_SLAB = QuarkBlockAndItemBuilder.create("tile_vertical_slab", new VerticalSlabBlock(AbstractBlock.Properties.copy(BlockInit.TILE_SLAB)));
//	public static final Block VERTICAL_RAISED_PAVEMENT = QuarkBlockAndItemBuilder.create("pavement_vertical_slope", new VerticalSlabBlock(AbstractBlock.Properties.copy(BlockInit.RAISED_PAVEMENT)));
//	public static final Block VERTICAL_RAISED_GRAVEL_ROAD = QuarkBlockAndItemBuilder.create("vertical_raised_gravel_road", new VerticalSlabBlock(AbstractBlock.Properties.copy(BlockInit.RAISED_GRAVEL_ROAD)));
//	public static final Block VERTICAL_RAISED_DIRT_ROAD = QuarkBlockAndItemBuilder.create("vertical_raised_dirt_road", new VerticalSlabBlock(AbstractBlock.Properties.copy(BlockInit.RAISED_DIRT_ROAD)));
//	public static final Block VERTICAL_RAISED_PAVED_ROAD = QuarkBlockAndItemBuilder.create("vertical_raised_paved_road", new VerticalSlabBlock(AbstractBlock.Properties.copy(BlockInit.RAISED_PAVED_ROAD)));
//	
//	public static final Block ARAUCARIA_VERTICAL_PLANKS = QuarkBlockAndItemBuilder.create("araucaria_vertical_planks", new Block(AbstractBlock.Properties.copy(BlockInit.ARAUCARIA_PLANKS)));
//	public static final Block CONIFER_VERTICAL_PLANKS = QuarkBlockAndItemBuilder.create("conifer_vertical_planks", new Block(AbstractBlock.Properties.copy(BlockInit.CONIFER_PLANKS)));
//	public static final Block GINKGO_VERTICAL_PLANKS = QuarkBlockAndItemBuilder.create("ginkgo_vertical_planks", new Block(AbstractBlock.Properties.copy(BlockInit.GINKGO_PLANKS)));
//	public static final Block SCORCHED_VERTICAL_PLANKS = QuarkBlockAndItemBuilder.create("scorched_vertical_planks", new Block(AbstractBlock.Properties.copy(BlockInit.SCORCHED_PLANKS)));
//	
//	public static final Block ARAUCARIA_POST = QuarkBlockAndItemBuilder.create("araucaria_post", new PostBlock(AbstractBlock.Properties.copy(BlockInit.ARAUCARIA_LOG)));
//	public static final Block STRIPPED_ARAUCARIA_POST = QuarkBlockAndItemBuilder.create("stripped_araucaria_post", new PostBlock(AbstractBlock.Properties.copy(BlockInit.ARAUCARIA_LOG)));
//	public static final Block CONIFER_POST = QuarkBlockAndItemBuilder.create("conifer_post", new PostBlock(AbstractBlock.Properties.copy(BlockInit.CONIFER_LOG)));
//	public static final Block STRIPPED_CONIFER_POST = QuarkBlockAndItemBuilder.create("stripped_conifer_post", new PostBlock(AbstractBlock.Properties.copy(BlockInit.CONIFER_LOG)));
//	public static final Block GINKGO_POST = QuarkBlockAndItemBuilder.create("ginkgo_post", new PostBlock(AbstractBlock.Properties.copy(BlockInit.GINKGO_LOG)));
//	public static final Block STRIPPED_GINKGO_POST = QuarkBlockAndItemBuilder.create("stripped_ginkgo_post", new PostBlock(AbstractBlock.Properties.copy(BlockInit.GINKGO_LOG)));
//	public static final Block SCORCHED_POST = QuarkBlockAndItemBuilder.create("scorched_post", new PostBlock(AbstractBlock.Properties.copy(BlockInit.SCORCHED_LOG)));
//	public static final Block STRIPPED_SCORCHED_POST = QuarkBlockAndItemBuilder.create("stripped_scorched_post", new PostBlock(AbstractBlock.Properties.copy(BlockInit.SCORCHED_LOG)));
//	
//	public static final Block ARAUCARIA_LEAF_CARPET = QuarkBlockAndItemBuilder.create("araucaria_leaf_carpet", new LeafCarpetBlock(AbstractBlock.Properties.copy(BlockInit.ARAUCARIA_LEAVES)));
//	public static final Block CONIFER_LEAF_CARPET = QuarkBlockAndItemBuilder.create("conifer_leaf_carpet", new LeafCarpetBlock(AbstractBlock.Properties.copy(BlockInit.CONIFER_LEAVES)));
//	public static final Block GINKGO_LEAF_CARPET = QuarkBlockAndItemBuilder.create("ginkgo_leaf_carpet", new LeafCarpetBlock(AbstractBlock.Properties.copy(BlockInit.GINKGO_LEAVES)));
//	
//	public static final Block ARAUCARIA_BOOKSHELF = QuarkBlockAndItemBuilder.create("araucaria_bookshelf", new QuarkBookshelfBlock(AbstractBlock.Properties.copy(BlockInit.ARAUCARIA_PLANKS)));
//	public static final Block CONIFER_BOOKSHELF = QuarkBlockAndItemBuilder.create("conifer_bookshelf", new QuarkBookshelfBlock(AbstractBlock.Properties.copy(BlockInit.CONIFER_PLANKS)));
//	public static final Block GINKGO_BOOKSHELF = QuarkBlockAndItemBuilder.create("ginkgo_bookshelf", new QuarkBookshelfBlock(AbstractBlock.Properties.copy(BlockInit.GINKGO_PLANKS)));
//	public static final Block SCORCHED_BOOKSHELF = QuarkBlockAndItemBuilder.create("scorched_bookshelf", new QuarkBookshelfBlock(AbstractBlock.Properties.copy(BlockInit.SCORCHED_PLANKS)));
//	
//	public static final Block ARAUCARIA_LADDER = QuarkBlockAndItemBuilder.create("araucaria_ladder", new LadderBlock(AbstractBlock.Properties.copy(Blocks.LADDER)));
//	public static final Block CONIFER_LADDER = QuarkBlockAndItemBuilder.create("conifer_ladder", new LadderBlock(AbstractBlock.Properties.copy(Blocks.LADDER)));
//	public static final Block GINKGO_LADDER = QuarkBlockAndItemBuilder.create("ginkgo_ladder", new LadderBlock(AbstractBlock.Properties.copy(Blocks.LADDER)));
//	public static final Block SCORCHED_LADDER = QuarkBlockAndItemBuilder.create("scorched_ladder", new LadderBlock(AbstractBlock.Properties.copy(Blocks.LADDER)));
//	
//	public static final Block ARAUCARIA_CHEST = createChestBlock("araucaria_chest");
//	public static final Block ARAUCARIA_TRAPPED_CHEST = createTrappedChestBlock("araucaria_chest");
//	public static final Block CONIFER_CHEST = createChestBlock("conifer_chest");
//	public static final Block CONIFER_TRAPPED_CHEST = createTrappedChestBlock("conifer_chest");
//	public static final Block GINKGO_CHEST = createChestBlock("ginkgo_chest");
//	public static final Block GINKGO_TRAPPED_CHEST = createTrappedChestBlock("ginkgo_chest");
//	public static final Block SCORCHED_CHEST = createChestBlock("scorched_chest");
//	public static final Block SCORCHED_TRAPPED_CHEST = createTrappedChestBlock("scorched_chest");
//	
//	public static final Block ARAUCARIA_HEDGE = QuarkBlockAndItemBuilder.create("araucaria_hedge", new HedgeBlock(AbstractBlock.Properties.copy(BlockInit.ARAUCARIA_FENCE)));
//	public static final Block CONIFER_HEDGE = QuarkBlockAndItemBuilder.create("conifer_hedge", new HedgeBlock(AbstractBlock.Properties.copy(BlockInit.CONIFER_FENCE)));
//	public static final Block GINKGO_HEDGE = QuarkBlockAndItemBuilder.create("ginkgo_hedge", new HedgeBlock(AbstractBlock.Properties.copy(BlockInit.GINKGO_FENCE)));
//
//	public static Block createChestBlock(String id) 
//	{
//		Block chest = QuarkBlockBuilder.create(id, new QuarkChestBlock(ModUtils.ID + ":" + id, Block.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD)));
//		QuarkItemBuilder.create(id, new BlockItem(chest, new Properties().tab(ModItemGroup.BLOCKS).setISTER(() -> chestISTER(false))));
//		ChestManager.putChestInfo(ModUtils.ID, id, false);
//		return chest;
//	}
//	
//	public static Block createTrappedChestBlock(String id) 
//	{
//		Block chest = QuarkBlockBuilder.create("trapped_" + id, new QuarkTrappedChestBlock(ModUtils.ID + ":" + id + "_trapped", Block.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD)));
//		QuarkItemBuilder.create("trapped_" + id, new BlockItem(chest, new Properties().tab(ModItemGroup.BLOCKS).setISTER(() -> chestISTER(true))));
//		ChestManager.putChestInfo(ModUtils.ID, id, true);
//		return chest;
//	}
//	
//	@OnlyIn(Dist.CLIENT)
//	private static Callable<ItemStackTileEntityRenderer> chestISTER(boolean trapped) 
//	{
//		return () -> new ChestItemRenderer<TileEntity>(trapped ? QuarkTrappedChestTileEntity::new : QuarkChestTileEntity::new);
//	}
//	
//	public static void init() { }
}
