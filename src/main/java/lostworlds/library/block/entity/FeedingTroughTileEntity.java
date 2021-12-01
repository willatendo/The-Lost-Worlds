package lostworlds.library.block.entity;

import lostworlds.content.ModUtils;
import lostworlds.content.server.init.TileEntityInit;
import lostworlds.library.block.FeedingTroughBlock;
import lostworlds.library.container.FeedingTroughContainer;
import lostworlds.library.container.inventory.FeedingTroughInventory;
import lostworlds.library.container.slot.FeedingTroughSlot;
import lostworlds.library.entity.CreatureDiet;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
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
	public FeedingTroughInventory handler;
	
	public FeedingTroughTileEntity() 
	{
		super(TileEntityInit.FEEDING_TROUGH_TILE_ENTITY);
		handler = new FeedingTroughInventory(1, this::changed);
	}
	
	@Override
	public void load(BlockState state, CompoundNBT nbt) 
	{
		this.handler.deserializeNBT(nbt.getCompound("ItemStackHandler"));
		super.load(state, nbt);
	}
	
	@Override
	public CompoundNBT save(CompoundNBT nbt) 
	{
		nbt.put("ItemStackHandler", this.handler.serializeNBT());
		return super.save(nbt);
	}
	
	public void changed(int slot) 
	{
		this.setChanged();
	}
	
	@Override
	public ItemStack removeItem(int index, int count) 
	{
		return handler.extractItem(index, count, false);
	}
	
	@Override
	public void setItem(int index, ItemStack stack) 
	{
		handler.setStackInSlot(index, stack);
		this.setChanged();
	}
	
	@Override
	public boolean stillValid(PlayerEntity entity) 
	{
		return !entity.isSpectator();
	}
	
	@Override
	public void clearContent() 
	{
		for(int i = 0; i < handler.getSlots(); i++) 
		{
			handler.setStackInSlot(i, ItemStack.EMPTY);
		}
		this.setChanged();
	}
	
	@Override
	public int getContainerSize() 
	{
		return 1;
	}
	
	@Override
	public boolean isEmpty() 
	{
		for(int i = 0; i < handler.getSlots(); i++) 
		{
			if(handler.getStackInSlot(i).getCount() > 0)
			{
				return false;
			}
		}
		return true;
	}
	
	@Override
	public NonNullList<ItemStack> getItems() 
	{
		NonNullList<ItemStack> list = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
		for(int i = 0; i < getContainerSize(); i++) 
		{
			list.set(i, handler.getStackInSlot(i));
		}
		return list;
	}
	
	@Override
	protected void setItems(NonNullList<ItemStack> items) 
	{
		for(int i = 0; i < getContainerSize(); i++) 
		{
			handler.setStackInSlot(i, items.get(i));
		}
		this.setChanged();
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
		return new FeedingTroughContainer(windowID, inv, this, this.handler);
	}

	@Override
	public void tick() 
	{
		if(!this.level.isClientSide) 
		{
			if(this.getItem(1) != null)
			{
				if(FeedingTroughSlot.HERBIVORE_FOODS.contains(this.getItem(1).getItem()))
				{
					this.level.setBlock(this.worldPosition, this.level.getBlockState(this.worldPosition).setValue(FeedingTroughBlock.DIET, CreatureDiet.HERBIVORE), 3);
				}
				else if(FeedingTroughSlot.CARNIVORE_FOODS.contains(this.getItem(1).getItem()))
				{
					this.level.setBlock(this.worldPosition, this.level.getBlockState(this.worldPosition).setValue(FeedingTroughBlock.DIET, CreatureDiet.CARNIVORE), 3);
				}
				else if(FeedingTroughSlot.INSECTIVORE_FOODS.contains(this.getItem(1).getItem()))
				{
					this.level.setBlock(this.worldPosition, this.level.getBlockState(this.worldPosition).setValue(FeedingTroughBlock.DIET, CreatureDiet.INSECTIVORE), 3);
				}
				else if(FeedingTroughSlot.PISCAVORE_FOODS.contains(this.getItem(1).getItem()))
				{
					this.level.setBlock(this.worldPosition, this.level.getBlockState(this.worldPosition).setValue(FeedingTroughBlock.DIET, CreatureDiet.PISCIVORE), 3);
				}
			}
		}
	}
}
