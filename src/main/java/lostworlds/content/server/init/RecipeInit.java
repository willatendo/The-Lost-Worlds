package lostworlds.content.server.init;

import lostworlds.content.ModRegistry;
import lostworlds.content.ModUtils;
import lostworlds.library.container.recipes.AmberDNAExtractorRecipe;
import lostworlds.library.container.recipes.AmberDNAExtractorRecipeSerialiser;
import lostworlds.library.container.recipes.AnalyzerRecipe;
import lostworlds.library.container.recipes.AnalyzerRecipeSerialiser;
import lostworlds.library.container.recipes.ArchaeologyTableRecipe;
import lostworlds.library.container.recipes.CultivatorRecipe;
import lostworlds.library.container.recipes.CultivatorRecipeSerialiser;
import lostworlds.library.container.recipes.DNAExtractorRecipe;
import lostworlds.library.container.recipes.DNAExtractorRecipeSerialiser;
import lostworlds.library.container.recipes.DNAInjectorRecipe;
import lostworlds.library.container.recipes.DNAInjectorRecipeSerialiser;
import lostworlds.library.container.recipes.FossilCleanerRecipe;
import lostworlds.library.container.recipes.FossilCleanerRecipeSerialiser;
import lostworlds.library.container.recipes.FossilGrinderRecipe;
import lostworlds.library.container.recipes.FossilGrinderRecipeSerialiser;
import lostworlds.library.container.recipes.PaleontologyTableRecipe;
import lostworlds.library.container.recipes.TimeMachineRecipe;
import lostworlds.library.container.recipes.TimeMachineRecipeSerialiser;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RecipeInit 
{
	public static final IRecipeSerializer<FossilCleanerRecipe> FOSSIL_CLEANER_RECIPE_SERIALIZER = new FossilCleanerRecipeSerialiser();
	public static final IRecipeSerializer<FossilGrinderRecipe> FOSSIL_GRINDER_RECIPE_SERIALIZER = new FossilGrinderRecipeSerialiser();
	public static final IRecipeSerializer<DNAExtractorRecipe> DNA_EXTRACTOR_RECIPE_SERIALIZER = new DNAExtractorRecipeSerialiser();
	public static final IRecipeSerializer<AmberDNAExtractorRecipe> AMBER_DNA_EXTRACTOR_RECIPE_SERIALIZER = new AmberDNAExtractorRecipeSerialiser();
	public static final IRecipeSerializer<AnalyzerRecipe> ANALYZER_RECIPE_SERIALIZER = new AnalyzerRecipeSerialiser();
	public static final IRecipeSerializer<DNAInjectorRecipe> DNA_INJECTOR_RECIPE_SERIALIZER = new DNAInjectorRecipeSerialiser();
	public static final IRecipeSerializer<CultivatorRecipe> CULTIVATOR_RECIPE_SERIALIZER = new CultivatorRecipeSerialiser();
	public static final IRecipeSerializer<ArchaeologyTableRecipe> ARCHAEOLOGY_TABLE_RECIPE_SERIALIZER = new ArchaeologyTableRecipe.Serializer();
	public static final IRecipeSerializer<PaleontologyTableRecipe> PALEONTOLOGY_TABLE_RECIPE_SERIALIZER = new PaleontologyTableRecipe.Serializer();
	public static final IRecipeSerializer<TimeMachineRecipe> TIME_MACHINE_RECIPE_SERIALIZER = new TimeMachineRecipeSerialiser();

	public static final IRecipeType<FossilCleanerRecipe> FOSSIL_CLEANER_RECIPE = registerType("fossil_cleaner");
	public static final IRecipeType<FossilGrinderRecipe> FOSSIL_GRINDER_RECIPE = registerType("fossil_grinder");
	public static final IRecipeType<DNAExtractorRecipe> DNA_EXTRACTOR_RECIPE = registerType("dna_extractor");
	public static final IRecipeType<AmberDNAExtractorRecipe> AMBER_DNA_EXTRACTOR_RECIPE = registerType("amber_dna_extractor");
	public static final IRecipeType<AnalyzerRecipe> ANALYZER_RECIPE = registerType("analyzer");
	public static final IRecipeType<DNAInjectorRecipe> DNA_INJECTOR_RECIPE = registerType("dna_injector");
	public static final IRecipeType<CultivatorRecipe> CULTIVATOR_RECIPE = registerType("cultivator");
	public static final IRecipeType<ArchaeologyTableRecipe> ARCHAEOLOGY_TABLE_RECIPE = registerType("archaeology_table");
	public static final IRecipeType<PaleontologyTableRecipe> PALEONTOLOGY_TABLE_RECIPE = registerType("paleontology_table");
	public static final IRecipeType<TimeMachineRecipe> TIME_MACHINE_RECIPE = registerType("time_machine");

	public static final IRecipeSerializer<?> FOSSIL_CLEANER_SERIALIZER = ModRegistry.register("fossil_cleaner", FOSSIL_CLEANER_RECIPE_SERIALIZER);
	public static final IRecipeSerializer<?> FOSSIL_GRINDER_SERIALIZER = ModRegistry.register("fossil_grinder", FOSSIL_GRINDER_RECIPE_SERIALIZER);
	public static final IRecipeSerializer<?> AMBER_DNA_EXTRACTOR_SERIALIZER = ModRegistry.register("amber_dna_extractor", AMBER_DNA_EXTRACTOR_RECIPE_SERIALIZER);
	public static final IRecipeSerializer<?> DNA_EXTRACTOR_SERIALIZER = ModRegistry.register("dna_extractor", DNA_EXTRACTOR_RECIPE_SERIALIZER);
	public static final IRecipeSerializer<?> ANALYZER_SERIALIZER = ModRegistry.register("analyzer", ANALYZER_RECIPE_SERIALIZER);
	public static final IRecipeSerializer<?> DNA_INJECTOR_SERIALIZER = ModRegistry.register("dna_injector", DNA_INJECTOR_RECIPE_SERIALIZER);
	public static final IRecipeSerializer<?> CULTIVATOR_SERIALIZER = ModRegistry.register("cultivator", CULTIVATOR_RECIPE_SERIALIZER);
	public static final IRecipeSerializer<?> ARCHAEOLOGY_TABLE_SERIALIZER = ModRegistry.register("archaeology_table", ARCHAEOLOGY_TABLE_RECIPE_SERIALIZER);
	public static final IRecipeSerializer<?> PALEONTOLOGY_TABLE_SERIALIZER = ModRegistry.register("paleontology_table", PALEONTOLOGY_TABLE_RECIPE_SERIALIZER);
	public static final IRecipeSerializer<?> TIME_MACHINE_SERIALIZER = ModRegistry.register("time_machine", TIME_MACHINE_RECIPE_SERIALIZER);
	
	private static class RecipeType<T extends IRecipe<?>> implements IRecipeType<T> 
	{
		@Override
		public String toString() 
		{
			return Registry.RECIPE_TYPE.getKey(this).toString();
		}
	}
	
	private static <T extends IRecipeType> T registerType(String recipeTypeId) 
	{
		return (T) Registry.register(Registry.RECIPE_TYPE, ModUtils.rL(recipeTypeId), new RecipeType<>());
	}
	
	//Registry
	public static void init() { ModUtils.LOGGER.debug("Registering Mod Recipes"); }
}
