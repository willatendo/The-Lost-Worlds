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

public class DNAExtractorContainer extends AbstractContainerMenu {
	private final ContainerLevelAccess canInteractWithCallable;
	private final ContainerData data;
	private final Level level;
	private final RecipeType<DNAExtractorRecipe> recipeType = LostWorldsRecipes.DNA_EXTRACTOR_RECIPE;
	private final RecipeType<AmberDNAExtractorRecipe> secondaryRecipeType = LostWorldsRecipes.AMBER_DNA_EXTRACTOR_RECIPE;

	public DNAExtractorContainer(MenuType<? extends DNAExtractorContainer> containerType, int windowID, Inventory playerInventory, DNAExtractorTileEntity tileEntity, Container inventory) {
		super(containerType, windowID);
		this.level = playerInventory.player.level;
		this.data = tileEntity.getExtractingData();
		this.canInteractWithCallable = ContainerLevelAccess.create(tileEntity.getLevel(), tileEntity.getBlockPos());

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

	public DNAExtractorContainer(MenuType<? extends DNAExtractorContainer> containerType, int windowID, Inventory playerInventory, FriendlyByteBuf buffer) {
		this(containerType, windowID, playerInventory, new DNAExtractorTileEntity(), getTileEntity(playerInventory, buffer));
	}

	private static DNAExtractorTileEntity getTileEntity(Inventory playerInventory, FriendlyByteBuf data) {
		Objects.requireNonNull(playerInventory, "Error: " + DNAExtractorContainer.class.getSimpleName() + " - Player Inventory cannot be null!");
		Objects.requireNonNull(data, "Error: " + DNAExtractorContainer.class.getSimpleName() + " - Packer Buffer Data cannot be null!");

		final BlockEntity tileEntityAtPos = playerInventory.player.level.getBlockEntity(data.readBlockPos());
		if (tileEntityAtPos instanceof DNAExtractorTileEntity) {
			return (DNAExtractorTileEntity) tileEntityAtPos;
		}

		throw new IllegalStateException("Error: " + DNAExtractorContainer.class.getSimpleName() + " - TileEntity is not corrent! " + tileEntityAtPos);
	}

	@Override
	public boolean stillValid(Player playerIn) {
		return this.canInteractWithCallable.evaluate((world, blockPos) -> world.getBlockState(blockPos).getBlock() instanceof DNAExtractorBlock && playerIn.distanceToSqr((double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.5D, (double) blockPos.getZ() + 0.5D) <= 64.0D, true);
	}

	@OnlyIn(Dist.CLIENT)
	public int getProgress() {
		int extractingProgress = this.data.get(2);
		int extractingTotalTime = this.data.get(3);
		return extractingTotalTime != 0 && extractingProgress != 0 ? extractingProgress * 35 / extractingTotalTime : 0;
	}

	@Override
	public ItemStack quickMoveStack(Player player, int i) {
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
		return this.level.getRecipeManager().getRecipeFor((RecipeType) this.recipeType, new SimpleContainer(stack), this.level).isPresent() || this.level.getRecipeManager().getRecipeFor((RecipeType) this.secondaryRecipeType, new SimpleContainer(stack), this.level).isPresent();
	}
}
