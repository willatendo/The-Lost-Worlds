package lostworlds.server.menu;

import lostworlds.server.block.DNAInjectorBlock;
import lostworlds.server.block.entity.DNAInjectorBlockEntity;
import lostworlds.server.menu.recipes.DNAInjectorRecipe;
import lostworlds.server.menu.recipes.LostWorldsRecipes;
import lostworlds.server.menu.slot.DNADiscSlot;
import lostworlds.server.menu.slot.EggSlot;
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
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class DNAInjectorMenu extends AbstractContainerMenu {
	private final ContainerLevelAccess containerLevelAccess;
	private final ContainerData data;
	private final Level level;
	private final RecipeType<DNAInjectorRecipe> recipeType = LostWorldsRecipes.DNA_INJECTOR_RECIPE;

	public DNAInjectorMenu(MenuType<? extends DNAInjectorMenu> type, int windowID, Inventory inventory, DNAInjectorBlockEntity blockEntity) {
		super(type, windowID);
		this.level = inventory.player.level;
		this.data = blockEntity.getInjectorData();
		this.containerLevelAccess = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());

		this.addSlot(new DNADiscSlot(blockEntity, 0, 56, 17));
		this.addSlot(new EggSlot(blockEntity, 1, 56, 53));
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

	public DNAInjectorMenu(MenuType<? extends DNAInjectorMenu> type, int windowID, Inventory inventory, FriendlyByteBuf buffer) {
		this(type, windowID, inventory, (DNAInjectorBlockEntity) inventory.player.level.getBlockEntity(buffer.readBlockPos()));
	}

	@Override
	public boolean stillValid(Player player) {
		return this.containerLevelAccess.evaluate((world, blockPos) -> world.getBlockState(blockPos).getBlock() instanceof DNAInjectorBlock && player.distanceToSqr((double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.5D, (double) blockPos.getZ() + 0.5D) <= 64.0D, true);
	}

	@OnlyIn(Dist.CLIENT)
	public int getProgress() {
		int injectingProgress = this.data.get(2);
		int injectingTotalTime = this.data.get(3);
		return injectingTotalTime != 0 && injectingProgress != 0 ? injectingProgress * 16 / injectingTotalTime : 0;
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
				if (canInject(itemstack1)) {
					if (!this.moveItemStackTo(itemstack1, 0, 2, true)) {
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

	protected boolean canInject(ItemStack stack) {
		return this.level.getRecipeManager().getRecipeFor((RecipeType) this.recipeType, new SimpleContainer(stack), this.level).isPresent();
	}
}
