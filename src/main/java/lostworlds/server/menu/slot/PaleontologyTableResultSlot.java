package lostworlds.server.menu.slot;

import lostworlds.server.menu.inventory.PaleontologyTableInventory;
import lostworlds.server.menu.recipes.LostWorldsRecipeTypes;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeEventFactory;

public class PaleontologyTableResultSlot extends Slot {
	private final PaleontologyTableInventory craftSlots;
	private final Player player;
	private int removeCount;

	public PaleontologyTableResultSlot(Player player, PaleontologyTableInventory inv, Container iinv, int slotSeed, int x, int y) {
		super(iinv, slotSeed, x, y);
		this.player = player;
		this.craftSlots = inv;
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return false;
	}

	@Override
	public ItemStack remove(int slot) {
		if (this.hasItem()) {
			this.removeCount += Math.min(slot, this.getItem().getCount());
		}

		return super.remove(slot);
	}

	@Override
	protected void onQuickCraft(ItemStack stack, int slot) {
		this.removeCount += slot;
		this.checkTakeAchievements(stack);
	}

	@Override
	protected void onSwapCraft(int slot) {
		this.removeCount += slot;
	}

	@Override
	protected void checkTakeAchievements(ItemStack stack) {
		if (this.removeCount > 0) {
			stack.onCraftedBy(this.player.level, this.player, this.removeCount);
			ForgeEventFactory.firePlayerCraftingEvent(this.player, stack, this.craftSlots);
		}

		if (this.container instanceof RecipeHolder) {
			((RecipeHolder) this.container).awardUsedRecipes(this.player);
		}

		this.removeCount = 0;
	}

	@Override
	public void onTake(Player player, ItemStack stack) {
		this.checkTakeAchievements(stack);
		ForgeHooks.setCraftingPlayer(player);
		NonNullList<ItemStack> nonnulllist = player.level.getRecipeManager().getRemainingItemsFor(LostWorldsRecipeTypes.PALEONTOLOGY_TABLE_RECIPE.get(), this.craftSlots, player.level);
		ForgeHooks.setCraftingPlayer(null);
		for (int i = 0; i < nonnulllist.size(); ++i) {
			ItemStack itemstack = this.craftSlots.getItem(i);
			ItemStack itemstack1 = nonnulllist.get(i);
			if (!itemstack.isEmpty()) {
				this.craftSlots.removeItem(i, 1);
				itemstack = this.craftSlots.getItem(i);
			}

			if (!itemstack1.isEmpty()) {
				if (itemstack.isEmpty()) {
					this.craftSlots.setItem(i, itemstack1);
				} else if (ItemStack.isSame(itemstack, itemstack1) && ItemStack.tagMatches(itemstack, itemstack1)) {
					itemstack1.grow(itemstack.getCount());
					this.craftSlots.setItem(i, itemstack1);
				} else if (!this.player.getInventory().add(itemstack1)) {
					this.player.drop(itemstack1, false);
				}
			}
		}
	}
}
