package lostworlds.server.menu.recipes.serialiser;

import com.google.gson.JsonObject;

import lostworlds.server.item.LostWorldsItems;
import lostworlds.server.menu.recipes.AmberDNAExtractorRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class AmberDNAExtractorRecipeSerialiser extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<AmberDNAExtractorRecipe> {
	@Override
	public AmberDNAExtractorRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
		Ingredient softTissue = Ingredient.of(LostWorldsItems.AMBER.get());
		Ingredient vile = Ingredient.of(LostWorldsItems.EMPTY_VILE.get());

		return new AmberDNAExtractorRecipe(recipeId, softTissue, vile);
	}

	@Override
	public AmberDNAExtractorRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
		Ingredient softTissue = Ingredient.fromNetwork(buffer);
		Ingredient vile = Ingredient.fromNetwork(buffer);

		return new AmberDNAExtractorRecipe(recipeId, softTissue, vile);
	}

	@Override
	public void toNetwork(FriendlyByteBuf buffer, AmberDNAExtractorRecipe recipe) {
		buffer.writeItemStack(recipe.getResultItem(), false);

		Ingredient input = recipe.getIngredients().get(0);
		input.toNetwork(buffer);
	}
}
