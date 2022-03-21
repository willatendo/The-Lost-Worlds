package lostworlds.server.container;

import java.util.Objects;

import lostworlds.server.block.FossilCleanerBlock;
import lostworlds.server.block.entity.FossilCleanerTileEntity;
import lostworlds.server.container.recipes.FossilCleanerRecipe;
import lostworlds.server.container.recipes.LostWorldsRecipes;
import lostworlds.server.container.slot.FossilCleanerFuelSlot;
import lostworlds.server.container.slot.ResultSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FossilCleanerContainer extends Container {
	private final IWorldPosCallable canInteractWithCallable;
	private final IIntArray data;
	protected final World level;
	public final FossilCleanerTileEntity tile;
	private final IRecipeType<? extends FossilCleanerRecipe> recipeType = LostWorldsRecipes.FOSSIL_CLEANER_RECIPE;

	public FossilCleanerContainer(int windowID, PlayerInventory playerInv, FossilCleanerTileEntity tileEntity, IInventory tile) {
		super(LostWorldsContainers.FOSSIL_CLEANER_CONTAINER, windowID);
		this.data = tileEntity.getCleanerData();
		this.tile = tileEntity;
		this.level = playerInv.player.level;
		this.canInteractWithCallable = IWorldPosCallable.create(tileEntity.getLevel(), tileEntity.getBlockPos());

		this.addSlot(new Slot(tile, 0, 56, 17));
		this.addSlot(new FossilCleanerFuelSlot(tile, 1, 56, 53));
		this.addSlot(new ResultSlot(playerInv.player, tile, 2, 116, 35));

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlot(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int k = 0; k < 9; ++k) {
			this.addSlot(new Slot(playerInv, k, 8 + k * 18, 142));
		}

		this.addDataSlots(this.data);
	}

	public FossilCleanerContainer(int windowID, PlayerInventory playerInv, PacketBuffer data) {
		this(windowID, playerInv, new FossilCleanerTileEntity(), getTileEntity(playerInv, data));
	}

	private static FossilCleanerTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
		Objects.requireNonNull(playerInventory, "Error: " + FossilCleanerContainer.class.getSimpleName() + " - Player Inventory cannot be null!");
		Objects.requireNonNull(data, "Error: " + FossilCleanerContainer.class.getSimpleName() + " - Packer Buffer Data cannot be null!");

		final TileEntity tileEntityAtPos = playerInventory.player.level.getBlockEntity(data.readBlockPos());
		if (tileEntityAtPos instanceof FossilCleanerTileEntity)
			return (FossilCleanerTileEntity) tileEntityAtPos;

		throw new IllegalStateException("Error: " + FossilCleanerContainer.class.getSimpleName() + " - TileEntity is not corrent! " + tileEntityAtPos);
	}

	@Override
	public boolean stillValid(PlayerEntity playerIn) {
		return this.canInteractWithCallable.evaluate((world, blockPos) -> world.getBlockState(blockPos).getBlock() instanceof FossilCleanerBlock && playerIn.distanceToSqr((double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.5D, (double) blockPos.getZ() + 0.5D) <= 64.0D, true);
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
	public ItemStack quickMoveStack(PlayerEntity player, int slotNum) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(slotNum);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (slotNum == 2) {
				if (!this.moveItemStackTo(itemstack1, 3, 39, true)) {
					return ItemStack.EMPTY;
				}

				slot.onQuickCraft(itemstack1, itemstack);
			} else if (slotNum != 1 && slotNum != 0) {
				if (this.canClean(itemstack1)) {
					if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (this.isFuel(itemstack1)) {
					if (!this.moveItemStackTo(itemstack1, 1, 2, false)) {
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

	protected boolean canClean(ItemStack stack) {
		return this.level.getRecipeManager().getRecipeFor((IRecipeType) this.recipeType, new Inventory(stack), this.level).isPresent();
	}

	protected boolean isFuel(ItemStack stack) {
		return stack.getItem() == Items.WATER_BUCKET || stack.getItem() == PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER).getItem();
	}
}