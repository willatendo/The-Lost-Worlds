package lostworlds.library.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.util.NonNullList;

public class ArchaeologyTableInventory implements IInventory, IRecipeHelperPopulator
{
	private final NonNullList<ItemStack> items;
	private final int width;
	private final int height;
	private final Container menu;
	
	public ArchaeologyTableInventory(Container container, int width, int height) 
	{
		this.items = NonNullList.withSize(width * height, ItemStack.EMPTY);
		this.menu = container;
		this.width = width;
		this.height = height;
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
	public ItemStack getItem(int slot) 
	{
		return slot >= this.getContainerSize() ? ItemStack.EMPTY : this.items.get(slot);
	}
	
	@Override
	public ItemStack removeItemNoUpdate(int slot) 
	{
		return ItemStackHelper.takeItem(this.items, slot);
	}
	
	@Override
	public ItemStack removeItem(int width, int height) 
	{
		ItemStack itemstack = ItemStackHelper.removeItem(this.items, width, height);
		if(!itemstack.isEmpty()) 
		{
			this.menu.slotsChanged(this);
		}
		
		return itemstack;
	}
	
	@Override
	public void setItem(int slot, ItemStack stack) 
	{
		this.items.set(slot, stack);
		this.menu.slotsChanged(this);
	}
	
	@Override
	public void setChanged() { }
	
	@Override
	public boolean stillValid(PlayerEntity player) 
	{
		return true;
	}
	
	@Override
	public void clearContent() 
	{
		this.items.clear();
	}
	
	public int getHeight() 
	{
		return this.height;
	}
	
	public int getWidth() 
	{
		return this.width;
	}
	
	@Override
	public void fillStackedContents(RecipeItemHelper helper) 
	{
		for(ItemStack itemstack : this.items) 
		{
			helper.accountSimpleStack(itemstack);
		}
	}
}
