package lostworlds.server.item;

import lostworlds.server.LostWorldsRegistry;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.item.Item;

public class FoodSeedsItem extends Item {
	protected FoodSeedsItem(Properties properties) {
		super(properties);
	}

	private static Properties properties() {
		return new Properties().tab(LostWorldsUtils.ITEMS);
	}

	public static Item create(String plant) {
		Item item = new FoodSeedsItem(properties().stacksTo(1).food(LostWorldsFoods.SEEDS));
		LostWorldsRegistry.register(plant + "_seeds", item);
		return item;
	}
}
