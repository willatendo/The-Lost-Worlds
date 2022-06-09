package lostworlds.server.impl.block;

import java.util.List;

import lostworlds.api.APIAmberRecipeType;
import lostworlds.server.LostWorldsTags;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class DNAFromAmberRecipe implements APIAmberRecipeType {
	@Override
	public List<Item> outputs() {
		return ForgeRegistries.ITEMS.tags().getTag(LostWorldsTags.ModItemTags.AMBER_RESULTS.tag).stream().toList();
	}
}
