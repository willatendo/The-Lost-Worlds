package lostworlds.server.craft;

import java.util.ArrayList;
import java.util.List;

import lostworlds.api.APIAmberRecipeType;
import net.minecraft.world.item.Item;

public record AmberOutput(ArrayList<Item> item) implements APIAmberRecipeType {
	@Override
	public List<Item> outputs() {
		return this.item;
	}
}
