package lostworlds.server.menu.recipes.data;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Consumer;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import lostworlds.server.menu.recipes.LostWorldsRecipeSerializers;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;

public class PaleontologyTableRecipeBuilder {
	private final Item result;
	private final int count;
	private final List<String> rows = Lists.newArrayList();
	private final Map<Character, Ingredient> key = Maps.newLinkedHashMap();
	private final Advancement.Builder advancement = Advancement.Builder.advancement();

	public PaleontologyTableRecipeBuilder(ItemLike output, int count) {
		this.result = output.asItem();
		this.count = count;
	}

	public static PaleontologyTableRecipeBuilder shaped(ItemLike output) {
		return shaped(output, 1);
	}

	public static PaleontologyTableRecipeBuilder shaped(ItemLike output, int count) {
		return new PaleontologyTableRecipeBuilder(output, count);
	}

	public PaleontologyTableRecipeBuilder define(Character character, ItemLike item) {
		return this.define(character, Ingredient.of(item));
	}

	public PaleontologyTableRecipeBuilder define(Character character, Ingredient ingreident) {
		if (this.key.containsKey(character)) {
			throw new IllegalArgumentException("Symbol '" + character + "' is already defined!");
		} else if (character == ' ') {
			throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
		} else {
			this.key.put(character, ingreident);
			return this;
		}
	}

	public PaleontologyTableRecipeBuilder pattern(String pattern) {
		if (!this.rows.isEmpty() && pattern.length() != this.rows.get(0).length()) {
			throw new IllegalArgumentException("Pattern must be the same width on every line!");
		} else {
			this.rows.add(pattern);
			return this;
		}
	}

	public PaleontologyTableRecipeBuilder unlockedBy(String has, CriterionTriggerInstance criteria) {
		this.advancement.addCriterion(has, criteria);
		return this;
	}

	public void save(Consumer<FinishedRecipe> consumer) {
		this.save(consumer, Registry.ITEM.getKey(this.result));
	}

	public void save(Consumer<FinishedRecipe> consumer, String id) {
		ResourceLocation resourcelocation = Registry.ITEM.getKey(this.result);
		if ((new ResourceLocation(id)).equals(resourcelocation)) {
			throw new IllegalStateException("Shaped Recipe " + id + " should remove its 'save' argument");
		} else {
			this.save(consumer, new ResourceLocation(id));
		}
	}

	public void save(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
		this.ensureValid(id);
		this.advancement.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
		consumer.accept(new PaleontologyTableRecipeBuilder.Result(id, this.result, this.count, this.rows, this.key, this.advancement, new ResourceLocation(id.getNamespace(), "recipes/" + this.result.getItemCategory().getRecipeFolderName() + "/" + id.getPath())));
	}

	private void ensureValid(ResourceLocation id) {
		if (this.rows.isEmpty()) {
			throw new IllegalStateException("No pattern is defined for shaped recipe " + id + "!");
		} else {
			Set<Character> set = Sets.newHashSet(this.key.keySet());
			set.remove(' ');

			for (String s : this.rows) {
				for (int i = 0; i < s.length(); ++i) {
					char c0 = s.charAt(i);
					if (!this.key.containsKey(c0) && c0 != ' ') {
						throw new IllegalStateException("Pattern in recipe " + id + " uses undefined symbol '" + c0 + "'");
					}

					set.remove(c0);
				}
			}

			if (!set.isEmpty()) {
				throw new IllegalStateException("Ingredients are defined but not used in pattern for recipe " + id);
			} else if (this.rows.size() == 1 && this.rows.get(0).length() == 1) {
				throw new IllegalStateException("Shaped recipe " + id + " only takes in a single item - should it be a shapeless recipe instead?");
			} else if (this.advancement.getCriteria().isEmpty()) {
				throw new IllegalStateException("No way of obtaining recipe " + id);
			}
		}
	}

	public class Result implements FinishedRecipe {
		private final ResourceLocation id;
		private final Item result;
		private final int count;
		private final List<String> pattern;
		private final Map<Character, Ingredient> key;
		private final Advancement.Builder advancement;
		private final ResourceLocation advancementId;

		public Result(ResourceLocation id, Item output, int count, List<String> pattern, Map<Character, Ingredient> key, Advancement.Builder advancement, ResourceLocation advancementId) {
			this.id = id;
			this.result = output;
			this.count = count;
			this.pattern = pattern;
			this.key = key;
			this.advancement = advancement;
			this.advancementId = advancementId;
		}

		@Override
		public void serializeRecipeData(JsonObject json) {
			JsonArray jsonarray = new JsonArray();

			for (String s : this.pattern) {
				jsonarray.add(s);
			}

			json.add("pattern", jsonarray);
			JsonObject jsonobject = new JsonObject();

			for (Entry<Character, Ingredient> entry : this.key.entrySet()) {
				jsonobject.add(String.valueOf(entry.getKey()), entry.getValue().toJson());
			}

			json.add("key", jsonobject);
			JsonObject jsonobject1 = new JsonObject();
			jsonobject1.addProperty("item", Registry.ITEM.getKey(this.result).toString());
			if (this.count > 1) {
				jsonobject1.addProperty("count", this.count);
			}

			json.add("output", jsonobject1);
		}

		@Override
		public RecipeSerializer<?> getType() {
			return LostWorldsRecipeSerializers.PALEONTOLOGY_TABLE_RECIPE_SERIALIZER;
		}

		@Override
		public ResourceLocation getId() {
			return this.id;
		}

		@Override
		public JsonObject serializeAdvancement() {
			return this.advancement.serializeToJson();
		}

		@Override
		public ResourceLocation getAdvancementId() {
			return this.advancementId;
		}
	}
}
