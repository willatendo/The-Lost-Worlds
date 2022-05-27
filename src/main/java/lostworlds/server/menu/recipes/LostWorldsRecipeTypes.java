package lostworlds.server.menu.recipes;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public class LostWorldsRecipeTypes<T extends Recipe<?>> implements RecipeType<T> {
	public static final RecipeType<FossilCleanerRecipe> FOSSIL_CLEANER_RECIPE = register("fossil_cleaner");
	public static final RecipeType<FossilGrinderRecipe> FOSSIL_GRINDER_RECIPE = register("fossil_grinder");
	public static final RecipeType<DNAExtractorRecipe> DNA_EXTRACTOR_RECIPE = register("dna_extractor");
	public static final RecipeType<AnalyzerRecipe> ANALYZER_RECIPE = register("analyzer");
	public static final RecipeType<DNAInjectorRecipe> DNA_INJECTOR_RECIPE = register("dna_injector");
	public static final RecipeType<CultivatorRecipe> CULTIVATOR_RECIPE = register("cultivator");
	public static final RecipeType<ArchaeologyTableRecipe> ARCHAEOLOGY_TABLE_RECIPE = register("archaeology_table");
	public static final RecipeType<PaleontologyTableRecipe> PALEONTOLOGY_TABLE_RECIPE = register("paleontology_table");
	public static final RecipeType<TimeMachineRecipe> TIME_MACHINE_RECIPE = register("time_machine");

	private static <T extends Recipe<?>> RecipeType<T> register(String id) {
		LostWorldsRecipeTypes<T> type = new LostWorldsRecipeTypes<>(id);
		return type;
	}

	private String registryName;

	private LostWorldsRecipeTypes(String name) {
		this.registryName = name;
	}

	@Override
	public String toString() {
		return LostWorldsUtils.ID + ":" + registryName;
	}

	private static void register(String id, RecipeType<?> recipeType) {
		Registry.register(Registry.RECIPE_TYPE, LostWorldsUtils.rL(id), recipeType);
	}

	public static void init() {
		register("fossil_cleaner", FOSSIL_CLEANER_RECIPE);
		register("fossil_grinder", FOSSIL_GRINDER_RECIPE);
		register("dna_extractor", DNA_EXTRACTOR_RECIPE);
		register("analyzer", ANALYZER_RECIPE);
		register("dna_injector", DNA_INJECTOR_RECIPE);
		register("cultivator", CULTIVATOR_RECIPE);
		register("archaeology_table", ARCHAEOLOGY_TABLE_RECIPE);
		register("paleontology_table", PALEONTOLOGY_TABLE_RECIPE);
		register("time_machine", TIME_MACHINE_RECIPE);
	}
}
