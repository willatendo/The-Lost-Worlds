package lostworlds.content.server;

import lostworlds.library.util.ModUtil;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

public class ModTags 
{
	public static class ModItemTags
	{
		public static final ITag.INamedTag<Item> ARAUCARIA_LOGS = tag("araucaria_logs");
		public static final ITag.INamedTag<Item> BLOOD_SYRINGES = tag("blood_syringes");
		public static final ITag.INamedTag<Item> BLOOD_VILES = tag("blood_viles");
		public static final ITag.INamedTag<Item> CONIFER_LOGS = tag("conifer_logs");
		public static final ITag.INamedTag<Item> DNA_DISCS = tag("dna_discs");
		public static final ITag.INamedTag<Item> DNA = tag("dna");
		public static final ITag.INamedTag<Item> FOOD_SEEDS = tag("food_seeds");
		public static final ITag.INamedTag<Item> FOSSILS = tag("fossils");
		public static final ITag.INamedTag<Item> GINKGO_LOGS = tag("ginkgo_logs");
		public static final ITag.INamedTag<Item> SCORCHED_LOGS = tag("scorched_logs");
		public static final ITag.INamedTag<Item> SOFT_TISSUE = tag("soft_tissue");
		public static final ITag.INamedTag<Item> TIME_BOOK_FUEL = tag("time_book_fuel");
		public static final ITag.INamedTag<Item> TIME_BOOKS = tag("time_books");
		
		private static ITag.INamedTag<Item> tag(String id)
		{
			return ItemTags.createOptional(ModUtil.rL(id));
		}
	}
	
	public static class ModBlockTags
	{
		public static final ITag.INamedTag<Block> ARAUCARIA_LOGS = tag("araucaria_logs");
		public static final ITag.INamedTag<Block> CONIFER_LOGS = tag("conifer_logs");
		public static final ITag.INamedTag<Block> DARK_CONCRETE = tag("dark_concrete");
		public static final ITag.INamedTag<Block> DECORATIVE_BLOCKS = tag("decorative_blocks");
		public static final ITag.INamedTag<Block> DECORATIVE_DOORS = tag("decorative_doors");
		public static final ITag.INamedTag<Block> FOSSILS = tag("");
		public static final ITag.INamedTag<Block> GINKGO_LOGS = tag("ginkgo_logs");
		public static final ITag.INamedTag<Block> GLASS = tag("glass");
		public static final ITag.INamedTag<Block> LIGHT_CONCRETE = tag("light_concrete");
		public static final ITag.INamedTag<Block> PAVEMENT = tag("pavement");
		public static final ITag.INamedTag<Block> REFINED_WOODEN_PLANKS = tag("refined_wooden_planks");
		public static final ITag.INamedTag<Block> ROADS = tag("roads");
		public static final ITag.INamedTag<Block> SCORCHED_LOGS = tag("scorched_logs");
		public static final ITag.INamedTag<Block> WOODEN_PLANKS = tag("wooden_planks");
		
		private static ITag.INamedTag<Block> tag(String id)
		{
			return BlockTags.createOptional(ModUtil.rL(id));
		}
	}
}
