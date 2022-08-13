package lostworlds.server.trades;

import java.util.List;
import java.util.Random;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;

public class MultiItemForEmeraldsTrade implements ItemListing {
	private final List<Item> itemsForSale;
	private final int amountSold;
	private final int cost;
	private final int tradeUses;
	private final int villagerXp;

	public MultiItemForEmeraldsTrade(List<Item> itemsForSale, int amountSold, int cost, int tradeUses, int villagerXp) {
		this.itemsForSale = itemsForSale;
		this.amountSold = amountSold;
		this.cost = cost;
		this.tradeUses = tradeUses;
		this.villagerXp = villagerXp;
	}

	@Override
	public MerchantOffer getOffer(Entity entity, Random rand) {
		int choose = (int) (rand.nextFloat() * itemsForSale.size());
		return new MerchantOffer(new ItemStack(Items.EMERALD, this.cost), new ItemStack(this.itemsForSale.get(choose), this.amountSold), this.tradeUses, this.villagerXp, 0.05F);
	}
}
