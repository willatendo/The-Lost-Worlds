package lostworlds.server.craft;

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

import lostworlds.server.container.recipes.PaleontologyTableRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;

@ZenRegister
@ZenCodeType.Name("mods.lostworlds.paleontology")
public class PaleontologyTableRecipeManager implements IRecipeHandler<PaleontologyTableRecipe> {
	@Override
	public String dumpToCommandString(final IRecipeManager manager, final PaleontologyTableRecipe recipe) {

		final NonNullList<Ingredient> ingredients = recipe.getIngredients();
		return String.format("craftingTable.addShaped(%s, %s, %s);", StringUtils.quoteAndEscape(recipe.getId()), ItemStackHelper.getCommandString(recipe.getResultItem()), IntStream.range(0, recipe.getRecipeHeight()).mapToObj(y -> IntStream.range(0, recipe.getRecipeWidth()).mapToObj(x -> ingredients.get(y * recipe.getRecipeWidth() + x)).map(IIngredient::fromIngredient).map(IIngredient::getCommandString).collect(Collectors.joining(", ", "[", "]"))).collect(Collectors.joining(", ", "[", "]")));
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
