package lostworlds.library.container.recipes;

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

public class AnalyzerRecipeSerialiser extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<AnalyzerRecipe>
{
	@Override
	public AnalyzerRecipe fromJson(ResourceLocation recipeId, JsonObject json) 
	{
		ItemStack output = CraftingHelper.getItemStack(JSONUtils.getAsJsonObject(json, "output"), true);
		Ingredient dna = Ingredient.fromJson(JSONUtils.getAsJsonObject(json, "dna"));
		Ingredient dnaDisc = Ingredient.of(ItemInit.STORAGE_DISC);

		return new AnalyzerRecipe(recipeId, dna, dnaDisc, output);
	}

	@Override
	public AnalyzerRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) 
	{
		ItemStack output = buffer.readItem();
		Ingredient dna = Ingredient.fromNetwork(buffer);
		Ingredient dnaDisc = Ingredient.fromNetwork(buffer);

		return new AnalyzerRecipe(recipeId, dna, dnaDisc, output);
	}

	@Override
	public void toNetwork(PacketBuffer buffer, AnalyzerRecipe recipe) 
	{
		Ingredient input = recipe.getIngredients().get(0);
		input.toNetwork(buffer);

		buffer.writeItemStack(recipe.getResultItem(), false);
	}
}
