package lostworlds.server.container.recipes;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.container.recipes.serialiser.AmberDNAExtractorRecipeSerialiser;
import lostworlds.server.container.recipes.serialiser.AnalyzerRecipeSerialiser;
import lostworlds.server.container.recipes.serialiser.ArchaeologyTableRecipeSerialiser;
import lostworlds.server.container.recipes.serialiser.CultivatorRecipeSerialiser;
import lostworlds.server.container.recipes.serialiser.DNAExtractorRecipeSerialiser;
import lostworlds.server.container.recipes.serialiser.DNAInjectorRecipeSerialiser;
import lostworlds.server.container.recipes.serialiser.FossilCleanerRecipeSerialiser;
import lostworlds.server.container.recipes.serialiser.FossilGrinderRecipeSerialiser;
import lostworlds.server.container.recipes.serialiser.PaleontologyTableRecipeSerialiser;
import lostworlds.server.container.recipes.serialiser.TimeMachineRecipeSerialiser;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsRecipes {
	public static final IRecipeSerializer<FossilCleanerRecipe> FOSSIL_CLEANER_RECIPE_SERIALIZER = new FossilCleanerRecipeSerialiser();
	public static final IRecipeSerializer<FossilGrinderRecipe> FOSSIL_GRINDER_RECIPE_SERIALIZER = new FossilGrinderRecipeSerialiser();
	public static final IRecipeSerializer<DNAExtractorRecipe> DNA_EXTRACTOR_RECIPE_SERIALIZER = new DNAExtractorRecipeSerialiser();
	public static final IRecipeSerializer<AmberDNAExtractorRecipe> AMBER_DNA_EXTRACTOR_RECIPE_SERIALIZER = new AmberDNAExtractorRecipeSerialiser();
	public static final IRecipeSerializer<AnalyzerRecipe> ANALYZER_RECIPE_SERIALIZER = new AnalyzerRecipeSerialiser();
	public static final IRecipeSerializer<DNAInjectorRecipe> DNA_INJECTOR_RECIPE_SERIALIZER = new DNAInjectorRecipeSerialiser();
	public static final IRecipeSerializer<CultivatorRecipe> CULTIVATOR_RECIPE_SERIALIZER = new CultivatorRecipeSerialiser();
	public static final IRecipeSerializer<ArchaeologyTableRecipe> ARCHAEOLOGY_TABLE_RECIPE_SERIALIZER = new ArchaeologyTableRecipeSerialiser();
	public static final IRecipeSerializer<PaleontologyTableRecipe> PALEONTOLOGY_TABLE_RECIPE_SERIALIZER = new PaleontologyTableRecipeSerialiser();
	public static final IRecipeSerializer<TimeMachineRecipe> TIME_MACHINE_RECIPE_SERIALIZER = new TimeMachineRecipeSerialiser();

	public static final IRecipeType<FossilCleanerRecipe> FOSSIL_CLEANER_RECIPE = register("fossil_cleaner");
	public static final IRecipeType<FossilGrinderRecipe> FOSSIL_GRINDER_RECIPE = register("fossil_grinder");
	public static final IRecipeType<DNAExtractorRecipe> DNA_EXTRACTOR_RECIPE = register("dna_extractor");
	public static final IRecipeType<AmberDNAExtractorRecipe> AMBER_DNA_EXTRACTOR_RECIPE = register("amber_dna_extractor");
	public static final IRecipeType<AnalyzerRecipe> ANALYZER_RECIPE = register("analyzer");
	public static final IRecipeType<DNAInjectorRecipe> DNA_INJECTOR_RECIPE = register("dna_injector");
	public static final IRecipeType<CultivatorRecipe> CULTIVATOR_RECIPE = register("cultivator");
	public static final IRecipeType<ArchaeologyTableRecipe> ARCHAEOLOGY_TABLE_RECIPE = register("archaeology_table");
	public static final IRecipeType<PaleontologyTableRecipe> PALEONTOLOGY_TABLE_RECIPE = register("paleontology_table");
	public static final IRecipeType<TimeMachineRecipe> TIME_MACHINE_RECIPE = register("time_machine");

	public static final IRecipeSerializer<?> FOSSIL_CLEANER_SERIALIZER = register("fossil_cleaner", FOSSIL_CLEANER_RECIPE_SERIALIZER);
	public static final IRecipeSerializer<?> FOSSIL_GRINDER_SERIALIZER = register("fossil_grinder", FOSSIL_GRINDER_RECIPE_SERIALIZER);
	public static final IRecipeSerializer<?> AMBER_DNA_EXTRACTOR_SERIALIZER = register("amber_dna_extractor", AMBER_DNA_EXTRACTOR_RECIPE_SERIALIZER);
	public static final IRecipeSerializer<?> DNA_EXTRACTOR_SERIALIZER = register("dna_extractor", DNA_EXTRACTOR_RECIPE_SERIALIZER);
	public static final IRecipeSerializer<?> ANALYZER_SERIALIZER = register("analyzer", ANALYZER_RECIPE_SERIALIZER);
	public static final IRecipeSerializer<?> DNA_INJECTOR_SERIALIZER = register("dna_injector", DNA_INJECTOR_RECIPE_SERIALIZER);
	public static final IRecipeSerializer<?> CULTIVATOR_SERIALIZER = register("cultivator", CULTIVATOR_RECIPE_SERIALIZER);
	public static final IRecipeSerializer<?> ARCHAEOLOGY_TABLE_SERIALIZER = register("archaeology_table", ARCHAEOLOGY_TABLE_RECIPE_SERIALIZER);
	public static final IRecipeSerializer<?> PALEONTOLOGY_TABLE_SERIALIZER = register("paleontology_table", PALEONTOLOGY_TABLE_RECIPE_SERIALIZER);
	public static final IRecipeSerializer<?> TIME_MACHINE_SERIALIZER = register("time_machine", TIME_MACHINE_RECIPE_SERIALIZER);

	public static <T extends IRecipeType> T register(String id) {
		return (T) Registry.register(Registry.RECIPE_TYPE, LostWorldsUtils.rL(id), new ModRecipeType<>());
	}

	public static IRecipeSerializer<?> register(String id, IRecipeSerializer<?> recipe) {
		recipe.setRegistryName(LostWorldsUtils.rL(id));
		ForgeRegistries.RECIPE_SERIALIZERS.register(recipe);
		return recipe;
	}

	public static void init() {
		LostWorldsUtils.LOGGER.debug("Registering Mod Recipes");
	}
}
