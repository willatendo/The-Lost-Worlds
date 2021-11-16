package lostworlds.library.container.recipes.serialiser;

import com.google.gson.JsonObject;

import lostworlds.library.container.recipes.FossilCleanerRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class FossilCleanerRecipeSerialiser extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<FossilCleanerRecipe>
{
	@Override
	public FossilCleanerRecipe fromJson(ResourceLocation recipeId, JsonObject json) 
	{
		ItemStack output = CraftingHelper.getItemStack(JSONUtils.getAsJsonObject(json, "output"), true);
		Ingredient fossil = Ingredient.fromJson(JSONUtils.getAsJsonObject(json, "fossil"));
		
		return new FossilCleanerRecipe(recipeId, fossil, output);
	}

	@Override
	public FossilCleanerRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) 
	{
		ItemStack output = buffer.readItem();
		Ingredient fossil = Ingredient.fromNetwork(buffer);

		return new FossilCleanerRecipe(recipeId, fossil, output);
	}

	@Override
	public void toNetwork(PacketBuffer buffer, FossilCleanerRecipe recipe) 
	{
		buffer.writeItemStack(recipe.getResultItem(), false);

		Ingredient input = recipe.getIngredients().get(0);
		input.toNetwork(buffer);
	}
}
