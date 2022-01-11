package lostworlds.library.trades;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;

public class EmeraldsForMultiItemTrade implements ITrade {
	private final List<Item> itemsWanted;
	private final List<Integer> ammount;
	private final List<Integer> offer;
	private final int tradeUses;
	private final int villagerXp;

	public EmeraldsForMultiItemTrade(List<Item> itemsWanted, List<Integer> ammount, List<Integer> offer, int tradeUses, int villagerXp) {
		this.itemsWanted = itemsWanted;
		this.ammount = ammount;
		this.offer = offer;
		this.tradeUses = tradeUses;
		this.villagerXp = villagerXp;
	}

	@Override
	public MerchantOffer getOffer(Entity entity, Random rand) {
		int choose = (int) (rand.nextFloat() * itemsWanted.size());
		return new MerchantOffer(new ItemStack(itemsWanted.get(choose), ammount.get(choose)), new ItemStack(Items.EMERALD, offer.get(choose)), this.tradeUses, this.villagerXp, 0.05F);
	}
}
