package lostworlds.server.container.recipes.serialiser;

import com.google.gson.JsonObject;

import lostworlds.server.container.recipes.TimeMachineRecipe;
import lostworlds.server.item.LostWorldsItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class TimeMachineRecipeSerialiser extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<TimeMachineRecipe> {
	public TimeMachineRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
		String s1 = GsonHelper.getAsString(json, "output");
		int i = GsonHelper.getAsInt(json, "count");
		ItemStack itemstack = new ItemStack(Registry.ITEM.get(new ResourceLocation(s1)), i);
		return new TimeMachineRecipe(recipeId, itemstack, Ingredient.of(Items.BOOK), Ingredient.of(LostWorldsItems.CHARGED_CRYSTAL_SCARAB_GEM.get()));
	}

	public TimeMachineRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
		ItemStack itemstack = buffer.readItem();
		Ingredient book = Ingredient.fromNetwork(buffer);
		Ingredient power = Ingredient.fromNetwork(buffer);

		return new TimeMachineRecipe(recipeId, itemstack, book, power);
	}

	public void toNetwork(FriendlyByteBuf buffer, TimeMachineRecipe recipe) {
		buffer.writeItem(recipe.result);
	}
}
