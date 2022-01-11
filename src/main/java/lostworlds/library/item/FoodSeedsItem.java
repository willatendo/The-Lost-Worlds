package lostworlds.library.item;

import lostworlds.content.ModRegistry;
import lostworlds.content.ModUtils;
import lostworlds.content.server.init.FoodInit;
import net.minecraft.item.Item;

public class FoodSeedsItem extends Item {
	protected FoodSeedsItem(Properties properties) {
		super(properties);
	}

	private static Properties properties() {
		return new Properties().tab(ModUtils.ITEMS);
	}

	public static Item create(String plant) {
		Item item = new FoodSeedsItem(properties().stacksTo(1).food(FoodInit.SEEDS));
		ModRegistry.register(plant + "_seeds", item);
		return item;
	}
}
