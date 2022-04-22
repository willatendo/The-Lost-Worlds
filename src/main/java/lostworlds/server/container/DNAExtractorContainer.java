package lostworlds.server.container;

import java.util.Objects;

import lostworlds.server.block.DNAExtractorBlock;
import lostworlds.server.block.entity.DNAExtractorTileEntity;
import lostworlds.server.container.recipes.AmberDNAExtractorRecipe;
import lostworlds.server.container.recipes.DNAExtractorRecipe;
import lostworlds.server.container.recipes.LostWorldsRecipes;
import lostworlds.server.container.slot.DNAExtractorInputSlot;
import lostworlds.server.container.slot.ResultSlot;
import lostworlds.server.container.slot.VileSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class DNAExtractorContainer extends Container {
	private final IWorldPosCallable canInteractWithCallable;
	private final IIntArray data;
	private final World level;
	private final IRecipeType<DNAExtractorRecipe> recipeType = LostWorldsRecipes.DNA_EXTRACTOR_RECIPE;
	private final IRecipeType<AmberDNAExtractorRecipe> secondaryRecipeType = LostWorldsRecipes.AMBER_DNA_EXTRACTOR_RECIPE;

	public DNAExtractorContainer(ContainerType<? extends DNAExtractorContainer> containerType, int windowID, PlayerInventory playerInventory, DNAExtractorTileEntity tileEntity, IInventory inventory) {
		super(containerType, windowID);
		this.level = playerInventory.player.level;
		this.data = tileEntity.getExtractingData();
		this.canInteractWithCallable = IWorldPosCallable.create(tileEntity.getLevel(), tileEntity.getBlockPos());

		this.addSlot(new DNAExtractorInputSlot(inventory, 0, 56, 25));
		this.addSlot(new VileSlot(inventory, 1, 56, 45));
		this.addSlot(new ResultSlot(playerInventory.player, inventory, 2, 116, 35));

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

	public DNAExtractorContainer(ContainerType<? extends DNAExtractorContainer> containerType, int windowID, PlayerInventory playerInventory, PacketBuffer buffer) {
		this(containerType, windowID, playerInventory, new DNAExtractorTileEntity(), getTileEntity(playerInventory, buffer));
	}

	private static DNAExtractorTileEntity getTileEntity(PlayerInventory playerInventory, PacketBuffer data) {
		Objects.requireNonNull(playerInventory, "Error: " + DNAExtractorContainer.class.getSimpleName() + " - Player Inventory cannot be null!");
		Objects.requireNonNull(data, "Error: " + DNAExtractorContainer.class.getSimpleName() + " - Packer Buffer Data cannot be null!");

		final TileEntity tileEntityAtPos = playerInventory.player.level.getBlockEntity(data.readBlockPos());
		if (tileEntityAtPos instanceof DNAExtractorTileEntity) {
			return (DNAExtractorTileEntity) tileEntityAtPos;
		}

		throw new IllegalStateException("Error: " + DNAExtractorContainer.class.getSimpleName() + " - TileEntity is not corrent! " + tileEntityAtPos);
	}

	@Override
	public boolean stillValid(PlayerEntity playerIn) {
		return this.canInteractWithCallable.evaluate((world, blockPos) -> world.getBlockState(blockPos).getBlock() instanceof DNAExtractorBlock && playerIn.distanceToSqr((double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.5D, (double) blockPos.getZ() + 0.5D) <= 64.0D, true);
	}

	@OnlyIn(Dist.CLIENT)
	public int getProgress() {
		int extractingProgress = this.data.get(2);
		int extractingTotalTime = this.data.get(3);
		return extractingTotalTime != 0 && extractingProgress != 0 ? extractingProgress * 35 / extractingTotalTime : 0;
	}

	@Override
	public ItemStack quickMoveStack(PlayerEntity player, int i) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(i);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (i == 2) {
				if (!this.moveItemStackTo(itemstack1, 3, 39, true)) {
					return ItemStack.EMPTY;
				}

				slot.onQuickCraft(itemstack1, itemstack);
			} else if (i != 1 && i != 0) {
				if (canExtract(itemstack1)) {
					if (!this.moveItemStackTo(itemstack1, 0, 2, true)) {
						return ItemStack.EMPTY;
					}
				} else if (i >= 3 && i < 30) {
					if (!this.moveItemStackTo(itemstack1, 30, 39, false)) {
						return ItemStack.EMPTY;
					}
				} else if (i >= 30 && i < 39 && !this.moveItemStackTo(itemstack1, 3, 30, false)) {
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

	protected boolean canExtract(ItemStack stack) {
		return this.level.getRecipeManager().getRecipeFor((IRecipeType) this.recipeType, new Inventory(stack), this.level).isPresent() || this.level.getRecipeManager().getRecipeFor((IRecipeType) this.secondaryRecipeType, new Inventory(stack), this.level).isPresent();
	}
}
