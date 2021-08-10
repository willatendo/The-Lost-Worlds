package lostworlds.library.slot;

import lostworlds.content.server.init.RecipeInit;
import lostworlds.library.inventory.ArchaeologyTableInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.ForgeHooks;

public class ArchaeologyTableResultSlot extends Slot 
{
	private final ArchaeologyTableInventory craftSlots;
	private final PlayerEntity player;
	private int removeCount;

	public ArchaeologyTableResultSlot(PlayerEntity player, ArchaeologyTableInventory inv, IInventory iinv, int slotSeed, int x, int y) 
	{
		super(iinv, slotSeed, x, y);
		this.player = player;
		this.craftSlots = inv;
	}

	@Override
	public boolean mayPlace(ItemStack stack) 
	{
		return false;
	}

	@Override
	public ItemStack remove(int slot) 
	{
		if(this.hasItem()) 
		{
			this.removeCount += Math.min(slot, this.getItem().getCount());
		}

		return super.remove(slot);
	}
	
	@Override
	protected void onQuickCraft(ItemStack stack, int slot) 
	{
		this.removeCount += slot;
		this.checkTakeAchievements(stack);
	}

	@Override
	protected void onSwapCraft(int slot) 
	{
		this.removeCount += slot;
	}

	@Override
	protected void checkTakeAchievements(ItemStack stack) 
	{
		if(this.removeCount > 0) 
		{
			stack.onCraftedBy(this.player.level, this.player, this.removeCount);
			net.minecraftforge.fml.hooks.BasicEventHooks.firePlayerCraftingEvent(this.player, stack, this.craftSlots);
		}

		if(this.container instanceof IRecipeHolder) 
		{
			((IRecipeHolder) this.container).awardUsedRecipes(this.player);
		}

		this.removeCount = 0;
	}

	public ItemStack onTake(PlayerEntity player, ItemStack stack) 
	{
		this.checkTakeAchievements(stack);
		ForgeHooks.setCraftingPlayer(player);
		NonNullList<ItemStack> nonnulllist = player.level.getRecipeManager().getRemainingItemsFor(RecipeInit.ARCHAEOLOGY_TABLE_RECIPE, this.craftSlots, player.level);
		ForgeHooks.setCraftingPlayer(null);
		for(int i = 0; i < nonnulllist.size(); ++i) 
		{
			ItemStack itemstack = this.craftSlots.getItem(i);
			ItemStack itemstack1 = nonnulllist.get(i);
			if(!itemstack.isEmpty()) 
			{
				this.craftSlots.removeItem(i, 1);
				itemstack = this.craftSlots.getItem(i);
			}

			if(!itemstack1.isEmpty()) 
			{
				if(itemstack.isEmpty()) 
				{
					this.craftSlots.setItem(i, itemstack1);
				} 
				else if(ItemStack.isSame(itemstack, itemstack1) && ItemStack.tagMatches(itemstack, itemstack1)) 
				{
					itemstack1.grow(itemstack.getCount());
					this.craftSlots.setItem(i, itemstack1);
				} 
				else if(!this.player.inventory.add(itemstack1)) 
				{
					this.player.drop(itemstack1, false);
				}
			}
		}

		return stack;
	}
}
