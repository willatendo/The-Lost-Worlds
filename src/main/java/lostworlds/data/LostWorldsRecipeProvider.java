package lostworlds.data;

import java.util.function.Consumer;

import lostworlds.data.recipe.AnalyzerRecipeBuilder;
import lostworlds.data.recipe.DnaExtractorRecipeBuilder;
import lostworlds.data.recipe.FossilCleanerRecipeBuilder;
import lostworlds.server.entity.utils.enums.DinoTypes;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.crafting.Ingredient;

public class LostWorldsRecipeProvider extends RecipeProvider {
	public LostWorldsRecipeProvider(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
		for (DinoTypes dinos : DinoTypes.values()) {
			if (dinos != DinoTypes.NAUTILUS && dinos != DinoTypes.PALAEONISCUM && dinos != DinoTypes.ANOMALOCARIS) {
				FossilCleanerRecipeBuilder.simple(Ingredient.of(dinos.getPlasteredSkullItem().get()), dinos.getSkullItem().get()).unlockedBy("has_item", has(dinos.getSkullItem().get())).save(consumer);
				FossilCleanerRecipeBuilder.simple(Ingredient.of(dinos.getPlasteredArmBonesItem().get()), dinos.getArmBonesItem().get()).unlockedBy("has_item", has(dinos.getArmBonesItem().get())).save(consumer);
				FossilCleanerRecipeBuilder.simple(Ingredient.of(dinos.getPlasteredLegBonesItem().get()), dinos.getLegBonesItem().get()).unlockedBy("has_item", has(dinos.getLegBonesItem().get())).save(consumer);
				FossilCleanerRecipeBuilder.simple(Ingredient.of(dinos.getPlasteredRibCageBonesItem().get()), dinos.getRibCageItem().get()).unlockedBy("has_item", has(dinos.getRibCageItem().get())).save(consumer);
				FossilCleanerRecipeBuilder.simple(Ingredient.of(dinos.getPlasteredTailBonesItem().get()), dinos.getTailItem().get()).unlockedBy("has_item", has(dinos.getTailItem().get())).save(consumer);
			}
			DnaExtractorRecipeBuilder.simple(Ingredient.of(dinos.getSoftTissue().get()), dinos.getDNA().get()).unlockedBy("has_item", has(dinos.getDNA().get())).save(consumer);
			AnalyzerRecipeBuilder.simple(Ingredient.of(dinos.getDNA().get()), dinos.getDNADisc().get()).unlockedBy("has_item", has(dinos.getDNA().get())).save(consumer);
		}
	}
}
