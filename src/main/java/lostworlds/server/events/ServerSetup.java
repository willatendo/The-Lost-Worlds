package lostworlds.server.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lostworlds.client.LostWorldsConfig;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.block.NautilusShellBlock;
import lostworlds.server.block.Plants;
import lostworlds.server.block.Trees;
import lostworlds.server.entity.LostWorldsVillagerProfessions;
import lostworlds.server.entity.illager.FossilPoacherEntity;
import lostworlds.server.entity.spawner.FossilPoachingGroupSpawner;
import lostworlds.server.entity.utils.enums.DinoTypes;
import lostworlds.server.item.LostWorldsItems;
import lostworlds.server.structure.LostWorldsStructures;
import lostworlds.server.trades.EmeraldsForMultiItemTrade;
import lostworlds.server.trades.MultiItemForEmeraldsTrade;
import lostworlds.server.util.JigsawUtils;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShovelItem;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.MapDecoration;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppedEvent;

public class ServerSetup {
	@EventBusSubscriber
	static class AddVillageStructures {
		@SubscribeEvent
		public static void onServerAboutToStartEvent(FMLServerAboutToStartEvent event) {
			if (LostWorldsConfig.SERVER_CONFIG.villageStructures.get()) {
				LostWorldsUtils.LOGGER.debug("Loading: Village Structures");

				// Plains Village Structures
				JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/plains/houses"), LostWorldsUtils.rL("village/plains/plains_archaeologist_hut"), LostWorldsConfig.SERVER_CONFIG.villageStructureWeights.get());
				JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/plains/houses"), LostWorldsUtils.rL("village/plains/plains_paleontology_hut"), LostWorldsConfig.SERVER_CONFIG.villageStructureWeights.get());

				// Taiga Village Structures
				JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/taiga/houses"), LostWorldsUtils.rL("village/taiga/taiga_archaeologist_hut"), LostWorldsConfig.SERVER_CONFIG.villageStructureWeights.get());
				JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/taiga/houses"), LostWorldsUtils.rL("village/taiga/taiga_paleontology_hut"), LostWorldsConfig.SERVER_CONFIG.villageStructureWeights.get());

				// Savanna Village Structures
				JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/savanna/houses"), LostWorldsUtils.rL("village/savanna/savanna_archaeologist_hut"), LostWorldsConfig.SERVER_CONFIG.villageStructureWeights.get());
				JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/savanna/houses"), LostWorldsUtils.rL("village/savanna/savanna_paleontology_hut"), LostWorldsConfig.SERVER_CONFIG.villageStructureWeights.get());

				// Snowy Village Structures
				JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/snowy/houses"), LostWorldsUtils.rL("village/snowy/snowy_archaeologist_hut"), LostWorldsConfig.SERVER_CONFIG.villageStructureWeights.get());
				JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/snowy/houses"), LostWorldsUtils.rL("village/snowy/snowy_paleontology_hut"), LostWorldsConfig.SERVER_CONFIG.villageStructureWeights.get());

				// Desert Village Structures
				JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/desert/houses"), LostWorldsUtils.rL("village/desert/desert_archaeologist_hut"), LostWorldsConfig.SERVER_CONFIG.villageStructureWeights.get());
				JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/desert/houses"), LostWorldsUtils.rL("village/desert/desert_paleontology_hut"), LostWorldsConfig.SERVER_CONFIG.villageStructureWeights.get());

				LostWorldsUtils.LOGGER.debug("Finished: Village Structures");
			}
		}
	}

	@EventBusSubscriber(modid = LostWorldsUtils.ID, bus = Bus.MOD)
	static class ModVillagerTrades {
		public static void fillTradeData() {
			LostWorldsUtils.LOGGER.debug("Loading: Villager Trades");

			// Archaeologist
			VillagerTrades.ITrade[] archaeology1 = new VillagerTrades.ITrade[] { new VillagerTrades.ItemsForEmeraldsTrade(Items.CLAY_BALL, 2, 20, 2), new VillagerTrades.ItemsForEmeraldsTrade(Items.CLAY, 16, 10, 5), new VillagerTrades.ItemsForEmeraldsTrade(LostWorldsBlocks.ARCHAEOLOGY_TABLE.asItem(), 15, 1, 3), };
			VillagerTrades.ITrade[] archaeology2 = new VillagerTrades.ITrade[] { new VillagerTrades.ItemsForEmeraldsTrade(LostWorldsItems.WET_PAPER.get(), 3, 5, 4) };
			VillagerTrades.ITrade[] archaeology3 = new VillagerTrades.ITrade[] { new MultiItemForEmeraldsTrade(ImmutableList.of(LostWorldsItems.GOLD_BRUSH.get(), LostWorldsItems.IRON_BRUSH.get(), LostWorldsItems.LEATHER_BRUSH.get()), ImmutableList.of(1, 1, 1), ImmutableList.of(32, 16, 4), 1, 36) };
			VillagerTrades.ITrade[] archaeology4 = new VillagerTrades.ITrade[] { new VillagerTrades.ItemsForEmeraldsTrade(LostWorldsItems.PERMIAN_PERIOD_TIME_BOOK.get(), 1, 1, 36), new VillagerTrades.ItemsForEmeraldsTrade(LostWorldsItems.JURASSIC_PERIOD_TIME_BOOK.get(), 64, 1, 36), new VillagerTrades.ItemsForEmeraldsTrade(LostWorldsItems.CRETACEOUS_PERIOD_TIME_BOOK.get(), 64, 1, 36) };
			VillagerTrades.ITrade[] archaeology5 = new VillagerTrades.ITrade[] { new VillagerTrades.ItemsForEmeraldsTrade(Items.LODESTONE, 64, 1, 36), new EmeraldsForMultiItemTrade(ImmutableList.of(LostWorldsItems.DIAMOND_BRUSH.get(), LostWorldsItems.CLOTH_MASK.get(), LostWorldsItems.CRYSTAL_SCARAB_BRUSH.get(), LostWorldsItems.NETHERITE_BRUSH.get()), ImmutableList.of(1, 1, 1, 1), ImmutableList.of(32, 5, 64, 48), 1, 36), new MultiItemForEmeraldsTrade(ImmutableList.of(Items.GOLD_INGOT, Items.DIAMOND, Items.IRON_INGOT, Items.NETHERITE_SCRAP, LostWorldsItems.CHARGED_CRYSTAL_SCARAB_GEM.get(), LostWorldsItems.CRYSTAL_SCARAB_BOTTOM_LEFT_LEG.get(), LostWorldsItems.CRYSTAL_SCARAB_BOTTOM_RIGHT_LEG.get(), LostWorldsItems.CRYSTAL_SCARAB_THORAX.get(), LostWorldsItems.CRYSTAL_SCARAB_TOP_LEFT_LEG.get(), LostWorldsItems.CRYSTAL_SCARAB_TOP_RIGHT_LEG.get()), ImmutableList.of(5, 1, 3, 1, 1), ImmutableList.of(10, 30, 20, 50, 64), 1, 36) };

			// Paleontologist
			VillagerTrades.ITrade[] paleontology1 = new VillagerTrades.ITrade[] { new VillagerTrades.EmeraldForItemsTrade(LostWorldsBlocks.TINY_FOSSILISED_EGG.asItem(), 4, 4, 10), new VillagerTrades.EmeraldForItemsTrade(LostWorldsBlocks.SMALL_PLASTERED_FOSSILISED_EGG.asItem(), 5, 4, 10), new VillagerTrades.ItemsForEmeraldsTrade(LostWorldsBlocks.PALEONTOLOGY_TABLE.asItem(), 4, 1, 36) };

			VillagerTrades.ITrade[] paleontology2 = new VillagerTrades.ITrade[] { new VillagerTrades.EmeraldForItemsTrade(LostWorldsBlocks.MEDIUM_PLASTERED_FOSSILISED_EGG.asItem(), 6, 4, 10), new VillagerTrades.ItemsForEmeraldsTrade(LostWorldsItems.FIELD_GUIDE.get(), 25, 1, 20), new VillagerTrades.ItemsForEmeraldsTrade(LostWorldsItems.WET_PAPER.get(), 3, 5, 12), new VillagerTrades.EmeraldForMapTrade(4, LostWorldsStructures.SURFACE_FOSSIL, MapDecoration.Type.RED_MARKER, 1, 42), new VillagerTrades.EmeraldForMapTrade(4, LostWorldsStructures.SUBTERRANEAN_FOSSIL, MapDecoration.Type.BANNER_RED, 1, 42) };

			VillagerTrades.ITrade[] paleontology3 = new VillagerTrades.ITrade[] { new VillagerTrades.EmeraldForItemsTrade(LostWorldsBlocks.LARGE_FOSSILISED_EGG.asItem(), 7, 2, 15), new VillagerTrades.ItemsForEmeraldsTrade(LostWorldsItems.HAMMER.get(), 16, 1, 20) };

			VillagerTrades.ITrade[] paleontology4 = new VillagerTrades.ITrade[] { new MultiItemForEmeraldsTrade(ImmutableList.of(LostWorldsItems.DIAMOND_BRUSH.get(), LostWorldsItems.GOLD_BRUSH.get(), LostWorldsItems.IRON_BRUSH.get(), LostWorldsItems.LEATHER_BRUSH.get()), ImmutableList.of(1, 1, 1, 1), ImmutableList.of(64, 32, 16, 4), 1, 26) };

			ArrayList<Item> dnaForSale = Lists.newArrayList();
			for (Trees trees : Trees.values()) {
				dnaForSale.add(trees.getDNA().get());
			}
			for (Plants plants : Plants.values()) {
				dnaForSale.add(plants.getDNA().get());
			}
			for (DinoTypes dinos : DinoTypes.values()) {
				dnaForSale.add(dinos.getDNA().get());
			}
			VillagerTrades.ITrade[] paleontology5 = new VillagerTrades.ITrade[] { new VillagerTrades.ItemsForEmeraldsTrade(LostWorldsItems.CONTRACEPTIVES.get(), 12, 5, 20), new MultiItemForEmeraldsTrade(dnaForSale, ImmutableList.of(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1), ImmutableList.of(64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64), 1, 200) };

			VillagerTrades.TRADES.put(LostWorldsVillagerProfessions.ARCHAEOLOGIST, toIntMap(ImmutableMap.of(1, archaeology1, 2, archaeology2, 3, archaeology3, 4, archaeology4, 5, archaeology5)));
			VillagerTrades.TRADES.put(LostWorldsVillagerProfessions.PALEONTOLOGIST, toIntMap(ImmutableMap.of(1, paleontology1, 2, paleontology2, 3, paleontology3, 4, paleontology4, 5, paleontology5)));

			LostWorldsUtils.LOGGER.debug("Finished: Villager Trades");
		}

		private static Int2ObjectMap<VillagerTrades.ITrade[]> toIntMap(ImmutableMap<Integer, VillagerTrades.ITrade[]> tradeMap) {
			return new Int2ObjectOpenHashMap<>(tradeMap);
		}

		@SubscribeEvent
		public static void initVillagerTradesArray(FMLCommonSetupEvent event) {
			event.enqueueWork(() -> {
				ModVillagerTrades.fillTradeData();
			});
		}
	}

	@EventBusSubscriber(modid = LostWorldsUtils.ID, bus = Bus.FORGE)
	static class AddBlockItem {
		@SubscribeEvent
		public static void addNautilusShell(RightClickBlock event) {
			PlayerEntity entity = event.getPlayer();
			Hand hand = event.getHand();
			ItemStack stack = entity.getItemInHand(hand);
			Item item = stack.getItem();

			if (item == Items.NAUTILUS_SHELL) {
				World world = event.getWorld();
				BlockPos pos = event.getPos().relative(event.getFace());
				BlockPos clickedPos = event.getPos();
				Direction direction = entity.getDirection().getOpposite();

				if (!(world.getBlockState(clickedPos).getBlock() instanceof ITileEntityProvider) || !(world.getBlockState(clickedPos).hasTileEntity())) {
					if (world.getBlockState(pos.below()).isFaceSturdy(world, pos, direction)) {
						entity.swing(hand);
						if (!entity.isCreative()) {
							stack.shrink(1);
						}
						world.setBlockAndUpdate(pos, LostWorldsBlocks.NAUTILUS_SHELL.defaultBlockState().setValue(NautilusShellBlock.HORIZONTAL_FACING, direction));
						world.playSound(entity, pos, LostWorldsBlocks.NAUTILUS_SHELL.getSoundType(LostWorldsBlocks.NAUTILUS_SHELL.defaultBlockState()).getPlaceSound(), SoundCategory.BLOCKS, 1.0F, 1.0F);
					}
				}

				if (entity.isCrouching()) {
					if (world.getBlockState(pos.below()).isFaceSturdy(world, pos, direction)) {
						entity.swing(hand);
						if (!entity.isCreative()) {
							stack.shrink(1);
						}
						world.setBlockAndUpdate(pos, LostWorldsBlocks.NAUTILUS_SHELL.defaultBlockState().setValue(NautilusShellBlock.HORIZONTAL_FACING, direction));
						world.playSound(entity, pos, LostWorldsBlocks.NAUTILUS_SHELL.getSoundType(LostWorldsBlocks.NAUTILUS_SHELL.defaultBlockState()).getPlaceSound(), SoundCategory.BLOCKS, 1.0F, 1.0F);
					}
					event.setCanceled(true);
				}
			}
		}
	}

	@EventBusSubscriber(bus = Bus.MOD)
	static class IllagerSetup {
		@SubscribeEvent
		public void onEntityJoin(EntityJoinWorldEvent event) {
			LostWorldsUtils.LOGGER.debug("Loading: Adding Goals");

			if (event.getEntity() instanceof VillagerEntity) {
				VillagerEntity villager = (VillagerEntity) event.getEntity();
				villager.goalSelector.addGoal(1, new AvoidEntityGoal(villager, FossilPoacherEntity.class, 16.0F, 0.7D, 0.7D));
			}
			LostWorldsUtils.LOGGER.debug("Finished: Adding Goals");
		}
	}

	@EventBusSubscriber(modid = LostWorldsUtils.ID)
	static class SpawnerHandler {
		private static Map<ResourceLocation, FossilPoachingGroupSpawner> spawners = new HashMap<>();

		@SubscribeEvent
		public static void onServerStart(FMLServerStartedEvent event) {
			spawners.put(DimensionType.OVERWORLD_EFFECTS, new FossilPoachingGroupSpawner());
		}

		@SubscribeEvent
		public static void onServerStart(FMLServerStoppedEvent event) {
			spawners.clear();
		}

		@SubscribeEvent
		public static void onWorldTick(TickEvent.WorldTickEvent event) {
			if (event.phase != TickEvent.Phase.START)
				return;

			if (event.side != LogicalSide.SERVER)
				return;

			FossilPoachingGroupSpawner spawner = spawners.get(event.world.dimension().location());
			if (spawner != null) {
				spawner.tick((ServerWorld) event.world);
			}
		}
	}

	@EventBusSubscriber(modid = LostWorldsUtils.ID, bus = Bus.MOD)
	static class VanillaMaps {
		@SubscribeEvent
		public static void addToMaps(final FMLCommonSetupEvent event) {
			LostWorldsUtils.LOGGER.debug("Adding to Stripping Map");

			addToStrippingMap(LostWorldsBlocks.PETRIFIED_ARAUCARIA_LOG, LostWorldsBlocks.STRIPPED_PETRIFIED_ARAUCARIA_LOG);
			addToStrippingMap(LostWorldsBlocks.PETRIFIED_CALAMITES_LOG, LostWorldsBlocks.STRIPPED_PETRIFIED_CALAMITES_LOG);
			addToStrippingMap(LostWorldsBlocks.PETRIFIED_CONIFER_LOG, LostWorldsBlocks.STRIPPED_PETRIFIED_CONIFER_LOG);
			addToStrippingMap(LostWorldsBlocks.PETRIFIED_CYPRESS_LOG, LostWorldsBlocks.STRIPPED_PETRIFIED_CYPRESS_LOG);
			addToStrippingMap(LostWorldsBlocks.PETRIFIED_GINKGO_LOG, LostWorldsBlocks.STRIPPED_PETRIFIED_GINKGO_LOG);
			addToStrippingMap(LostWorldsBlocks.PETRIFIED_SEQUOIA_LOG, LostWorldsBlocks.STRIPPED_PETRIFIED_SEQUOIA_LOG);

			addToStrippingMap(LostWorldsBlocks.ARAUCARIA_LOG, LostWorldsBlocks.STRIPPED_ARAUCARIA_LOG);
			addToStrippingMap(LostWorldsBlocks.ARAUCARIA_WOOD, LostWorldsBlocks.STRIPPED_ARAUCARIA_WOOD);
			addToStrippingMap(LostWorldsBlocks.CALAMITES_LOG, LostWorldsBlocks.STRIPPED_CALAMITES_LOG);
			addToStrippingMap(LostWorldsBlocks.CALAMITES_WOOD, LostWorldsBlocks.STRIPPED_CALAMITES_WOOD);
			addToStrippingMap(LostWorldsBlocks.CONIFER_LOG, LostWorldsBlocks.STRIPPED_CONIFER_LOG);
			addToStrippingMap(LostWorldsBlocks.CONIFER_WOOD, LostWorldsBlocks.STRIPPED_CONIFER_WOOD);
			addToStrippingMap(LostWorldsBlocks.CYPRESS_LOG, LostWorldsBlocks.STRIPPED_CYPRESS_LOG);
			addToStrippingMap(LostWorldsBlocks.CYPRESS_WOOD, LostWorldsBlocks.STRIPPED_CYPRESS_WOOD);
			addToStrippingMap(LostWorldsBlocks.GINKGO_LOG, LostWorldsBlocks.STRIPPED_GINKGO_LOG);
			addToStrippingMap(LostWorldsBlocks.GINKGO_WOOD, LostWorldsBlocks.STRIPPED_GINKGO_WOOD);
			addToStrippingMap(LostWorldsBlocks.SCORCHED_LOG, LostWorldsBlocks.STRIPPED_SCORCHED_LOG);
			addToStrippingMap(LostWorldsBlocks.SCORCHED_WOOD, LostWorldsBlocks.STRIPPED_SCORCHED_WOOD);
			addToStrippingMap(LostWorldsBlocks.SEQUOIA_LOG, LostWorldsBlocks.STRIPPED_SEQUOIA_LOG);
			addToStrippingMap(LostWorldsBlocks.SEQUOIA_WOOD, LostWorldsBlocks.STRIPPED_SEQUOIA_WOOD);

			LostWorldsUtils.LOGGER.debug("Finished Adding to Stripping Map");

			LostWorldsUtils.LOGGER.debug("Adding to Tilling Map");

			addToTillingMap(LostWorldsBlocks.MOSSY_SOIL);

			LostWorldsUtils.LOGGER.debug("Finished Adding to Tilling Map");

			LostWorldsUtils.LOGGER.debug("Adding to Flattening Map");

			addToFlatteningMap(LostWorldsBlocks.MOSSY_SOIL);

			LostWorldsUtils.LOGGER.debug("Finished Adding to Flattening Map");

			LostWorldsUtils.LOGGER.debug("Adding Flammables");

			addToFlammables(LostWorldsBlocks.ARAUCARIA_FENCE, 5, 20);
			addToFlammables(LostWorldsBlocks.ARAUCARIA_FENCE_GATE, 5, 20);
			addToFlammables(LostWorldsBlocks.ARAUCARIA_LEAVES, 30, 60);
			addToFlammables(LostWorldsBlocks.ARAUCARIA_LOG, 5, 5);
			addToFlammables(LostWorldsBlocks.ARAUCARIA_PLANKS, 5, 20);
			addToFlammables(LostWorldsBlocks.ARAUCARIA_SLAB, 5, 20);
			addToFlammables(LostWorldsBlocks.ARAUCARIA_STAIRS, 5, 20);
			addToFlammables(LostWorldsBlocks.ARAUCARIA_WOOD, 5, 5);
			addToFlammables(LostWorldsBlocks.STRIPPED_ARAUCARIA_LOG, 5, 5);
			addToFlammables(LostWorldsBlocks.STRIPPED_ARAUCARIA_WOOD, 5, 5);

			addToFlammables(LostWorldsBlocks.CALAMITES_FENCE, 5, 20);
			addToFlammables(LostWorldsBlocks.CALAMITES_FENCE_GATE, 5, 20);
			addToFlammables(LostWorldsBlocks.CALAMITES_LEAVES, 30, 60);
			addToFlammables(LostWorldsBlocks.CALAMITES_LOG, 5, 5);
			addToFlammables(LostWorldsBlocks.CALAMITES_PLANKS, 5, 20);
			addToFlammables(LostWorldsBlocks.CALAMITES_SLAB, 5, 20);
			addToFlammables(LostWorldsBlocks.CALAMITES_STAIRS, 5, 20);
			addToFlammables(LostWorldsBlocks.CALAMITES_WOOD, 5, 5);
			addToFlammables(LostWorldsBlocks.STRIPPED_CALAMITES_LOG, 5, 5);
			addToFlammables(LostWorldsBlocks.STRIPPED_CALAMITES_WOOD, 5, 5);

			addToFlammables(LostWorldsBlocks.CONIFER_FENCE, 5, 20);
			addToFlammables(LostWorldsBlocks.CONIFER_FENCE_GATE, 5, 20);
			addToFlammables(LostWorldsBlocks.CONIFER_LEAVES, 30, 60);
			addToFlammables(LostWorldsBlocks.CONIFER_LOG, 5, 5);
			addToFlammables(LostWorldsBlocks.CONIFER_PLANKS, 5, 20);
			addToFlammables(LostWorldsBlocks.CONIFER_SLAB, 5, 20);
			addToFlammables(LostWorldsBlocks.CONIFER_STAIRS, 5, 20);
			addToFlammables(LostWorldsBlocks.CONIFER_WOOD, 5, 5);
			addToFlammables(LostWorldsBlocks.STRIPPED_CONIFER_LOG, 5, 5);
			addToFlammables(LostWorldsBlocks.STRIPPED_CONIFER_WOOD, 5, 5);

			addToFlammables(LostWorldsBlocks.CYPRESS_FENCE, 5, 20);
			addToFlammables(LostWorldsBlocks.CYPRESS_FENCE_GATE, 5, 20);
			addToFlammables(LostWorldsBlocks.CYPRESS_LEAVES, 30, 60);
			addToFlammables(LostWorldsBlocks.CYPRESS_LOG, 5, 5);
			addToFlammables(LostWorldsBlocks.CYPRESS_PLANKS, 5, 20);
			addToFlammables(LostWorldsBlocks.CYPRESS_SLAB, 5, 20);
			addToFlammables(LostWorldsBlocks.CYPRESS_STAIRS, 5, 20);
			addToFlammables(LostWorldsBlocks.CYPRESS_WOOD, 5, 5);
			addToFlammables(LostWorldsBlocks.STRIPPED_CYPRESS_LOG, 5, 5);
			addToFlammables(LostWorldsBlocks.STRIPPED_CYPRESS_WOOD, 5, 5);

			addToFlammables(LostWorldsBlocks.GINKGO_FENCE, 5, 20);
			addToFlammables(LostWorldsBlocks.GINKGO_FENCE_GATE, 5, 20);
			addToFlammables(LostWorldsBlocks.GINKGO_LEAVES, 30, 60);
			addToFlammables(LostWorldsBlocks.GINKGO_LOG, 5, 5);
			addToFlammables(LostWorldsBlocks.GINKGO_PLANKS, 5, 20);
			addToFlammables(LostWorldsBlocks.GINKGO_SLAB, 5, 20);
			addToFlammables(LostWorldsBlocks.GINKGO_STAIRS, 5, 20);
			addToFlammables(LostWorldsBlocks.GINKGO_WOOD, 5, 5);
			addToFlammables(LostWorldsBlocks.STRIPPED_GINKGO_LOG, 5, 5);
			addToFlammables(LostWorldsBlocks.STRIPPED_GINKGO_WOOD, 5, 5);

			addToFlammables(LostWorldsBlocks.SCORCHED_FENCE, 5, 20);
			addToFlammables(LostWorldsBlocks.SCORCHED_FENCE_GATE, 5, 20);
			addToFlammables(LostWorldsBlocks.SCORCHED_LOG, 5, 5);
			addToFlammables(LostWorldsBlocks.SCORCHED_PLANKS, 5, 20);
			addToFlammables(LostWorldsBlocks.SCORCHED_SLAB, 5, 20);
			addToFlammables(LostWorldsBlocks.SCORCHED_STAIRS, 5, 20);
			addToFlammables(LostWorldsBlocks.SCORCHED_WOOD, 5, 5);
			addToFlammables(LostWorldsBlocks.STRIPPED_SCORCHED_LOG, 5, 5);
			addToFlammables(LostWorldsBlocks.STRIPPED_SCORCHED_WOOD, 5, 5);

			addToFlammables(LostWorldsBlocks.SEQUOIA_FENCE, 5, 20);
			addToFlammables(LostWorldsBlocks.SEQUOIA_FENCE_GATE, 5, 20);
			addToFlammables(LostWorldsBlocks.SEQUOIA_LOG, 5, 5);
			addToFlammables(LostWorldsBlocks.SEQUOIA_PLANKS, 5, 20);
			addToFlammables(LostWorldsBlocks.SEQUOIA_SLAB, 5, 20);
			addToFlammables(LostWorldsBlocks.SEQUOIA_STAIRS, 5, 20);
			addToFlammables(LostWorldsBlocks.SEQUOIA_WOOD, 5, 5);
			addToFlammables(LostWorldsBlocks.STRIPPED_SEQUOIA_LOG, 5, 5);
			addToFlammables(LostWorldsBlocks.STRIPPED_SEQUOIA_WOOD, 5, 5);

			LostWorldsUtils.LOGGER.debug("Finished Adding Flammables");
		}

		private static void addToStrippingMap(Block logBlock, Block strippedLogBlock) {
			AxeItem.STRIPABLES = Maps.newHashMap(AxeItem.STRIPABLES);
			AxeItem.STRIPABLES.put(logBlock, strippedLogBlock);
		}

		private static void addToTillingMap(Block dirt) {
			HoeItem.TILLABLES = Maps.newHashMap(HoeItem.TILLABLES);
			HoeItem.TILLABLES.put(dirt, Blocks.FARMLAND.defaultBlockState());
		}

		private static void addToFlatteningMap(Block dirt) {
			ShovelItem.FLATTENABLES = Maps.newHashMap(ShovelItem.FLATTENABLES);
			ShovelItem.FLATTENABLES.put(dirt, Blocks.GRASS_PATH.defaultBlockState());
		}

		private static void addToFlammables(Block burnable, int catchFlame, int burn) {
			FireBlock fireblock = (FireBlock) Blocks.FIRE;
			fireblock.setFlammable(burnable, catchFlame, burn);
		}
	}
}
