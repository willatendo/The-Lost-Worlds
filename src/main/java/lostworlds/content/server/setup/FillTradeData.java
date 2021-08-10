package lostworlds.content.server.setup;

import java.util.List;
import java.util.Random;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lostworlds.content.server.init.BlockInit;
import lostworlds.content.server.init.ItemInit;
import lostworlds.content.server.init.VillagerProfessionInit;
import lostworlds.library.util.ModUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/*
 * Author: Willatendo
 * Date: July 3, 2021
 */

@EventBusSubscriber(modid = ModUtil.ID, bus = Bus.MOD)
public class FillTradeData 
{
	public static void fillTradeData() 
	{
		VillagerTrades.ITrade[] level1 = new VillagerTrades.ITrade[] 
		{
			new VillagerTrades.ItemsForEmeraldsTrade(Items.CLAY_BALL, 2, 20, 12),
			new VillagerTrades.ItemsForEmeraldsTrade(Items.CLAY, 16, 10, 12),
			new VillagerTrades.ItemsForEmeraldsTrade(BlockInit.ARCHAEOLOGY_TABLE.asItem(), 15, 1, 20),
			//new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.KYLIX, 5, 1, 20)
		};
		VillagerTrades.ITrade[] level2 = new VillagerTrades.ITrade[] 
		{
			//new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.FEILD_GUIDE, 25, 1, 20),
			new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.WET_PAPER, 3, 5, 12)
		};
		VillagerTrades.ITrade[] level3 = new VillagerTrades.ITrade[] 
		{
			new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.LEATHER_BRUSH, 16, 1, 20)
		};
		VillagerTrades.ITrade[] level4 = new VillagerTrades.ITrade[] 
		{
			new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.PERMIAN_PERIOD_TIME_BOOK, 64, 1, 100),
			//new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.JURASSIC_ERA_TIME_BOOK, 64, 1, 100)
		};
		VillagerTrades.ITrade[] level5 = new VillagerTrades.ITrade[] 
		{
			new VillagerTrades.ItemsForEmeraldsTrade(Items.LODESTONE, 64, 1, 100),
			new FillTradeData.MultiItemForEmeraldsTrade(ImmutableList.of(Items.GOLD_INGOT, Items.DIAMOND, Items.IRON_INGOT, Items.NETHERITE_SCRAP, ItemInit.BROKEN_CRYSTAL_SCARAB_GEM), ImmutableList.of(5, 1, 3, 1, 1), ImmutableList.of(10, 30, 20, 50, 64), 1, 100)
		};
		VillagerTrades.TRADES.put(VillagerProfessionInit.ARCHAEOLOGIST, toIntMap(ImmutableMap.of(1, level1, 2, level2, 3, level3, 4, level4, 5, level5)));
	}
	
	private static Int2ObjectMap<VillagerTrades.ITrade[]> toIntMap(ImmutableMap<Integer, VillagerTrades.ITrade[]> tradeMap) 
	{
		return new Int2ObjectOpenHashMap<>(tradeMap);
	}
	
	@SubscribeEvent
	public static void initVillagerTradesArray(FMLCommonSetupEvent event)
	{
		event.enqueueWork(() -> {
			FillTradeData.fillTradeData();
		});
	}
	
	public static class MultiItemForEmeraldsTrade implements ITrade
	{
		private final List<Item> itemsForSale;
		private final List<Integer> amountSoldPerItem;
		private final List<Integer> costPerItem;
		private final int tradeUses;
		private final int villagerXp;
		
		public MultiItemForEmeraldsTrade(List<Item> itemsForSale, List<Integer> amountSoldPerItem, List<Integer> costPerItem, int tradeUses, int villagerXp)
		{
			this.itemsForSale = itemsForSale;
			this.amountSoldPerItem = amountSoldPerItem;
			this.costPerItem = costPerItem;
			this.tradeUses = tradeUses;
			this.villagerXp = villagerXp;
		}
		
		@Override
		public MerchantOffer getOffer(Entity entity, Random rand) 
		{
			int choose = (int) (rand.nextFloat() * itemsForSale.size());
			return new MerchantOffer(new ItemStack(Items.EMERALD, costPerItem.get(choose)), new ItemStack(itemsForSale.get(choose), amountSoldPerItem.get(choose)), this.tradeUses, this.villagerXp, 0.05F);
		}
	}
}
