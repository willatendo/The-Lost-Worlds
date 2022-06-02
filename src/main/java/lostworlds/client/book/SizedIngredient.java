package lostworlds.client.book;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class SizedIngredient implements Predicate<ItemStack> {
	public static final SizedIngredient EMPTY = of(Ingredient.EMPTY, 0);

	private final Ingredient ingredient;
	private final int amount;

	private WeakReference<ItemStack[]> lastIngredientMatch;
	private List<ItemStack> matchingStacks;

	private SizedIngredient(Ingredient ingredient, int amount) {
		this.ingredient = ingredient;
		this.amount = amount;
	}

	public static SizedIngredient of(Ingredient ingredient, int amount) {
		return new SizedIngredient(ingredient, amount);
	}

	public static SizedIngredient of(Ingredient ingredient) {
		return of(ingredient, 1);
	}

	public static SizedIngredient fromItems(int amount, ItemLike... items) {
		return of(Ingredient.of(items), amount);
	}

	public static SizedIngredient fromItems(ItemLike... items) {
		return fromItems(1, items);
	}

	public static SizedIngredient fromTag(TagKey<Item> tag, int amount) {
		return of(Ingredient.of(tag), amount);
	}

	public static SizedIngredient fromTag(TagKey<Item> tag) {
		return fromTag(tag, 1);
	}

	@Override
	public boolean test(ItemStack stack) {
		return stack.getCount() >= this.amount && this.ingredient.test(stack);
	}

	public boolean hasNoMatchingStacks() {
		return this.ingredient.isEmpty();
	}

	public List<ItemStack> getMatchingStacks() {
		ItemStack[] ingredientMatch = this.ingredient.getItems();
		if (this.matchingStacks == null || this.lastIngredientMatch.get() != ingredientMatch) {
			this.matchingStacks = Arrays.stream(ingredientMatch).map(stack -> {
				if (stack.getCount() != this.amount) {
					stack = stack.copy();
					stack.setCount(this.amount);
				}
				return stack;
			}).collect(Collectors.toList());
			this.lastIngredientMatch = new WeakReference<>(ingredientMatch);
		}
		return this.matchingStacks;
	}

	public void write(FriendlyByteBuf buffer) {
		buffer.writeVarInt(this.amount);
		this.ingredient.toNetwork(buffer);
	}

	public JsonObject serialize() {
		JsonElement ingredient = this.ingredient.toJson();
		JsonObject json = null;
		if (ingredient.isJsonObject()) {
			json = ingredient.getAsJsonObject();
			if (json.has("ingredient") || json.has("amount_needed")) {
				json = null;
			}
		}
		if (json == null) {
			json = new JsonObject();
			json.add("ingredient", ingredient);
		}
		if (this.amount != 1) {
			json.addProperty("amount_needed", this.amount);
		}
		return json;
	}

	public static SizedIngredient read(FriendlyByteBuf buffer) {
		int amount = buffer.readVarInt();
		Ingredient ingredient = Ingredient.fromNetwork(buffer);
		return of(ingredient, amount);
	}

	public static SizedIngredient deserialize(JsonObject json) {
		int amount = GsonHelper.getAsInt(json, "amount_needed", 1);
		Ingredient ingredient;
		if (json.has("ingredient")) {
			ingredient = Ingredient.fromJson(JsonHelper.getElement(json, "ingredient"));
		} else {
			ingredient = Ingredient.fromJson(json);
		}

		return of(ingredient, amount);
	}
}
