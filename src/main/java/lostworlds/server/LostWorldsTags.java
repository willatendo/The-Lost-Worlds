package lostworlds.server;

import static lostworlds.LostWorldsMod.getRegistrate;
import static lostworlds.server.LostWorldsTags.NameSpace.MOD;

import java.util.function.Function;

import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.nullness.NonNullFunction;

import lostworlds.server.util.registrate.LostWorldsRegistrate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class LostWorldsTags {
	private static final LostWorldsRegistrate REGISTRATE = getRegistrate();

	public static <T> ITag.INamedTag<T> tag(Function<ResourceLocation, ITag.INamedTag<T>> wrapperFactory, String namespace, String path) {
		return wrapperFactory.apply(new ResourceLocation(namespace, path));
	}

	public static <T> ITag.INamedTag<T> forgeTag(Function<ResourceLocation, ITag.INamedTag<T>> wrapperFactory, String path) {
		return tag(wrapperFactory, "forge", path);
	}

	public static ITag.INamedTag<Block> forgeBlockTag(String path) {
		return forgeTag(BlockTags::createOptional, path);
	}

	public static ITag.INamedTag<Item> forgeItemTag(String path) {
		return forgeTag(ItemTags::createOptional, path);
	}

	public static <T extends Block, P> NonNullFunction<BlockBuilder<T, P>, ItemBuilder<BlockItem, BlockBuilder<T, P>>> tagBlockAndItem(String path) {
		return b -> b.tag(forgeBlockTag(path)).item().tag(forgeItemTag(path));
	}

	public static void addToTag(ITag.INamedTag<Item> itemTag, Item... item) {
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

		public final ITag.INamedTag<Item> tag;

		ModItemTags() {
			this(MOD);
		}

		ModItemTags(NameSpace namespace) {
			this(namespace, namespace.optionalDefault, namespace.alwaysDatagenDefault);
		}

		ModItemTags(NameSpace namespace, String path) {
			this(namespace, path, namespace.optionalDefault, namespace.alwaysDatagenDefault);
		}

		ModItemTags(NameSpace namespace, boolean optional, boolean alwaysDatagen) {
			this(namespace, null, optional, alwaysDatagen);
		}

		ModItemTags(NameSpace namespace, String path, boolean optional, boolean alwaysDatagen) {
			ResourceLocation id = new ResourceLocation(namespace.id, path == null ? name().toLowerCase() : path);
			if (optional) {
				tag = ItemTags.createOptional(id);
			} else {
				tag = ItemTags.bind(id.toString());
			}
			if (alwaysDatagen) {
				REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, prov -> prov.tag(tag));
			}
		}

		public boolean matches(Item item) {
			return this.tag.contains(item);
		}

		public boolean matches(ItemStack stack) {
			return this.tag.contains(stack.getItem());
		}

		public void add(Item... values) {
			REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, prov -> prov.tag(tag).add(values));
		}

		public void includeIn(ITag.INamedTag<Item> parent) {
			REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, prov -> prov.tag(parent).addTag(tag));
		}

		public void includeIn(ModItemTags parent) {
			includeIn(parent.tag);
		}

		public void includeAll(ITag.INamedTag<Item> child) {
			REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, prov -> prov.tag(tag).addTag(child));
		}
	}

	public enum ModBlockTags {
		ARAUCARIA_LOGS,
		ANCIENT_SAPLINGS,
		BASE_STONE_PERMIAN,
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
		PAVEMENT,
		PETRIFIED_LOGS,
		REFINED_WOODEN_PLANKS,
		ROADS,
		SCORCHED_LOGS,
		SEQUOIA_LOGS,
		WOODEN_PLANKS;

		public final ITag.INamedTag<Block> tag;

		ModBlockTags() {
			this(MOD);
		}

		ModBlockTags(NameSpace namespace) {
			this(namespace, namespace.optionalDefault, namespace.alwaysDatagenDefault);
		}

		ModBlockTags(NameSpace namespace, String path) {
			this(namespace, path, namespace.optionalDefault, namespace.alwaysDatagenDefault);
		}

		ModBlockTags(NameSpace namespace, boolean optional, boolean alwaysDatagen) {
			this(namespace, null, optional, alwaysDatagen);
		}

		ModBlockTags(NameSpace namespace, String path, boolean optional, boolean alwaysDatagen) {
			ResourceLocation id = new ResourceLocation(namespace.id, path == null ? name().toLowerCase() : path);
			if (optional) {
				tag = BlockTags.createOptional(id);
			} else {
				tag = BlockTags.bind(id.toString());
			}
			if (alwaysDatagen) {
				REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, prov -> prov.tag(tag));
				LostWorldsUtils.LOGGER.debug("Made " + tag.toString());
			}
		}

		public boolean matches(Block block) {
			return tag.contains(block.getBlock());
		}

		public boolean matches(BlockState state) {
			return matches(state.getBlock());
		}

		public void add(Block... values) {
			REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, prov -> prov.tag(tag).add(values));
		}

		public void includeIn(ITag.INamedTag<Block> parent) {
			REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, prov -> prov.tag(parent).addTag(tag));
		}

		public void includeIn(ModBlockTags parent) {
			includeIn(parent.tag);
		}

		public void includeAll(ITag.INamedTag<Block>... childen) {
			REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, prov -> prov.tag(tag).addTags(childen));
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
		ModItemTags.TIME_BOOK_FUEL.add(Items.REDSTONE);
		ModItemTags.NAUTILUS_FOSSILS.add(Items.NAUTILUS_SHELL);

		ModBlockTags.CALAMITES_PLACEABLES.includeAll(BlockTags.SAND, Tags.Blocks.DIRT, Tags.Blocks.GRAVEL);

		ModBlockTags.DINO_SPAWNABLES.includeAll(BlockTags.SAND, Tags.Blocks.DIRT, Tags.Blocks.GRAVEL, Tags.Blocks.STONE);

		ModBlockTags.JURASSIC_PARK_ERA.includeAll(ModBlockTags.LIGHT_CONCRETE.tag, ModBlockTags.WOODEN_PLANKS.tag);

		ModBlockTags.JURASSIC_WORLD_ERA.includeAll(ModBlockTags.DARK_CONCRETE.tag, ModBlockTags.REFINED_WOODEN_PLANKS.tag);

		ModBlockTags.CARVER_BLOCKS.add(Blocks.BASALT, Blocks.BLACKSTONE, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.GRAVEL, Blocks.MAGMA_BLOCK, Blocks.STONE, Blocks.COBBLESTONE, Blocks.SNOW_BLOCK);
	}
}
