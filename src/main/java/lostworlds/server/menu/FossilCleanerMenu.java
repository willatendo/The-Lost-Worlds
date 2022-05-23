package lostworlds.server.menu;

import lostworlds.server.block.FossilCleanerBlock;
import lostworlds.server.block.entity.FossilCleanerBlockEntity;
import lostworlds.server.menu.recipes.FossilCleanerRecipe;
import lostworlds.server.menu.recipes.LostWorldsRecipes;
import lostworlds.server.menu.slot.FossilCleanerFuelSlot;
import lostworlds.server.menu.slot.ResultSlot;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FossilCleanerMenu extends AbstractContainerMenu {
	private final ContainerLevelAccess containerLevelAccess;
	private final ContainerData data;
	private final Level level;
	private final RecipeType<FossilCleanerRecipe> recipeType = LostWorldsRecipes.FOSSIL_CLEANER_RECIPE;

	public FossilCleanerMenu(MenuType<? extends FossilCleanerMenu> containerType, int windowID, Inventory inventory, FossilCleanerBlockEntity blockEntity) {
		super(containerType, windowID);
		this.data = blockEntity.getCleanerData();
		this.level = inventory.player.level;
		this.containerLevelAccess = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());

		this.addSlot(new Slot(blockEntity, 0, 56, 17));
		this.addSlot(new FossilCleanerFuelSlot(blockEntity, 1, 56, 53));
		this.addSlot(new ResultSlot(inventory.player, blockEntity, 2, 116, 35));

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int k = 0; k < 9; ++k) {
			this.addSlot(new Slot(inventory, k, 8 + k * 18, 142));
		}

		this.addDataSlots(this.data);
	}

	public FossilCleanerMenu(MenuType<? extends FossilCleanerMenu> containerType, int windowID, Inventory inventory, FriendlyByteBuf buffer) {
		this(containerType, windowID, inventory, (FossilCleanerBlockEntity) inventory.player.level.getBlockEntity(buffer.readBlockPos()));
	}

	@Override
	public boolean stillValid(Player player) {
		return this.containerLevelAccess.evaluate((world, blockPos) -> world.getBlockState(blockPos).getBlock() instanceof FossilCleanerBlock && player.distanceToSqr((double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.5D, (double) blockPos.getZ() + 0.5D) <= 64.0D, true);
	}

	@OnlyIn(Dist.CLIENT)
	public boolean isOn() {
		return this.data.get(0) > 0;
	}

	@OnlyIn(Dist.CLIENT)
	public int getProgress() {
		int cleaningProgress = this.data.get(2);
		int cleaningTotalTime = this.data.get(3);
		return cleaningTotalTime != 0 && cleaningProgress != 0 ? cleaningProgress * 32 / cleaningTotalTime : 0;
	}

	@OnlyIn(Dist.CLIENT)
	public int getOnProgress() {
		int i = this.data.get(1);
		if (i == 0) {
			i = 3500;
		}

		return this.data.get(0) * 16 / i;
	}

	@Override
	public ItemStack quickMoveStack(Player player, int containerSlot) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(containerSlot);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (containerSlot == 2) {
				if (!this.moveItemStackTo(itemstack1, 3, 39, true)) {
					return ItemStack.EMPTY;
				}

				slot.onQuickCraft(itemstack1, itemstack);
			} else if (containerSlot != 1 && containerSlot != 0) {
				if (this.canClean(itemstack1)) {
					if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (this.isFuel(itemstack1)) {
					if (!this.moveItemStackTo(itemstack1, 1, 2, false)) {
						return ItemStack.EMPTY;
					}
				} else if (containerSlot >= 3 && containerSlot < 30) {
					if (!this.moveItemStackTo(itemstack1, 30, 39, false)) {
						return ItemStack.EMPTY;
					}
				} else if (containerSlot >= 30 && containerSlot < 39 && !this.moveItemStackTo(itemstack1, 3, 30, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(itemstack1, 3, 39, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(player, itemstack1);
		}

		return itemstack;
	}

	protected boolean canClean(ItemStack stack) {
		return this.level.getRecipeManager().getRecipeFor((RecipeType) this.recipeType, new SimpleContainer(stack), this.level).isPresent();
	}

	protected boolean isFuel(ItemStack stack) {
		return stack.getItem() == Items.WATER_BUCKET || stack.getItem() == PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER).getItem();
	}
}