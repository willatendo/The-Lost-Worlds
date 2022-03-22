package lostworlds.server.tab;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModTab extends ItemGroup {
	private ItemStack icon;

	public ModTab(String id) {
		super(LostWorldsUtils.ID + "." + id);
	}

	@Override
	public ItemStack makeIcon() {
		return this.icon;
	}

	public void setIcon(ItemStack icon) {
		this.icon = icon;
	}
}
