package lostworlds.library.container.recipes;

import com.google.gson.JsonObject;

import lostworlds.library.item.CrystalScarabGemItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class TimeMachineRecipeSerialiser extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<TimeMachineRecipe> 
{
	public TimeMachineRecipe fromJson(ResourceLocation recipeId, JsonObject json) 
	{
		String s1 = JSONUtils.getAsString(json, "result");
		int i = JSONUtils.getAsInt(json, "count");
		ItemStack itemstack = new ItemStack(Registry.ITEM.get(new ResourceLocation(s1)), i);
		return new TimeMachineRecipe(recipeId, itemstack, Ingredient.of(Items.BOOK), Ingredient.of(CrystalScarabGemItem.Gems.CHARGED_CRYSTAL_SCARAB_GEM.getItem()));
	}

	public TimeMachineRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) 
	{
		ItemStack itemstack = buffer.readItem();
		Ingredient book = Ingredient.fromNetwork(buffer);
		Ingredient power = Ingredient.fromNetwork(buffer);
		
		return new TimeMachineRecipe(recipeId, itemstack, book, power);
	}

	public void toNetwork(PacketBuffer buffer, TimeMachineRecipe recipe) 
	{
		buffer.writeItem(recipe.result);
	}
}
