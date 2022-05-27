package lostworlds.server.block.entity;

import java.util.List;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.terrestrial.PrehistoricMob;
import lostworlds.server.menu.FeedingTroughMenu;
import lostworlds.server.menu.LostWorldsMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FeedingTroughBlockEntity extends RandomizableContainerBlockEntity implements MenuProvider {
	protected NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);

	public FeedingTroughBlockEntity(BlockEntityType<FeedingTroughBlockEntity> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, FeedingTroughBlockEntity blockEntity) {
		List<PrehistoricMob> list = blockEntity.level.getEntitiesOfClass(PrehistoricMob.class, blockEntity.getRenderBoundingBox().inflate(400.0D));
		for (PrehistoricMob prehistoric : list) {
			if (prehistoric.isHungry()) {
				for (ItemStack items : blockEntity.items) {
					if (prehistoric.isFood(items)) {
						prehistoric.setHunger(prehistoric.maxHunger());
						blockEntity.removeItem(0, 1);
					}
				}
			}
		}
	}

	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(tag, this.items);
	}

	@Override
	public void saveAdditional(CompoundTag tag) {
		super.saveAdditional(tag);
		ContainerHelper.saveAllItems(tag, this.items);
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
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket packet) {
		CompoundTag tag = packet.getTag();
		handleUpdateTag(tag);
	}

	@Override
	public CompoundTag getUpdateTag() {
		CompoundTag tag = new CompoundTag();
		this.saveAdditional(tag);
		return tag;
	}

	@Override
	public void handleUpdateTag(CompoundTag tag) {
		this.load(tag);
	}

	@Override
	protected Component getDefaultName() {
		return LostWorldsUtils.tTC("menu", "feeding_trough");
	}

	@Override
	protected AbstractContainerMenu createMenu(int windowID, Inventory playerInventory) {
		return new FeedingTroughMenu(LostWorldsMenus.FEEDING_TROUGH_CONTAINER.get(), windowID, playerInventory, this);
	}
}
