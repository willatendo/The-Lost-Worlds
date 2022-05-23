package lostworlds.server.menu.recipes.serialiser;

import java.util.Map;

import com.google.gson.JsonObject;

import lostworlds.server.menu.recipes.PaleontologyTableRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class PaleontologyTableRecipeSerialiser extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<PaleontologyTableRecipe> {
	public PaleontologyTableRecipe fromJson(ResourceLocation id, JsonObject json) {
		Map<String, Ingredient> map = PaleontologyTableRecipe.keyFromJson(GsonHelper.getAsJsonObject(json, "key"));
		String[] astring = PaleontologyTableRecipe.shrink(PaleontologyTableRecipe.patternFromJson(GsonHelper.getAsJsonArray(json, "pattern")));
		int i = astring[0].length();
		int j = astring.length;
		NonNullList<Ingredient> nonnulllist = PaleontologyTableRecipe.dissolvePattern(astring, map, i, j);
		ItemStack itemstack = PaleontologyTableRecipe.itemFromJson(GsonHelper.getAsJsonObject(json, "output"));
		return new PaleontologyTableRecipe(id, i, j, nonnulllist, itemstack);
	}

	public PaleontologyTableRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
		int i = buffer.readVarInt();
		int j = buffer.readVarInt();
		NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i * j, Ingredient.EMPTY);

		for (int k = 0; k < nonnulllist.size(); ++k) {
			nonnulllist.set(k, Ingredient.fromNetwork(buffer));
		}

		ItemStack itemstack = buffer.readItem();
		return new PaleontologyTableRecipe(id, i, j, nonnulllist, itemstack);
	}

	public void toNetwork(FriendlyByteBuf buffer, PaleontologyTableRecipe recipe) {
		buffer.writeVarInt(recipe.width);
		buffer.writeVarInt(recipe.height);

		buffer.writeItem(recipe.result);

		for (Ingredient ingredient : recipe.recipeItems) {
			ingredient.toNetwork(buffer);
		}
	}
}