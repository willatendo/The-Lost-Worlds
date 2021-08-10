package lostworlds.library.container;

import lostworlds.content.server.init.BlockInit;
import lostworlds.content.server.init.ContainerInit;
import lostworlds.content.server.init.ItemInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.BookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IWorldPosCallable;

/*
 * Author: Willatendo
 * Date: July 8, 2021
 */

public class TimeMachineContainer extends Container
{
	private final IInventory timeMachineSlots = new Inventory(2) 
	{
		public void setChanged() 
		{
			super.setChanged();
			TimeMachineContainer.this.slotsChanged(this);
		}
	};
	private final IWorldPosCallable access;
	
	public TimeMachineContainer(int windowId, PlayerInventory inv, IWorldPosCallable worldPos) 
	{
		super(ContainerInit.TIME_MACHINE_CONTAINER.get(), windowId);
		
		this.access = worldPos;
		
		this.slots.add(new Slot(timeMachineSlots, 0, 8, 34)
		{
			@Override
			public boolean mayPlace(ItemStack stack) 
			{
				return stack.getItem() instanceof BookItem ? true : false;
			}
			
			@Override
			public int getMaxStackSize() 
			{
				return 1;
			}
		});
		this.slots.add(new Slot(timeMachineSlots, 1, 29, 34)
		{
			@Override
			public boolean mayPlace(ItemStack stack) 
			{
				return stack.getItem() == ItemInit.CHARGED_CRYSTAL_SCARAB_GEM ? true : false;
			}
		});
		
		for(int i = 0; i < 3; ++i) 
		{
			for(int j = 0; j < 9; ++j) 
			{
				this.addSlot(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		
		for(int k = 0; k < 9; ++k) 
		{
			this.addSlot(new Slot(inv, k, 8 + k * 18, 142));
		}
	}
	
	public TimeMachineContainer(int windowId, PlayerInventory inv, PacketBuffer buffer) 
	{
		this(windowId, inv, IWorldPosCallable.NULL);
	}

	@Override
	public boolean stillValid(PlayerEntity player) 
	{
		return stillValid(this.access, player, BlockInit.TIME_MACHINE);
	}
}
