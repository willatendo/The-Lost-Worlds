package lostworlds.library.block.entity;

import lostworlds.content.ModUtils;
import lostworlds.content.server.init.TileEntityInit;
import lostworlds.library.container.DisplayCaseContainer;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IClearable;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.util.Constants;

public class DisplayCaseTileEntity extends LockableLootTileEntity implements IClearable, INamedContainerProvider
{
	private NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);

	public DisplayCaseTileEntity() 
	{
		super(TileEntityInit.DISPLAY_CASE_TILE_ENTITY);
	}
	
	@Override
	public void setChanged() 
	{
		if(this.level == null) 
		{
			return; 
		}
		this.updateTileOnInventoryChanged();
		if(this.needsToUpdateClientWhenChanged()) 
		{
			this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
		}
		super.setChanged();
	}
	
	@Deprecated
	public void updateOnChangedBeforePacket() { }
	
	public void updateTileOnInventoryChanged() 
	{
		this.updateOnChangedBeforePacket();
	}
	
	public boolean needsToUpdateClientWhenChanged() 
	{
		return true;
	}
	
	public void updateClientVisualsOnLoad() { }
	
	@Override
	public void load(BlockState state, CompoundNBT nbt) 
	{
		super.load(state, nbt);
		if(!this.tryLoadLootTable(nbt)) 
		{
			this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		}
		ItemStackHelper.loadAllItems(nbt, this.items);
		if(this.level != null)
		{
			if(this.level.isClientSide) 
			{
				this.updateClientVisualsOnLoad();
			}
			else
			{
				this.updateTileOnInventoryChanged();
			}
		}
	}
	
	@Override
	public CompoundNBT save(CompoundNBT nbt) 
	{
		super.save(nbt);
		if(!this.trySaveLootTable(nbt)) 
		{
			ItemStackHelper.saveAllItems(nbt, this.items);
        }
		return nbt;
	}

	@Override
	public SUpdateTileEntityPacket getUpdatePacket() 
	{
		return new SUpdateTileEntityPacket(this.worldPosition, 0, this.getUpdateTag());
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) 
	{
		this.load(this.getBlockState(), pkt.getTag());
	}
	
	@Override
	public NonNullList<ItemStack> getItems() 
	{
		return this.items;
	}
	
	@Override
	protected void setItems(NonNullList<ItemStack> items) 
	{
		this.items = items;
	}

	@Override
	protected ITextComponent getDefaultName() 
	{
		return ModUtils.tTC("container", "display_case");
	}

	@Override
	protected Container createMenu(int windowID, PlayerInventory inv)
	{
		return new DisplayCaseContainer(windowID, inv, this, this);
	}
	
	@Override
	public int getContainerSize() 
	{
		return this.items.size();
	}
	
	public ItemStack getDisplayedItem()
	{
		return this.getItem(0);
	}
}
