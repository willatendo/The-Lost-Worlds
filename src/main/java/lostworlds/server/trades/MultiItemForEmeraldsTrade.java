package lostworlds.server.trades;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;

public class MultiItemForEmeraldsTrade implements ITrade {
	private final List<Item> itemsForSale;
	private final List<Integer> amountSoldPerItem;
	private final List<Integer> costPerItem;
	private final int tradeUses;
	private final int villagerXp;

	public MultiItemForEmeraldsTrade(List<Item> itemsForSale, List<Integer> amountSoldPerItem, List<Integer> costPerItem, int tradeUses, int villagerXp) {
		this.itemsForSale = itemsForSale;
		this.amountSoldPerItem = amountSoldPerItem;
		this.costPerItem = costPerItem;
		this.tradeUses = tradeUses;
		this.villagerXp = villagerXp;
	}

	@Override
	public MerchantOffer getOffer(Entity entity, Random rand) {
		int choose = (int) (rand.nextFloat() * itemsForSale.size());
		return new MerchantOffer(new ItemStack(Items.EMERALD, costPerItem.get(choose)), new ItemStack(itemsForSale.get(choose), amountSoldPerItem.get(choose)), this.tradeUses, this.villagerXp, 0.05F);
	}
}
