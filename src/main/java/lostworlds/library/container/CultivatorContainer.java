package lostworlds.library.container;

import java.util.Objects;

import lostworlds.content.server.init.ContainerInit;
import lostworlds.content.server.init.RecipeInit;
import lostworlds.library.block.CultivatorBlock;
import lostworlds.library.recipe.CultivatorRecipe;
import lostworlds.library.slot.ResultSlot;
import lostworlds.library.tileentity.CultivatorTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CultivatorContainer extends Container
{
	private final IWorldPosCallable canInteractWithCallable;
	private final IIntArray data;
	private final World level;
	private final IRecipeType<CultivatorRecipe> recipeType = RecipeInit.CULTIVATOR_RECIPE;
	public final CultivatorTileEntity tile;
	
	public CultivatorContainer(int windowID, PlayerInventory playerInv, CultivatorTileEntity tileEntity, IInventory tile) 
	{
		super(ContainerInit.CULTIVATOR_CONTAINER, windowID);
		this.level = playerInv.player.level;
		this.data = tileEntity.getGrinderData();
		this.tile = tileEntity;
		this.canInteractWithCallable = IWorldPosCallable.create(tileEntity.getLevel(), tileEntity.getBlockPos());
		
		this.addSlot(new Slot(tile, 0, 53, 35));
		this.addSlot(new ResultSlot(playerInv.player, tile, 1, 116, 35));
		
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
	
	public CultivatorContainer(int windowID, PlayerInventory playerInv, PacketBuffer data) 
	{
		this(windowID, playerInv, new CultivatorTileEntity(), getTileEntity(playerInv, data));
 	}
	
	private static CultivatorTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data)
	{
		Objects.requireNonNull(playerInventory, "Error: " + CultivatorContainer.class.getSimpleName() + " - Player Inventory cannot be null!");
		Objects.requireNonNull(data, "Error: " + CultivatorContainer.class.getSimpleName() + " - Packer Buffer Data cannot be null!");
		
		final TileEntity tileEntityAtPos = playerInventory.player.level.getBlockEntity(data.readBlockPos());
		if(tileEntityAtPos instanceof CultivatorTileEntity)
			return (CultivatorTileEntity) tileEntityAtPos;
		
		throw new IllegalStateException("Error: " + CultivatorContainer.class.getSimpleName() + " - TileEntity is not corrent! " + tileEntityAtPos);
	}
	
	@Override
	public boolean stillValid(PlayerEntity playerIn)
	{
		return this.canInteractWithCallable.evaluate((world, blockPos) -> world.getBlockState(blockPos).getBlock() instanceof CultivatorBlock && playerIn.distanceToSqr((double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.5D, (double) blockPos.getZ() + 0.5D) <= 64.0D, true);
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
			if(i == 1) 
			{
				if(!this.moveItemStackTo(itemstack1, 2, 38, true)) 
				{
					return ItemStack.EMPTY;
				}
				
				slot.onQuickCraft(itemstack1, itemstack);
			}
			else if(i != 0) 
			{
				if(canGrind(itemstack1)) 
				{
					if(!this.moveItemStackTo(itemstack1, 0, 1, false)) 
					{
						return ItemStack.EMPTY;
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
			
			if (itemstack1.getCount() == itemstack.getCount()) 
			{
				return ItemStack.EMPTY;
			}
			
			slot.onTake(player, itemstack1);
		}
		
		return itemstack;
	}
	
	protected boolean canGrind(ItemStack stack) 
	{
		return this.level.getRecipeManager().getRecipeFor((IRecipeType)this.recipeType, new Inventory(stack), this.level).isPresent();
	}
	
	@OnlyIn(Dist.CLIENT)
	public int getProgress()
	{
		int cultivatingProgress = this.data.get(2);
        int cultivatingTotalTime = this.data.get(3);
        return cultivatingTotalTime != 0 && cultivatingProgress != 0 ? cultivatingProgress * 35 / cultivatingTotalTime : 0;
    }
}
