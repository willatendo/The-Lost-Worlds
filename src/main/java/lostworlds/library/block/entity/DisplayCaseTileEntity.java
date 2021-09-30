package lostworlds.library.block.entity;

import lostworlds.content.ModUtils;
import lostworlds.content.server.init.TileEntityInit;
import lostworlds.library.container.DisplayCaseContainer;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IClearable;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;

public class DisplayCaseTileEntity extends LockableLootTileEntity implements IClearable, INamedContainerProvider
{
	private NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);

	public DisplayCaseTileEntity() 
	{
		super(TileEntityInit.DISPLAY_CASE_TILE_ENTITY);
	}

	@Override
	public void load(BlockState state, CompoundNBT nbt) 
	{
		super.load(state, nbt);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(nbt, this.items);
	}
	
	@Override
	public CompoundNBT save(CompoundNBT nbt) 
	{
		super.save(nbt);
		ItemStackHelper.saveAllItems(nbt, this.items);
		return nbt;
	}

	@Override
	public NonNullList<ItemStack> getItems() 
	{
		return this.items;
	}
	
	@Override
	protected void setItems(NonNullList<ItemStack> items) 
	{
		this.items = items;
	}

	@Override
	protected ITextComponent getDefaultName() 
	{
		return ModUtils.tTC("container", "display_case");
	}

	@Override
	protected Container createMenu(int windowID, PlayerInventory inv)
	{
		return new DisplayCaseContainer(windowID, inv, this, this);
	}
	
	@Override
	public int getContainerSize() 
	{
		return this.items.size();
	}
	
	public ItemStack getDisplayedItem()
	{
		return this.getItem(0);
	}
}
