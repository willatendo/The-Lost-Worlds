package lostworlds.server.container.recipes.data;

import java.util.function.Consumer;

import com.google.gson.JsonObject;

import lostworlds.server.container.recipes.LostWorldsRecipes;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;

public class DnaInjectorRecipeBuilder {
	private final Item output;
	private final Ingredient input;
	private final Ingredient storage;
	private final Advancement.Builder advancement = Advancement.Builder.advancement();

	private DnaInjectorRecipeBuilder(ItemLike output, Ingredient input, Ingredient storage) {
		this.output = output.asItem();
		this.input = input;
		this.storage = storage;
	}

	public static DnaInjectorRecipeBuilder simple(Ingredient input, Ingredient storage, ItemLike output) {
		return new DnaInjectorRecipeBuilder(output, input, storage);
	}

	public static DnaInjectorRecipeBuilder simple(Ingredient input, ItemLike output) {
		return simple(input, Ingredient.of(Items.EGG), output);
	}

	public DnaInjectorRecipeBuilder unlockedBy(String name, CriterionTriggerInstance criteria) {
		this.advancement.addCriterion(name, criteria);
		return this;
	}

	public void save(Consumer<FinishedRecipe> consumer) {
		this.save(consumer, Registry.ITEM.getKey(this.output));
	}

	public void save(Consumer<FinishedRecipe> consumer, String name) {
		ResourceLocation resourcelocation = Registry.ITEM.getKey(this.output);
		ResourceLocation resourcelocation1 = new ResourceLocation(name);
		if (resourcelocation1.equals(resourcelocation)) {
			throw new IllegalStateException("Recipe " + resourcelocation1 + " should remove its 'save' argument");
		} else {
			this.save(consumer, resourcelocation1);
		}
	}

	public void save(Consumer<FinishedRecipe> consumer, ResourceLocation name) {
		this.ensureValid(name);
		this.advancement.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(name)).rewards(AdvancementRewards.Builder.recipe(name)).requirements(RequirementsStrategy.OR);
		consumer.accept(new DnaInjectorRecipeBuilder.Result(name, this.input, this.storage, this.output, this.advancement, new ResourceLocation(name.getNamespace(), "recipes/" + this.output.getItemCategory().getRecipeFolderName() + "/" + name.getPath())));
	}

	private void ensureValid(ResourceLocation id) {
		if (this.advancement.getCriteria().isEmpty()) {
			throw new IllegalStateException("No way of obtaining recipe " + id);
		}
	}

	public static class Result implements FinishedRecipe {
		private final ResourceLocation id;
		private final Item output;
		private final Ingredient input;
		private final Ingredient storage;
		private final Advancement.Builder advancement;
		private final ResourceLocation advancementId;

		public Result(ResourceLocation id, Ingredient input, Ingredient storage, Item output, Advancement.Builder advancement, ResourceLocation advancementId) {
			this.id = id;
			this.input = input;
			this.storage = storage;
			this.output = output;
			this.advancement = advancement;
			this.advancementId = advancementId;
		}

		@Override
		public void serializeRecipeData(JsonObject json) {
			json.add("input", this.input.toJson());
			json.add("storage", this.storage.toJson());
			json.addProperty("output", Registry.ITEM.getKey(this.output).toString());
		}

		@Override
		public RecipeSerializer<?> getType() {
			return LostWorldsRecipes.DNA_INJECTOR_RECIPE_SERIALIZER;
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