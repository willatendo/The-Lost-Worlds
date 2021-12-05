package lostworlds.library.block.entity;

import lostworlds.content.ModUtils;
import lostworlds.content.server.init.TileEntityInit;
import lostworlds.library.block.FeedingTroughBlock;
import lostworlds.library.container.FeedingTroughContainer;
import lostworlds.library.container.slot.FeedingTroughSlot;
import lostworlds.library.entity.utils.enums.CreatureDiet;
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

public class FeedingTroughTileEntity extends LockableLootTileEntity implements INamedContainerProvider, ITickableTileEntity 
{
	protected NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);

	public FeedingTroughTileEntity() 
	{
		super(TileEntityInit.FEEDING_TROUGH_TILE_ENTITY);
	}

	@Override
	public void load(BlockState state, CompoundNBT nbt) 
	{
		super.load(state, nbt);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(nbt, this.items);
	}

	@Override
	public CompoundNBT save(CompoundNBT nbt) 
	{
		ItemStackHelper.saveAllItems(nbt, this.items);
		return super.save(nbt);
	}

	public void changed(int slot) 
	{
		this.setChanged();
	}

	@Override
	public ItemStack removeItem(int index, int count) 
	{
		return ItemStackHelper.removeItem(this.items, index, count);
	}

	@Override
	public void setItem(int index, ItemStack stack) 
	{
		this.items.set(index, stack);
		if(stack.getCount() > this.getMaxStackSize()) 
		{
			stack.setCount(this.getMaxStackSize());
		}
	}

	@Override
	public boolean stillValid(PlayerEntity entity) 
	{
		return !entity.isSpectator();
	}

	@Override
	public void clearContent() 
	{
		this.items.clear();
	}

	@Override
	protected NonNullList<ItemStack> getItems() 
	{
		return this.items;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> items) 
	{
		this.items = items;
	}

	@Override
	public int getContainerSize() 
	{
		return this.items.size();
	}

	@Override
	public boolean isEmpty() 
	{
		for(ItemStack itemstack : this.items) 
		{
			if(!itemstack.isEmpty()) 
			{
				return false;
			}
		}

		return true;
	}

	@Override
	public SUpdateTileEntityPacket getUpdatePacket() 
	{
		CompoundNBT update = getUpdateTag();
		int data = 0;
		return new SUpdateTileEntityPacket(this.worldPosition, data, update);
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) 
	{
		CompoundNBT update = pkt.getTag();
		handleUpdateTag(this.getBlockState(), update);
	}

	@Override
	public CompoundNBT getUpdateTag() 
	{
		CompoundNBT nbt = new CompoundNBT();
		save(nbt);
		return nbt;
	}

	@Override
	public void handleUpdateTag(BlockState state, CompoundNBT nbt) 
	{
		load(state, nbt);
	}

	@Override
	protected ITextComponent getDefaultName() 
	{
		return ModUtils.tTC("container", "feeding_trough");
	}

	@Override
	protected Container createMenu(int windowID, PlayerInventory inv) 
	{
		return new FeedingTroughContainer(windowID, inv, this);
	}

	public boolean hasItems() 
	{
		ItemStack itemstack = this.items.get(0);
		return !itemstack.isEmpty();
	}

	@Override
	public void tick() 
	{
		boolean flag = this.hasItems();
		boolean flag1 = false;

		if(!this.level.isClientSide) 
		{
			ItemStack itemstack = this.items.get(0);
			if(flag) 
			{
				if(flag != this.hasItems()) 
				{
					flag1 = true;
					if(FeedingTroughSlot.HERBIVORE_FOODS.contains(itemstack.getItem())) 
					{
						this.level.setBlock(this.worldPosition, this.level.getBlockState(this.worldPosition).setValue(FeedingTroughBlock.DIET, CreatureDiet.HERBIVORE), 3);
					} 
					else if(FeedingTroughSlot.CARNIVORE_FOODS.contains(itemstack.getItem())) 
					{
						this.level.setBlock(this.worldPosition, this.level.getBlockState(this.worldPosition).setValue(FeedingTroughBlock.DIET, CreatureDiet.CARNIVORE), 3);
					} 
					else if(FeedingTroughSlot.INSECTIVORE_FOODS.contains(itemstack.getItem())) 
					{
						this.level.setBlock(this.worldPosition, this.level.getBlockState(this.worldPosition).setValue(FeedingTroughBlock.DIET, CreatureDiet.INSECTIVORE), 3);
					} 
					else if(FeedingTroughSlot.PISCAVORE_FOODS.contains(itemstack.getItem())) 
					{
						this.level.setBlock(this.worldPosition, this.level.getBlockState(this.worldPosition).setValue(FeedingTroughBlock.DIET, CreatureDiet.PISCIVORE), 3);
					}
				}
			}
		}

		if(flag1) 
		{
			this.setChanged();
		}
	}
}
