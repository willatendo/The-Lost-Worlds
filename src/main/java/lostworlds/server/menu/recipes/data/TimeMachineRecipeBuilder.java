package lostworlds.server.menu.recipes.data;

import java.util.function.Consumer;

import com.google.gson.JsonObject;

import lostworlds.server.menu.recipes.LostWorldsRecipeSerializers;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;

public class TimeMachineRecipeBuilder {
	private final Item output;
	private final int count;
	private final Advancement.Builder advancement = Advancement.Builder.advancement();

	private TimeMachineRecipeBuilder(ItemLike output, int count) {
		this.output = output.asItem();
		this.count = count;
	}

	public static TimeMachineRecipeBuilder simple(ItemLike output, int count) {
		return new TimeMachineRecipeBuilder(output, count);
	}

	public TimeMachineRecipeBuilder unlockedBy(String name, CriterionTriggerInstance criteria) {
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
		consumer.accept(new TimeMachineRecipeBuilder.Result(name, this.output, this.count, this.advancement, new ResourceLocation(name.getNamespace(), "recipes/" + this.output.getItemCategory().getRecipeFolderName() + "/" + name.getPath())));
	}

	private void ensureValid(ResourceLocation id) {
		if (this.advancement.getCriteria().isEmpty()) {
			throw new IllegalStateException("No way of obtaining recipe " + id);
		}
	}

	public static class Result implements FinishedRecipe {
		private final ResourceLocation id;
		private final Item output;
		private final int count;
		private final Advancement.Builder advancement;
		private final ResourceLocation advancementId;

		public Result(ResourceLocation id, Item output, int count, Advancement.Builder advancement, ResourceLocation advancementId) {
			this.id = id;
			this.output = output;
			this.count = count;
			this.advancement = advancement;
			this.advancementId = advancementId;
		}

		@Override
		public void serializeRecipeData(JsonObject json) {
			json.addProperty("output", Registry.ITEM.getKey(this.output).toString());
			json.addProperty("count", this.count);
		}

		@Override
		public RecipeSerializer<?> getType() {
			return LostWorldsRecipeSerializers.TIME_MACHINE_RECIPE_SERIALIZER;
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