package lostworlds.library.container;

import java.util.Objects;

import lostworlds.content.server.init.BlockInit;
import lostworlds.content.server.init.ContainerInit;
import lostworlds.library.block.FossilCleanerBlock;
import lostworlds.library.block.entity.FossilCleanerTileEntity;
import lostworlds.library.container.slot.FossilCleanerFuelSlot;
import lostworlds.library.container.slot.PlasteredFossilSlot;
import lostworlds.library.container.slot.ResultSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FossilCleanerContainer extends Container
{
	private final IWorldPosCallable canInteractWithCallable;
	private final IIntArray data;
	public final FossilCleanerTileEntity tile;
	
	public FossilCleanerContainer(int windowID, PlayerInventory playerInv, FossilCleanerTileEntity tileEntity, IInventory tile) 
	{
		super(ContainerInit.FOSSIL_CLEANER_CONTAINER, windowID);
		this.data = tileEntity.getCleanerData();
		this.tile = tileEntity;
		this.canInteractWithCallable = IWorldPosCallable.create(tileEntity.getLevel(), tileEntity.getBlockPos());
		
		this.addSlot(new PlasteredFossilSlot(tile, 0, 56, 17));
		this.addSlot(new FossilCleanerFuelSlot(tile, 1, 56, 53));
		this.addSlot(new ResultSlot(playerInv.player, tile, 2, 116, 35));
		this.addSlot(new ResultSlot(playerInv.player, tile, 3, 139, 16));
		this.addSlot(new ResultSlot(playerInv.player, tile, 4, 139, 35));
		this.addSlot(new ResultSlot(playerInv.player, tile, 5, 139, 54));
		
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
	
	protected boolean canClean(ItemStack stack) 
	{
		return stack.getItem() == BlockInit.ACCENT_DARK_CONCRETE.asItem();
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