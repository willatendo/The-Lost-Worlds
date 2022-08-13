package lostworlds.server.menu.recipes;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class LostWorldsRecipeTypes {
	public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registry.RECIPE_TYPE_REGISTRY, LostWorldsUtils.ID);
	public static final RegistryObject<RecipeType<FossilCleanerRecipe>> FOSSIL_CLEANER_RECIPE = register("fossil_cleaner");
	public static final RegistryObject<RecipeType<FossilGrinderRecipe>> FOSSIL_GRINDER_RECIPE = register("fossil_grinder");
	public static final RegistryObject<RecipeType<DNAExtractorRecipe>> DNA_EXTRACTOR_RECIPE = register("dna_extractor");
	public static final RegistryObject<RecipeType<AnalyzerRecipe>> ANALYZER_RECIPE = register("analyzer");
	public static final RegistryObject<RecipeType<DNAInjectorRecipe>> DNA_INJECTOR_RECIPE = register("dna_injector");
	public static final RegistryObject<RecipeType<CultivatorRecipe>> CULTIVATOR_RECIPE = register("cultivator");
	public static final RegistryObject<RecipeType<ArchaeologyTableRecipe>> ARCHAEOLOGY_TABLE_RECIPE = register("archaeology_table");
	public static final RegistryObject<RecipeType<PaleontologyTableRecipe>> PALEONTOLOGY_TABLE_RECIPE = register("paleontology_table");
	public static final RegistryObject<RecipeType<TimeMachineRecipe>> TIME_MACHINE_RECIPE = register("time_machine");

	private static <T extends Recipe<?>> RegistryObject<RecipeType<T>> register(String id) {
		return RECIPE_TYPES.register(id, () -> new RecipeType<T>() {
		});
	}
}
