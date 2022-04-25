package lostworlds.server.container.recipes.serialiser;

import com.google.gson.JsonObject;

import lostworlds.server.container.recipes.DNAExtractorRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class DNAExtractorRecipeSerialiser extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<DNAExtractorRecipe> {
	@Override
	public DNAExtractorRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
		ItemStack output = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(JSONUtils.getAsString(json, "output"))));
		Ingredient input = Ingredient.fromJson(JSONUtils.isArrayNode(json, "input") ? JSONUtils.getAsJsonArray(json, "input") : JSONUtils.getAsJsonObject(json, "input"));
		Ingredient storage = Ingredient.fromJson(JSONUtils.getAsJsonObject(json, "storage"));

		return new DNAExtractorRecipe(recipeId, input, storage, output);
	}

	@Override
	public DNAExtractorRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
		ItemStack output = buffer.readItem();
		Ingredient input = Ingredient.fromNetwork(buffer);
		Ingredient storage = Ingredient.fromNetwork(buffer);

		return new DNAExtractorRecipe(recipeId, input, storage, output);
	}

	@Override
	public void toNetwork(PacketBuffer buffer, DNAExtractorRecipe recipe) {
		buffer.writeItemStack(recipe.getResultItem(), false);

		Ingredient input = recipe.getIngredients().get(0);
		input.toNetwork(buffer);
	}
}
