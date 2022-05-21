package lostworlds.server.container.recipes.serialiser;

import java.util.Map;

import com.google.gson.JsonObject;

import lostworlds.server.container.recipes.PaleontologyTableRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class PaleontologyTableRecipeSerialiser extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<PaleontologyTableRecipe> {
	public PaleontologyTableRecipe fromJson(ResourceLocation id, JsonObject json) {
		Map<String, Ingredient> map = PaleontologyTableRecipe.keyFromJson(JSONUtils.getAsJsonObject(json, "key"));
		String[] astring = PaleontologyTableRecipe.shrink(PaleontologyTableRecipe.patternFromJson(JSONUtils.getAsJsonArray(json, "pattern")));
		int i = astring[0].length();
		int j = astring.length;
		NonNullList<Ingredient> nonnulllist = PaleontologyTableRecipe.dissolvePattern(astring, map, i, j);
		ItemStack itemstack = PaleontologyTableRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "output"));
		return new PaleontologyTableRecipe(id, i, j, nonnulllist, itemstack);
	}

	public PaleontologyTableRecipe fromNetwork(ResourceLocation id, PacketBuffer buffer) {
		int i = buffer.readVarInt();
		int j = buffer.readVarInt();
		NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i * j, Ingredient.EMPTY);

		for (int k = 0; k < nonnulllist.size(); ++k) {
			nonnulllist.set(k, Ingredient.fromNetwork(buffer));
		}

		ItemStack itemstack = buffer.readItem();
		return new PaleontologyTableRecipe(id, i, j, nonnulllist, itemstack);
	}

	public void toNetwork(PacketBuffer buffer, PaleontologyTableRecipe recipe) {
		buffer.writeVarInt(recipe.width);
		buffer.writeVarInt(recipe.height);

		buffer.writeItem(recipe.result);

		for (Ingredient ingredient : recipe.recipeItems) {
			ingredient.toNetwork(buffer);
		}
	}
}