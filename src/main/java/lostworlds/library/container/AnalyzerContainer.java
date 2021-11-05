package lostworlds.library.container;

import java.util.Objects;

import lostworlds.content.server.init.ContainerInit;
import lostworlds.content.server.init.RecipeInit;
import lostworlds.library.block.AnalyzerBlock;
import lostworlds.library.block.entity.AnalyzerTileEntity;
import lostworlds.library.container.recipes.AnalyzerRecipe;
import lostworlds.library.container.slot.DNASlot;
import lostworlds.library.container.slot.DiscSlot;
import lostworlds.library.container.slot.ResultSlot;
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

public class AnalyzerContainer extends Container
{
	private final IWorldPosCallable canInteractWithCallable;
	private final IIntArray data;
	private final World level;
	private final IRecipeType<AnalyzerRecipe> recipeType = RecipeInit.ANALYZER_RECIPE;
	public final AnalyzerTileEntity tile;
	
	public AnalyzerContainer(int windowID, PlayerInventory playerInv, AnalyzerTileEntity tileEntity, IInventory tile) 
	{
		super(ContainerInit.ANALYZER_CONTAINER, windowID);
		this.level = playerInv.player.level;
		this.data = tileEntity.getAnalysingData();
		this.tile = tileEntity;
		this.canInteractWithCallable = IWorldPosCallable.create(tileEntity.getLevel(), tileEntity.getBlockPos());
	
		this.addSlot(new DNASlot(tile, 0, 56, 25));
		this.addSlot(new DiscSlot(tile, 1, 56, 45));
		this.addSlot(new ResultSlot(playerInv.player, tile, 2, 116, 35));
		
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
	
	public AnalyzerContainer(int windowID, PlayerInventory playerInv, PacketBuffer data) 
	{
		this(windowID, playerInv, new AnalyzerTileEntity(), getTileEntity(playerInv, data));
 	}
	
	private static AnalyzerTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data)
	{
		Objects.requireNonNull(playerInventory, "Error: " + AnalyzerContainer.class.getSimpleName() + " - Player Inventory cannot be null!");
		Objects.requireNonNull(data, "Error: " + AnalyzerContainer.class.getSimpleName() + " - Packer Buffer Data cannot be null!");
		
		final TileEntity tileEntityAtPos = playerInventory.player.level.getBlockEntity(data.readBlockPos());
		if(tileEntityAtPos instanceof AnalyzerTileEntity)
			return (AnalyzerTileEntity) tileEntityAtPos;
		
		throw new IllegalStateException("Error: " + AnalyzerContainer.class.getSimpleName() + " - TileEntity is not corrent! " + tileEntityAtPos);
	}
	
	@Override
	public boolean stillValid(PlayerEntity entity)
	{
		return this.canInteractWithCallable.evaluate((world, blockPos) -> world.getBlockState(blockPos).getBlock() instanceof AnalyzerBlock && entity.distanceToSqr((double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.5D, (double) blockPos.getZ() + 0.5D) <= 64.0D, true);
    }
	
	@OnlyIn(Dist.CLIENT)
	public int getProgress()
	{
		int analysingProgress = this.data.get(2);
        int analysingTotalTime = this.data.get(3);
        return analysingTotalTime != 0 && analysingProgress != 0 ? analysingProgress * 32 / analysingTotalTime : 0;
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
			if(i == 2) 
			{
				if(!this.moveItemStackTo(itemstack1, 3, 39, true)) 
				{
					return ItemStack.EMPTY;
				}
				
				slot.onQuickCraft(itemstack1, itemstack);
			}
			else if(i != 1 && i != 0) 
			{
				if(canAnalyze(itemstack1)) 
				{
					if(!this.moveItemStackTo(itemstack1, 0, 2, true)) 
					{
						return ItemStack.EMPTY;
					}
				}
				else if(i >= 3 && i < 30) 
				{
					if(!this.moveItemStackTo(itemstack1, 30, 39, false)) 
					{
						return ItemStack.EMPTY;
					}
				} 
				else if(i >= 30 && i < 39 && !this.moveItemStackTo(itemstack1, 3, 30, false)) 
				{
					return ItemStack.EMPTY;
				}
			}
			else if(!this.moveItemStackTo(itemstack1, 3, 39, false)) 
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
	
	protected boolean canAnalyze(ItemStack stack) 
	{
		return this.level.getRecipeManager().getRecipeFor((IRecipeType)this.recipeType, new Inventory(stack), this.level).isPresent();
	}
}