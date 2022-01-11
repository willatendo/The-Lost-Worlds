package lostworlds.library.util;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

public class IngredientUtil {
	public static Ingredient combine(Ingredient ingredient1, Ingredient ingredient2) {
		Ingredient ingredients = Ingredient.of(Items.AIR);
		ArrayList<ItemStack[]> items = Lists.newArrayList(ingredient1.getItems(), ingredient2.getItems());
		for (int i = 0; i < items.size(); i++) {
			ingredients.of(items.get(i));
		}
		return ingredients;
	}
}
