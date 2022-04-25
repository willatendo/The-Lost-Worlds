package lostworlds.server.container.recipes.serialiser;

import com.google.gson.JsonObject;

import lostworlds.server.container.recipes.DNAInjectorRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class DNAInjectorRecipeSerialiser extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<DNAInjectorRecipe> {
	@Override
	public DNAInjectorRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
		ItemStack output = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(JSONUtils.getAsString(json, "output"))));
		Ingredient input = Ingredient.fromJson(JSONUtils.isArrayNode(json, "input") ? JSONUtils.getAsJsonArray(json, "input") : JSONUtils.getAsJsonObject(json, "input"));
		Ingredient storage = Ingredient.fromJson(JSONUtils.getAsJsonObject(json, "storage"));

		return new DNAInjectorRecipe(recipeId, input, storage, output);
	}

	@Override
	public DNAInjectorRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
		ItemStack output = buffer.readItem();
		Ingredient input = Ingredient.fromNetwork(buffer);
		Ingredient storage = Ingredient.fromNetwork(buffer);

		return new DNAInjectorRecipe(recipeId, input, storage, output);
	}

	@Override
	public void toNetwork(PacketBuffer buffer, DNAInjectorRecipe recipe) {
		buffer.writeItemStack(recipe.getResultItem(), false);

		Ingredient input = recipe.getIngredients().get(0);
		input.toNetwork(buffer);
	}
}
