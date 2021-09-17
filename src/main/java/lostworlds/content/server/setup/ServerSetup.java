package lostworlds.content.server.setup;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lostworlds.content.server.init.BlockInit;
import lostworlds.content.server.init.ItemInit;
import lostworlds.content.server.init.VillagerProfessionInit;
import lostworlds.library.entity.illager.FossilPoacherEntity;
import lostworlds.library.item.CrystalScarabGemItem;
import lostworlds.library.trades.MultiItemForEmeraldsTrade;
import lostworlds.library.util.JigsawUtils;
import lostworlds.library.util.ModUtils;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Items;
import net.minecraft.item.ShovelItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;

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
			VillagerTrades.ITrade[] archaeology1 = new VillagerTrades.ITrade[] 
			{
				new VillagerTrades.ItemsForEmeraldsTrade(Items.CLAY_BALL, 2, 20, 12),
				new VillagerTrades.ItemsForEmeraldsTrade(Items.CLAY, 16, 10, 12),
				new VillagerTrades.ItemsForEmeraldsTrade(BlockInit.ARCHAEOLOGY_TABLE.asItem(), 15, 1, 20),
				//new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.KYLIX, 5, 1, 20)
			};
			
			VillagerTrades.ITrade[] archaeology2 = new VillagerTrades.ITrade[] 
			{
				//new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.FEILD_GUIDE, 25, 1, 20),
				new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.WET_PAPER, 3, 5, 12)
			};
			
			VillagerTrades.ITrade[] archaeology3 = new VillagerTrades.ITrade[] 
			{
				new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.LEATHER_BRUSH, 16, 1, 20)
			};
			
			VillagerTrades.ITrade[] archaeology4 = new VillagerTrades.ITrade[] 
			{
				new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.PERMIAN_PERIOD_TIME_BOOK, 64, 1, 100),
				//new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.JURASSIC_ERA_TIME_BOOK, 64, 1, 100)
			};
			
			VillagerTrades.ITrade[] archaeology5 = new VillagerTrades.ITrade[] 
			{
				new VillagerTrades.ItemsForEmeraldsTrade(Items.LODESTONE, 64, 1, 100),
				new MultiItemForEmeraldsTrade(ImmutableList.of(Items.GOLD_INGOT, Items.DIAMOND, Items.IRON_INGOT, Items.NETHERITE_SCRAP, CrystalScarabGemItem.crystal_scarab_abdomen, CrystalScarabGemItem.crystal_scarab_bottom_left_leg, CrystalScarabGemItem.crystal_scarab_bottom_right_leg, CrystalScarabGemItem.crystal_scarab_thorax, CrystalScarabGemItem.crystal_scarab_top_left_leg, CrystalScarabGemItem.crystal_scarab_top_right_leg), ImmutableList.of(5, 1, 3, 1, 1), ImmutableList.of(10, 30, 20, 50, 64), 1, 100)
			};
			VillagerTrades.TRADES.put(VillagerProfessionInit.ARCHAEOLOGIST, toIntMap(ImmutableMap.of(1, archaeology1, 2, archaeology2, 3, archaeology3, 4, archaeology4, 5, archaeology5)));
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
	
	@EventBusSubscriber(bus = Bus.MOD)
	static class IllagerSetup
	{
		@SubscribeEvent
		public void onEntityJoin(EntityJoinWorldEvent event) 
		{
			if(event.getEntity() instanceof VillagerEntity) 
			{
				VillagerEntity villager = (VillagerEntity) event.getEntity(); 
				villager.goalSelector.addGoal(1, new AvoidEntityGoal(villager, FossilPoacherEntity.class, 16.0F, 0.7D, 0.7D));
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
			addToStrippingMap(BlockInit.PETRIFIED_GINKGO_LOG, BlockInit.STRIPPED_PETRIFIED_GINKGO_LOG);
			
			addToStrippingMap(BlockInit.ARAUCARIA_LOG, BlockInit.STRIPPED_ARAUCARIA_LOG);
			addToStrippingMap(BlockInit.ARAUCARIA_WOOD, BlockInit.STRIPPED_ARAUCARIA_WOOD);
			addToStrippingMap(BlockInit.CALAMITES_LOG, BlockInit.STRIPPED_CALAMITES_LOG);
			addToStrippingMap(BlockInit.CALAMITES_WOOD, BlockInit.STRIPPED_CALAMITES_WOOD);
			addToStrippingMap(BlockInit.CONIFER_LOG, BlockInit.STRIPPED_CONIFER_LOG);
			addToStrippingMap(BlockInit.CONIFER_WOOD, BlockInit.STRIPPED_CONIFER_WOOD);
			addToStrippingMap(BlockInit.GINKGO_LOG, BlockInit.STRIPPED_GINKGO_LOG);
			addToStrippingMap(BlockInit.GINKGO_WOOD, BlockInit.STRIPPED_GINKGO_WOOD);
			addToStrippingMap(BlockInit.SCORCHED_LOG, BlockInit.STRIPPED_SCORCHED_LOG);
			addToStrippingMap(BlockInit.SCORCHED_WOOD, BlockInit.STRIPPED_SCORCHED_WOOD);
			
			ModUtils.LOGGER.debug("Finished Adding to Stripping Map");
			
			ModUtils.LOGGER.debug("Adding to Tilling Map");
			
			addToTillingMap(BlockInit.MOSSY_SOIL);
			
			ModUtils.LOGGER.debug("Finished Adding to Tilling Map");
			
			ModUtils.LOGGER.debug("Adding to Flattening Map");
			
			addToFlatteningMap(BlockInit.MOSSY_SOIL);
			
			ModUtils.LOGGER.debug("Finished Adding to Flattening Map");
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
	}
}
