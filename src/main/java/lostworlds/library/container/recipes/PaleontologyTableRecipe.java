package lostworlds.library.container.recipes;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import lostworlds.content.server.init.RecipeInit;
import lostworlds.library.container.inventory.PaleontologyTableInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IShapedRecipe;

public class PaleontologyTableRecipe implements IRecipe<PaleontologyTableInventory>, IShapedRecipe<PaleontologyTableInventory> 
{
	static int MAX_WIDTH = 3;
	static int MAX_HEIGHT = 3;

	public static void setCraftingSize(int width, int height) 
	{
		if (MAX_WIDTH < width) 
		{
			MAX_WIDTH = width;
		}
		if (MAX_HEIGHT < height) 
		{
			MAX_HEIGHT = height;
		}
	}

	public final int width;
	public final int height;
	public final NonNullList<Ingredient> recipeItems;
	public final ItemStack result;
	public final ResourceLocation id;

	public PaleontologyTableRecipe(ResourceLocation id, int width, int height, NonNullList<Ingredient> recipeItems, ItemStack result) 
	{
		this.id = id;
		this.width = width;
		this.height = height;
		this.recipeItems = recipeItems;
		this.result = result;
	}

	@Override
	public ResourceLocation getId() 
	{
		return this.id;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() 
	{
		return RecipeInit.PALEONTOLOGY_TABLE_SERIALIZER;
	}

	@Override
	public ItemStack getResultItem() 
	{
		return this.result;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() 
	{
		return this.recipeItems;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) 
	{
		return width >= this.width && height >= this.height;
	}
	
	public NonNullList<ItemStack> getOutput()
	{
		NonNullList<ItemStack> output = NonNullList.create();
		output.add(this.result);
		return output;
	}

	@Override
	public boolean matches(PaleontologyTableInventory inv, World world) 
	{
		for(int i = 0; i <= inv.getWidth() - this.width; ++i) 
		{
			for(int j = 0; j <= inv.getHeight() - this.height; ++j) 
			{
				if(this.matches(inv, i, j, true)) 
				{
					return true;
				}

				if(this.matches(inv, i, j, false)) 
				{
					return true;
				}
			}
		}

		return false;
	}

	public boolean matches(PaleontologyTableInventory inv, int width, int height, boolean b) 
	{
		for(int i = 0; i < inv.getWidth(); ++i) 
		{
			for(int j = 0; j < inv.getHeight(); ++j) 
			{
				int k = i - width;
				int l = j - height;
				Ingredient ingredient = Ingredient.EMPTY;
				if(k >= 0 && l >= 0 && k < this.width && l < this.height) 
				{
					if(b) 
					{
						ingredient = this.recipeItems.get(this.width - k - 1 + l * this.width);
					} 
					else 
					{
						ingredient = this.recipeItems.get(k + l * this.width);
					}
				}

				if(!ingredient.test(inv.getItem(i + j * inv.getWidth())))
				{
					return false;
				}
			}
		}

		return true;
	}

	@Override
	public ItemStack assemble(PaleontologyTableInventory inv) 
	{
		return this.getResultItem().copy();
	}

	public int getWidth() 
	{
		return this.width;
	}

	@Override
	public int getRecipeWidth() 
	{
		return getWidth();
	}

	public int getHeight() 
	{
		return this.height;
	}

	@Override
	public int getRecipeHeight() 
	{
		return getHeight();
	}

	public static NonNullList<Ingredient> dissolvePattern(String[] stacks, Map<String, Ingredient> map, int width, int height) 
	{
		NonNullList<Ingredient> nonnulllist = NonNullList.withSize(width * height, Ingredient.EMPTY);
		Set<String> set = Sets.newHashSet(map.keySet());
		set.remove(" ");
		
		for(int i = 0; i < stacks.length; ++i) 
		{
			for(int j = 0; j < stacks[i].length(); ++j) 
			{
				String s = stacks[i].substring(j, j + 1);
				Ingredient ingredient = map.get(s);
				if(ingredient == null) 
				{
					throw new JsonSyntaxException("Pattern references symbol '" + s + "' but it's not defined in the key");
				}
				
				set.remove(s);
				nonnulllist.set(j + width * i, ingredient);
			}
		}
		
		if(!set.isEmpty()) 
		{
			throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + set);
		} 
		else 
		{
			return nonnulllist;
		}
	}
	
	public static String[] shrink(String... stacks) 
	{
		int i = Integer.MAX_VALUE;
		int j = 0;
		int k = 0;
		int l = 0;

		for(int i1 = 0; i1 < stacks.length; ++i1) 
		{
			String s = stacks[i1];
			i = Math.min(i, firstNonSpace(s));
			int j1 = lastNonSpace(s);
			j = Math.max(j, j1);
			if(j1 < 0) 
			{
				if(k == i1) 
				{
					++k;
				}

				++l;
			} 
			else 
			{
				l = 0;
			}
		}

		if(stacks.length == l) 
		{
			return new String[0];
		} 
		else 
		{
			String[] astring = new String[stacks.length - l - k];

			for(int k1 = 0; k1 < astring.length; ++k1) 
			{
				astring[k1] = stacks[k1 + k].substring(i, j + 1);
			}

			return astring;
		}
	}

	public static int firstNonSpace(String stacks) 
	{
		int i;
		for(i = 0; i < stacks.length() && stacks.charAt(i) == ' '; ++i) { }

		return i;
	}

	public static int lastNonSpace(String stacks) 
	{
		int i;
		for(i = stacks.length() - 1; i >= 0 && stacks.charAt(i) == ' '; --i) { }

		return i;
	}

	public static String[] patternFromJson(JsonArray array) 
	{
		String[] astring = new String[array.size()];
		if(astring.length > MAX_HEIGHT) 
		{
			throw new JsonSyntaxException("Invalid pattern: too many rows, " + MAX_HEIGHT + " is maximum");
		} 
		else if(astring.length == 0) 
		{
			throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
		} 
		else 
		{
			for(int i = 0; i < astring.length; ++i) 
			{
				String s = JSONUtils.convertToString(array.get(i), "pattern[" + i + "]");
				if(s.length() > MAX_WIDTH) 
				{
					throw new JsonSyntaxException("Invalid pattern: too many columns, " + MAX_WIDTH + " is maximum");
				}

				if(i > 0 && astring[0].length() != s.length()) 
				{
					throw new JsonSyntaxException("Invalid pattern: each row must be the same width");
				}

				astring[i] = s;
			}

			return astring;
		}
	}

	public static Map<String, Ingredient> keyFromJson(JsonObject json) 
	{
		Map<String, Ingredient> map = Maps.newHashMap();

		for(Entry<String, JsonElement> entry : json.entrySet()) 
		{
			if(entry.getKey().length() != 1) 
			{
				throw new JsonSyntaxException("Invalid key entry: '" + (String) entry.getKey() + "' is an invalid symbol (must be 1 character only).");
			}

			if(" ".equals(entry.getKey())) 
			{
				throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
			}

			map.put(entry.getKey(), Ingredient.fromJson(entry.getValue()));
		}

		map.put(" ", Ingredient.EMPTY);
		return map;
	}

	public static ItemStack itemFromJson(JsonObject json) 
	{
		if(json.has("data")) 
		{
			throw new JsonParseException("Disallowed data tag found");
		} 
		else 
		{
			return CraftingHelper.getItemStack(json, true);
		}
	}

	@Override
	public IRecipeType<?> getType() 
	{
		return RecipeInit.PALEONTOLOGY_TABLE_RECIPE;
	}
}
