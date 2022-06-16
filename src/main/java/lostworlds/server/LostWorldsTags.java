package lostworlds.server;

import static lostworlds.LostWorldsMod.getRegistrate;
import static lostworlds.server.LostWorldsTags.NameSpace.MOD;

import java.util.Collections;
import java.util.Locale;

import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.nullness.NonNullFunction;

import lostworlds.server.species.SpeciesType;
import lostworlds.server.util.registrate.LostWorldsRegistrate;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class LostWorldsTags {
	private static final LostWorldsRegistrate REGISTRATE = getRegistrate();

	public static <T extends IForgeRegistryEntry<T>> TagKey<T> optionalTag(IForgeRegistry<T> registry, ResourceLocation id) {
		return registry.tags().createOptionalTagKey(id, Collections.emptySet());
	}

	public static <T extends IForgeRegistryEntry<T>> TagKey<T> forgeTag(IForgeRegistry<T> registry, String path) {
		return optionalTag(registry, new ResourceLocation("forge", path));
	}

	public static TagKey<Block> forgeBlockTag(String path) {
		return forgeTag(ForgeRegistries.BLOCKS, path);
	}

	public static TagKey<Item> forgeItemTag(String path) {
		return forgeTag(ForgeRegistries.ITEMS, path);
	}

	public static <T extends Block, P> NonNullFunction<BlockBuilder<T, P>, ItemBuilder<BlockItem, BlockBuilder<T, P>>> tagBlockAndItem(String... path) {
		return b -> {
			for (String p : path) {
				b.tag(forgeBlockTag(p));
			}
			ItemBuilder<BlockItem, BlockBuilder<T, P>> item = b.item();
			for (String p : path) {
				item.tag(forgeItemTag(p));
			}
			return item;
		};
	}

	public static void addToTag(TagKey<Item> itemTag, Item... item) {
		REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, provider -> provider.tag(itemTag).add(item));
	}

	public enum NameSpace {
		MOD(LostWorldsUtils.ID, false, true);

		public final String id;
		public final boolean optionalDefault;
		public final boolean alwaysDatagenDefault;

		NameSpace(String id) {
			this(id, true, false);
		}

		NameSpace(String id, boolean optionalDefault, boolean alwaysDatagenDefault) {
			this.id = id;
			this.optionalDefault = optionalDefault;
			this.alwaysDatagenDefault = alwaysDatagenDefault;
		}

	}

	public enum ModItemTags {
		ALLOSAURUS_FOSSILS,
		AMBER_RESULTS,
		ANOMALOCARIS_FOSSILS,
		ARAUCARIA_LOGS,
		ANCIENT_SAPLINGS,
		ANCIENT_SEEDS,
		BARK_SAMPLES,
		BLOOD_SYRINGES,
		BLOOD_VILES,
		BROKEN_CRYSTAL_SCARAB_GEMS,
		BRUSHES,
		CARNOTAURUS_FOSSILS,
		CALAMITES_LOGS,
		CHILESAURUS_FOSSILS,
		CRYOLOPHOSAURUS_FOSSILS,
		CRYPTOCLIDUS_FOSSILS,
		CREATURE_ITEMS,
		CONIFER_LOGS,
		CYPRESS_LOGS,
		DARK_CONCRETE,
		DECORATIVE_ITEMS,
		DECORATIVE_BLOCKS,
		DECORATIVE_DOORS,
		DIICTODON_FOSSILS,
		DILOPHOSAURUS_FOSSILS,
		DIMETRODON_FOSSILS,
		DNA_DISCS,
		DNA,
		EDAPHOSAURUS_FOSSILS,
		EGGS,
		EORAPTOR_FOSSILS,
		ELECTRONICS,
		EUSTREPTOSPONDYLUS_FOSSILS,
		FOOD_SEEDS,
		FOSSILS,
		FUKUIVENATOR_FOSSILS,
		GINKGO_LOGS,
		GIGANOTOSAURUS_FOSSILS,
		GORGONOPS_FOSSILS,
		GREAT_AUK_FOSSILS,
		KENTROSAURUS_FOSSILS,
		JURASSIC_PARK_ERA,
		JURASSIC_WORLD_ERA,
		LIAONINGOSAURUS_FOSSILS,
		LIGHT_CONCRETE,
		NAUTILUS_FOSSILS,
		OPHTHALMOSAURUS_FOSSILS,
		OSTROMIA_FOSSILS,
		OURANOSAURUS_FOSSILS,
		PARKSOSAURUS_FOSSILS,
		PALAEONISCUM_FOSSILS,
		PAVEMENT,
		PETRIFIED_LOGS,
		PLANT_FOSSILS,
		PLANT_SOFT_TISSUE,
		PLASTERED_FOSSILS,
		PROCOMPSOGNATHUS_FOSSILS,
		PROTOSUCHUS_FOSSILS,
		PSITTACOSAURUS_FOSSILS,
		REFINED_WOODEN_PLANKS,
		RHINESUCHUS_FOSSILS,
		ROADS,
		SCORCHED_LOGS,
		SEQUOIA_LOGS,
		SKELETONS,
		SOFT_TISSUE,
		SUCHOMIMUS_FOSSILS,
		TETRACERATOPS_FOSSILS,
		TIME_BOOK_FUEL,
		TIME_BOOKS,
		THANOS_FOSSILS,
		TRACE_FOSSILS,
		TYRANNOSAURUS_FOSSILS,
		UTAHRAPTOR_FOSSILS,
		WOODEN_PLANKS,
		ZEPHYROSAURUS_FOSSILS;

		public final TagKey<Item> tag;

		private ModItemTags() {
			this(MOD);
		}

		private ModItemTags(NameSpace namespace) {
			this(namespace, namespace.optionalDefault, namespace.alwaysDatagenDefault);
		}

		private ModItemTags(NameSpace namespace, String path) {
			this(namespace, path, namespace.optionalDefault, namespace.alwaysDatagenDefault);
		}

		private ModItemTags(NameSpace namespace, boolean optional, boolean alwaysDatagen) {
			this(namespace, null, optional, alwaysDatagen);
		}

		private ModItemTags(NameSpace namespace, String path, boolean optional, boolean alwaysDatagen) {
			ResourceLocation id = new ResourceLocation(namespace.id, path == null ? name().toLowerCase(Locale.ROOT) : path);
			if (optional) {
				this.tag = optionalTag(ForgeRegistries.ITEMS, id);
			} else {
				this.tag = ItemTags.create(id);
			}
			if (alwaysDatagen) {
				REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, prov -> prov.tag(this.tag));
			}
		}

		public boolean matches(Item item) {
			return item.builtInRegistryHolder().is(this.tag);
		}

		public boolean matches(ItemStack stack) {
			return stack.is(this.tag);
		}

		public void add(Item... values) {
			REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, prov -> prov.tag(this.tag).add(values));
		}

		public void includeIn(TagKey<Item> parent) {
			REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, prov -> prov.tag(parent).addTag(this.tag));
		}

		public void includeIn(ModItemTags parent) {
			this.includeIn(parent.tag);
		}

		public void includeAll(TagKey<Item> child) {
			REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, prov -> prov.tag(this.tag).addTag(child));
		}
	}

	public enum ModBlockTags {
		ARAUCARIA_LOGS,
		ANCIENT_SAPLINGS,
		BASE_STONE_PERMIAN,
		PERMIAN_STONE_ORE_REPLACEABLES,
		PERMIAN_DEEPSLATE_ORE_REPLACEABLES,
		BASE_STONE_JURASSIC,
		CARVER_BLOCKS,
		CALAMITES_PLACEABLES,
		CALAMITES_LOGS,
		CONIFER_LOGS,
		CYPRESS_LOGS,
		DARK_CONCRETE,
		DECORATIVE_BLOCKS,
		DECORATIVE_DOORS,
		DINO_SPAWNABLES,
		EGGS,
		FOSSILS,
		GINKGO_LOGS,
		JURASSIC_PARK_ERA,
		JURASSIC_WORLD_ERA,
		LIGHT_CONCRETE,
		MINEABLE_WITH_BRUSH,
		MINEABLE_WITH_HAMMER,
		PAVEMENT,
		PETRIFIED_LOGS,
		REFINED_WOODEN_PLANKS,
		ROADS,
		SCORCHED_LOGS,
		SEQUOIA_LOGS,
		WOODEN_PLANKS;

		public final TagKey<Block> tag;

		private ModBlockTags() {
			this(MOD);
		}

		private ModBlockTags(NameSpace namespace) {
			this(namespace, namespace.optionalDefault, namespace.alwaysDatagenDefault);
		}

		private ModBlockTags(NameSpace namespace, String path) {
			this(namespace, path, namespace.optionalDefault, namespace.alwaysDatagenDefault);
		}

		private ModBlockTags(NameSpace namespace, boolean optional, boolean alwaysDatagen) {
			this(namespace, null, optional, alwaysDatagen);
		}

		private ModBlockTags(NameSpace namespace, String path, boolean optional, boolean alwaysDatagen) {
			ResourceLocation id = new ResourceLocation(namespace.id, path == null ? name().toLowerCase(Locale.ROOT) : path);
			if (optional) {
				this.tag = optionalTag(ForgeRegistries.BLOCKS, id);
			} else {
				this.tag = BlockTags.create(id);
			}
			if (alwaysDatagen) {
				REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, prov -> prov.tag(this.tag));
			}
		}

		public boolean matches(Block block) {
			return block.builtInRegistryHolder().is(this.tag);
		}

		public boolean matches(BlockState state) {
			return state.is(this.tag);
		}

		public void add(Block... values) {
			REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, prov -> prov.tag(this.tag).add(values));
		}

		public void includeIn(TagKey<Block> parent) {
			REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, prov -> prov.tag(parent).addTag(this.tag));
		}

		public void includeIn(ModBlockTags parent) {
			this.includeIn(parent.tag);
		}

		public void includeAll(TagKey<Block>... children) {
			for (TagKey<Block> tag : children) {
				REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, prov -> prov.tag(this.tag).addTag(tag));
			}
		}
	}

	public static class ModBiomeTags {
		public static final TagKey<Biome> HAS_BLACK_MARKET = create("has_structure/black_market");
		public static final TagKey<Biome> HAS_SURFACE_FOSSIL = create("has_structure/surface_fossil");
		public static final TagKey<Biome> HAS_SUBTERRANEAN_FOSSIL = create("has_structure/subterranean_fossil");
		public static final TagKey<Biome> HAS_TRACE_FOSSIL = create("has_structure/trace_fossil");
		public static final TagKey<Biome> HAS_METEORITE = create("has_structure/meteorite");

		private static TagKey<Biome> create(String id) {
			return TagKey.create(Registry.BIOME_REGISTRY, LostWorldsUtils.rL(id));
		}
	}

	public static class ModConfiguredStructureTags {
		public static final TagKey<ConfiguredStructureFeature<?, ?>> FOSSIL_MAP_LOCATION = create("fossil_map_location");

		private static TagKey<ConfiguredStructureFeature<?, ?>> create(String id) {
			return TagKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, LostWorldsUtils.rL(id));
		}
	}

	public static class ModSpeciesTypeTags {
		public static final TagKey<SpeciesType> ALLOSAURUS = create("allosaurus");
		public static final TagKey<SpeciesType> ANOMALOCARIS = create("anomalocaris");
		public static final TagKey<SpeciesType> CARNOTAURUS = create("carnotaurus");
		public static final TagKey<SpeciesType> CHILESAURUS = create("chilesaurus");
		public static final TagKey<SpeciesType> CRYOLOPHOSAURUS = create("cryolophosaurus");
		public static final TagKey<SpeciesType> CRYPTOCLIDUS = create("cryptoclidus");
		public static final TagKey<SpeciesType> DIICTODON = create("diictodon");
		public static final TagKey<SpeciesType> DILOPHOSAURUS = create("dilophosaurus");
		public static final TagKey<SpeciesType> DIMETRODON = create("dimetrodon");
		public static final TagKey<SpeciesType> EDAPHOSAURUS = create("edaphosaurus");
		public static final TagKey<SpeciesType> EORAPTOR = create("eoraptor");
		public static final TagKey<SpeciesType> EUSTREPTOSPONDYLUS = create("eustreptospondylus");
		public static final TagKey<SpeciesType> FUKUIVENATOR = create("fukuivenator");
		public static final TagKey<SpeciesType> GIGANOTOSAURUS = create("giganotosaurus");
		public static final TagKey<SpeciesType> GORGONOPS = create("gorgonops");
		public static final TagKey<SpeciesType> GREAT_AUK = create("great_auk");
		public static final TagKey<SpeciesType> KENTROSAURUS = create("kentrosaurus");
		public static final TagKey<SpeciesType> LIAONINGOSAURUS = create("liaoningosaurus");
		public static final TagKey<SpeciesType> NAUTILUS = create("nautilus");
		public static final TagKey<SpeciesType> OPHTHALMOSAURUS = create("ophthalmosaurus");
		public static final TagKey<SpeciesType> OSTROMIA = create("ostromia");
		public static final TagKey<SpeciesType> OURANOSAURUS = create("ouranosaurus");
		public static final TagKey<SpeciesType> PARKSOSAURUS = create("parksosaurus");
		public static final TagKey<SpeciesType> PALAEONISCUM = create("palaeoniscum");
		public static final TagKey<SpeciesType> PROCOMPSOGNATHUS = create("procompsognathus");
		public static final TagKey<SpeciesType> PROTOSUCHUS = create("protosuchus");
		public static final TagKey<SpeciesType> PSITTACOSAURUS = create("psittacosaurus");
		public static final TagKey<SpeciesType> RHINESUCHUS = create("rhinesuchus");
		public static final TagKey<SpeciesType> SUCHOMIMUS = create("suchomimus");
		public static final TagKey<SpeciesType> UTAHRAPTOR = create("utahraptor");
		public static final TagKey<SpeciesType> THANOS = create("thanos");
		public static final TagKey<SpeciesType> TETRACERATOPS = create("tetraceratops");
		public static final TagKey<SpeciesType> TYRANNOSAURUS = create("tyrannosaurus");
		public static final TagKey<SpeciesType> ZEPHYROSAURUS = create("zephyrosaurus");

		private static TagKey<SpeciesType> create(String id) {
			return TagKey.create(LostWorldsRegistries.SPECIES_TYPES, LostWorldsUtils.rL(id));
		}
	}

	public static void init() {
		ModItemTags.TIME_BOOK_FUEL.add(Items.REDSTONE);
		ModItemTags.NAUTILUS_FOSSILS.add(Items.NAUTILUS_SHELL);

		ModBlockTags.CALAMITES_PLACEABLES.includeAll(BlockTags.SAND, BlockTags.DIRT, Tags.Blocks.GRAVEL);

		ModBlockTags.DINO_SPAWNABLES.includeAll(BlockTags.SAND, BlockTags.DIRT, Tags.Blocks.GRAVEL, Tags.Blocks.STONE);

		ModBlockTags.JURASSIC_PARK_ERA.includeAll(ModBlockTags.LIGHT_CONCRETE.tag, ModBlockTags.WOODEN_PLANKS.tag);

		ModBlockTags.JURASSIC_WORLD_ERA.includeAll(ModBlockTags.DARK_CONCRETE.tag, ModBlockTags.REFINED_WOODEN_PLANKS.tag);

		ModBlockTags.CARVER_BLOCKS.add(Blocks.BASALT, Blocks.BLACKSTONE, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.GRAVEL, Blocks.MAGMA_BLOCK, Blocks.STONE, Blocks.COBBLESTONE, Blocks.SNOW_BLOCK);

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
