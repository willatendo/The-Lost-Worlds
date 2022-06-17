package lostworlds.server.setup;

import java.util.ArrayList;
import java.util.Arrays;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import com.tterrag.registrate.util.entry.BlockEntry;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lostworlds.server.LostWorldsTags.ModConfiguredStructureTags;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.biome.features.configured.LostWorldsConfiguredFeatures;
import lostworlds.server.biome.features.placed.LostWorldsPlacedFeatures;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.block.Plants;
import lostworlds.server.block.Trees;
import lostworlds.server.dimension.LostWorldsNoise;
import lostworlds.server.dimension.LostWorldsNoiseGeneratorSettings;
import lostworlds.server.entity.LostWorldsEntities;
import lostworlds.server.entity.LostWorldsVillagerProfessions;
import lostworlds.server.entity.utils.enums.DinoTypes;
import lostworlds.server.item.LostWorldsItems;
import lostworlds.server.item.SyringeItem;
import lostworlds.server.network.LostWorldsNetwork;
import lostworlds.server.structure.LostWorldsConfiguredStructures;
import lostworlds.server.structure.LostWorldsStructurePecies;
import lostworlds.server.structure.LostWorldsStructureSets;
import lostworlds.server.trades.EmeraldsForMultiItemTrade;
import lostworlds.server.trades.MultiItemForEmeraldsTrade;
import lostworlds.server.util.registrate.WoodTypes;
import lostworlds.server.world.terrablender.LostWorldsTerrablender;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import terrablender.core.TerraBlender;

@EventBusSubscriber(modid = LostWorldsUtils.ID, bus = Bus.MOD)
public class CommonSetup {
	public static void fillTradeData() {
		VillagerTrades.ItemListing[] archaeology1 = new VillagerTrades.ItemListing[] { new VillagerTrades.ItemsForEmeralds(Items.CLAY_BALL, 2, 20, 2), new VillagerTrades.ItemsForEmeralds(Items.CLAY, 16, 10, 5), new VillagerTrades.ItemsForEmeralds(LostWorldsBlocks.ARCHAEOLOGY_TABLE.get(), 15, 1, 12, 3), };
		VillagerTrades.ItemListing[] archaeology2 = new VillagerTrades.ItemListing[] { new VillagerTrades.ItemsForEmeralds(LostWorldsItems.WET_PAPER.get(), 3, 5, 4) };
		VillagerTrades.ItemListing[] archaeology3 = new VillagerTrades.ItemListing[] { new MultiItemForEmeraldsTrade(ImmutableList.of(LostWorldsItems.GOLD_BRUSH.get(), LostWorldsItems.IRON_BRUSH.get(), LostWorldsItems.LEATHER_BRUSH.get()), ImmutableList.of(1, 1, 1), ImmutableList.of(32, 16, 4), 1, 36) };
		VillagerTrades.ItemListing[] archaeology4 = new VillagerTrades.ItemListing[] { new VillagerTrades.ItemsForEmeralds(LostWorldsItems.PERMIAN_PERIOD_TIME_BOOK.get(), 1, 1, 36), new VillagerTrades.ItemsForEmeralds(LostWorldsItems.JURASSIC_PERIOD_TIME_BOOK.get(), 64, 1, 36), new VillagerTrades.ItemsForEmeralds(LostWorldsItems.CRETACEOUS_PERIOD_TIME_BOOK.get(), 64, 1, 36) };
		VillagerTrades.ItemListing[] archaeology5 = new VillagerTrades.ItemListing[] { new VillagerTrades.ItemsForEmeralds(Items.LODESTONE, 64, 1, 36), new EmeraldsForMultiItemTrade(ImmutableList.of(LostWorldsItems.DIAMOND_BRUSH.get(), LostWorldsItems.CLOTH_MASK.get(), LostWorldsItems.CRYSTAL_SCARAB_BRUSH.get(), LostWorldsItems.NETHERITE_BRUSH.get()), ImmutableList.of(1, 1, 1, 1), ImmutableList.of(32, 5, 64, 48), 1, 36), new MultiItemForEmeraldsTrade(ImmutableList.of(Items.GOLD_INGOT, Items.DIAMOND, Items.IRON_INGOT, Items.NETHERITE_SCRAP, LostWorldsItems.CHARGED_CRYSTAL_SCARAB_GEM.get(), LostWorldsItems.CRYSTAL_SCARAB_BOTTOM_LEFT_LEG.get(), LostWorldsItems.CRYSTAL_SCARAB_BOTTOM_RIGHT_LEG.get(), LostWorldsItems.CRYSTAL_SCARAB_THORAX.get(), LostWorldsItems.CRYSTAL_SCARAB_TOP_LEFT_LEG.get(), LostWorldsItems.CRYSTAL_SCARAB_TOP_RIGHT_LEG.get()), ImmutableList.of(5, 1, 3, 1, 1), ImmutableList.of(10, 30, 20, 50, 64), 1, 36) };

		VillagerTrades.ItemListing[] paleontology1 = new VillagerTrades.ItemListing[] { new VillagerTrades.EmeraldForItems(LostWorldsBlocks.TINY_FOSSILISED_EGG.get(), 4, 4, 10), new VillagerTrades.EmeraldForItems(LostWorldsBlocks.SMALL_PLASTERED_FOSSILISED_EGG.get(), 5, 4, 10), new VillagerTrades.ItemsForEmeralds(LostWorldsBlocks.PALEONTOLOGY_TABLE.get(), 4, 1, 12, 36) };
		VillagerTrades.ItemListing[] paleontology2 = new VillagerTrades.ItemListing[] { new VillagerTrades.EmeraldForItems(LostWorldsBlocks.MEDIUM_PLASTERED_FOSSILISED_EGG.get(), 6, 4, 10), new VillagerTrades.ItemsForEmeralds(LostWorldsItems.FIELD_GUIDE.get(), 25, 1, 20), new VillagerTrades.ItemsForEmeralds(LostWorldsItems.WET_PAPER.get(), 3, 5, 12), new VillagerTrades.TreasureMapForEmeralds(4, ModConfiguredStructureTags.FOSSIL_MAP_LOCATION, "filled_map.fossil", MapDecoration.Type.RED_MARKER, 1, 42) };
		VillagerTrades.ItemListing[] paleontology3 = new VillagerTrades.ItemListing[] { new VillagerTrades.EmeraldForItems(LostWorldsBlocks.LARGE_FOSSILISED_EGG.get(), 7, 2, 15), new VillagerTrades.ItemsForEmeralds(LostWorldsItems.HAMMER.get(), 16, 1, 20) };
		VillagerTrades.ItemListing[] paleontology4 = new VillagerTrades.ItemListing[] { new MultiItemForEmeraldsTrade(ImmutableList.of(LostWorldsItems.DIAMOND_BRUSH.get(), LostWorldsItems.GOLD_BRUSH.get(), LostWorldsItems.IRON_BRUSH.get(), LostWorldsItems.LEATHER_BRUSH.get()), ImmutableList.of(1, 1, 1, 1), ImmutableList.of(64, 32, 16, 4), 1, 26) };
		ArrayList<Item> dnaForSale = Lists.newArrayList();
		ArrayList<Integer> dnaAmountSold = Lists.newArrayList();
		ArrayList<Integer> dnaCost = Lists.newArrayList();
		for (Trees trees : Trees.values()) {
			dnaForSale.add(trees.getDNA().get());
			dnaAmountSold.add(1);
			dnaCost.add(64);
		}
		for (Plants plants : Plants.values()) {
			dnaForSale.add(plants.getDNA().get());
			dnaAmountSold.add(1);
			dnaCost.add(64);
		}
		for (DinoTypes dinos : DinoTypes.values()) {
			dnaForSale.add(dinos.getDNA().get());
			dnaAmountSold.add(1);
			dnaCost.add(64);
		}
		VillagerTrades.ItemListing[] paleontology5 = new VillagerTrades.ItemListing[] { new VillagerTrades.ItemsForEmeralds(LostWorldsItems.CONTRACEPTIVES.get(), 12, 5, 20), new MultiItemForEmeraldsTrade(dnaForSale, dnaAmountSold, dnaCost, 1, 200) };

		VillagerTrades.TRADES.put(LostWorldsVillagerProfessions.ARCHAEOLOGIST.get(), toIntMap(ImmutableMap.of(1, archaeology1, 2, archaeology2, 3, archaeology3, 4, archaeology4, 5, archaeology5)));
		VillagerTrades.TRADES.put(LostWorldsVillagerProfessions.PALEONTOLOGIST.get(), toIntMap(ImmutableMap.of(1, paleontology1, 2, paleontology2, 3, paleontology3, 4, paleontology4, 5, paleontology5)));
	}

	private static Int2ObjectMap<VillagerTrades.ItemListing[]> toIntMap(ImmutableMap<Integer, VillagerTrades.ItemListing[]> tradeMap) {
		return new Int2ObjectOpenHashMap<>(tradeMap);
	}

	private static void addToStrippingMap(BlockEntry<? extends Block> logBlock, BlockEntry<? extends Block> strippedLogBlock) {
		AxeItem.STRIPPABLES = Maps.newHashMap(AxeItem.STRIPPABLES);
		AxeItem.STRIPPABLES.put(logBlock.get(), strippedLogBlock.get());
	}

	private static void addToTillingMap(BlockEntry<? extends Block> dirt) {
		HoeItem.TILLABLES = Maps.newHashMap(HoeItem.TILLABLES);
		HoeItem.TILLABLES.put(dirt.get(), Pair.of(HoeItem::onlyIfAirAbove, HoeItem.changeIntoState(Blocks.FARMLAND.defaultBlockState())));
	}

	private static void addToFlatteningMap(BlockEntry<? extends Block> dirt) {
		ShovelItem.FLATTENABLES = Maps.newHashMap(ShovelItem.FLATTENABLES);
		ShovelItem.FLATTENABLES.put(dirt.get(), Blocks.DIRT_PATH.defaultBlockState());
	}

	private static void addToFlammables(BlockEntry<? extends Block> burnable, int catchFlame, int burn) {
		FireBlock fireblock = (FireBlock) Blocks.FIRE;
		fireblock.setFlammable(burnable.get(), catchFlame, burn);
	}

	private static void addToComposterMap(BlockEntry<? extends Block> compostable, float chance) {
		ComposterBlock.add(chance, compostable.asStack().getItem());
	}

	@SubscribeEvent
	public static void commonSetup(FMLCommonSetupEvent event) {
		BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.POISON)), Ingredient.of(Items.SUGAR), LostWorldsItems.CONTRACEPTIVES.get().getDefaultInstance());

		addToStrippingMap(LostWorldsBlocks.ARAUCARIA.getBlock(WoodTypes.PETRIFIED_LOG).get(), LostWorldsBlocks.ARAUCARIA.getBlock(WoodTypes.STRIPPED_PETRIFIED_LOG).get());
		addToStrippingMap(LostWorldsBlocks.CALAMITES.getBlock(WoodTypes.PETRIFIED_LOG).get(), LostWorldsBlocks.CALAMITES.getBlock(WoodTypes.STRIPPED_PETRIFIED_LOG).get());
		addToStrippingMap(LostWorldsBlocks.CONIFER.getBlock(WoodTypes.PETRIFIED_LOG).get(), LostWorldsBlocks.CONIFER.getBlock(WoodTypes.STRIPPED_PETRIFIED_LOG).get());
		addToStrippingMap(LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.PETRIFIED_LOG).get(), LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.STRIPPED_PETRIFIED_LOG).get());
		addToStrippingMap(LostWorldsBlocks.GINKGO.getBlock(WoodTypes.PETRIFIED_LOG).get(), LostWorldsBlocks.GINKGO.getBlock(WoodTypes.STRIPPED_PETRIFIED_LOG).get());
		addToStrippingMap(LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.PETRIFIED_LOG).get(), LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.STRIPPED_PETRIFIED_LOG).get());
		addToStrippingMap(LostWorldsBlocks.ARAUCARIA.getBlock(WoodTypes.LOG).get(), LostWorldsBlocks.ARAUCARIA.getBlock(WoodTypes.STRIPPED_LOG).get());
		addToStrippingMap(LostWorldsBlocks.ARAUCARIA.getBlock(WoodTypes.WOOD).get(), LostWorldsBlocks.ARAUCARIA.getBlock(WoodTypes.STRIPPED_WOOD).get());
		addToStrippingMap(LostWorldsBlocks.CALAMITES.getBlock(WoodTypes.LOG).get(), LostWorldsBlocks.CALAMITES.getBlock(WoodTypes.STRIPPED_LOG).get());
		addToStrippingMap(LostWorldsBlocks.CALAMITES.getBlock(WoodTypes.WOOD).get(), LostWorldsBlocks.CALAMITES.getBlock(WoodTypes.STRIPPED_WOOD).get());
		addToStrippingMap(LostWorldsBlocks.CONIFER.getBlock(WoodTypes.LOG).get(), LostWorldsBlocks.CONIFER.getBlock(WoodTypes.STRIPPED_LOG).get());
		addToStrippingMap(LostWorldsBlocks.CONIFER.getBlock(WoodTypes.WOOD).get(), LostWorldsBlocks.CONIFER.getBlock(WoodTypes.STRIPPED_WOOD).get());
		addToStrippingMap(LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.LOG).get(), LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.STRIPPED_LOG).get());
		addToStrippingMap(LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.WOOD).get(), LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.STRIPPED_WOOD).get());
		addToStrippingMap(LostWorldsBlocks.GINKGO.getBlock(WoodTypes.LOG).get(), LostWorldsBlocks.GINKGO.getBlock(WoodTypes.STRIPPED_LOG).get());
		addToStrippingMap(LostWorldsBlocks.GINKGO.getBlock(WoodTypes.WOOD).get(), LostWorldsBlocks.GINKGO.getBlock(WoodTypes.STRIPPED_WOOD).get());
		addToStrippingMap(LostWorldsBlocks.SCORCHED.getBlock(WoodTypes.LOG).get(), LostWorldsBlocks.SCORCHED.getBlock(WoodTypes.STRIPPED_LOG).get());
		addToStrippingMap(LostWorldsBlocks.SCORCHED.getBlock(WoodTypes.WOOD).get(), LostWorldsBlocks.SCORCHED.getBlock(WoodTypes.STRIPPED_WOOD).get());
		addToStrippingMap(LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.LOG).get(), LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.STRIPPED_LOG).get());
		addToStrippingMap(LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.WOOD).get(), LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.STRIPPED_WOOD).get());
		addToTillingMap(LostWorldsBlocks.MOSSY_SOIL);
		addToFlatteningMap(LostWorldsBlocks.MOSSY_SOIL);
		addToFlammables(LostWorldsBlocks.ARAUCARIA.getBlock(WoodTypes.FENCE_GATE).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.ARAUCARIA.getBlock(WoodTypes.LEAVES).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.ARAUCARIA.getBlock(WoodTypes.STAIRS).get(), 30, 60);
		addToFlammables(LostWorldsBlocks.ARAUCARIA.getBlock(WoodTypes.LOG).get(), 5, 5);
		addToFlammables(LostWorldsBlocks.ARAUCARIA.getBlock(WoodTypes.PLANKS).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.ARAUCARIA.getBlock(WoodTypes.FENCE).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.ARAUCARIA.getBlock(WoodTypes.SLAB).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.ARAUCARIA.getBlock(WoodTypes.WOOD).get(), 5, 5);
		addToFlammables(LostWorldsBlocks.ARAUCARIA.getBlock(WoodTypes.STRIPPED_LOG).get(), 5, 5);
		addToFlammables(LostWorldsBlocks.ARAUCARIA.getBlock(WoodTypes.STRIPPED_WOOD).get(), 5, 5);
		addToFlammables(LostWorldsBlocks.CALAMITES.getBlock(WoodTypes.FENCE_GATE).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.CALAMITES.getBlock(WoodTypes.LEAVES).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.CALAMITES.getBlock(WoodTypes.STAIRS).get(), 30, 60);
		addToFlammables(LostWorldsBlocks.CALAMITES.getBlock(WoodTypes.LOG).get(), 5, 5);
		addToFlammables(LostWorldsBlocks.CALAMITES.getBlock(WoodTypes.PLANKS).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.CALAMITES.getBlock(WoodTypes.FENCE).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.CALAMITES.getBlock(WoodTypes.SLAB).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.CALAMITES.getBlock(WoodTypes.WOOD).get(), 5, 5);
		addToFlammables(LostWorldsBlocks.CALAMITES.getBlock(WoodTypes.STRIPPED_LOG).get(), 5, 5);
		addToFlammables(LostWorldsBlocks.CALAMITES.getBlock(WoodTypes.STRIPPED_WOOD).get(), 5, 5);
		addToFlammables(LostWorldsBlocks.CONIFER.getBlock(WoodTypes.FENCE_GATE).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.CONIFER.getBlock(WoodTypes.LEAVES).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.CONIFER.getBlock(WoodTypes.STAIRS).get(), 30, 60);
		addToFlammables(LostWorldsBlocks.CONIFER.getBlock(WoodTypes.LOG).get(), 5, 5);
		addToFlammables(LostWorldsBlocks.CONIFER.getBlock(WoodTypes.PLANKS).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.CONIFER.getBlock(WoodTypes.FENCE).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.CONIFER.getBlock(WoodTypes.SLAB).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.CONIFER.getBlock(WoodTypes.WOOD).get(), 5, 5);
		addToFlammables(LostWorldsBlocks.CONIFER.getBlock(WoodTypes.STRIPPED_LOG).get(), 5, 5);
		addToFlammables(LostWorldsBlocks.CONIFER.getBlock(WoodTypes.STRIPPED_WOOD).get(), 5, 5);
		addToFlammables(LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.FENCE_GATE).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.LEAVES).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.STAIRS).get(), 30, 60);
		addToFlammables(LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.LOG).get(), 5, 5);
		addToFlammables(LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.PLANKS).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.FENCE).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.SLAB).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.WOOD).get(), 5, 5);
		addToFlammables(LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.STRIPPED_LOG).get(), 5, 5);
		addToFlammables(LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.STRIPPED_WOOD).get(), 5, 5);
		addToFlammables(LostWorldsBlocks.GINKGO.getBlock(WoodTypes.FENCE_GATE).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.GINKGO.getBlock(WoodTypes.LEAVES).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.GINKGO.getBlock(WoodTypes.STAIRS).get(), 30, 60);
		addToFlammables(LostWorldsBlocks.GINKGO.getBlock(WoodTypes.LOG).get(), 5, 5);
		addToFlammables(LostWorldsBlocks.GINKGO.getBlock(WoodTypes.PLANKS).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.GINKGO.getBlock(WoodTypes.FENCE).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.GINKGO.getBlock(WoodTypes.SLAB).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.GINKGO.getBlock(WoodTypes.WOOD).get(), 5, 5);
		addToFlammables(LostWorldsBlocks.GINKGO.getBlock(WoodTypes.STRIPPED_LOG).get(), 5, 5);
		addToFlammables(LostWorldsBlocks.GINKGO.getBlock(WoodTypes.STRIPPED_WOOD).get(), 5, 5);
		addToFlammables(LostWorldsBlocks.SCORCHED.getBlock(WoodTypes.FENCE_GATE).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.SCORCHED.getBlock(WoodTypes.STAIRS).get(), 30, 60);
		addToFlammables(LostWorldsBlocks.SCORCHED.getBlock(WoodTypes.LOG).get(), 5, 5);
		addToFlammables(LostWorldsBlocks.SCORCHED.getBlock(WoodTypes.PLANKS).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.SCORCHED.getBlock(WoodTypes.FENCE).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.SCORCHED.getBlock(WoodTypes.SLAB).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.SCORCHED.getBlock(WoodTypes.WOOD).get(), 5, 5);
		addToFlammables(LostWorldsBlocks.SCORCHED.getBlock(WoodTypes.STRIPPED_LOG).get(), 5, 5);
		addToFlammables(LostWorldsBlocks.SCORCHED.getBlock(WoodTypes.STRIPPED_WOOD).get(), 5, 5);
		addToFlammables(LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.FENCE_GATE).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.LEAVES).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.STAIRS).get(), 30, 60);
		addToFlammables(LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.LOG).get(), 5, 5);
		addToFlammables(LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.PLANKS).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.FENCE).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.SLAB).get(), 5, 20);
		addToFlammables(LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.WOOD).get(), 5, 5);
		addToFlammables(LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.STRIPPED_LOG).get(), 5, 5);
		addToFlammables(LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.STRIPPED_WOOD).get(), 5, 5);
		addToComposterMap(LostWorldsBlocks.ARAUCARIA.getBlock(WoodTypes.SAPLING).get(), 0.3F);
		addToComposterMap(LostWorldsBlocks.CALAMITES.getBlock(WoodTypes.SAPLING).get(), 0.3F);
		addToComposterMap(LostWorldsBlocks.CONIFER.getBlock(WoodTypes.SAPLING).get(), 0.3F);
		addToComposterMap(LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.SAPLING).get(), 0.3F);
		addToComposterMap(LostWorldsBlocks.GINKGO.getBlock(WoodTypes.SAPLING).get(), 0.3F);
		addToComposterMap(LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.SAPLING).get(), 0.3F);
		addToComposterMap(LostWorldsBlocks.ARAUCARIA.getBlock(WoodTypes.LEAVES).get(), 0.3F);
		addToComposterMap(LostWorldsBlocks.CALAMITES.getBlock(WoodTypes.LEAVES).get(), 0.3F);
		addToComposterMap(LostWorldsBlocks.CONIFER.getBlock(WoodTypes.LEAVES).get(), 0.3F);
		addToComposterMap(LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.LEAVES).get(), 0.3F);
		addToComposterMap(LostWorldsBlocks.GINKGO.getBlock(WoodTypes.LEAVES).get(), 0.3F);
		addToComposterMap(LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.LEAVES).get(), 0.3F);
		addToComposterMap(LostWorldsBlocks.ARCHAEFRUTUS, 0.65F);
		addToComposterMap(LostWorldsBlocks.ALETHOPTERIS, 0.5F);
		addToComposterMap(LostWorldsBlocks.BRAZILEA, 0.65F);
		addToComposterMap(LostWorldsBlocks.ALETHOPTERIS, 0.5F);
		addToComposterMap(LostWorldsBlocks.CEPHALOTAXUS, 0.5F);
		addToComposterMap(LostWorldsBlocks.CYCAD, 0.65F);
		addToComposterMap(LostWorldsBlocks.DICKSONIA, 0.65F);
		addToComposterMap(LostWorldsBlocks.DILLHOFFIA, 0.65F);
		addToComposterMap(LostWorldsBlocks.DUISBERGIA, 0.65F);
		addToComposterMap(LostWorldsBlocks.EUDICOTS, 0.65F);
		addToComposterMap(LostWorldsBlocks.GROUND_FERNS, 0.65F);
		addToComposterMap(LostWorldsBlocks.MAGNOLIA, 0.65F);
		addToComposterMap(LostWorldsBlocks.OSMUNDA, 0.65F);
		addToComposterMap(LostWorldsBlocks.PERMIAN_DESERT_FERNS, 0.65F);
		addToComposterMap(LostWorldsBlocks.PERMIAN_DESERT_SHRUB, 0.65F);
		addToComposterMap(LostWorldsBlocks.WILLIAMSONIA, 0.65F);
		addToComposterMap(LostWorldsBlocks.ZAMITES, 0.65F);

		for (DinoTypes types : DinoTypes.values()) {
			SyringeItem.MAP.add(Pair.of(types.getEntityType(), types.getBloodSyringe()));
		}

		event.enqueueWork(() -> {
			CommonSetup.fillTradeData();

			LostWorldsNetwork.registerPackets();

			if (LostWorldsUtils.modLoaded(TerraBlender.MOD_ID)) {
				LostWorldsTerrablender.init();
			}

			LostWorldsConfiguredFeatures.init();
			LostWorldsPlacedFeatures.init();

			LostWorldsStructurePecies.init();
			LostWorldsStructureSets.init();
			LostWorldsConfiguredStructures.init();

			LostWorldsNoiseGeneratorSettings.init();
			LostWorldsNoise.init();
		});

		LostWorldsUtils.translateToWaves(LostWorldsEntities.FOSSIL_POACHER.get(), Arrays.asList(1, 0, 0, 0, 1, 2, 2, 3));
	}
}
