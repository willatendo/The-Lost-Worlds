package lostworlds.server.container.recipes;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.core.Registry;

public class ModRecipeType<T extends Recipe<?>> implements RecipeType<T> {
	@Override
	public String toString() {
		return Registry.RECIPE_TYPE.getKey(this).toString();
	}
}
