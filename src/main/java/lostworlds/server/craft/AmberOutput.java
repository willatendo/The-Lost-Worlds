package lostworlds.server.craft;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.compress.utils.Lists;

import lostworlds.api.APIAmberRecipeType;
import net.minecraft.world.item.Item;

public record AmberOutput(ArrayList<Item> item) implements APIAmberRecipeType {
	@Override
	public List<Item> outputs() {
		ArrayList<Item> items = Lists.newArrayList();
		for (Item item : this.item) {
			items.add(item);
		}
		return items;
	}
}
