package lostworlds.server.block.entity;

import java.util.List;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.container.FeedingTroughContainer;
import lostworlds.server.container.LostWorldsContainers;
import lostworlds.server.entity.terrestrial.PrehistoricEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.TickableBlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;

public class FeedingTroughTileEntity extends RandomizableContainerBlockEntity implements MenuProvider, TickableBlockEntity {
	protected NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);

	public FeedingTroughTileEntity() {
		super(LostWorldsBlockEntities.FEEDING_TROUGH_TILE_ENTITY.get());
	}

	@Override
	public void tick() {
		List<PrehistoricEntity> list = this.level.getEntitiesOfClass(PrehistoricEntity.class, this.getRenderBoundingBox().inflate(400.0D));
		for (PrehistoricEntity prehistoric : list) {
			if (prehistoric.isHungry()) {
				for (ItemStack items : this.items) {
					if (prehistoric.isFood(items)) {
						prehistoric.setHunger(prehistoric.maxHunger());
						this.removeItem(0, 1);
					}
				}
			}
		}
	}

	@Override
	public void load(BlockState state, CompoundTag nbt) {
		super.load(state, nbt);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(nbt, this.items);
	}

	@Override
	public CompoundTag save(CompoundTag nbt) {
		ContainerHelper.saveAllItems(nbt, this.items);
		return super.save(nbt);
	}

	public void changed(int slot) {
		this.setChanged();
	}

	@Override
	public ItemStack removeItem(int index, int count) {
		return ContainerHelper.removeItem(this.items, index, count);
	}

	@Override
	public void setItem(int index, ItemStack stack) {
		this.items.set(index, stack);
		if (stack.getCount() > this.getMaxStackSize()) {
			stack.setCount(this.getMaxStackSize());
		}
	}

	@Override
	public boolean stillValid(Player entity) {
		return !entity.isSpectator();
	}

	@Override
	public void clearContent() {
		this.items.clear();
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.items;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> items) {
		this.items = items;
	}

	@Override
	public int getContainerSize() {
		return this.items.size();
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.items) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}

		return true;
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
		return LostWorldsUtils.tTC("container", "feeding_trough");
	}

	@Override
	protected AbstractContainerMenu createMenu(int windowID, Inventory playerInventory) {
		return new FeedingTroughContainer(LostWorldsContainers.FEEDING_TROUGH_CONTAINER.get(), windowID, playerInventory, this);
	}
}
