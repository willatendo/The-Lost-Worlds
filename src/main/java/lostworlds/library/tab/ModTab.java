package lostworlds.library.tab;

import lostworlds.library.util.ModUtils;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModTab extends ItemGroup
{
	private ItemStack icon;
	
	public ModTab(String id, ItemStack icon) 
	{
		super(ModUtils.ID + "." + id);
		this.icon = icon;
	}
		
	@Override
	public ItemStack makeIcon() 
	{
		return this.icon;
	}
	
	public void setIcon(ItemStack icon)
	{
		this.icon = icon;
	}
}
