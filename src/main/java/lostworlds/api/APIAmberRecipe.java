package lostworlds.api;

import java.util.ArrayList;

import net.minecraft.item.Item;

/*
 * Adds more results to the amber dna extractor recipes.
 */

public class APIAmberRecipe {
	public static final ArrayList<Item> ADDITIONAL_OUTPUTS = new ArrayList<>();

	/*
	 * Add an additional result to the amber dna extractor recipe outputs. Call when items are registered.
	 * 
	 * @param The {@link Item} to be added
	 */
	public static void addAdditionalOutputs(Item... items) {
		for (Item item : items) {
			ADDITIONAL_OUTPUTS.add(item);
		}
	}
}
