package lostworlds.content.server.init;

import lostworlds.content.ModRegistry;
import lostworlds.content.ModUtils;
import lostworlds.library.recipe.AnalyzerRecipe;
import lostworlds.library.recipe.AnalyzerRecipeSerialiser;
import lostworlds.library.recipe.ArchaeologyTableRecipe;
import lostworlds.library.recipe.CultivatorRecipe;
import lostworlds.library.recipe.CultivatorRecipeSerialiser;
import lostworlds.library.recipe.DNAExtractorRecipe;
import lostworlds.library.recipe.DNAExtractorRecipeSerialiser;
import lostworlds.library.recipe.DNAInjectorRecipe;
import lostworlds.library.recipe.DNAInjectorRecipeSerialiser;
import lostworlds.library.recipe.FossilGrinderRecipe;
import lostworlds.library.recipe.FossilGrinderRecipeSerialiser;
import lostworlds.library.recipe.TimeMachineRecipe;
import lostworlds.library.recipe.TimeMachineRecipeSerialiser;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class RecipeInit 
{
	public static final IRecipeSerializer<FossilGrinderRecipe> FOSSIL_GRINDER_RECIPE_SERIALIZER = new FossilGrinderRecipeSerialiser();
	public static final IRecipeSerializer<DNAExtractorRecipe> DNA_EXTRACTOR_RECIPE_SERIALIZER = new DNAExtractorRecipeSerialiser();
	public static final IRecipeSerializer<AnalyzerRecipe> ANALYZER_RECIPE_SERIALIZER = new AnalyzerRecipeSerialiser();
	public static final IRecipeSerializer<DNAInjectorRecipe> DNA_INJECTOR_RECIPE_SERIALIZER = new DNAInjectorRecipeSerialiser();
	public static final IRecipeSerializer<CultivatorRecipe> CULTIVATOR_RECIPE_SERIALIZER = new CultivatorRecipeSerialiser();
	public static final IRecipeSerializer<ArchaeologyTableRecipe> ARCHAEOLOGY_TABLE_RECIPE_SERIALIZER = new ArchaeologyTableRecipe.Serializer();
	public static final IRecipeSerializer<TimeMachineRecipe> TIME_MACHINE_RECIPE_SERIALIZER = new TimeMachineRecipeSerialiser();
	
	public static final IRecipeType<FossilGrinderRecipe> FOSSIL_GRINDER_RECIPE = registerType(ModUtils.rL("fossil_grinder"));
	public static final IRecipeType<DNAExtractorRecipe> DNA_EXTRACTOR_RECIPE = registerType(ModUtils.rL("dna_extractor"));
	public static final IRecipeType<AnalyzerRecipe> ANALYZER_RECIPE = registerType(ModUtils.rL("analyzer"));
	public static final IRecipeType<DNAInjectorRecipe> DNA_INJECTOR_RECIPE = registerType(ModUtils.rL("dna_injector"));
	public static final IRecipeType<CultivatorRecipe> CULTIVATOR_RECIPE = registerType(ModUtils.rL("cultivator"));
	public static final IRecipeType<ArchaeologyTableRecipe> ARCHAEOLOGY_TABLE_RECIPE = registerType(ModUtils.rL("archaeology_table"));
	public static final IRecipeType<TimeMachineRecipe> TIME_MACHINE_RECIPE = registerType(ModUtils.rL("time_machine"));

	public static final IRecipeSerializer<?> FOSSIL_GRINDER_SERIALIZER = ModRegistry.register("fossil_grinder", FOSSIL_GRINDER_RECIPE_SERIALIZER);
	public static final IRecipeSerializer<?> DNA_EXTRACTOR_SERIALIZER = ModRegistry.register("dna_extractor", DNA_EXTRACTOR_RECIPE_SERIALIZER);
	public static final IRecipeSerializer<?> ANALYZER_SERIALIZER = ModRegistry.register("analyzer", ANALYZER_RECIPE_SERIALIZER);
	public static final IRecipeSerializer<?> DNA_INJECTOR_SERIALIZER = ModRegistry.register("dna_injector", DNA_INJECTOR_RECIPE_SERIALIZER);
	public static final IRecipeSerializer<?> CULTIVATOR_SERIALIZER = ModRegistry.register("cultivator", CULTIVATOR_RECIPE_SERIALIZER);
	public static final IRecipeSerializer<?> ARCHAEOLOGY_TABLE_SERIALIZER = ModRegistry.register("archaeology_table", ARCHAEOLOGY_TABLE_RECIPE_SERIALIZER);
	public static final IRecipeSerializer<?> TIME_MACHINE_SERIALIZER = ModRegistry.register("time_machine", TIME_MACHINE_RECIPE_SERIALIZER);
	
	private static class RecipeType<T extends IRecipe<?>> implements IRecipeType<T> 
	{
		@Override
		public String toString() 
		{
			return Registry.RECIPE_TYPE.getKey(this).toString();
		}
	}
	
	private static <T extends IRecipeType> T registerType(ResourceLocation recipeTypeId) 
	{
		return (T) Registry.register(Registry.RECIPE_TYPE, recipeTypeId, new RecipeType<>());
	}
	
	//Registry
	public static void init() { ModUtils.LOGGER.debug("Registering Mod Recipes"); }
}
