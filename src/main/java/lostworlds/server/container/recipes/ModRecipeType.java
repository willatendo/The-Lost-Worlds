package lostworlds.server.container.recipes;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;

public class ModRecipeType<T extends IRecipe<?>> implements IRecipeType<T> {
	@Override
	public String toString() {
		return Registry.RECIPE_TYPE.getKey(this).toString();
	}
}
