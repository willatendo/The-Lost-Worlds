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
				FossilCleanerRecipeBuilder.simple(Ingredient.of(dinos.getPlasteredSkullItem()), dinos.getSkullItem()).unlockedBy("has_item", has(dinos.getSkullItem())).save(consumer);
				FossilCleanerRecipeBuilder.simple(Ingredient.of(dinos.getPlasteredArmBonesItem()), dinos.getArmBonesItem()).unlockedBy("has_item", has(dinos.getArmBonesItem())).save(consumer);
				FossilCleanerRecipeBuilder.simple(Ingredient.of(dinos.getPlasteredLegBonesItem()), dinos.getLegBonesItem()).unlockedBy("has_item", has(dinos.getLegBonesItem())).save(consumer);
				FossilCleanerRecipeBuilder.simple(Ingredient.of(dinos.getPlasteredRibCageBonesItem()), dinos.getRibCageItem()).unlockedBy("has_item", has(dinos.getRibCageItem())).save(consumer);
				FossilCleanerRecipeBuilder.simple(Ingredient.of(dinos.getPlasteredTailBonesItem()), dinos.getTailItem()).unlockedBy("has_item", has(dinos.getTailItem())).save(consumer);
			}
			DnaExtractorRecipeBuilder.simple(Ingredient.of(dinos.getSoftTissue()), dinos.getDNA()).unlockedBy("has_item", has(dinos.getDNA())).save(consumer);
			AnalyzerRecipeBuilder.simple(Ingredient.of(dinos.getDNA()), dinos.getDNADisc()).unlockedBy("has_item", has(dinos.getDNA())).save(consumer);
		}
	}
}
