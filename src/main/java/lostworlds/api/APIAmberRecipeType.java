package lostworlds.api;

import java.util.List;

import net.minecraft.world.item.Item;

/*
 * Adds additional outputs to the Amber Recipe.
 * 
 * Make sure to add it to the {@link APIRegistry}.AMBER_RECIPE_TYPES
 */

public interface APIAmberRecipeType {
	List<Item> outputs();
}
