package lostworlds.server.item;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ModItem extends Item {
	public ModItem() {
		this(LostWorldsUtils.ITEMS);
	}

	public ModItem(ItemGroup group) {
		this(new Properties().tab(group));
	}

	public ModItem(Food food) {
		this(new Properties().tab(LostWorldsUtils.ITEMS).food(food));
	}

	public ModItem(int stacksize) {
		super(new Properties().tab(LostWorldsUtils.ITEMS).stacksTo(stacksize));
	}

	public ModItem(Properties properties) {
		super(properties);
	}
}
