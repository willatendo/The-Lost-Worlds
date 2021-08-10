package lostworlds.library.recipe;

import com.google.gson.JsonObject;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

/*
 * Author: Willatendo
 * Date: July 1, 2021
 */

public class FossilGrinderRecipeSerialiser extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<FossilGrinderRecipe>
{
	@Override
	public FossilGrinderRecipe fromJson(ResourceLocation recipeId, JsonObject json) 
	{
		ItemStack output = CraftingHelper.getItemStack(JSONUtils.getAsJsonObject(json, "output"), true);
		Ingredient fossil = Ingredient.fromJson((JSONUtils.isArrayNode(json, "fossil") ? JSONUtils.getAsJsonArray(json, "fossil") : JSONUtils.getAsJsonObject(json, "fossil")));

		return new FossilGrinderRecipe(recipeId, fossil, output);
	}

	@Override
	public FossilGrinderRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) 
	{
		ItemStack output = buffer.readItem();
		Ingredient fossil = Ingredient.fromNetwork(buffer);

		return new FossilGrinderRecipe(recipeId, fossil, output);
	}

	@Override
	public void toNetwork(PacketBuffer buffer, FossilGrinderRecipe recipe) 
	{
		Ingredient input = recipe.getIngredients().get(0);
		input.toNetwork(buffer);

		buffer.writeItemStack(recipe.getResultItem(), false);
	}
}
