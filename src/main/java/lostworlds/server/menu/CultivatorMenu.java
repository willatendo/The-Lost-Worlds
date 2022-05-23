package lostworlds.server.menu;

import lostworlds.server.block.CultivatorBlock;
import lostworlds.server.block.entity.CultivatorBlockEntity;
import lostworlds.server.menu.recipes.CultivatorRecipe;
import lostworlds.server.menu.recipes.LostWorldsRecipes;
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

public class CultivatorMenu extends AbstractContainerMenu {
	private final ContainerLevelAccess containerLevelAccess;
	private final ContainerData data;
	private final Level level;
	private final RecipeType<CultivatorRecipe> recipeType = LostWorldsRecipes.CULTIVATOR_RECIPE;

	public CultivatorMenu(MenuType<? extends CultivatorMenu> containerType, int windowID, Inventory inventory, CultivatorBlockEntity blockEntity) {
		super(containerType, windowID);
		this.level = inventory.player.level;
		this.data = blockEntity.getCultivatorData();
		this.containerLevelAccess = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());

		this.addSlot(new Slot(blockEntity, 0, 53, 35));
		this.addSlot(new ResultSlot(inventory.player, blockEntity, 1, 116, 35));

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

	public CultivatorMenu(MenuType<? extends CultivatorMenu> containerType, int windowID, Inventory inventory, FriendlyByteBuf buffer) {
		this(containerType, windowID, inventory, (CultivatorBlockEntity) inventory.player.level.getBlockEntity(buffer.readBlockPos()));
	}

	@Override
	public boolean stillValid(Player player) {
		return this.containerLevelAccess.evaluate((world, blockPos) -> world.getBlockState(blockPos).getBlock() instanceof CultivatorBlock && player.distanceToSqr((double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.5D, (double) blockPos.getZ() + 0.5D) <= 64.0D, true);
	}

	@OnlyIn(Dist.CLIENT)
	public int getProgress() {
		int cultivatingProgress = this.data.get(2);
		int cultivatingTotalTime = this.data.get(3);
		return cultivatingTotalTime != 0 && cultivatingProgress != 0 ? cultivatingProgress * 35 / cultivatingTotalTime : 0;
	}

	@Override
	public ItemStack quickMoveStack(Player player, int containerSlot) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(containerSlot);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (containerSlot == 1) {
				if (!this.moveItemStackTo(itemstack1, 2, 38, true)) {
					return ItemStack.EMPTY;
				}

				slot.onQuickCraft(itemstack1, itemstack);
			} else if (containerSlot != 0) {
				if (canCultivate(itemstack1)) {
					if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (containerSlot >= 2 && containerSlot < 29) {
					if (!this.moveItemStackTo(itemstack1, 29, 38, false)) {
						return ItemStack.EMPTY;
					}
				} else if (containerSlot >= 29 && containerSlot < 38 && !this.moveItemStackTo(itemstack1, 2, 29, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(itemstack1, 2, 38, false)) {
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

	protected boolean canCultivate(ItemStack stack) {
		return this.level.getRecipeManager().getRecipeFor((RecipeType) this.recipeType, new SimpleContainer(stack), this.level).isPresent();
	}
}
