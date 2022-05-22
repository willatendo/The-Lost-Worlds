package lostworlds.server.tab;

import java.util.function.Supplier;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModTab extends CreativeModeTab {
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
