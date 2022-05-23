package lostworlds.server.menu.inventory;

import javax.annotation.Nullable;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.core.NonNullList;

public class ArchaeologyTableResultInventory implements Container, RecipeHolder {
	private final NonNullList<ItemStack> itemStacks = NonNullList.withSize(1, ItemStack.EMPTY);
	@Nullable
	private Recipe<?> recipeUsed;

	@Override
	public int getContainerSize() {
		return 1;
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.itemStacks) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	@Override
	public ItemStack getItem(int slot) {
		return this.itemStacks.get(0);
	}

	@Override
	public ItemStack removeItem(int width, int height) {
		return ContainerHelper.takeItem(this.itemStacks, 0);
	}

	@Override
	public ItemStack removeItemNoUpdate(int slot) {
		return ContainerHelper.takeItem(this.itemStacks, 0);
	}

	@Override
	public void setItem(int slot, ItemStack stack) {
		this.itemStacks.set(0, stack);
	}

	@Override
	public void setChanged() {
	}

	@Override
	public boolean stillValid(Player player) {
		return true;
	}

	@Override
	public void clearContent() {
		this.itemStacks.clear();
	}

	@Override
	public void setRecipeUsed(@Nullable Recipe<?> recipe) {
		this.recipeUsed = recipe;
	}

	@Override
	@Nullable
	public Recipe<?> getRecipeUsed() {
		return this.recipeUsed;
	}
}
