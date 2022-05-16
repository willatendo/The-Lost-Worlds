package lostworlds.server.container.recipes.data;

import java.util.function.Consumer;

import com.google.gson.JsonObject;

import lostworlds.server.container.recipes.LostWorldsRecipes;
import lostworlds.server.item.LostWorldsItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class DnaExtractorRecipeBuilder {
	private final Item output;
	private final Ingredient input;
	private final Ingredient storage;
	private final Advancement.Builder advancement = Advancement.Builder.advancement();

	private DnaExtractorRecipeBuilder(IItemProvider output, Ingredient input, Ingredient vile) {
		this.output = output.asItem();
		this.input = input;
		this.storage = vile;
	}

	public static DnaExtractorRecipeBuilder simple(Ingredient input, Ingredient storage, IItemProvider output) {
		return new DnaExtractorRecipeBuilder(output, input, storage);
	}

	public static DnaExtractorRecipeBuilder simple(Ingredient input, IItemProvider output) {
		return simple(input, Ingredient.of(LostWorldsItems.EMPTY_VILE.get()), output);
	}

	public DnaExtractorRecipeBuilder unlockedBy(String name, ICriterionInstance criteria) {
		this.advancement.addCriterion(name, criteria);
		return this;
	}

	public void save(Consumer<IFinishedRecipe> consumer) {
		this.save(consumer, Registry.ITEM.getKey(this.output));
	}

	public void save(Consumer<IFinishedRecipe> consumer, String name) {
		ResourceLocation resourcelocation = Registry.ITEM.getKey(this.output);
		ResourceLocation resourcelocation1 = new ResourceLocation(name);
		if (resourcelocation1.equals(resourcelocation)) {
			throw new IllegalStateException("Recipe " + resourcelocation1 + " should remove its 'save' argument");
		} else {
			this.save(consumer, resourcelocation1);
		}
	}

	public void save(Consumer<IFinishedRecipe> consumer, ResourceLocation name) {
		this.ensureValid(name);
		this.advancement.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(name)).rewards(AdvancementRewards.Builder.recipe(name)).requirements(IRequirementsStrategy.OR);
		consumer.accept(new DnaExtractorRecipeBuilder.Result(name, this.input, this.storage, this.output, this.advancement, new ResourceLocation(name.getNamespace(), "recipes/" + this.output.getItemCategory().getRecipeFolderName() + "/" + name.getPath())));
	}

	private void ensureValid(ResourceLocation id) {
		if (this.advancement.getCriteria().isEmpty()) {
			throw new IllegalStateException("No way of obtaining recipe " + id);
		}
	}

	public static class Result implements IFinishedRecipe {
		private final ResourceLocation id;
		private final Item result;
		private final Ingredient input;
		private final Ingredient storage;
		private final Advancement.Builder advancement;
		private final ResourceLocation advancementId;

		public Result(ResourceLocation id, Ingredient input, Ingredient storage, Item result, Advancement.Builder advancement, ResourceLocation advancementId) {
			this.id = id;
			this.input = input;
			this.storage = storage;
			this.result = result;
			this.advancement = advancement;
			this.advancementId = advancementId;
		}

		@Override
		public void serializeRecipeData(JsonObject json) {
			json.add("input", this.input.toJson());
			json.add("storage", this.storage.toJson());
			json.addProperty("output", Registry.ITEM.getKey(this.result).toString());
		}

		@Override
		public IRecipeSerializer<?> getType() {
			return LostWorldsRecipes.DNA_EXTRACTOR_RECIPE_SERIALIZER;
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