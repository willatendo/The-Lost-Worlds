package lostworlds.server.menu.recipes.serialiser;

import com.google.gson.JsonObject;

import lostworlds.server.menu.recipes.FossilCleanerRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class FossilCleanerRecipeSerialiser extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<FossilCleanerRecipe> {
	@Override
	public FossilCleanerRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
		ItemStack output = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(GsonHelper.getAsString(json, "output"))));
		Ingredient input = Ingredient.fromJson(GsonHelper.isArrayNode(json, "input") ? GsonHelper.getAsJsonArray(json, "input") : GsonHelper.getAsJsonObject(json, "input"));

		return new FossilCleanerRecipe(recipeId, input, output);
	}

	@Override
	public FossilCleanerRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
		ItemStack output = buffer.readItem();
		Ingredient input = Ingredient.fromNetwork(buffer);

		return new FossilCleanerRecipe(recipeId, input, output);
	}

	@Override
	public void toNetwork(FriendlyByteBuf buffer, FossilCleanerRecipe recipe) {
		buffer.writeItemStack(recipe.getResultItem(), false);

		Ingredient input = recipe.getIngredients().get(0);
		input.toNetwork(buffer);
	}
}
