package lostworlds.library.recipe;

import com.google.gson.JsonObject;

import lostworlds.content.server.init.ItemInit;
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

public class DNAExtractorRecipeSerialiser extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<DNAExtractorRecipe>
{
	@Override
	public DNAExtractorRecipe fromJson(ResourceLocation recipeId, JsonObject json) 
	{
		ItemStack output = CraftingHelper.getItemStack(JSONUtils.getAsJsonObject(json, "output"), true);
		Ingredient softTissue = Ingredient.fromJson(JSONUtils.getAsJsonObject(json, "softTissue"));
		Ingredient vile = Ingredient.of(ItemInit.EMPTY_VILE);

		return new DNAExtractorRecipe(recipeId, softTissue, vile, output);
	}

	@Override
	public DNAExtractorRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) 
	{
		ItemStack output = buffer.readItem();
		Ingredient softTissue = Ingredient.fromNetwork(buffer);
		Ingredient vile = Ingredient.fromNetwork(buffer);

		return new DNAExtractorRecipe(recipeId, softTissue, vile, output);
	}

	@Override
	public void toNetwork(PacketBuffer buffer, DNAExtractorRecipe recipe) 
	{
		Ingredient input = recipe.getIngredients().get(0);
		input.toNetwork(buffer);

		buffer.writeItemStack(recipe.getResultItem(), false);
	}
}
