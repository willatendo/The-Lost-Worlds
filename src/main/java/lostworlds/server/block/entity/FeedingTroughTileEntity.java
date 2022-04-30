package lostworlds.server.block.entity;

import java.util.List;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.container.FeedingTroughContainer;
import lostworlds.server.container.LostWorldsContainers;
import lostworlds.server.entity.terrestrial.PrehistoricEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;

public class FeedingTroughTileEntity extends LockableLootTileEntity implements INamedContainerProvider, ITickableTileEntity {
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
	public void load(BlockState state, CompoundNBT nbt) {
		super.load(state, nbt);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(nbt, this.items);
	}

	@Override
	public CompoundNBT save(CompoundNBT nbt) {
		ItemStackHelper.saveAllItems(nbt, this.items);
		return super.save(nbt);
	}

	public void changed(int slot) {
		this.setChanged();
	}

	@Override
	public ItemStack removeItem(int index, int count) {
		return ItemStackHelper.removeItem(this.items, index, count);
	}

	@Override
	public void setItem(int index, ItemStack stack) {
		this.items.set(index, stack);
		if (stack.getCount() > this.getMaxStackSize()) {
			stack.setCount(this.getMaxStackSize());
		}
	}

	@Override
	public boolean stillValid(PlayerEntity entity) {
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
	public SUpdateTileEntityPacket getUpdatePacket() {
		CompoundNBT update = getUpdateTag();
		int data = 0;
		return new SUpdateTileEntityPacket(this.worldPosition, data, update);
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		CompoundNBT update = pkt.getTag();
		handleUpdateTag(this.getBlockState(), update);
	}

	@Override
	public CompoundNBT getUpdateTag() {
		CompoundNBT nbt = new CompoundNBT();
		save(nbt);
		return nbt;
	}

	@Override
	public void handleUpdateTag(BlockState state, CompoundNBT nbt) {
		load(state, nbt);
	}

	@Override
	protected ITextComponent getDefaultName() {
		return LostWorldsUtils.tTC("container", "feeding_trough");
	}

	@Override
	protected Container createMenu(int windowID, PlayerInventory playerInventory) {
		return new FeedingTroughContainer(LostWorldsContainers.FEEDING_TROUGH_CONTAINER.get(), windowID, playerInventory, this);
	}
}
