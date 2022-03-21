package lostworlds.data.recipe;

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

public class AnalyzerRecipeBuilder {
	private final Item result;
	private final Ingredient dna;
	private final Ingredient dnaDisc;
	private final Advancement.Builder advancement = Advancement.Builder.advancement();

	private AnalyzerRecipeBuilder(IItemProvider result, Ingredient dna, Ingredient dnaDisc) {
		this.result = result.asItem();
		this.dna = dna;
		this.dnaDisc = dnaDisc;
	}

	public static AnalyzerRecipeBuilder simple(Ingredient dna, Ingredient dnaDisc, IItemProvider result) {
		return new AnalyzerRecipeBuilder(result, dna, dnaDisc);
	}

	public static AnalyzerRecipeBuilder simple(Ingredient dna, IItemProvider result) {
		return simple(dna, Ingredient.of(LostWorldsItems.STORAGE_DISC), result);
	}

	public AnalyzerRecipeBuilder unlockedBy(String name, ICriterionInstance criteria) {
		this.advancement.addCriterion(name, criteria);
		return this;
	}

	public void save(Consumer<IFinishedRecipe> consumer) {
		this.save(consumer, Registry.ITEM.getKey(this.result));
	}

	public void save(Consumer<IFinishedRecipe> consumer, String name) {
		ResourceLocation resourcelocation = Registry.ITEM.getKey(this.result);
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
		consumer.accept(new AnalyzerRecipeBuilder.Result(name, this.dna, this.dnaDisc, this.result, this.advancement, new ResourceLocation(name.getNamespace(), "recipes/" + this.result.getItemCategory().getRecipeFolderName() + "/" + name.getPath())));
	}

	private void ensureValid(ResourceLocation id) {
		if (this.advancement.getCriteria().isEmpty()) {
			throw new IllegalStateException("No way of obtaining recipe " + id);
		}
	}

	public static class Result implements IFinishedRecipe {
		private final ResourceLocation id;
		private final Item result;
		private final Ingredient dna;
		private final Ingredient dnaDisc;
		private final Advancement.Builder advancement;
		private final ResourceLocation advancementId;

		public Result(ResourceLocation id, Ingredient dna, Ingredient dnaDisc, Item result, Advancement.Builder advancement, ResourceLocation advancementId) {
			this.id = id;
			this.dna = dna;
			this.dnaDisc = dnaDisc;
			this.result = result;
			this.advancement = advancement;
			this.advancementId = advancementId;
		}

		@Override
		public void serializeRecipeData(JsonObject json) {
			json.add("dna", this.dna.toJson());
			json.add("dnaDisc", this.dnaDisc.toJson());
			json.addProperty("output", Registry.ITEM.getKey(this.result).toString());
		}

		@Override
		public IRecipeSerializer<?> getType() {
			return LostWorldsRecipes.ANALYZER_RECIPE_SERIALIZER;
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