package lostworlds.content.server.setup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lostworlds.content.ModUtils;
import lostworlds.content.server.init.BlockInit;
import lostworlds.content.server.init.ItemInit;
import lostworlds.content.server.init.VillagerProfessionInit;
import lostworlds.library.block.NautilusShellBlock;
import lostworlds.library.block.Plants;
import lostworlds.library.block.Trees;
import lostworlds.library.entity.DinoTypes;
import lostworlds.library.entity.illager.FossilPoacherEntity;
import lostworlds.library.entity.spawner.FossilPoachingGroupSpawner;
import lostworlds.library.item.CrystalScarabGemItem;
import lostworlds.library.trades.EmeraldsForMultiItemTrade;
import lostworlds.library.trades.MultiItemForEmeraldsTrade;
import lostworlds.library.util.JigsawUtils;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
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

public class ServerSetup 
{
	@EventBusSubscriber
	static class AddVillageStructures
	{
		@SubscribeEvent
		public static void onServerAboutToStartEvent(FMLServerAboutToStartEvent event)
		{
			ModUtils.LOGGER.debug("Loading: Village Structures");

			//Plains Village Structures
			JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/plains/houses"), ModUtils.rL("village/plains/plains_archaeologist_hut"), 25);
			
			//Taiga Village Structures
			JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/taiga/houses"), ModUtils.rL("village/taiga/taiga_archaeologist_hut"), 25);

			//Savanna Village Structures
			JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/savanna/houses"), ModUtils.rL("village/savanna/savanna_archaeologist_hut"), 25);
			
			//Snowy Village Structures
			JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/snowy/houses"), ModUtils.rL("village/snowy/snowy_archaeologist_hut"), 25);
			
			//Desert Village Structures
			JigsawUtils.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/desert/houses"), ModUtils.rL("village/desert/desert_archaeologist_hut"), 25);
		
			ModUtils.LOGGER.debug("Finished: Village Structures");
		}
	}
	
	@EventBusSubscriber(modid = ModUtils.ID, bus = Bus.MOD)
	static class ModVillagerTrades 
	{
		public static void fillTradeData() 
		{
			ModUtils.LOGGER.debug("Loading: Villager Trades");
			
			//Archaeologist
			VillagerTrades.ITrade[] archaeology1 = new VillagerTrades.ITrade[] 
			{
				new VillagerTrades.ItemsForEmeraldsTrade(Items.CLAY_BALL, 2, 20, 12),
				new VillagerTrades.ItemsForEmeraldsTrade(Items.CLAY, 16, 10, 12),
				new VillagerTrades.ItemsForEmeraldsTrade(BlockInit.ARCHAEOLOGY_TABLE.asItem(), 15, 1, 20),
			};
			VillagerTrades.ITrade[] archaeology2 = new VillagerTrades.ITrade[] 
			{
				new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.WET_PAPER, 3, 5, 12)
			};
			VillagerTrades.ITrade[] archaeology3 = new VillagerTrades.ITrade[] 
			{
				new MultiItemForEmeraldsTrade(ImmutableList.of(ItemInit.GOLD_BRUSH, ItemInit.IRON_BRUSH, ItemInit.LEATHER_BRUSH), ImmutableList.of(1, 1, 1), ImmutableList.of(32, 16, 4), 1, 100)
			};
			VillagerTrades.ITrade[] archaeology4 = new VillagerTrades.ITrade[] 
			{
				new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.PERMIAN_PERIOD_TIME_BOOK, 1, 1, 100),
				new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.JURASSIC_PERIOD_TIME_BOOK, 64, 1, 100),
				new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.CRETACEOUS_PERIOD_TIME_BOOK, 64, 1, 100)
			};
			VillagerTrades.ITrade[] archaeology5 = new VillagerTrades.ITrade[] 
			{
				new VillagerTrades.ItemsForEmeraldsTrade(Items.LODESTONE, 64, 1, 100),
				new EmeraldsForMultiItemTrade(ImmutableList.of(ItemInit.DIAMOND_BRUSH, ItemInit.CLOTH_MASK, ItemInit.CRYSTAL_SCARAB_BRUSH, ItemInit.NETHERITE_BRUSH), ImmutableList.of(1, 1, 1, 1), ImmutableList.of(32, 5, 64, 48), 1, 100),
				new MultiItemForEmeraldsTrade(ImmutableList.of(Items.GOLD_INGOT, Items.DIAMOND, Items.IRON_INGOT, Items.NETHERITE_SCRAP, CrystalScarabGemItem.Gems.CHARGED_CRYSTAL_SCARAB_GEM.getItem(), CrystalScarabGemItem.Gems.CRYSTAL_SCARAB_BOTTOM_LEFT_LEG.getItem(), CrystalScarabGemItem.Gems.CRYSTAL_SCARAB_BOTTOM_RIGHT_LEG.getItem(), CrystalScarabGemItem.Gems.CRYSTAL_SCARAB_THORAX.getItem(), CrystalScarabGemItem.Gems.CRYSTAL_SCARAB_TOP_LEFT_LEG.getItem(), CrystalScarabGemItem.Gems.CRYSTAL_SCARAB_TOP_RIGHT_LEG.getItem()), ImmutableList.of(5, 1, 3, 1, 1), ImmutableList.of(10, 30, 20, 50, 64), 1, 100)
			};
			
			//Paleontologist
			VillagerTrades.ITrade[] paleontology1 = new VillagerTrades.ITrade[] 
			{
				new VillagerTrades.EmeraldForItemsTrade(BlockInit.TINY_FOSSILISED_EGG.asItem(), 4, 4, 10),
				new VillagerTrades.EmeraldForItemsTrade(BlockInit.SMALL_PLASTERED_FOSSILISED_EGG.asItem(), 5, 4, 10),
				new MultiItemForEmeraldsTrade(ImmutableList.of(BlockInit.ACACIA_PALEONTOLOGY_TABLE.asItem(), BlockInit.ARAUCARIA_PALEONTOLOGY_TABLE.asItem(), BlockInit.BIRCH_PALEONTOLOGY_TABLE.asItem(), BlockInit.CALAMITES_PALEONTOLOGY_TABLE.asItem(), BlockInit.CONIFER_PALEONTOLOGY_TABLE.asItem(), BlockInit.CRIMSON_PALEONTOLOGY_TABLE.asItem(), BlockInit.CYPRESS_PALEONTOLOGY_TABLE.asItem(), BlockInit.DARK_OAK_PALEONTOLOGY_TABLE.asItem(), BlockInit.GINKGO_PALEONTOLOGY_TABLE.asItem(), BlockInit.JUNGLE_PALEONTOLOGY_TABLE.asItem(), BlockInit.OAK_PALEONTOLOGY_TABLE.asItem(), BlockInit.SCORCHED_PALEONTOLOGY_TABLE.asItem(), BlockInit.SEQUOIA_PALEONTOLOGY_TABLE.asItem(), BlockInit.SPRUCE_PALEONTOLOGY_TABLE.asItem(), BlockInit.WARPED_PALEONTOLOGY_TABLE.asItem()), ImmutableList.of(1, 1, 1, 1, 1, 1, 1, 1, 1), ImmutableList.of(10, 10, 10, 10, 10, 10, 10, 10, 10), 5, 20)
			};
			
			VillagerTrades.ITrade[] paleontology2 = new VillagerTrades.ITrade[] 
			{
				new VillagerTrades.EmeraldForItemsTrade(BlockInit.MEDIUM_PLASTERED_FOSSILISED_EGG.asItem(), 6, 4, 10),
				new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.FIELD_GUIDE, 25, 1, 20),
				new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.WET_PAPER, 3, 5, 12)
			};
			
			VillagerTrades.ITrade[] paleontology3 = new VillagerTrades.ITrade[] 
			{
				new VillagerTrades.EmeraldForItemsTrade(BlockInit.LARGE_FOSSILISED_EGG.asItem(), 7, 2, 15),
				new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.HAMMER, 16, 1, 20)
			};
			
			VillagerTrades.ITrade[] paleontology4 = new VillagerTrades.ITrade[] 
			{
				new MultiItemForEmeraldsTrade(ImmutableList.of(ItemInit.DIAMOND_BRUSH, ItemInit.GOLD_BRUSH, ItemInit.IRON_BRUSH, ItemInit.LEATHER_BRUSH), ImmutableList.of(1, 1, 1, 1), ImmutableList.of(64, 32, 16, 4), 1, 100)
			};
			
			ArrayList<Item> dnaForSale = Lists.newArrayList();
			for(Trees trees : Trees.values())
			{
				dnaForSale.add(trees.getDNA());
			}
			for(Plants plants : Plants.values())
			{
				dnaForSale.add(plants.getDNA());
			}
			for(DinoTypes dinos : DinoTypes.values())
			{
				dnaForSale.add(dinos.getDNA());
			}
			VillagerTrades.ITrade[] paleontology5 = new VillagerTrades.ITrade[] 
			{
				new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.CONTRACEPTIVES, 12, 5, 20),
				new MultiItemForEmeraldsTrade(dnaForSale, ImmutableList.of(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1), ImmutableList.of(64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64), 1, 200)
			};
			VillagerTrades.TRADES.put(VillagerProfessionInit.ARCHAEOLOGIST, toIntMap(ImmutableMap.of(1, archaeology1, 2, archaeology2, 3, archaeology3, 4, archaeology4, 5, archaeology5)));
			VillagerTrades.TRADES.put(VillagerProfessionInit.PALEONTOLOGIST, toIntMap(ImmutableMap.of(1, paleontology1, 2, paleontology2, 3, paleontology3, 4, paleontology4, 5, paleontology5)));

			ModUtils.LOGGER.debug("Finished: Villager Trades");
		}
		
		private static Int2ObjectMap<VillagerTrades.ITrade[]> toIntMap(ImmutableMap<Integer, VillagerTrades.ITrade[]> tradeMap) 
		{
			return new Int2ObjectOpenHashMap<>(tradeMap);
		}
		
		@SubscribeEvent
		public static void initVillagerTradesArray(FMLCommonSetupEvent event)
		{
			event.enqueueWork(() -> 
			{
				ModVillagerTrades.fillTradeData();
			});
		}
	}
	
	@EventBusSubscriber(modid = ModUtils.ID, bus = Bus.FORGE)
	static class AddBlockItem
	{
		@SubscribeEvent
		public static void addNautilusShell(RightClickBlock event)
		{
			PlayerEntity entity = event.getPlayer();
			Hand hand = event.getHand();
			ItemStack stack = entity.getItemInHand(hand);
			Item item = stack.getItem();
			World world = event.getWorld();
			BlockPos pos = event.getPos().above();
			Direction direction = entity.getDirection().getOpposite();
			
			if(item == Items.NAUTILUS_SHELL)
			{	
				entity.swing(hand);
				if(!entity.isCreative())
				{
					stack.shrink(1);
				}
				world.setBlockAndUpdate(pos, BlockInit.NAUTILUS_SHELL.defaultBlockState().setValue(NautilusShellBlock.HORIZONTAL_FACING, direction));
				world.playSound(entity, pos, BlockInit.NAUTILUS_SHELL.getSoundType(BlockInit.NAUTILUS_SHELL.defaultBlockState()).getPlaceSound(), SoundCategory.BLOCKS, 1.0F, 1.0F);
			}
		}
	}
	
	@EventBusSubscriber(bus = Bus.MOD)
	static class IllagerSetup
	{
		@SubscribeEvent
		public void onEntityJoin(EntityJoinWorldEvent event) 
		{
			ModUtils.LOGGER.debug("Loading: Adding Goals");

			if(event.getEntity() instanceof VillagerEntity) 
			{
				VillagerEntity villager = (VillagerEntity) event.getEntity(); 
				villager.goalSelector.addGoal(1, new AvoidEntityGoal(villager, FossilPoacherEntity.class, 16.0F, 0.7D, 0.7D));
			}
			ModUtils.LOGGER.debug("Finished: Adding Goals");
		}
	}
	
	@EventBusSubscriber(modid = ModUtils.ID)
	static class SpawnerHandler
	{
		private static Map<ResourceLocation, FossilPoachingGroupSpawner> spawners = new HashMap<>();

	    @SubscribeEvent
	    public static void onServerStart(FMLServerStartedEvent event)
	    {
	        spawners.put(DimensionType.OVERWORLD_EFFECTS, new FossilPoachingGroupSpawner());
	    }

	    @SubscribeEvent
	    public static void onServerStart(FMLServerStoppedEvent event)
	    {
	        spawners.clear();
	    }

	    @SubscribeEvent
	    public static void onWorldTick(TickEvent.WorldTickEvent event)
	    {
	        if(event.phase != TickEvent.Phase.START)
	            return;

	        if(event.side != LogicalSide.SERVER)
	            return;

	        FossilPoachingGroupSpawner spawner = spawners.get(event.world.dimension().location());
	        if(spawner != null)
	        {
	            spawner.tick((ServerWorld) event.world);
	        }
	    }
	}

	@EventBusSubscriber(modid = ModUtils.ID, bus = Bus.MOD)
	static class VanillaMaps
	{
		@SubscribeEvent
		public static void addToMaps(final FMLCommonSetupEvent event)
		{
			ModUtils.LOGGER.debug("Adding to Stripping Map");
			
			addToStrippingMap(BlockInit.PETRIFIED_ARAUCARIA_LOG, BlockInit.STRIPPED_PETRIFIED_ARAUCARIA_LOG);
			addToStrippingMap(BlockInit.PETRIFIED_CALAMITES_LOG, BlockInit.STRIPPED_PETRIFIED_CALAMITES_LOG);
			addToStrippingMap(BlockInit.PETRIFIED_CONIFER_LOG, BlockInit.STRIPPED_PETRIFIED_CONIFER_LOG);
			addToStrippingMap(BlockInit.PETRIFIED_CYPRESS_LOG, BlockInit.STRIPPED_PETRIFIED_CYPRESS_LOG);
			addToStrippingMap(BlockInit.PETRIFIED_GINKGO_LOG, BlockInit.STRIPPED_PETRIFIED_GINKGO_LOG);
			addToStrippingMap(BlockInit.PETRIFIED_SEQUOIA_LOG, BlockInit.STRIPPED_PETRIFIED_SEQUOIA_LOG);
			
			addToStrippingMap(BlockInit.ARAUCARIA_LOG, BlockInit.STRIPPED_ARAUCARIA_LOG);
			addToStrippingMap(BlockInit.ARAUCARIA_WOOD, BlockInit.STRIPPED_ARAUCARIA_WOOD);
			addToStrippingMap(BlockInit.CALAMITES_LOG, BlockInit.STRIPPED_CALAMITES_LOG);
			addToStrippingMap(BlockInit.CALAMITES_WOOD, BlockInit.STRIPPED_CALAMITES_WOOD);
			addToStrippingMap(BlockInit.CONIFER_LOG, BlockInit.STRIPPED_CONIFER_LOG);
			addToStrippingMap(BlockInit.CONIFER_WOOD, BlockInit.STRIPPED_CONIFER_WOOD);
			addToStrippingMap(BlockInit.CYPRESS_LOG, BlockInit.STRIPPED_CYPRESS_LOG);
			addToStrippingMap(BlockInit.CYPRESS_WOOD, BlockInit.STRIPPED_CYPRESS_WOOD);
			addToStrippingMap(BlockInit.GINKGO_LOG, BlockInit.STRIPPED_GINKGO_LOG);
			addToStrippingMap(BlockInit.GINKGO_WOOD, BlockInit.STRIPPED_GINKGO_WOOD);
			addToStrippingMap(BlockInit.SCORCHED_LOG, BlockInit.STRIPPED_SCORCHED_LOG);
			addToStrippingMap(BlockInit.SCORCHED_WOOD, BlockInit.STRIPPED_SCORCHED_WOOD);
			addToStrippingMap(BlockInit.SEQUOIA_LOG, BlockInit.STRIPPED_SEQUOIA_LOG);
			addToStrippingMap(BlockInit.SEQUOIA_WOOD, BlockInit.STRIPPED_SEQUOIA_WOOD);
			
			ModUtils.LOGGER.debug("Finished Adding to Stripping Map");
			
			ModUtils.LOGGER.debug("Adding to Tilling Map");
			
			addToTillingMap(BlockInit.MOSSY_SOIL);
			
			ModUtils.LOGGER.debug("Finished Adding to Tilling Map");
			
			ModUtils.LOGGER.debug("Adding to Flattening Map");
			
			addToFlatteningMap(BlockInit.MOSSY_SOIL);
			
			ModUtils.LOGGER.debug("Finished Adding to Flattening Map");
			
			ModUtils.LOGGER.debug("Adding Flammables");

			addToFlammables(BlockInit.ARAUCARIA_FENCE, 5, 20);
			addToFlammables(BlockInit.ARAUCARIA_FENCE_GATE, 5, 20);
			addToFlammables(BlockInit.ARAUCARIA_LEAVES, 30, 60);
			addToFlammables(BlockInit.ARAUCARIA_LOG, 5, 5);
			addToFlammables(BlockInit.ARAUCARIA_PLANKS, 5, 20);
			addToFlammables(BlockInit.ARAUCARIA_SLAB, 5, 20);
			addToFlammables(BlockInit.ARAUCARIA_STAIRS, 5, 20);
			addToFlammables(BlockInit.ARAUCARIA_WOOD, 5, 5);
			addToFlammables(BlockInit.STRIPPED_ARAUCARIA_LOG, 5, 5);
			addToFlammables(BlockInit.STRIPPED_ARAUCARIA_WOOD, 5, 5);

			addToFlammables(BlockInit.CALAMITES_FENCE, 5, 20);
			addToFlammables(BlockInit.CALAMITES_FENCE_GATE, 5, 20);
			addToFlammables(BlockInit.CALAMITES_LEAVES, 30, 60);
			addToFlammables(BlockInit.CALAMITES_LOG, 5, 5);
			addToFlammables(BlockInit.CALAMITES_PLANKS, 5, 20);
			addToFlammables(BlockInit.CALAMITES_SLAB, 5, 20);
			addToFlammables(BlockInit.CALAMITES_STAIRS, 5, 20);
			addToFlammables(BlockInit.CALAMITES_WOOD, 5, 5);
			addToFlammables(BlockInit.STRIPPED_CALAMITES_LOG, 5, 5);
			addToFlammables(BlockInit.STRIPPED_CALAMITES_WOOD, 5, 5);

			addToFlammables(BlockInit.CONIFER_FENCE, 5, 20);
			addToFlammables(BlockInit.CONIFER_FENCE_GATE, 5, 20);
			addToFlammables(BlockInit.CONIFER_LEAVES, 30, 60);
			addToFlammables(BlockInit.CONIFER_LOG, 5, 5);
			addToFlammables(BlockInit.CONIFER_PLANKS, 5, 20);
			addToFlammables(BlockInit.CONIFER_SLAB, 5, 20);
			addToFlammables(BlockInit.CONIFER_STAIRS, 5, 20);
			addToFlammables(BlockInit.CONIFER_WOOD, 5, 5);
			addToFlammables(BlockInit.STRIPPED_CONIFER_LOG, 5, 5);
			addToFlammables(BlockInit.STRIPPED_CONIFER_WOOD, 5, 5);

			addToFlammables(BlockInit.CYPRESS_FENCE, 5, 20);
			addToFlammables(BlockInit.CYPRESS_FENCE_GATE, 5, 20);
			addToFlammables(BlockInit.CYPRESS_LEAVES, 30, 60);
			addToFlammables(BlockInit.CYPRESS_LOG, 5, 5);
			addToFlammables(BlockInit.CYPRESS_PLANKS, 5, 20);
			addToFlammables(BlockInit.CYPRESS_SLAB, 5, 20);
			addToFlammables(BlockInit.CYPRESS_STAIRS, 5, 20);
			addToFlammables(BlockInit.CYPRESS_WOOD, 5, 5);
			addToFlammables(BlockInit.STRIPPED_CYPRESS_LOG, 5, 5);
			addToFlammables(BlockInit.STRIPPED_CYPRESS_WOOD, 5, 5);

			addToFlammables(BlockInit.GINKGO_FENCE, 5, 20);
			addToFlammables(BlockInit.GINKGO_FENCE_GATE, 5, 20);
			addToFlammables(BlockInit.GINKGO_LEAVES, 30, 60);
			addToFlammables(BlockInit.GINKGO_LOG, 5, 5);
			addToFlammables(BlockInit.GINKGO_PLANKS, 5, 20);
			addToFlammables(BlockInit.GINKGO_SLAB, 5, 20);
			addToFlammables(BlockInit.GINKGO_STAIRS, 5, 20);
			addToFlammables(BlockInit.GINKGO_WOOD, 5, 5);
			addToFlammables(BlockInit.STRIPPED_GINKGO_LOG, 5, 5);
			addToFlammables(BlockInit.STRIPPED_GINKGO_WOOD, 5, 5);

			addToFlammables(BlockInit.SCORCHED_FENCE, 5, 20);
			addToFlammables(BlockInit.SCORCHED_FENCE_GATE, 5, 20);
			addToFlammables(BlockInit.SCORCHED_LOG, 5, 5);
			addToFlammables(BlockInit.SCORCHED_PLANKS, 5, 20);
			addToFlammables(BlockInit.SCORCHED_SLAB, 5, 20);
			addToFlammables(BlockInit.SCORCHED_STAIRS, 5, 20);
			addToFlammables(BlockInit.SCORCHED_WOOD, 5, 5);
			addToFlammables(BlockInit.STRIPPED_SCORCHED_LOG, 5, 5);
			addToFlammables(BlockInit.STRIPPED_SCORCHED_WOOD, 5, 5);

			addToFlammables(BlockInit.SEQUOIA_FENCE, 5, 20);
			addToFlammables(BlockInit.SEQUOIA_FENCE_GATE, 5, 20);
			addToFlammables(BlockInit.SEQUOIA_LOG, 5, 5);
			addToFlammables(BlockInit.SEQUOIA_PLANKS, 5, 20);
			addToFlammables(BlockInit.SEQUOIA_SLAB, 5, 20);
			addToFlammables(BlockInit.SEQUOIA_STAIRS, 5, 20);
			addToFlammables(BlockInit.SEQUOIA_WOOD, 5, 5);
			addToFlammables(BlockInit.STRIPPED_SEQUOIA_LOG, 5, 5);
			addToFlammables(BlockInit.STRIPPED_SEQUOIA_WOOD, 5, 5);
			
			ModUtils.LOGGER.debug("Finished Adding Flammables");
		}
		
		private static void addToStrippingMap(Block logBlock, Block strippedLogBlock)
		{
			AxeItem.STRIPABLES = Maps.newHashMap(AxeItem.STRIPABLES);
			AxeItem.STRIPABLES.put(logBlock, strippedLogBlock);
		}
		
		private static void addToTillingMap(Block dirt)
		{
			HoeItem.TILLABLES = Maps.newHashMap(HoeItem.TILLABLES);
			HoeItem.TILLABLES.put(dirt, Blocks.FARMLAND.defaultBlockState());
		}
		
		private static void addToFlatteningMap(Block dirt)
		{
			ShovelItem.FLATTENABLES = Maps.newHashMap(ShovelItem.FLATTENABLES);
			ShovelItem.FLATTENABLES.put(dirt, Blocks.GRASS_PATH.defaultBlockState());
		}
		
		private static void addToFlammables(Block burnable, int catchFlame, int burn)
		{
			FireBlock fireblock = (FireBlock)Blocks.FIRE;
			fireblock.setFlammable(burnable, catchFlame, burn);
		}
	}
}
