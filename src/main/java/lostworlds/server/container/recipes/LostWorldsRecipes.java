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
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.core.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsRecipes {
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, LostWorldsUtils.ID);

	public static final RecipeSerializer<FossilCleanerRecipe> FOSSIL_CLEANER_RECIPE_SERIALIZER = new FossilCleanerRecipeSerialiser();
	public static final RecipeSerializer<FossilGrinderRecipe> FOSSIL_GRINDER_RECIPE_SERIALIZER = new FossilGrinderRecipeSerialiser();
	public static final RecipeSerializer<DNAExtractorRecipe> DNA_EXTRACTOR_RECIPE_SERIALIZER = new DNAExtractorRecipeSerialiser();
	public static final RecipeSerializer<AmberDNAExtractorRecipe> AMBER_DNA_EXTRACTOR_RECIPE_SERIALIZER = new AmberDNAExtractorRecipeSerialiser();
	public static final RecipeSerializer<AnalyzerRecipe> ANALYZER_RECIPE_SERIALIZER = new AnalyzerRecipeSerialiser();
	public static final RecipeSerializer<DNAInjectorRecipe> DNA_INJECTOR_RECIPE_SERIALIZER = new DNAInjectorRecipeSerialiser();
	public static final RecipeSerializer<CultivatorRecipe> CULTIVATOR_RECIPE_SERIALIZER = new CultivatorRecipeSerialiser();
	public static final RecipeSerializer<ArchaeologyTableRecipe> ARCHAEOLOGY_TABLE_RECIPE_SERIALIZER = new ArchaeologyTableRecipeSerialiser();
	public static final RecipeSerializer<PaleontologyTableRecipe> PALEONTOLOGY_TABLE_RECIPE_SERIALIZER = new PaleontologyTableRecipeSerialiser();
	public static final RecipeSerializer<TimeMachineRecipe> TIME_MACHINE_RECIPE_SERIALIZER = new TimeMachineRecipeSerialiser();

	public static final RecipeType<FossilCleanerRecipe> FOSSIL_CLEANER_RECIPE = register("fossil_cleaner");
	public static final RecipeType<FossilGrinderRecipe> FOSSIL_GRINDER_RECIPE = register("fossil_grinder");
	public static final RecipeType<DNAExtractorRecipe> DNA_EXTRACTOR_RECIPE = register("dna_extractor");
	public static final RecipeType<AmberDNAExtractorRecipe> AMBER_DNA_EXTRACTOR_RECIPE = register("amber_dna_extractor");
	public static final RecipeType<AnalyzerRecipe> ANALYZER_RECIPE = register("analyzer");
	public static final RecipeType<DNAInjectorRecipe> DNA_INJECTOR_RECIPE = register("dna_injector");
	public static final RecipeType<CultivatorRecipe> CULTIVATOR_RECIPE = register("cultivator");
	public static final RecipeType<ArchaeologyTableRecipe> ARCHAEOLOGY_TABLE_RECIPE = register("archaeology_table");
	public static final RecipeType<PaleontologyTableRecipe> PALEONTOLOGY_TABLE_RECIPE = register("paleontology_table");
	public static final RecipeType<TimeMachineRecipe> TIME_MACHINE_RECIPE = register("time_machine");

	public static final RegistryObject<RecipeSerializer<FossilCleanerRecipe>> FOSSIL_CLEANER_SERIALIZER = RECIPE_SERIALIZERS.register("fossil_cleaner", () -> FOSSIL_CLEANER_RECIPE_SERIALIZER);
	public static final RegistryObject<RecipeSerializer<FossilGrinderRecipe>> FOSSIL_GRINDER_SERIALIZER = RECIPE_SERIALIZERS.register("fossil_grinder", () -> FOSSIL_GRINDER_RECIPE_SERIALIZER);
	public static final RegistryObject<RecipeSerializer<AmberDNAExtractorRecipe>> AMBER_DNA_EXTRACTOR_SERIALIZER = RECIPE_SERIALIZERS.register("amber_dna_extractor", () -> AMBER_DNA_EXTRACTOR_RECIPE_SERIALIZER);
	public static final RegistryObject<RecipeSerializer<DNAExtractorRecipe>> DNA_EXTRACTOR_SERIALIZER = RECIPE_SERIALIZERS.register("dna_extractor", () -> DNA_EXTRACTOR_RECIPE_SERIALIZER);
	public static final RegistryObject<RecipeSerializer<AnalyzerRecipe>> ANALYZER_SERIALIZER = RECIPE_SERIALIZERS.register("analyzer", () -> ANALYZER_RECIPE_SERIALIZER);
	public static final RegistryObject<RecipeSerializer<DNAInjectorRecipe>> DNA_INJECTOR_SERIALIZER = RECIPE_SERIALIZERS.register("dna_injector", () -> DNA_INJECTOR_RECIPE_SERIALIZER);
	public static final RegistryObject<RecipeSerializer<CultivatorRecipe>> CULTIVATOR_SERIALIZER = RECIPE_SERIALIZERS.register("cultivator", () -> CULTIVATOR_RECIPE_SERIALIZER);
	public static final RegistryObject<RecipeSerializer<ArchaeologyTableRecipe>> ARCHAEOLOGY_TABLE_SERIALIZER = RECIPE_SERIALIZERS.register("archaeology_table", () -> ARCHAEOLOGY_TABLE_RECIPE_SERIALIZER);
	public static final RegistryObject<RecipeSerializer<PaleontologyTableRecipe>> PALEONTOLOGY_TABLE_SERIALIZER = RECIPE_SERIALIZERS.register("paleontology_table", () -> PALEONTOLOGY_TABLE_RECIPE_SERIALIZER);
	public static final RegistryObject<RecipeSerializer<TimeMachineRecipe>> TIME_MACHINE_SERIALIZER = RECIPE_SERIALIZERS.register("time_machine", () -> TIME_MACHINE_RECIPE_SERIALIZER);

	public static <T extends RecipeType> T register(String id) {
		return (T) Registry.register(Registry.RECIPE_TYPE, LostWorldsUtils.rL(id), new ModRecipeType<>());
	}

	public static void deferred(IEventBus bus) {
		RECIPE_SERIALIZERS.register(bus);
	}
}
