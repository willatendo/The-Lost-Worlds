package lostworlds.server.container.recipes.serialiser;

import java.util.Map;

import com.google.gson.JsonObject;

import lostworlds.server.container.recipes.ArchaeologyTableRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ArchaeologyTableRecipeSerialiser extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<ArchaeologyTableRecipe> {
	public ArchaeologyTableRecipe fromJson(ResourceLocation id, JsonObject json) {
		Map<String, Ingredient> map = ArchaeologyTableRecipe.keyFromJson(JSONUtils.getAsJsonObject(json, "key"));
		String[] astring = ArchaeologyTableRecipe.shrink(ArchaeologyTableRecipe.patternFromJson(JSONUtils.getAsJsonArray(json, "pattern")));
		int i = astring[0].length();
		int j = astring.length;
		NonNullList<Ingredient> nonnulllist = ArchaeologyTableRecipe.dissolvePattern(astring, map, i, j);
		ItemStack itemstack = ArchaeologyTableRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "output"));
		return new ArchaeologyTableRecipe(id, i, j, nonnulllist, itemstack);
	}

	public ArchaeologyTableRecipe fromNetwork(ResourceLocation id, PacketBuffer buffer) {
		int i = buffer.readVarInt();
		int j = buffer.readVarInt();
		NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i * j, Ingredient.EMPTY);

		for (int k = 0; k < nonnulllist.size(); ++k) {
			nonnulllist.set(k, Ingredient.fromNetwork(buffer));
		}

		ItemStack itemstack = buffer.readItem();
		return new ArchaeologyTableRecipe(id, i, j, nonnulllist, itemstack);
	}

	public void toNetwork(PacketBuffer buffer, ArchaeologyTableRecipe recipe) {
		buffer.writeVarInt(recipe.width);
		buffer.writeVarInt(recipe.height);

		buffer.writeItem(recipe.result);

		for (Ingredient ingredient : recipe.recipeItems) {
			ingredient.toNetwork(buffer);
		}
	}
}
