package lostworlds.library.tileentity;

import javax.annotation.Nullable;

import lostworlds.content.server.init.TileEntityInit;
import lostworlds.library.container.TimeMachineContainer;
import lostworlds.library.util.ModUtil;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.INameable;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.text.ITextComponent;

/*
 * Author: Willatendo
 * Date: July 8, 2021
 */

public class TimeMachineTileEntity extends TileEntity implements INameable, INamedContainerProvider	
{
	private ITextComponent name;

	public TimeMachineTileEntity() 
	{
		super(TileEntityInit.TIME_MACHINE_TILE_ENTITY.get());
	}
	
	public CompoundNBT save(CompoundNBT nbt) 
	{
		super.save(nbt);
		if(this.hasCustomName()) 
		{
			nbt.putString("CustomName", ITextComponent.Serializer.toJson(this.name));
		}
		
		return nbt;
	}
	
	public void load(BlockState state, CompoundNBT nbt) 
	{
		super.load(state, nbt);
		if(nbt.contains("CustomName", 8)) 
		{
			this.name = ITextComponent.Serializer.fromJson(nbt.getString("CustomName"));
		}
	}
	
	public ITextComponent getName() 
	{
		return (ITextComponent)(this.name != null ? this.name : ModUtil.tTC("container", "time_machine"));
	}
	
	public void setCustomName(@Nullable ITextComponent text) 
	{
		this.name = text;
	}
	
	@Nullable
	public ITextComponent getCustomName() 
	{
		return this.name;
	}

	@Override
	public Container createMenu(int windowId, PlayerInventory inv, PlayerEntity player) 
	{
		return new TimeMachineContainer(windowId, inv, IWorldPosCallable.NULL);
	}

	@Override
	public ITextComponent getDisplayName() 
	{
		return this.getName();
	}
}
