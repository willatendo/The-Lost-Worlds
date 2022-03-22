package lostworlds.server.container.recipes.serialiser;

import com.google.gson.JsonObject;

import lostworlds.server.container.recipes.AmberDNAExtractorRecipe;
import lostworlds.server.item.LostWorldsItems;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class AmberDNAExtractorRecipeSerialiser extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<AmberDNAExtractorRecipe> {
	@Override
	public AmberDNAExtractorRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
		Ingredient softTissue = Ingredient.of(LostWorldsItems.AMBER.get());
		Ingredient vile = Ingredient.of(LostWorldsItems.EMPTY_VILE.get());

		return new AmberDNAExtractorRecipe(recipeId, softTissue, vile);
	}

	@Override
	public AmberDNAExtractorRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
		Ingredient softTissue = Ingredient.fromNetwork(buffer);
		Ingredient vile = Ingredient.fromNetwork(buffer);

		return new AmberDNAExtractorRecipe(recipeId, softTissue, vile);
	}

	@Override
	public void toNetwork(PacketBuffer buffer, AmberDNAExtractorRecipe recipe) {
		buffer.writeItemStack(recipe.getResultItem(), false);

		Ingredient input = recipe.getIngredients().get(0);
		input.toNetwork(buffer);
	}
}
