package lostworlds.server.menu.recipes;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.menu.recipes.serialiser.AnalyzerRecipeSerialiser;
import lostworlds.server.menu.recipes.serialiser.ArchaeologyTableRecipeSerialiser;
import lostworlds.server.menu.recipes.serialiser.CultivatorRecipeSerialiser;
import lostworlds.server.menu.recipes.serialiser.DNAExtractorRecipeSerialiser;
import lostworlds.server.menu.recipes.serialiser.DNAInjectorRecipeSerialiser;
import lostworlds.server.menu.recipes.serialiser.FossilCleanerRecipeSerialiser;
import lostworlds.server.menu.recipes.serialiser.FossilGrinderRecipeSerialiser;
import lostworlds.server.menu.recipes.serialiser.PaleontologyTableRecipeSerialiser;
import lostworlds.server.menu.recipes.serialiser.TimeMachineRecipeSerialiser;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LostWorldsRecipeSerializers {
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.RECIPE_SERIALIZERS, LostWorldsUtils.ID);

	public static final RecipeSerializer<FossilCleanerRecipe> FOSSIL_CLEANER_RECIPE_SERIALIZER = new FossilCleanerRecipeSerialiser();
	public static final RecipeSerializer<FossilGrinderRecipe> FOSSIL_GRINDER_RECIPE_SERIALIZER = new FossilGrinderRecipeSerialiser();
	public static final RecipeSerializer<DNAExtractorRecipe> DNA_EXTRACTOR_RECIPE_SERIALIZER = new DNAExtractorRecipeSerialiser();
	public static final RecipeSerializer<AnalyzerRecipe> ANALYZER_RECIPE_SERIALIZER = new AnalyzerRecipeSerialiser();
	public static final RecipeSerializer<DNAInjectorRecipe> DNA_INJECTOR_RECIPE_SERIALIZER = new DNAInjectorRecipeSerialiser();
	public static final RecipeSerializer<CultivatorRecipe> CULTIVATOR_RECIPE_SERIALIZER = new CultivatorRecipeSerialiser();
	public static final RecipeSerializer<ArchaeologyTableRecipe> ARCHAEOLOGY_TABLE_RECIPE_SERIALIZER = new ArchaeologyTableRecipeSerialiser();
	public static final RecipeSerializer<PaleontologyTableRecipe> PALEONTOLOGY_TABLE_RECIPE_SERIALIZER = new PaleontologyTableRecipeSerialiser();
	public static final RecipeSerializer<TimeMachineRecipe> TIME_MACHINE_RECIPE_SERIALIZER = new TimeMachineRecipeSerialiser();

	public static final RegistryObject<RecipeSerializer<FossilCleanerRecipe>> FOSSIL_CLEANER_SERIALIZER = RECIPE_SERIALIZERS.register("fossil_cleaner", () -> FOSSIL_CLEANER_RECIPE_SERIALIZER);
	public static final RegistryObject<RecipeSerializer<FossilGrinderRecipe>> FOSSIL_GRINDER_SERIALIZER = RECIPE_SERIALIZERS.register("fossil_grinder", () -> FOSSIL_GRINDER_RECIPE_SERIALIZER);
	public static final RegistryObject<RecipeSerializer<DNAExtractorRecipe>> DNA_EXTRACTOR_SERIALIZER = RECIPE_SERIALIZERS.register("dna_extractor", () -> DNA_EXTRACTOR_RECIPE_SERIALIZER);
	public static final RegistryObject<RecipeSerializer<AnalyzerRecipe>> ANALYZER_SERIALIZER = RECIPE_SERIALIZERS.register("analyzer", () -> ANALYZER_RECIPE_SERIALIZER);
	public static final RegistryObject<RecipeSerializer<DNAInjectorRecipe>> DNA_INJECTOR_SERIALIZER = RECIPE_SERIALIZERS.register("dna_injector", () -> DNA_INJECTOR_RECIPE_SERIALIZER);
	public static final RegistryObject<RecipeSerializer<CultivatorRecipe>> CULTIVATOR_SERIALIZER = RECIPE_SERIALIZERS.register("cultivator", () -> CULTIVATOR_RECIPE_SERIALIZER);
	public static final RegistryObject<RecipeSerializer<ArchaeologyTableRecipe>> ARCHAEOLOGY_TABLE_SERIALIZER = RECIPE_SERIALIZERS.register("archaeology_table", () -> ARCHAEOLOGY_TABLE_RECIPE_SERIALIZER);
	public static final RegistryObject<RecipeSerializer<PaleontologyTableRecipe>> PALEONTOLOGY_TABLE_SERIALIZER = RECIPE_SERIALIZERS.register("paleontology_table", () -> PALEONTOLOGY_TABLE_RECIPE_SERIALIZER);
	public static final RegistryObject<RecipeSerializer<TimeMachineRecipe>> TIME_MACHINE_SERIALIZER = RECIPE_SERIALIZERS.register("time_machine", () -> TIME_MACHINE_RECIPE_SERIALIZER);
}
