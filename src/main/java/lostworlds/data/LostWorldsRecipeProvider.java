package lostworlds.data;

import java.util.function.Consumer;

import lostworlds.server.LostWorldsTags;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.Plants;
import lostworlds.server.block.Trees;
import lostworlds.server.entity.utils.enums.AncientCreatures;
import lostworlds.server.menu.recipes.data.AnalyzerRecipeBuilder;
import lostworlds.server.menu.recipes.data.CultivatorRecipeBuilder;
import lostworlds.server.menu.recipes.data.DnaExtractorRecipeBuilder;
import lostworlds.server.menu.recipes.data.DnaInjectorRecipeBuilder;
import lostworlds.server.menu.recipes.data.FossilCleanerRecipeBuilder;
import lostworlds.server.menu.recipes.data.FossilGrinderRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

public class LostWorldsRecipeProvider extends RecipeProvider {
	public LostWorldsRecipeProvider(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
		for (AncientCreatures dinos : AncientCreatures.values()) {
			if (dinos != AncientCreatures.NAUTILUS && dinos != AncientCreatures.PALAEONISCUM && dinos != AncientCreatures.ANOMALOCARIS) {
				FossilCleanerRecipeBuilder.simple(Ingredient.of(dinos.getPlasteredSkullItem().get()), dinos.getSkullItem().get()).unlockedBy("has_item", has(dinos.getSkullItem().get())).save(consumer);
				FossilCleanerRecipeBuilder.simple(Ingredient.of(dinos.getPlasteredArmBonesItem().get()), dinos.getArmBonesItem().get()).unlockedBy("has_item", has(dinos.getArmBonesItem().get())).save(consumer);
				FossilCleanerRecipeBuilder.simple(Ingredient.of(dinos.getPlasteredLegBonesItem().get()), dinos.getLegBonesItem().get()).unlockedBy("has_item", has(dinos.getLegBonesItem().get())).save(consumer);
				FossilCleanerRecipeBuilder.simple(Ingredient.of(dinos.getPlasteredRibCageBonesItem().get()), dinos.getRibCageItem().get()).unlockedBy("has_item", has(dinos.getRibCageItem().get())).save(consumer);
				FossilCleanerRecipeBuilder.simple(Ingredient.of(dinos.getPlasteredTailBonesItem().get()), dinos.getTailItem().get()).unlockedBy("has_item", has(dinos.getTailItem().get())).save(consumer);
			}
			FossilGrinderRecipeBuilder.dino(Ingredient.of(dinos.getFossilTag()), dinos.getSoftTissue().get()).unlockedBy("has_item", has(dinos.getFossilTag())).save(consumer);
			DnaExtractorRecipeBuilder.simple(Ingredient.of(dinos.getSoftTissue().get()), dinos.getDNA().get()).unlockedBy("has_item", has(dinos.getSoftTissue().get())).save(consumer);
			DnaExtractorRecipeBuilder.simple(Ingredient.of(dinos.getBloodVile().get()), dinos.getDNA().get()).unlockedBy("has_item", has(dinos.getSoftTissue().get())).save(consumer, LostWorldsUtils.rL(dinos.getId() + "_dna_from_blood"));
			AnalyzerRecipeBuilder.simple(Ingredient.of(dinos.getDNA().get()), dinos.getDNADisc().get()).unlockedBy("has_item", has(dinos.getDNA().get())).save(consumer);
		}

		for (AncientCreatures dinos : AncientCreatures.hasSpawn()) {
			CultivatorRecipeBuilder.simple(Ingredient.of(dinos.getDNADisc().get()), dinos.getSpawn().get()).unlockedBy("has_item", has(dinos.getDNADisc().get())).save(consumer);
		}

		for (AncientCreatures dinos : AncientCreatures.eggLaying()) {
			DnaInjectorRecipeBuilder.simple(Ingredient.of(dinos.getDNADisc().get()), dinos.getEgg().get()).unlockedBy("has_item", has(dinos.getDNADisc().get())).save(consumer);
		}

		for (Trees trees : Trees.values()) {
			FossilGrinderRecipeBuilder.plant(Ingredient.of(trees.getBarkSample().get()), trees.getSoftTissue().get()).unlockedBy("has_item", has(trees.getBarkSample().get())).save(consumer);
			DnaExtractorRecipeBuilder.simple(Ingredient.of(trees.getSoftTissue().get()), trees.getDNA().get()).unlockedBy("has_item", has(trees.getSoftTissue().get())).save(consumer);
			AnalyzerRecipeBuilder.simple(Ingredient.of(trees.getDNA().get()), trees.getDNADisc().get()).unlockedBy("has_item", has(trees.getDNA().get())).save(consumer);
			CultivatorRecipeBuilder.simple(Ingredient.of(trees.getDNADisc().get()), trees.getSapling().get()).unlockedBy("has_item", has(trees.getDNADisc().get())).save(consumer);
		}

		for (Plants plants : Plants.values()) {
			FossilGrinderRecipeBuilder.plant(Ingredient.of(plants.getDrop().get()), plants.getSoftTissue().get()).unlockedBy("has_item", has(plants.getDrop().get())).save(consumer);
			DnaExtractorRecipeBuilder.simple(Ingredient.of(plants.getSoftTissue().get()), plants.getDNA().get()).unlockedBy("has_item", has(plants.getSoftTissue().get())).save(consumer);
			AnalyzerRecipeBuilder.simple(Ingredient.of(plants.getDNA().get()), plants.getDNADisc().get()).unlockedBy("has_item", has(plants.getDNA().get())).save(consumer);
			CultivatorRecipeBuilder.simple(Ingredient.of(plants.getDNADisc().get()), plants.getSeed().get()).unlockedBy("has_item", has(plants.getDNADisc().get())).save(consumer);
		}

		ShapelessRecipeBuilder.shapeless(Items.BONE_MEAL, 4).requires(Tags.Items.BONES).unlockedBy("has_item", has(LostWorldsTags.ModItemTags.FOSSILS.tag)).save(consumer, LostWorldsUtils.rL("bone_meal_from_bone"));
	}
}
