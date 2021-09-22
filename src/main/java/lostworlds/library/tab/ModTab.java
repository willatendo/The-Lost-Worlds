package lostworlds.library.tab;

import lostworlds.content.ModUtils;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModTab extends ItemGroup 
{
	private ItemStack icon;

	public ModTab() 
	{
		super(getGroupCountSafe(), ModUtils.ID + ".lost_worlds");
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
