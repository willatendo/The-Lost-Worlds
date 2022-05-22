package lostworlds.server.block.entity;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.container.DisplayCaseContainer;
import lostworlds.server.container.LostWorldsContainers;
import lostworlds.server.container.inventory.DisplayCaseInventory;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;

public class DisplayCaseTileEntity extends RandomizableContainerBlockEntity implements MenuProvider {
	public DisplayCaseInventory handler;

	public DisplayCaseTileEntity() {
		super(LostWorldsBlockEntities.DISPLAY_CASE_TILE_ENTITY.get());
		handler = new DisplayCaseInventory(1, this::changed);
	}

	@Override
	public void load(BlockState state, CompoundTag nbt) {
		this.handler.deserializeNBT(nbt.getCompound("ItemStackHandler"));
		super.load(state, nbt);
	}

	@Override
	public CompoundTag save(CompoundTag nbt) {
		nbt.put("ItemStackHandler", this.handler.serializeNBT());
		return super.save(nbt);
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
		CompoundTag update = getUpdateTag();
		int data = 0;
		return new ClientboundBlockEntityDataPacket(this.worldPosition, data, update);
	}

	@Override
	public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
		CompoundTag update = pkt.getTag();
		handleUpdateTag(this.getBlockState(), update);
	}

	@Override
	public CompoundTag getUpdateTag() {
		CompoundTag nbt = new CompoundTag();
		save(nbt);
		return nbt;
	}

	@Override
	public void handleUpdateTag(BlockState state, CompoundTag nbt) {
		load(state, nbt);
	}

	@Override
	protected Component getDefaultName() {
		return LostWorldsUtils.tTC("container", "display_case");
	}

	@Override
	protected AbstractContainerMenu createMenu(int windowID, Inventory playerInventory) {
		return new DisplayCaseContainer(LostWorldsContainers.DISPLAY_CASE_CONTAINER.get(), windowID, playerInventory, this, this.handler);
	}
}
