package lostworlds.server.container;

import java.util.Objects;

import lostworlds.server.block.FossilGrinderBlock;
import lostworlds.server.block.entity.FossilGrinderTileEntity;
import lostworlds.server.container.recipes.FossilGrinderRecipe;
import lostworlds.server.container.recipes.LostWorldsRecipes;
import lostworlds.server.container.slot.ResultSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FossilGrinderContainer extends AbstractContainerMenu {
	private final ContainerLevelAccess canInteractWithCallable;
	private final ContainerData data;
	private final Level level;
	private final RecipeType<FossilGrinderRecipe> recipeType = LostWorldsRecipes.FOSSIL_GRINDER_RECIPE;

	public FossilGrinderContainer(MenuType<? extends FossilGrinderContainer> containerType, int windowID, Inventory playerInventory, FossilGrinderTileEntity tileEntity, Container inventory) {
		super(containerType, windowID);
		this.level = playerInventory.player.level;
		this.data = tileEntity.getGrinderData();
		this.canInteractWithCallable = ContainerLevelAccess.create(tileEntity.getLevel(), tileEntity.getBlockPos());

		this.addSlot(new Slot(inventory, 0, 53, 35));
		this.addSlot(new ResultSlot(playerInventory.player, inventory, 1, 116, 35));
		this.addSlot(new ResultSlot(playerInventory.player, inventory, 2, 139, 35));

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int k = 0; k < 9; ++k) {
			this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
		}

		this.addDataSlots(this.data);
	}

	public FossilGrinderContainer(MenuType<? extends FossilGrinderContainer> containerType, int windowID, Inventory playerInventory, FriendlyByteBuf buffer) {
		this(containerType, windowID, playerInventory, new FossilGrinderTileEntity(), getTileEntity(playerInventory, buffer));
	}

	private static FossilGrinderTileEntity getTileEntity(Inventory playerInventory, FriendlyByteBuf data) {
		Objects.requireNonNull(playerInventory, "Error: " + FossilGrinderContainer.class.getSimpleName() + " - Player Inventory cannot be null!");
		Objects.requireNonNull(data, "Error: " + FossilGrinderContainer.class.getSimpleName() + " - Packer Buffer Data cannot be null!");

		final BlockEntity tileEntityAtPos = playerInventory.player.level.getBlockEntity(data.readBlockPos());
		if (tileEntityAtPos instanceof FossilGrinderTileEntity) {
			return (FossilGrinderTileEntity) tileEntityAtPos;
		}

		throw new IllegalStateException("Error: " + FossilGrinderContainer.class.getSimpleName() + " - TileEntity is not corrent! " + tileEntityAtPos);
	}

	@Override
	public boolean stillValid(Player playerIn) {
		return this.canInteractWithCallable.evaluate((world, blockPos) -> world.getBlockState(blockPos).getBlock() instanceof FossilGrinderBlock && playerIn.distanceToSqr((double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.5D, (double) blockPos.getZ() + 0.5D) <= 64.0D, true);
	}

	@OnlyIn(Dist.CLIENT)
	public int getProgress() {
		int grindingProgress = this.data.get(2);
		int grindingTotalTime = this.data.get(3);
		return grindingTotalTime != 0 && grindingProgress != 0 ? grindingProgress * 35 / grindingTotalTime : 0;
	}

	@Override
	public ItemStack quickMoveStack(Player player, int slotNum) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(slotNum);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (slotNum == 1 || slotNum == 2) {
				if (!this.moveItemStackTo(itemstack1, 3, 39, true)) {
					return ItemStack.EMPTY;
				}

				slot.onQuickCraft(itemstack1, itemstack);
			} else if (slotNum != 1 && slotNum != 0) {
				if (this.canGrind(itemstack1)) {
					if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (slotNum >= 3 && slotNum < 30) {
					if (!this.moveItemStackTo(itemstack1, 30, 39, false)) {
						return ItemStack.EMPTY;
					}
				} else if (slotNum >= 30 && slotNum < 39 && !this.moveItemStackTo(itemstack1, 3, 30, false)) {
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

	protected boolean canGrind(ItemStack stack) {
		return this.level.getRecipeManager().getRecipeFor((RecipeType) this.recipeType, new SimpleContainer(stack), this.level).isPresent();
	}
}
