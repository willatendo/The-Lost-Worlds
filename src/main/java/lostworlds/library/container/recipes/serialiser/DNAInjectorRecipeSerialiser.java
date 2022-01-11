package lostworlds.library.container.recipes.serialiser;

import com.google.gson.JsonObject;

import lostworlds.library.container.recipes.DNAInjectorRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class DNAInjectorRecipeSerialiser extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<DNAInjectorRecipe> {
	@Override
	public DNAInjectorRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
		ItemStack output = CraftingHelper.getItemStack(JSONUtils.getAsJsonObject(json, "output"), true);
		Ingredient dnaDisc = Ingredient.fromJson(JSONUtils.getAsJsonObject(json, "dnaDisc"));
		Ingredient egg = Ingredient.of(Items.EGG);

		return new DNAInjectorRecipe(recipeId, dnaDisc, egg, output);
	}

	@Override
	public DNAInjectorRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
		ItemStack output = buffer.readItem();
		Ingredient dnaDisc = Ingredient.fromNetwork(buffer);
		Ingredient egg = Ingredient.fromNetwork(buffer);

		return new DNAInjectorRecipe(recipeId, dnaDisc, egg, output);
	}

	@Override
	public void toNetwork(PacketBuffer buffer, DNAInjectorRecipe recipe) {
		buffer.writeItemStack(recipe.getResultItem(), false);

		Ingredient input = recipe.getIngredients().get(0);
		input.toNetwork(buffer);
	}
}
