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

import lostworlds.server.menu.recipes.PaleontologyTableRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

@ZenRegister
@ZenCodeType.Name("mods.lostworlds.paleontology")
public class PaleontologyTableRecipeManager implements IRecipeHandler<PaleontologyTableRecipe> {
	@Override
	public String dumpToCommandString(final IRecipeManager manager, final PaleontologyTableRecipe recipe) {

		final NonNullList<Ingredient> ingredients = recipe.getIngredients();
		return String.format("craftingTable.addShaped(%s, %s, %s);", StringUtil.quoteAndEscape(recipe.getId()), ItemStackUtil.getCommandString(recipe.getResultItem()), IntStream.range(0, recipe.getRecipeHeight()).mapToObj(y -> IntStream.range(0, recipe.getRecipeWidth()).mapToObj(x -> ingredients.get(y * recipe.getRecipeWidth() + x)).map(IIngredient::fromIngredient).map(IIngredient::getCommandString).collect(Collectors.joining(", ", "[", "]"))).collect(Collectors.joining(", ", "[", "]")));
	}

	@Override
	public Optional<Function<ResourceLocation, PaleontologyTableRecipe>> replaceIngredients(final IRecipeManager manager, final PaleontologyTableRecipe recipe, final List<IReplacementRule> rules) {

		return ReplacementHandlerHelper.replaceNonNullIngredientList(recipe.getIngredients(), Ingredient.class, recipe, rules, newIngredients -> id -> new PaleontologyTableRecipe(id, recipe.getRecipeWidth(), recipe.getRecipeHeight(), newIngredients, recipe.getResultItem()));
	}

	@Override
	public <U extends Recipe<?>> boolean doesConflict(final IRecipeManager manager, final PaleontologyTableRecipe firstRecipe, final U secondRecipe) {

		return CraftingTableRecipeConflictChecker.checkConflicts(manager, firstRecipe, secondRecipe);
	}
}
