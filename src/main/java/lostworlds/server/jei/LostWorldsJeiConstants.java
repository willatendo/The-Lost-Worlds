package lostworlds.server.jei;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.container.recipes.AnalyzerRecipe;
import lostworlds.server.container.recipes.ArchaeologyTableRecipe;
import lostworlds.server.container.recipes.CultivatorRecipe;
import lostworlds.server.container.recipes.DNAExtractorRecipe;
import lostworlds.server.container.recipes.DNAInjectorRecipe;
import lostworlds.server.container.recipes.FossilCleanerRecipe;
import lostworlds.server.container.recipes.FossilGrinderRecipe;
import lostworlds.server.container.recipes.PaleontologyTableRecipe;
import lostworlds.server.container.recipes.TimeMachineRecipe;
import lostworlds.server.jei.recipe.LightningRecipe;
import lostworlds.server.jei.recipe.WaterFuelRecipe;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.resources.ResourceLocation;

public class LostWorldsJeiConstants {
	public static final ResourceLocation LOST_WORLDS_PLUGIN = LostWorldsUtils.rL("lostworlds_plugin");

	public static final RecipeType WATER_FUEL_CATEGORY = RecipeType.create("lostworlds", "water_fuel_category", WaterFuelRecipe.class);
	public static final RecipeType FOSSIL_CLEANER_CATEGORY = RecipeType.create("lostworlds", "fossil_cleaner_category", FossilCleanerRecipe.class);
	public static final RecipeType FOSSIL_GRINDER_CATEGORY = RecipeType.create("lostworlds", "fossil_grinder_category", FossilGrinderRecipe.class);
	public static final RecipeType DNA_EXTRACTOR_CATEGORY = RecipeType.create("lostworlds", "dna_extractor_category", DNAExtractorRecipe.class);
	public static final RecipeType ANALYZER_CATEGORY = RecipeType.create("lostworlds", "analyzer_category", AnalyzerRecipe.class);
	public static final RecipeType DNA_INJECTOR_CATEGORY = RecipeType.create("lostworlds", "dna_injector_category", DNAInjectorRecipe.class);
	public static final RecipeType CULTIVATOR_CATEGORY = RecipeType.create("lostworlds", "cultivator_category", CultivatorRecipe.class);
	public static final RecipeType LIGHTNING_CATEGORY = RecipeType.create("lostworlds", "lightning_category", LightningRecipe.class);
	public static final RecipeType TIME_MACHINE_CATEGORY = RecipeType.create("lostworlds", "time_machine_category", TimeMachineRecipe.class);
	public static final RecipeType ARCHAEOLOGY_TABLE_CATEGORY = RecipeType.create("lostworlds", "archaeology_table_category", ArchaeologyTableRecipe.class);
	public static final RecipeType PALEONTOLOGY_TABLE_CATEGORY = RecipeType.create("lostworlds", "paleontology_table_category", PaleontologyTableRecipe.class);
}
