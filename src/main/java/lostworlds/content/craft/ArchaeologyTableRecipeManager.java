package lostworlds.content.craft;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.api.recipes.IRecipeHandler;
import com.blamejared.crafttweaker.api.recipes.IReplacementRule;
import com.blamejared.crafttweaker.api.recipes.ReplacementHandlerHelper;
import com.blamejared.crafttweaker.api.util.StringUtils;
import com.blamejared.crafttweaker.impl.helper.ItemStackHelper;
import com.blamejared.crafttweaker.impl.recipes.helper.CraftingTableRecipeConflictChecker;

import lostworlds.library.container.recipes.ArchaeologyTableRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

@ZenRegister
@ZenCodeType.Name("mods.lostworlds.archaeology")
public class ArchaeologyTableRecipeManager implements IRecipeHandler<ArchaeologyTableRecipe> {
	@Override
	public String dumpToCommandString(final IRecipeManager manager, final ArchaeologyTableRecipe recipe) {

		final NonNullList<Ingredient> ingredients = recipe.getIngredients();
		return String.format("craftingTable.addShaped(%s, %s, %s);", StringUtils.quoteAndEscape(recipe.getId()), ItemStackHelper.getCommandString(recipe.getResultItem()), IntStream.range(0, recipe.getRecipeHeight()).mapToObj(y -> IntStream.range(0, recipe.getRecipeWidth()).mapToObj(x -> ingredients.get(y * recipe.getRecipeWidth() + x)).map(IIngredient::fromIngredient).map(IIngredient::getCommandString).collect(Collectors.joining(", ", "[", "]"))).collect(Collectors.joining(", ", "[", "]")));
	}

	@Override
	public Optional<Function<ResourceLocation, ArchaeologyTableRecipe>> replaceIngredients(final IRecipeManager manager, final ArchaeologyTableRecipe recipe, final List<IReplacementRule> rules) {

		return ReplacementHandlerHelper.replaceNonNullIngredientList(recipe.getIngredients(), Ingredient.class, recipe, rules, newIngredients -> id -> new ArchaeologyTableRecipe(id, recipe.getRecipeWidth(), recipe.getRecipeHeight(), newIngredients, recipe.getResultItem()));
	}

	@Override
	public <U extends IRecipe<?>> boolean doesConflict(final IRecipeManager manager, final ArchaeologyTableRecipe firstRecipe, final U secondRecipe) {

		return CraftingTableRecipeConflictChecker.checkConflicts(manager, firstRecipe, secondRecipe);
	}
}
