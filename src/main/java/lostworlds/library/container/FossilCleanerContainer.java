package lostworlds.library.container;

import java.util.Objects;

import lostworlds.content.server.init.BlockInit;
import lostworlds.content.server.init.ContainerInit;
import lostworlds.library.block.FossilCleanerBlock;
import lostworlds.library.slot.FossilCleanerFuelSlot;
import lostworlds.library.slot.PlasteredFossilSlot;
import lostworlds.library.tileentity.FossilCleanerTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.FurnaceResultSlot;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/*
 * Author: Willatendo
 * Date: July 8, 2021
 */

public class FossilCleanerContainer extends Container
{
	private final IWorldPosCallable canInteractWithCallable;
	private final IIntArray data;
	public final FossilCleanerTileEntity tile;
	
	public FossilCleanerContainer(int windowID, PlayerInventory playerInv, FossilCleanerTileEntity tileEntity, IInventory tile) 
	{
		super(ContainerInit.FOSSIL_CLEANER_CONTAINER.get(), windowID);
		this.data = tileEntity.getCleanerData();
		this.tile = tileEntity;
		this.canInteractWithCallable = IWorldPosCallable.create(tileEntity.getLevel(), tileEntity.getBlockPos());
		
		this.addSlot(new PlasteredFossilSlot(tile, 0, 56, 17));
		this.addSlot(new FossilCleanerFuelSlot(tile, 1, 56, 53));
		this.addSlot(new FurnaceResultSlot(playerInv.player, tile, 2, 116, 35));
		
		for(int i = 0; i < 3; ++i) 
		{
			for(int j = 0; j < 9; ++j) 
			{
				this.addSlot(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		
		for(int k = 0; k < 9; ++k) 
		{
			this.addSlot(new Slot(playerInv, k, 8 + k * 18, 142));
		}
		
		this.addDataSlots(this.data);
	}
	
	public FossilCleanerContainer(int windowID, PlayerInventory playerInv, PacketBuffer data) 
	{
		this(windowID, playerInv, new FossilCleanerTileEntity(), getTileEntity(playerInv, data));
 	}
	
	private static FossilCleanerTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data)
	{
		Objects.requireNonNull(playerInventory, "Error: " + FossilCleanerContainer.class.getSimpleName() + " - Player Inventory cannot be null!");
		Objects.requireNonNull(data, "Error: " + FossilCleanerContainer.class.getSimpleName() + " - Packer Buffer Data cannot be null!");
		
		final TileEntity tileEntityAtPos = playerInventory.player.level.getBlockEntity(data.readBlockPos());
		if(tileEntityAtPos instanceof FossilCleanerTileEntity)
			return (FossilCleanerTileEntity) tileEntityAtPos;
		
		throw new IllegalStateException("Error: " + FossilCleanerContainer.class.getSimpleName() + " - TileEntity is not corrent! " + tileEntityAtPos);
	}
	
	@Override
	public boolean stillValid(PlayerEntity playerIn)
	{
		return this.canInteractWithCallable.evaluate((world, blockPos) -> world.getBlockState(blockPos).getBlock() instanceof FossilCleanerBlock && playerIn.distanceToSqr((double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.5D, (double) blockPos.getZ() + 0.5D) <= 64.0D, true);
    }
	
	@Override
	public ItemStack quickMoveStack(PlayerEntity player, int i) 
	{
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(i);
		if(slot != null && slot.hasItem()) 
		{
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if(i != 0 && i != 0) 
			{
				if(canClean(itemstack1))
				{
					if(!this.moveItemStackTo(itemstack1, 0, 1, false)) 
					{
						return ItemStack.EMPTY;
					}
				}
				else if(isWaterBucket(itemstack1))
				{
					if(!this.moveItemStackTo(itemstack1, 1, 2, false)) 
					{
						return ItemStack.EMPTY;
					}
				}
				else if(i == 1) 
				{
					if(isBucket(itemstack1))
					{
						if(!this.moveItemStackTo(itemstack1, 2, 38, true)) 
						{
							return ItemStack.EMPTY;
						}
					}
				}
				else if(i >= 2 && i < 29) 
				{
					if(!this.moveItemStackTo(itemstack1, 29, 38, false)) 
					{
						return ItemStack.EMPTY;
					}
				} 
				else if(i >= 29 && i < 38 && !this.moveItemStackTo(itemstack1, 2, 29, false)) 
				{
					return ItemStack.EMPTY;
				}
			}
			else if(!this.moveItemStackTo(itemstack1, 2, 38, false)) 
			{
				return ItemStack.EMPTY;
			}
			
			if(itemstack1.isEmpty()) 
			{
				slot.set(ItemStack.EMPTY);
			}
			else 
			{
				slot.setChanged();
			}
			
			if(itemstack1.getCount() == itemstack.getCount()) 
			{
				return ItemStack.EMPTY;
			}
			
			slot.onTake(player, itemstack1);
		}
		
		return itemstack;
	}
	
	protected boolean canClean(ItemStack stack) 
	{
		return stack.getItem() == BlockInit.PLASTERED_FOSSIL.asItem();
	}
	
	protected boolean isWaterBucket(ItemStack stack) 
	{
		return stack.getItem() == Items.WATER_BUCKET;
	}
	
	protected boolean isBucket(ItemStack stack) 
	{
		return stack.getItem() == Items.BUCKET;
	}
	
	@OnlyIn(Dist.CLIENT)
	public boolean isOn() 
	{
		return this.data.get(0) > 0;
	}
	
	@OnlyIn(Dist.CLIENT)
	public int getProgress()
	{
		int cleaningProgress = this.data.get(2);
        int cleaningTotalTime = this.data.get(3);
        return cleaningTotalTime != 0 && cleaningProgress != 0 ? cleaningProgress * 32 / cleaningTotalTime : 0;
    }
	
	@OnlyIn(Dist.CLIENT)
	public int getOnProgress() 
	{
		int i = this.data.get(1);
		if(i == 0) 
		{
			i = 3500;
		}
		
		return this.data.get(0) * 16 / i;
	}
}