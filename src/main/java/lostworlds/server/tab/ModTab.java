package lostworlds.server.tab;

import java.util.function.Supplier;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModTab extends ItemGroup {
	private Supplier<ItemStack> icon;

	public ModTab(String id) {
		super(LostWorldsUtils.ID + "." + id);
	}

	@Override
	public ItemStack makeIcon() {
		return this.icon.get();
	}

	public void setIcon(Supplier<ItemStack> icon) {
		this.icon = icon;
	}
}
