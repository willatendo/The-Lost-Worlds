package lostworlds.library.container;

import lostworlds.content.server.init.ContainerInit;
import lostworlds.library.block.entity.FeedingTroughTileEntity;
import lostworlds.library.container.slot.FeedingTroughSlot;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.ItemStackHandler;

public class FeedingTroughContainer extends Container
{
	private ItemStackHandler handler;
	
	public FeedingTroughContainer(ContainerType<?> container, int windowId) 
	{
		super(container, windowId);
	}
	
	public FeedingTroughContainer(int windowId, PlayerInventory inv, IInventory inventory, ItemStackHandler handler) 
	{
		super(ContainerInit.FEEDING_TROUGH_CONTAINER, windowId);
		this.handler = handler;
		
		this.addSlot(new FeedingTroughSlot(handler, 0, 80, 20));
		
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

	public static FeedingTroughContainer create(int windowId, PlayerInventory inv, PacketBuffer buffer)
	{
		BlockPos pos = buffer.readBlockPos();
		return new FeedingTroughContainer(windowId, inv, new Inventory(1), ((FeedingTroughTileEntity) Minecraft.getInstance().level.getBlockEntity(pos)).handler);
	}

	@Override
	public boolean stillValid(PlayerEntity entity) 
	{
		return !entity.isSpectator();
	}
	
	@Override
	public ItemStack quickMoveStack(PlayerEntity player, int fromSlot) 
	{
		ItemStack previous = ItemStack.EMPTY;
		Slot slot = (Slot) this.slots.get(fromSlot);

		if(slot != null && slot.hasItem()) 
		{
			ItemStack current = slot.getItem();
			previous = current.copy();

			if(fromSlot < this.handler.getSlots()) 
			{
				if(!this.moveItemStackTo(current, this.handler.getSlots(), this.handler.getSlots() + 36, true))
				{
					return ItemStack.EMPTY;
				}
			} 
			else 
			{
				if(!this.moveItemStackTo(current, 0, 6, false))
				{
					return ItemStack.EMPTY;
				}
			}

			if(current.getCount() == 0)
			{
				slot.set(ItemStack.EMPTY);
			}
			else
			{
				slot.setChanged();
			}

			if(current.getCount() == previous.getCount())
			{
				return ItemStack.EMPTY;
			}
			slot.onTake(player, current);
		}
		return previous;
	}
}
