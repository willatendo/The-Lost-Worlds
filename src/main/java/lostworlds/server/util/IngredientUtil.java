package lostworlds.server.util;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public class IngredientUtil {
	public static Ingredient combine(Ingredient ingredient1, Ingredient ingredient2) {
		ArrayList<ItemStack[]> items = Lists.newArrayList(ingredient1.getItems(), ingredient2.getItems());
		ArrayList<ItemStack> itemStacks = Lists.newArrayList();
		for (int i = 0; i < items.size(); i++) {
			for (int x = 0; x < items.get(i).length; x++) {
				itemStacks.add(items.get(i)[x]);
			}
		}
		return Ingredient.of(itemStacks.stream().toArray(ItemStack[]::new));
	}
}
