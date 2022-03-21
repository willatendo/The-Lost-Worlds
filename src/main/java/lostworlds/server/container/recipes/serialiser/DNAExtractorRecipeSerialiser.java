package lostworlds.server.container.recipes.serialiser;

import com.google.gson.JsonObject;

import lostworlds.server.container.recipes.DNAExtractorRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class DNAExtractorRecipeSerialiser extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<DNAExtractorRecipe> {
	@Override
	public DNAExtractorRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
		ItemStack output = CraftingHelper.getItemStack(JSONUtils.getAsJsonObject(json, "output"), true);
		Ingredient input = Ingredient.fromJson(JSONUtils.getAsJsonObject(json, "input"));
		Ingredient vile = Ingredient.fromJson(JSONUtils.getAsJsonObject(json, "vile"));

		return new DNAExtractorRecipe(recipeId, input, vile, output);
	}

	@Override
	public DNAExtractorRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
		ItemStack output = buffer.readItem();
		Ingredient input = Ingredient.fromNetwork(buffer);
		Ingredient vile = Ingredient.fromNetwork(buffer);

		return new DNAExtractorRecipe(recipeId, input, vile, output);
	}

	@Override
	public void toNetwork(PacketBuffer buffer, DNAExtractorRecipe recipe) {
		buffer.writeItemStack(recipe.getResultItem(), false);

		Ingredient input = recipe.getIngredients().get(0);
		input.toNetwork(buffer);
	}
}
