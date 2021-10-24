package lostworlds.library.container;

import lostworlds.content.server.init.ContainerInit;
import lostworlds.library.block.entity.DisplayCaseTileEntity;
import lostworlds.library.item.FossilItem;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class DisplayCaseContainer extends Container
{
	private ItemStackHandler handler;
	
	public DisplayCaseContainer(ContainerType<?> container, int windowId) 
	{
		super(container, windowId);
	}
	
	public DisplayCaseContainer(int windowId, PlayerInventory inv, IInventory inventory, ItemStackHandler handler) 
	{
		super(ContainerInit.DISPLAY_CASE_CONTAINER, windowId);
		this.handler = handler;
		
		this.addSlot(new SlotItemHandler(handler, 0, 80, 20)
		{
			@Override
			public boolean mayPlace(ItemStack stack) 
			{
				return correctTypeOfItem(stack);
			}
			
			public boolean correctTypeOfItem(ItemStack stack)
			{
				if(stack.getItem() instanceof BlockItem)
				{
					return false;
				}
				else if(stack.getItem() instanceof FossilItem)
				{
					return false;
				}
				else
				{
					return true;
				}
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

	public static DisplayCaseContainer create(int windowId, PlayerInventory inv, PacketBuffer buffer)
	{
		BlockPos pos = buffer.readBlockPos();
		return new DisplayCaseContainer(windowId, inv, new Inventory(1), ((DisplayCaseTileEntity) Minecraft.getInstance().level.getBlockEntity(pos)).handler);
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
