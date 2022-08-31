package lostworlds.server;

import static lostworlds.LostWorldsMod.getRegistrate;

import lostworlds.server.util.registrate.LostWorldsRegistrate;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;

public class LostWorldsTags {
	private static final LostWorldsRegistrate REGISTRATE = getRegistrate();

	public static class ModItemTags {
		public static final ITag.INamedTag<Item> ALLOSAURUS_FOSSILS = create("allosaurus_fossils");
		public static final ITag.INamedTag<Item> AMBER_RESULTS = create("amber_results");
		public static final ITag.INamedTag<Item> ANOMALOCARIS_FOSSILS = create("anomalocaris_fossils");
		public static final ITag.INamedTag<Item> ARAUCARIA_LOGS = create("araucaria_logs");
		public static final ITag.INamedTag<Item> ANCIENT_SAPLINGS = create("ancient_saplings");
		public static final ITag.INamedTag<Item> ANCIENT_SEEDS = create("ancient_seeds");
		public static final ITag.INamedTag<Item> BARK_SAMPLES = create("bark_samples");
		public static final ITag.INamedTag<Item> BLOOD_SYRINGES = create("blood_syringes");
		public static final ITag.INamedTag<Item> BLOOD_VILES = create("blood_viles");
		public static final ITag.INamedTag<Item> BROKEN_CRYSTAL_SCARAB_GEMS = create("broken_crystal_scarab_gems");
		public static final ITag.INamedTag<Item> BRUSHES = create("brushes");
		public static final ITag.INamedTag<Item> CARNOTAURUS_FOSSILS = create("carnotaurus_fossils");
		public static final ITag.INamedTag<Item> CALAMITES_LOGS = create("calamites_logs");
		public static final ITag.INamedTag<Item> CHILESAURUS_FOSSILS = create("chilesaurus_fossils");
		public static final ITag.INamedTag<Item> CRYOLOPHOSAURUS_FOSSILS = create("cryolophosaurus_fossils");
		public static final ITag.INamedTag<Item> CREATURE_ITEMS = create("creature_items");
		public static final ITag.INamedTag<Item> CONIFER_LOGS = create("conifer_logs");
		public static final ITag.INamedTag<Item> CYPRESS_LOGS = create("cypress_logs");
		public static final ITag.INamedTag<Item> DARK_CONCRETE = create("dark_concrete");
		public static final ITag.INamedTag<Item> DECORATIVE_ITEMS = create("decorative_items");
		public static final ITag.INamedTag<Item> DECORATIVE_BLOCKS = create("decorative_blocks");
		public static final ITag.INamedTag<Item> DECORATIVE_DOORS = create("decorative_doors");
		public static final ITag.INamedTag<Item> DIICTODON_FOSSILS = create("diictodon_fossils");
		public static final ITag.INamedTag<Item> DILOPHOSAURUS_FOSSILS = create("dilophosaurus_fossils");
		public static final ITag.INamedTag<Item> DIMETRODON_FOSSILS = create("dimetrodon_fossils");
		public static final ITag.INamedTag<Item> DNA_DISCS = create("dna_discs");
		public static final ITag.INamedTag<Item> DNA = create("dna");
		public static final ITag.INamedTag<Item> EDAPHOSAURUS_FOSSILS = create("edaphosaurus_fossils");
		public static final ITag.INamedTag<Item> EGGS = create("eggs");
		public static final ITag.INamedTag<Item> EORAPTOR_FOSSILS = create("eoraptor_fossils");
		public static final ITag.INamedTag<Item> ELECTRONICS = create("electronics");
		public static final ITag.INamedTag<Item> EUSTREPTOSPONDYLUS_FOSSILS = create("eustreptospondylus_fossils");
		public static final ITag.INamedTag<Item> FOOD_SEEDS = create("food_seeds");
		public static final ITag.INamedTag<Item> FOSSILS = create("fossils");
		public static final ITag.INamedTag<Item> FUKUIVENATOR_FOSSILS = create("fukuivenator_fossils");
		public static final ITag.INamedTag<Item> GINKGO_LOGS = create("ginkgo_logs");
		public static final ITag.INamedTag<Item> GIGANOTOSAURUS_FOSSILS = create("giganotosaurus_fossils");
		public static final ITag.INamedTag<Item> GORGONOPS_FOSSILS = create("gorgonops_fossils");
		public static final ITag.INamedTag<Item> GREAT_AUK_FOSSILS = create("great_auk_fossils");
		public static final ITag.INamedTag<Item> KENTROSAURUS_FOSSILS = create("kentrosaurus_fossils");
		public static final ITag.INamedTag<Item> JURASSIC_PARK_ERA = create("jurassic_park_era");
		public static final ITag.INamedTag<Item> JURASSIC_WORLD_ERA = create("jurassic_world_era");
		public static final ITag.INamedTag<Item> LIAONINGOSAURUS_FOSSILS = create("liaoningosaurus_fossils");
		public static final ITag.INamedTag<Item> LIGHT_CONCRETE = create("light_concrete");
		public static final ITag.INamedTag<Item> NAUTILUS_FOSSILS = create("nautilus_fossils");
		public static final ITag.INamedTag<Item> OPHTHALMOSAURUS_FOSSILS = create("ophthalmosaurus_fossils");
		public static final ITag.INamedTag<Item> OSTROMIA_FOSSILS = create("ostromia_fossils");
		public static final ITag.INamedTag<Item> OURANOSAURUS_FOSSILS = create("ouranosaurus_fossils");
		public static final ITag.INamedTag<Item> PARKSOSAURUS_FOSSILS = create("parksosaurus_fossils");
		public static final ITag.INamedTag<Item> PALAEONISCUM_FOSSILS = create("palaeoniscum_fossils");
		public static final ITag.INamedTag<Item> PAVEMENT = create("pavement");
		public static final ITag.INamedTag<Item> PETRIFIED_LOGS = create("petrified_logs");
		public static final ITag.INamedTag<Item> PLANT_FOSSILS = create("plant_fossils");
		public static final ITag.INamedTag<Item> PLANT_SOFT_TISSUE = create("plant_soft_tissue");
		public static final ITag.INamedTag<Item> PLASTERED_FOSSILS = create("plastered_fossils");
		public static final ITag.INamedTag<Item> PROCOMPSOGNATHUS_FOSSILS = create("procompsognathus_fossils");
		public static final ITag.INamedTag<Item> PROTOSUCHUS_FOSSILS = create("protosuchus_fossils");
		public static final ITag.INamedTag<Item> PSITTACOSAURUS_FOSSILS = create("psittacosaurus_fossils");
		public static final ITag.INamedTag<Item> REFINED_WOODEN_PLANKS = create("refined_wooden_planks");
		public static final ITag.INamedTag<Item> RHINESUCHUS_FOSSILS = create("rhinesuchus_fossils");
		public static final ITag.INamedTag<Item> ROADS = create("roads");
		public static final ITag.INamedTag<Item> SCORCHED_LOGS = create("scorched_logs");
		public static final ITag.INamedTag<Item> SEQUOIA_LOGS = create("sequoia_logs");
		public static final ITag.INamedTag<Item> SKELETONS = create("skeletons");
		public static final ITag.INamedTag<Item> SOFT_TISSUE = create("soft_tissue");
		public static final ITag.INamedTag<Item> SUCHOMIMUS_FOSSILS = create("suchomimus_fossils");
		public static final ITag.INamedTag<Item> TETRACERATOPS_FOSSILS = create("tetraceratops_fossils");
		public static final ITag.INamedTag<Item> TIME_BOOK_FUEL = create("time_book_fuel");
		public static final ITag.INamedTag<Item> TIME_BOOKS = create("time_books");
		public static final ITag.INamedTag<Item> THANOS_FOSSILS = create("thanos_fossils");
		public static final ITag.INamedTag<Item> TRACE_FOSSILS = create("trace_fossils");
		public static final ITag.INamedTag<Item> TYRANNOSAURUS_FOSSILS = create("tyrannosaurus_fossils");
		public static final ITag.INamedTag<Item> UTAHRAPTOR_FOSSILS = create("utahraptor_fossils");
		public static final ITag.INamedTag<Item> WOODEN_PLANKS = create("wooden_planks");
		public static final ITag.INamedTag<Item> ZEPHYROSAURUS_FOSSILS = create("zephyrosaurus_fossils");

		public static ITag.INamedTag<Item> create(String id) {
			return ItemTags.createOptional(LostWorldsUtils.rL(id));
		}
	}

	public static class ModBlockTags {
		public static final ITag.INamedTag<Block> ARAUCARIA_LOGS = create("araucaria_logs");
		public static final ITag.INamedTag<Block> ANCIENT_SAPLINGS = create("ancient_saplings");
		public static final ITag.INamedTag<Block> BASE_STONE_PERMIAN = create("base_stone_permian");
		public static final ITag.INamedTag<Block> BASE_STONE_JURASSIC = create("base_stone_jurassic");
		public static final ITag.INamedTag<Block> CARVER_BLOCKS = create("carver_blocks");
		public static final ITag.INamedTag<Block> CALAMITES_PLACEABLES = create("calamites_placeables");
		public static final ITag.INamedTag<Block> CALAMITES_LOGS = create("calamites_logs");
		public static final ITag.INamedTag<Block> CONIFER_LOGS = create("conifer_logs");
		public static final ITag.INamedTag<Block> CYPRESS_LOGS = create("cypress_logs");
		public static final ITag.INamedTag<Block> DARK_CONCRETE = create("dark_concrete");
		public static final ITag.INamedTag<Block> DECORATIVE_BLOCKS = create("decorative_blocks");
		public static final ITag.INamedTag<Block> DECORATIVE_DOORS = create("decorative_doors");
		public static final ITag.INamedTag<Block> DINO_SPAWNABLES = create("dino_spawnables");
		public static final ITag.INamedTag<Block> EGGS = create("eggs");
		public static final ITag.INamedTag<Block> FOSSILS = create("fossils");
		public static final ITag.INamedTag<Block> GINKGO_LOGS = create("ginkgo_logs");
		public static final ITag.INamedTag<Block> JURASSIC_PARK_ERA = create("jurassic_park_era");
		public static final ITag.INamedTag<Block> JURASSIC_WORLD_ERA = create("jurassic_world_era");
		public static final ITag.INamedTag<Block> LIGHT_CONCRETE = create("light_concrete");
		public static final ITag.INamedTag<Block> PAVEMENT = create("pavement");
		public static final ITag.INamedTag<Block> PETRIFIED_LOGS = create("petrified_logs");
		public static final ITag.INamedTag<Block> REFINED_WOODEN_PLANKS = create("refined_wooden_planks");
		public static final ITag.INamedTag<Block> ROADS = create("roads");
		public static final ITag.INamedTag<Block> SCORCHED_LOGS = create("scorched_logs");
		public static final ITag.INamedTag<Block> SEQUOIA_LOGS = create("sequoia_logs");
		public static final ITag.INamedTag<Block> WOODEN_PLANKS = create("wooden_planks");

		public static ITag.INamedTag<Block> create(String id) {
			return BlockTags.createOptional(LostWorldsUtils.rL(id));
		}
	}

	public static class ModEntityTypeTags {
		public static final ITag.INamedTag<EntityType<?>> FOSSILS = tag("fossils");
		public static final ITag.INamedTag<EntityType<?>> ANCIENT_CREATURES = tag("ancient_creatures");

		private static ITag.INamedTag<EntityType<?>> tag(String id) {
			return EntityTypeTags.createOptional(LostWorldsUtils.rL(id));
		}
	}

	public static void init() {
		REGISTRATE.add(ModItemTags.TIME_BOOK_FUEL, Items.REDSTONE);
		REGISTRATE.add(ModItemTags.NAUTILUS_FOSSILS, Items.NAUTILUS_SHELL);

		REGISTRATE.addTags(ModBlockTags.CALAMITES_PLACEABLES, BlockTags.SAND, Tags.Blocks.DIRT, Tags.Blocks.GRAVEL);

		REGISTRATE.addTags(ModBlockTags.DINO_SPAWNABLES, BlockTags.SAND, Tags.Blocks.DIRT, Tags.Blocks.GRAVEL, Tags.Blocks.STONE);

		REGISTRATE.addTags(ModBlockTags.JURASSIC_PARK_ERA, ModBlockTags.LIGHT_CONCRETE, ModBlockTags.WOODEN_PLANKS);

		REGISTRATE.addTags(ModBlockTags.JURASSIC_WORLD_ERA, ModBlockTags.DARK_CONCRETE, ModBlockTags.REFINED_WOODEN_PLANKS);

		REGISTRATE.blockAdd(ModBlockTags.CARVER_BLOCKS, Blocks.BASALT, Blocks.BLACKSTONE, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.GRAVEL, Blocks.MAGMA_BLOCK, Blocks.STONE, Blocks.COBBLESTONE, Blocks.SNOW_BLOCK);

		REGISTRATE.copyTag(LostWorldsTags.ModBlockTags.ANCIENT_SAPLINGS, LostWorldsTags.ModItemTags.ANCIENT_SAPLINGS);
		REGISTRATE.copyTag(LostWorldsTags.ModBlockTags.ARAUCARIA_LOGS, LostWorldsTags.ModItemTags.ARAUCARIA_LOGS);
		REGISTRATE.copyTag(LostWorldsTags.ModBlockTags.CALAMITES_LOGS, LostWorldsTags.ModItemTags.CALAMITES_LOGS);
		REGISTRATE.copyTag(LostWorldsTags.ModBlockTags.CONIFER_LOGS, LostWorldsTags.ModItemTags.CONIFER_LOGS);
		REGISTRATE.copyTag(LostWorldsTags.ModBlockTags.CYPRESS_LOGS, LostWorldsTags.ModItemTags.CYPRESS_LOGS);
		REGISTRATE.copyTag(LostWorldsTags.ModBlockTags.DARK_CONCRETE, LostWorldsTags.ModItemTags.DARK_CONCRETE);
		REGISTRATE.copyTag(LostWorldsTags.ModBlockTags.DECORATIVE_BLOCKS, LostWorldsTags.ModItemTags.DECORATIVE_BLOCKS);
		REGISTRATE.copyTag(LostWorldsTags.ModBlockTags.DECORATIVE_DOORS, LostWorldsTags.ModItemTags.DECORATIVE_DOORS);
		REGISTRATE.copyTag(LostWorldsTags.ModBlockTags.EGGS, LostWorldsTags.ModItemTags.EGGS);
		REGISTRATE.copyTag(LostWorldsTags.ModBlockTags.FOSSILS, LostWorldsTags.ModItemTags.FOSSILS);
		REGISTRATE.copyTag(LostWorldsTags.ModBlockTags.GINKGO_LOGS, LostWorldsTags.ModItemTags.GINKGO_LOGS);
		REGISTRATE.copyTag(LostWorldsTags.ModBlockTags.JURASSIC_PARK_ERA, LostWorldsTags.ModItemTags.JURASSIC_PARK_ERA);
		REGISTRATE.copyTag(LostWorldsTags.ModBlockTags.JURASSIC_WORLD_ERA, LostWorldsTags.ModItemTags.JURASSIC_WORLD_ERA);
		REGISTRATE.copyTag(LostWorldsTags.ModBlockTags.LIGHT_CONCRETE, LostWorldsTags.ModItemTags.LIGHT_CONCRETE);
		REGISTRATE.copyTag(LostWorldsTags.ModBlockTags.PAVEMENT, LostWorldsTags.ModItemTags.PAVEMENT);
		REGISTRATE.copyTag(LostWorldsTags.ModBlockTags.PETRIFIED_LOGS, LostWorldsTags.ModItemTags.PETRIFIED_LOGS);
		REGISTRATE.copyTag(LostWorldsTags.ModBlockTags.REFINED_WOODEN_PLANKS, LostWorldsTags.ModItemTags.REFINED_WOODEN_PLANKS);
		REGISTRATE.copyTag(LostWorldsTags.ModBlockTags.ROADS, LostWorldsTags.ModItemTags.ROADS);
		REGISTRATE.copyTag(LostWorldsTags.ModBlockTags.SCORCHED_LOGS, LostWorldsTags.ModItemTags.SCORCHED_LOGS);
		REGISTRATE.copyTag(LostWorldsTags.ModBlockTags.SEQUOIA_LOGS, LostWorldsTags.ModItemTags.SEQUOIA_LOGS);
		REGISTRATE.copyTag(LostWorldsTags.ModBlockTags.WOODEN_PLANKS, LostWorldsTags.ModItemTags.WOODEN_PLANKS);
	}
}
