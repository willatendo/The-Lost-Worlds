package lostworlds.library.container.recipes.serialiser;

import com.google.gson.JsonObject;

import lostworlds.content.server.init.ItemInit;
import lostworlds.library.container.recipes.AmberDNAExtractorRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class AmberDNAExtractorRecipeSerialiser extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<AmberDNAExtractorRecipe>
{
	@Override
	public AmberDNAExtractorRecipe fromJson(ResourceLocation recipeId, JsonObject json) 
	{
		Ingredient softTissue = Ingredient.of(ItemInit.AMBER);
		Ingredient vile = Ingredient.of(ItemInit.EMPTY_VILE);

		return new AmberDNAExtractorRecipe(recipeId, softTissue, vile);
	}

	@Override
	public AmberDNAExtractorRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) 
	{
		Ingredient softTissue = Ingredient.fromNetwork(buffer);
		Ingredient vile = Ingredient.fromNetwork(buffer);

		return new AmberDNAExtractorRecipe(recipeId, softTissue, vile);
	}

	@Override
	public void toNetwork(PacketBuffer buffer, AmberDNAExtractorRecipe recipe) 
	{
		buffer.writeItemStack(recipe.getResultItem(), false);

		Ingredient input = recipe.getIngredients().get(0);
		input.toNetwork(buffer);
	}
}
