package lostworlds.content.server.init;

import lostworlds.library.recipe.AnalyserRecipe;
import lostworlds.library.recipe.AnalyserRecipeSerialiser;
import lostworlds.library.recipe.ArchaeologyTableRecipe;
import lostworlds.library.recipe.DNAExtractorRecipe;
import lostworlds.library.recipe.DNAExtractorRecipeSerialiser;
import lostworlds.library.recipe.DNAInjectorRecipe;
import lostworlds.library.recipe.DNAInjectorRecipeSerialiser;
import lostworlds.library.recipe.FossilGrinderRecipe;
import lostworlds.library.recipe.FossilGrinderRecipeSerialiser;
import lostworlds.library.util.ModRegistry;
import lostworlds.library.util.ModUtil;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

/*
 * Author: Willatendo
 * Date: July 8, 2021
 */

public class RecipeInit 
{
	public static final IRecipeSerializer<FossilGrinderRecipe> FOSSIL_GRINDER_RECIPE_SERIALIZER = new FossilGrinderRecipeSerialiser();
	public static final IRecipeSerializer<DNAExtractorRecipe> DNA_EXTRACTOR_RECIPE_SERIALIZER = new DNAExtractorRecipeSerialiser();
	public static final IRecipeSerializer<AnalyserRecipe> ANALYSER_RECIPE_SERIALIZER = new AnalyserRecipeSerialiser();
	public static final IRecipeSerializer<DNAInjectorRecipe> DNA_INJECTOR_RECIPE_SERIALIZER = new DNAInjectorRecipeSerialiser();
	public static final IRecipeSerializer<ArchaeologyTableRecipe> ARCHAEOLOGY_TABLE_RECIPE_SERIALIZER = new ArchaeologyTableRecipe.Serializer();
	
	public static final IRecipeType<FossilGrinderRecipe> FOSSIL_GRINDER_RECIPE = registerType(ModUtil.rL("fossil_grinder"));
	public static final IRecipeType<DNAExtractorRecipe> DNA_EXTRACTOR_RECIPE = registerType(ModUtil.rL("dna_extractor"));
	public static final IRecipeType<AnalyserRecipe> ANALYSER_RECIPE = registerType(ModUtil.rL("analyser"));
	public static final IRecipeType<DNAInjectorRecipe> DNA_INJECTOR_RECIPE = registerType(ModUtil.rL("dna_injector"));
	public static final IRecipeType<ArchaeologyTableRecipe> ARCHAEOLOGY_TABLE_RECIPE = registerType(ModUtil.rL("archaeology_table"));

	public static final IRecipeSerializer<?> FOSSIL_GRINDER_SERIALIZER = ModRegistry.register("fossil_grinder", FOSSIL_GRINDER_RECIPE_SERIALIZER);
	public static final IRecipeSerializer<?> DNA_EXTRACTOR_SERIALIZER = ModRegistry.register("dna_extractor", DNA_EXTRACTOR_RECIPE_SERIALIZER);
	public static final IRecipeSerializer<?> ANALYSER_SERIALIZER = ModRegistry.register("analyser", ANALYSER_RECIPE_SERIALIZER);
	public static final IRecipeSerializer<?> DNA_INJECTOR_SERIALIZER = ModRegistry.register("dna_injector", DNA_INJECTOR_RECIPE_SERIALIZER);
	public static final IRecipeSerializer<?> ARCHAEOLOGY_TABLE_SERIALIZER = ModRegistry.register("archaeology_table", ARCHAEOLOGY_TABLE_RECIPE_SERIALIZER);
	
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
	public static void init() { ModUtil.LOGGER.debug("Registering Mod Recipes"); }
}
