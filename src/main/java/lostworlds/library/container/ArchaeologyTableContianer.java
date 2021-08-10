package lostworlds.library.container;

import java.util.Optional;

import lostworlds.content.server.init.BlockInit;
import lostworlds.content.server.init.ContainerInit;
import lostworlds.content.server.init.RecipeInit;
import lostworlds.library.inventory.ArchaeologyTableInventory;
import lostworlds.library.inventory.ArchaeologyTableResultInventory;
import lostworlds.library.recipe.ArchaeologyTableRecipe;
import lostworlds.library.slot.ArchaeologyTableResultSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.world.World;

public class ArchaeologyTableContianer extends Container 
{
	private final ArchaeologyTableInventory craftSlots = new ArchaeologyTableInventory(this, 5, 5);
	private final ArchaeologyTableResultInventory resultSlots = new ArchaeologyTableResultInventory();
	private final IWorldPosCallable access;
	private final PlayerEntity player;

	public ArchaeologyTableContianer(int windowId, PlayerInventory playerInv, PacketBuffer buffer) 
	{
		this(windowId, playerInv, IWorldPosCallable.NULL);
	}

	public ArchaeologyTableContianer(int windowId, PlayerInventory playerInv, IWorldPosCallable callable) 
	{
		super(ContainerInit.ARCHAEOLOGY_CONTAINER.get(), windowId);
		this.access = callable;
		this.player = playerInv.player;
		this.addSlot(new ArchaeologyTableResultSlot(playerInv.player, this.craftSlots, this.resultSlots, 0, 124, 35));

		for(int i = 0; i < 3; ++i) 
		{
			for(int j = 0; j < 3; ++j) 
			{
				this.addSlot(new Slot(this.craftSlots, j + i * 3, 30 + j * 18, 17 + i * 18));
			}
		}
		
		for(int k = 0; k < 3; ++k) 
		{
			for(int i1 = 0; i1 < 9; ++i1) 
			{
				this.addSlot(new Slot(playerInv, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
			}
		}
		
		for(int l = 0; l < 9; ++l) 
		{
			this.addSlot(new Slot(playerInv, l, 8 + l * 18, 142));
		}
	}

	protected static void slotChangedCraftingGrid(int slot, World world, PlayerEntity player, ArchaeologyTableInventory inv, ArchaeologyTableResultInventory result) 
	{
		if (!world.isClientSide) {
			ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) player;
			ItemStack itemstack = ItemStack.EMPTY;
			Optional<ArchaeologyTableRecipe> optional = world.getServer().getRecipeManager().getRecipeFor(RecipeInit.ARCHAEOLOGY_TABLE_RECIPE, inv, world);
			if(optional.isPresent()) 
			{
				ArchaeologyTableRecipe recipe = optional.get();
				if(result.setRecipeUsed(world, serverplayerentity, recipe)) 
				{
					itemstack = recipe.assemble(inv);
				}
			}

			result.setItem(0, itemstack);
			serverplayerentity.connection.send(new SSetSlotPacket(slot, 0, itemstack));
		}
	}

	@Override
	public void slotsChanged(IInventory iinv) 
	{
		this.access.execute((world, p_217069_2_) -> 
		{
			slotChangedCraftingGrid(this.containerId, world, this.player, this.craftSlots, this.resultSlots);
		});
	}

	public void fillCraftSlotsStackedContents(RecipeItemHelper helper) 
	{
		this.craftSlots.fillStackedContents(helper);
	}

	public void clearCraftingContent() 
	{
		this.craftSlots.clearContent();
		this.resultSlots.clearContent();
	}

	public boolean recipeMatches(IRecipe<? super ArchaeologyTableInventory> iRecipe) 
	{
		return iRecipe.matches(this.craftSlots, this.player.level);
	}

	@Override
	public void removed(PlayerEntity player) 
	{
		super.removed(player);
		this.access.execute((world, p_217068_3_) -> 
		{
			this.clearContainer(player, world, this.craftSlots);
		});
	}

	@Override
	public boolean stillValid(PlayerEntity player) 
	{
		return stillValid(this.access, player, BlockInit.ARCHAEOLOGY_TABLE);
	}

	@Override
	public ItemStack quickMoveStack(PlayerEntity player, int containerSlot) 
	{
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(containerSlot);
		if(slot != null && slot.hasItem()) 
		{
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if(containerSlot == 0) 
			{
				this.access.execute((world, p_217067_3_) -> 
				{
					itemstack1.getItem().onCraftedBy(itemstack1, world, player);
				});
				if(!this.moveItemStackTo(itemstack1, 10, 46, true)) 
				{
					return ItemStack.EMPTY;
				}

				slot.onQuickCraft(itemstack1, itemstack);
			} 
			else if(containerSlot >= 10 && containerSlot < 46) 
			{
				if(!this.moveItemStackTo(itemstack1, 1, 10, false)) 
				{
					if(containerSlot < 37) 
					{
						if(!this.moveItemStackTo(itemstack1, 37, 46, false)) 
						{
							return ItemStack.EMPTY;
						}
					} 
					else if(!this.moveItemStackTo(itemstack1, 10, 37, false)) 
					{
						return ItemStack.EMPTY;
					}
				}
			} 
			else if(!this.moveItemStackTo(itemstack1, 10, 46, false)) 
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

			ItemStack itemstack2 = slot.onTake(player, itemstack1);
			if(containerSlot == 0) 
			{
				player.drop(itemstack2, false);
			}
		}

		return itemstack;
	}

	@Override
	public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) 
	{
		return slot.container != this.resultSlots && super.canTakeItemForPickAll(stack, slot);
	}
}
