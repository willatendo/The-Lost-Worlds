package lostworlds.server.block.entity;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.menu.DisplayCaseMenu;
import lostworlds.server.menu.LostWorldsMenus;
import lostworlds.server.menu.inventory.DisplayCaseInventory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DisplayCaseBlockEntity extends RandomizableContainerBlockEntity implements MenuProvider {
	public DisplayCaseInventory handler;

	public DisplayCaseBlockEntity(BlockEntityType<DisplayCaseBlockEntity> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
		handler = new DisplayCaseInventory(1, this::changed);
	}

	@Override
	public void load(CompoundTag tag) {
		this.handler.deserializeNBT(tag.getCompound("ItemStackHandler"));
		super.load(tag);
	}

	@Override
	public void saveAdditional(CompoundTag tag) {
		super.saveAdditional(tag);
		tag.put("ItemStackHandler", this.handler.serializeNBT());
	}

	public void changed(int slot) {
		this.setChanged();
	}

	@Override
	public ItemStack removeItem(int index, int count) {
		return handler.extractItem(index, count, false);
	}

	@Override
	public void setItem(int index, ItemStack stack) {
		handler.setStackInSlot(index, stack);
		this.setChanged();
	}

	@Override
	public boolean stillValid(Player entity) {
		return !entity.isSpectator();
	}

	@Override
	public void clearContent() {
		for (int i = 0; i < handler.getSlots(); i++) {
			handler.setStackInSlot(i, ItemStack.EMPTY);
		}
		this.setChanged();
	}

	@Override
	public int getContainerSize() {
		return 1;
	}

	@Override
	public boolean isEmpty() {
		for (int i = 0; i < handler.getSlots(); i++) {
			if (handler.getStackInSlot(i).getCount() > 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public NonNullList<ItemStack> getItems() {
		NonNullList<ItemStack> list = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
		for (int i = 0; i < getContainerSize(); i++) {
			list.set(i, handler.getStackInSlot(i));
		}
		return list;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> items) {
		for (int i = 0; i < getContainerSize(); i++) {
			handler.setStackInSlot(i, items.get(i));
		}
		this.setChanged();
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket packet) {
		CompoundTag tag = packet.getTag();
		this.handleUpdateTag(tag);
	}

	@Override
	public CompoundTag getUpdateTag() {
		CompoundTag tag = new CompoundTag();
		this.saveAdditional(tag);
		return tag;
	}

	@Override
	public void handleUpdateTag(CompoundTag nbt) {
		this.load(nbt);
	}

	@Override
	protected Component getDefaultName() {
		return LostWorldsUtils.tTC("menu", "display_case");
	}

	@Override
	protected AbstractContainerMenu createMenu(int windowID, Inventory playerInventory) {
		return new DisplayCaseMenu(LostWorldsMenus.DISPLAY_CASE_CONTAINER.get(), windowID, playerInventory, this.handler);
	}
}
