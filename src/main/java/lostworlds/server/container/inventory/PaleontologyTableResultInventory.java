package lostworlds.server.container.inventory;

import javax.annotation.Nullable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;

public class PaleontologyTableResultInventory implements IInventory, IRecipeHolder {
	private final NonNullList<ItemStack> itemStacks = NonNullList.withSize(1, ItemStack.EMPTY);
	@Nullable
	private IRecipe<?> recipeUsed;

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
		return ItemStackHelper.takeItem(this.itemStacks, 0);
	}

	@Override
	public ItemStack removeItemNoUpdate(int slot) {
		return ItemStackHelper.takeItem(this.itemStacks, 0);
	}

	@Override
	public void setItem(int slot, ItemStack stack) {
		this.itemStacks.set(0, stack);
	}

	@Override
	public void setChanged() {
	}

	@Override
	public boolean stillValid(PlayerEntity player) {
		return true;
	}

	@Override
	public void clearContent() {
		this.itemStacks.clear();
	}

	@Override
	public void setRecipeUsed(@Nullable IRecipe<?> recipe) {
		this.recipeUsed = recipe;
	}

	@Override
	@Nullable
	public IRecipe<?> getRecipeUsed() {
		return this.recipeUsed;
	}
}
