package lostworlds.server.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import com.tterrag.registrate.util.entry.BlockEntry;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lostworlds.server.LostWorldsConfig;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.biome.LostWorldsConfiguredFeatures;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.block.NautilusShellBlock;
import lostworlds.server.block.Plants;
import lostworlds.server.block.Trees;
import lostworlds.server.entity.LostWorldsVillagerProfessions;
import lostworlds.server.entity.illager.FossilPoacherEntity;
import lostworlds.server.entity.spawner.FossilPoachingGroupSpawner;
import lostworlds.server.entity.utils.enums.DinoTypes;
import lostworlds.server.item.LostWorldsItems;
import lostworlds.server.item.SyringeItem;
import lostworlds.server.structure.LostWorldsStructures;
import lostworlds.server.trades.EmeraldsForMultiItemTrade;
import lostworlds.server.trades.MultiItemForEmeraldsTrade;
import lostworlds.server.util.JigsawUtils;
import lostworlds.server.util.registrate.WoodTypes;
import lostworlds.server.world.BiomeGen;
import lostworlds.server.world.EntityGen;
import lostworlds.server.world.FeatureGen;
import lostworlds.server.world.StructureGen;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
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
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.MapDecoration;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppedEvent;

public class ServerSetup {
	@EventBusSubscriber(modid = LostWorldsUtils.ID, bus = Bus.MOD)
	static class Registry {
		@SubscribeEvent
		public static void registerFeatures(Register<Feature<?>> event) {
			LostWorldsConfiguredFeatures.init();
		}
	}

	@EventBusSubscriber
	static class AddVillageStructures {
		@SubscribeEvent
		public static void onServerAboutToStartEvent(FMLServerAboutToStartEvent event) {
			if (LostWorldsConfig.COMMON_CONFIG.villageStructures.get()) {
				// Plains Village Structures
				JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/plains/houses"), LostWorldsUtils.rL("village/plains/plains_archaeologist_hut"), LostWorldsConfig.COMMON_CONFIG.villageStructureWeights.get());
				JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/plains/houses"), LostWorldsUtils.rL("village/plains/plains_paleontology_hut"), LostWorldsConfig.COMMON_CONFIG.villageStructureWeights.get());

				// Taiga Village Structures
				JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/taiga/houses"), LostWorldsUtils.rL("village/taiga/taiga_archaeologist_hut"), LostWorldsConfig.COMMON_CONFIG.villageStructureWeights.get());
				JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/taiga/houses"), LostWorldsUtils.rL("village/taiga/taiga_paleontology_hut"), LostWorldsConfig.COMMON_CONFIG.villageStructureWeights.get());

				// Savanna Village Structures
				JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/savanna/houses"), LostWorldsUtils.rL("village/savanna/savanna_archaeologist_hut"), LostWorldsConfig.COMMON_CONFIG.villageStructureWeights.get());
				JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/savanna/houses"), LostWorldsUtils.rL("village/savanna/savanna_paleontology_hut"), LostWorldsConfig.COMMON_CONFIG.villageStructureWeights.get());

				// Snowy Village Structures
				JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/snowy/houses"), LostWorldsUtils.rL("village/snowy/snowy_archaeologist_hut"), LostWorldsConfig.COMMON_CONFIG.villageStructureWeights.get());
				JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/snowy/houses"), LostWorldsUtils.rL("village/snowy/snowy_paleontology_hut"), LostWorldsConfig.COMMON_CONFIG.villageStructureWeights.get());

				// Desert Village Structures
				JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/desert/houses"), LostWorldsUtils.rL("village/desert/desert_archaeologist_hut"), LostWorldsConfig.COMMON_CONFIG.villageStructureWeights.get());
				JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/desert/houses"), LostWorldsUtils.rL("village/desert/desert_paleontology_hut"), LostWorldsConfig.COMMON_CONFIG.villageStructureWeights.get());
			}
		}
	}

	@EventBusSubscriber(modid = LostWorldsUtils.ID, bus = Bus.MOD)
	static class ModVillagerTrades {
		public static void fillTradeData() {
			// Archaeologist
			VillagerTrades.ITrade[] archaeology1 = new VillagerTrades.ITrade[] { new VillagerTrades.ItemsForEmeraldsTrade(Items.CLAY_BALL, 2, 20, 2), new VillagerTrades.ItemsForEmeraldsTrade(Items.CLAY, 16, 10, 5), new VillagerTrades.ItemsForEmeraldsTrade(LostWorldsBlocks.ARCHAEOLOGY_TABLE.get(), 15, 1, 12, 3), };
			VillagerTrades.ITrade[] archaeology2 = new VillagerTrades.ITrade[] { new VillagerTrades.ItemsForEmeraldsTrade(LostWorldsItems.WET_PAPER.get(), 3, 5, 4) };
			VillagerTrades.ITrade[] archaeology3 = new VillagerTrades.ITrade[] { new MultiItemForEmeraldsTrade(ImmutableList.of(LostWorldsItems.GOLD_BRUSH.get(), LostWorldsItems.IRON_BRUSH.get(), LostWorldsItems.LEATHER_BRUSH.get()), ImmutableList.of(1, 1, 1), ImmutableList.of(32, 16, 4), 1, 36) };
			VillagerTrades.ITrade[] archaeology4 = new VillagerTrades.ITrade[] { new VillagerTrades.ItemsForEmeraldsTrade(LostWorldsItems.PERMIAN_PERIOD_TIME_BOOK.get(), 1, 1, 36), new VillagerTrades.ItemsForEmeraldsTrade(LostWorldsItems.JURASSIC_PERIOD_TIME_BOOK.get(), 64, 1, 36), new VillagerTrades.ItemsForEmeraldsTrade(LostWorldsItems.CRETACEOUS_PERIOD_TIME_BOOK.get(), 64, 1, 36) };
			VillagerTrades.ITrade[] archaeology5 = new VillagerTrades.ITrade[] { new VillagerTrades.ItemsForEmeraldsTrade(Items.LODESTONE, 64, 1, 36), new EmeraldsForMultiItemTrade(ImmutableList.of(LostWorldsItems.DIAMOND_BRUSH.get(), LostWorldsItems.CLOTH_MASK.get(), LostWorldsItems.CRYSTAL_SCARAB_BRUSH.get(), LostWorldsItems.NETHERITE_BRUSH.get()), ImmutableList.of(1, 1, 1, 1), ImmutableList.of(32, 5, 64, 48), 1, 36), new MultiItemForEmeraldsTrade(ImmutableList.of(Items.GOLD_INGOT, Items.DIAMOND, Items.IRON_INGOT, Items.NETHERITE_SCRAP, LostWorldsItems.CHARGED_CRYSTAL_SCARAB_GEM.get(), LostWorldsItems.CRYSTAL_SCARAB_BOTTOM_LEFT_LEG.get(), LostWorldsItems.CRYSTAL_SCARAB_BOTTOM_RIGHT_LEG.get(), LostWorldsItems.CRYSTAL_SCARAB_THORAX.get(), LostWorldsItems.CRYSTAL_SCARAB_TOP_LEFT_LEG.get(), LostWorldsItems.CRYSTAL_SCARAB_TOP_RIGHT_LEG.get()), ImmutableList.of(5, 1, 3, 1, 1), ImmutableList.of(10, 30, 20, 50, 64), 1, 36) };

			// Paleontologist
			VillagerTrades.ITrade[] paleontology1 = new VillagerTrades.ITrade[] { new VillagerTrades.EmeraldForItemsTrade(LostWorldsBlocks.TINY_FOSSILISED_EGG.get(), 4, 4, 10), new VillagerTrades.EmeraldForItemsTrade(LostWorldsBlocks.SMALL_PLASTERED_FOSSILISED_EGG.get(), 5, 4, 10), new VillagerTrades.ItemsForEmeraldsTrade(LostWorldsBlocks.PALEONTOLOGY_TABLE.get(), 4, 1, 12, 36) };

			VillagerTrades.ITrade[] paleontology2 = new VillagerTrades.ITrade[] { new VillagerTrades.EmeraldForItemsTrade(LostWorldsBlocks.MEDIUM_PLASTERED_FOSSILISED_EGG.get(), 6, 4, 10), new VillagerTrades.ItemsForEmeraldsTrade(LostWorldsItems.FIELD_GUIDE.get(), 25, 1, 20), new VillagerTrades.ItemsForEmeraldsTrade(LostWorldsItems.WET_PAPER.get(), 3, 5, 12), new VillagerTrades.EmeraldForMapTrade(4, LostWorldsStructures.SURFACE_FOSSIL.get(), MapDecoration.Type.RED_MARKER, 1, 42), new VillagerTrades.EmeraldForMapTrade(4, LostWorldsStructures.SUBTERRANEAN_FOSSIL.get(), MapDecoration.Type.BANNER_RED, 1, 42) };

			VillagerTrades.ITrade[] paleontology3 = new VillagerTrades.ITrade[] { new VillagerTrades.EmeraldForItemsTrade(LostWorldsBlocks.LARGE_FOSSILISED_EGG.get(), 7, 2, 15), new VillagerTrades.ItemsForEmeraldsTrade(LostWorldsItems.HAMMER.get(), 16, 1, 20) };

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

			VillagerTrades.TRADES.put(LostWorldsVillagerProfessions.ARCHAEOLOGIST.get(), toIntMap(ImmutableMap.of(1, archaeology1, 2, archaeology2, 3, archaeology3, 4, archaeology4, 5, archaeology5)));
			VillagerTrades.TRADES.put(LostWorldsVillagerProfessions.PALEONTOLOGIST.get(), toIntMap(ImmutableMap.of(1, paleontology1, 2, paleontology2, 3, paleontology3, 4, paleontology4, 5, paleontology5)));
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
						world.setBlockAndUpdate(pos, LostWorldsBlocks.NAUTILUS_SHELL.getDefaultState().setValue(NautilusShellBlock.HORIZONTAL_FACING, direction));
						world.playSound(entity, pos, LostWorldsBlocks.NAUTILUS_SHELL.getDefaultState().getSoundType().getPlaceSound(), SoundCategory.BLOCKS, 1.0F, 1.0F);
					}
				}

				if (entity.isCrouching()) {
					if (world.getBlockState(pos.below()).isFaceSturdy(world, pos, direction)) {
						entity.swing(hand);
						if (!entity.isCreative()) {
							stack.shrink(1);
						}
						world.setBlockAndUpdate(pos, LostWorldsBlocks.NAUTILUS_SHELL.getDefaultState().setValue(NautilusShellBlock.HORIZONTAL_FACING, direction));
						world.playSound(entity, pos, LostWorldsBlocks.NAUTILUS_SHELL.getDefaultState().getSoundType().getPlaceSound(), SoundCategory.BLOCKS, 1.0F, 1.0F);
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
			if (event.getEntity() instanceof VillagerEntity) {
				VillagerEntity villager = (VillagerEntity) event.getEntity();
				villager.goalSelector.addGoal(1, new AvoidEntityGoal(villager, FossilPoacherEntity.class, 16.0F, 0.7D, 0.7D));
			}
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

	@EventBusSubscriber(modid = LostWorldsUtils.ID, bus = Bus.FORGE)
	static class BiomeHandler {
		@SubscribeEvent(priority = EventPriority.HIGH)
		public static void biomeStuff(BiomeLoadingEvent event) {
			// Biomes
			BiomeGen.init(event);

			// Spawns
			EntityGen.init(event);

			// Features
			FeatureGen.init(event);

			// Structures
			StructureGen.init(event);
		}
	}

	@EventBusSubscriber(modid = LostWorldsUtils.ID, bus = Bus.MOD)
	static class VanillaMaps {
		@SubscribeEvent
		public static void addToMaps(final FMLCommonSetupEvent event) {
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
		}

		private static void addToComposterMap(BlockEntry<? extends Block> compostable, float chance) {
			ComposterBlock.add(chance, compostable.asStack().getItem());
		}

		private static void addToStrippingMap(BlockEntry<? extends Block> logBlock, BlockEntry<? extends Block> strippedLogBlock) {
			AxeItem.STRIPABLES = Maps.newHashMap(AxeItem.STRIPABLES);
			AxeItem.STRIPABLES.put(logBlock.get(), strippedLogBlock.get());
		}

		private static void addToTillingMap(BlockEntry<? extends Block> dirt) {
			HoeItem.TILLABLES = Maps.newHashMap(HoeItem.TILLABLES);
			HoeItem.TILLABLES.put(dirt.get(), Blocks.FARMLAND.defaultBlockState());
		}

		private static void addToFlatteningMap(BlockEntry<? extends Block> dirt) {
			ShovelItem.FLATTENABLES = Maps.newHashMap(ShovelItem.FLATTENABLES);
			ShovelItem.FLATTENABLES.put(dirt.get(), Blocks.GRASS_PATH.defaultBlockState());
		}

		private static void addToFlammables(BlockEntry<? extends Block> burnable, int catchFlame, int burn) {
			FireBlock fireblock = (FireBlock) Blocks.FIRE;
			fireblock.setFlammable(burnable.get(), catchFlame, burn);
		}
	}
}
