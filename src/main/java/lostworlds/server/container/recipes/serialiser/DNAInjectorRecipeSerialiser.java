package lostworlds.server.container.recipes.serialiser;

import com.google.gson.JsonObject;

import lostworlds.server.container.recipes.DNAInjectorRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class DNAInjectorRecipeSerialiser extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<DNAInjectorRecipe> {
	@Override
	public DNAInjectorRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
		ItemStack output = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(GsonHelper.getAsString(json, "output"))));
		Ingredient input = Ingredient.fromJson(GsonHelper.isArrayNode(json, "input") ? GsonHelper.getAsJsonArray(json, "input") : GsonHelper.getAsJsonObject(json, "input"));
		Ingredient storage = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "storage"));

		return new DNAInjectorRecipe(recipeId, input, storage, output);
	}

	@Override
	public DNAInjectorRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
		ItemStack output = buffer.readItem();
		Ingredient input = Ingredient.fromNetwork(buffer);
		Ingredient storage = Ingredient.fromNetwork(buffer);

		return new DNAInjectorRecipe(recipeId, input, storage, output);
	}

	@Override
	public void toNetwork(FriendlyByteBuf buffer, DNAInjectorRecipe recipe) {
		buffer.writeItemStack(recipe.getResultItem(), false);

		Ingredient input = recipe.getIngredients().get(0);
		input.toNetwork(buffer);
	}
}
