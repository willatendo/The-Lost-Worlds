package lostworlds.server.craft;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.recipe.handler.IRecipeHandler;
import com.blamejared.crafttweaker.api.recipe.handler.IReplacementRule;
import com.blamejared.crafttweaker.api.recipe.handler.helper.CraftingTableRecipeConflictChecker;
import com.blamejared.crafttweaker.api.recipe.handler.helper.ReplacementHandlerHelper;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.blamejared.crafttweaker.api.util.ItemStackUtil;
import com.blamejared.crafttweaker.api.util.StringUtil;

import lostworlds.server.menu.recipes.ArchaeologyTableRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

@ZenRegister
@ZenCodeType.Name("mods.lostworlds.archaeology")
public class ArchaeologyTableRecipeManager implements IRecipeHandler<ArchaeologyTableRecipe> {
	@Override
	public String dumpToCommandString(final IRecipeManager manager, final ArchaeologyTableRecipe recipe) {

		final NonNullList<Ingredient> ingredients = recipe.getIngredients();
		return String.format("craftingTable.addShaped(%s, %s, %s);", StringUtil.quoteAndEscape(recipe.getId()), ItemStackUtil.getCommandString(recipe.getResultItem()), IntStream.range(0, recipe.getRecipeHeight()).mapToObj(y -> IntStream.range(0, recipe.getRecipeWidth()).mapToObj(x -> ingredients.get(y * recipe.getRecipeWidth() + x)).map(IIngredient::fromIngredient).map(IIngredient::getCommandString).collect(Collectors.joining(", ", "[", "]"))).collect(Collectors.joining(", ", "[", "]")));
	}

	@Override
	public Optional<Function<ResourceLocation, ArchaeologyTableRecipe>> replaceIngredients(final IRecipeManager manager, final ArchaeologyTableRecipe recipe, final List<IReplacementRule> rules) {

		return ReplacementHandlerHelper.replaceNonNullIngredientList(recipe.getIngredients(), Ingredient.class, recipe, rules, newIngredients -> id -> new ArchaeologyTableRecipe(id, recipe.getRecipeWidth(), recipe.getRecipeHeight(), newIngredients, recipe.getResultItem()));
	}

	@Override
	public <U extends Recipe<?>> boolean doesConflict(final IRecipeManager manager, final ArchaeologyTableRecipe firstRecipe, final U secondRecipe) {

		return CraftingTableRecipeConflictChecker.checkConflicts(manager, firstRecipe, secondRecipe);
	}
}
