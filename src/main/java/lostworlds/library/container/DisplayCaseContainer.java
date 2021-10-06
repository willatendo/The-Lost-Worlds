package lostworlds.library.container;

import java.util.Objects;

import lostworlds.content.server.init.ContainerInit;
import lostworlds.library.block.DisplayCaseBlock;
import lostworlds.library.block.entity.DisplayCaseTileEntity;
import lostworlds.library.item.FossilItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

public class DisplayCaseContainer extends Container
{
	private final IWorldPosCallable canInteractWithCallable;
	
	public DisplayCaseContainer(int windowId, PlayerInventory inv, DisplayCaseTileEntity tileEntity, IInventory inventory) 
	{
		super(ContainerInit.DISPLAY_CASE_CONTAINER, windowId);
		this.canInteractWithCallable = IWorldPosCallable.create(tileEntity.getLevel(), tileEntity.getBlockPos());
		
		this.addSlot(new Slot(tileEntity, 0, 80, 20)
		{
			@Override
			public boolean mayPlace(ItemStack stack) 
			{
				return stack.getItem() instanceof BlockItem ? false : stack.getItem() instanceof FossilItem ? false : true;
			}
		});
		
		for(int l = 0; l < 3; ++l) 
		{
			for(int k = 0; k < 9; ++k) 
			{
				this.addSlot(new Slot(inv, k + l * 9 + 9, 8 + k * 18, l * 18 + 51));
			}
		}
		
		for(int i1 = 0; i1 < 9; ++i1) 
		{
			this.addSlot(new Slot(inv, i1, 8 + i1 * 18, 109));
		}
	}

	public DisplayCaseContainer(int windowId, PlayerInventory inv, PacketBuffer buffer)
	{
		this(windowId, inv, getTileEntity(inv, buffer), new Inventory(1));
	}
	
	private static DisplayCaseTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data)
	{
		Objects.requireNonNull(playerInventory, "Error: " + DisplayCaseContainer.class.getSimpleName() + " - Player Inventory cannot be null!");
		Objects.requireNonNull(data, "Error: " + DisplayCaseContainer.class.getSimpleName() + " - Packer Buffer Data cannot be null!");
		
		final TileEntity tileEntityAtPos = playerInventory.player.level.getBlockEntity(data.readBlockPos());
		if(tileEntityAtPos instanceof DisplayCaseTileEntity)
			return (DisplayCaseTileEntity) tileEntityAtPos;
		
		throw new IllegalStateException("Error: " + DisplayCaseContainer.class.getSimpleName() + " - TileEntity is not corrent! " + tileEntityAtPos);
	}

	@Override
	public boolean stillValid(PlayerEntity entity) 
	{
		return this.canInteractWithCallable.evaluate((world, blockPos) -> world.getBlockState(blockPos).getBlock() instanceof DisplayCaseBlock && entity.distanceToSqr((double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.5D, (double) blockPos.getZ() + 0.5D) <= 64.0D, true);
	}
	
	@Override
	public ItemStack quickMoveStack(PlayerEntity player, int getslot) 
	{
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(getslot);
		if(slot != null && slot.hasItem()) 
		{
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if(getslot < 1) 
			{
				if(!this.moveItemStackTo(itemstack1, 1, this.slots.size(), true)) 
				{
					return ItemStack.EMPTY;
				}
			} 
			else if(!this.moveItemStackTo(itemstack1, 0, 1, false)) 
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
		}
		
		return itemstack;
	}
}
