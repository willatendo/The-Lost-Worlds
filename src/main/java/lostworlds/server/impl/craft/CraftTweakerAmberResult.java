package lostworlds.server.impl.craft;

import java.util.List;

import lostworlds.api.APIAmberRecipeType;
import net.minecraft.world.item.Item;

public record CraftTweakerAmberResult(List<Item> items) implements APIAmberRecipeType {
	@Override
	public List<Item> outputs() {
		return this.items;
	}
}
