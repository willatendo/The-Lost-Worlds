package lostworlds.server.item;

import java.util.function.Supplier;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvent;

public class AscentedMusicDiscItem extends MusicDiscItem {
	public AscentedMusicDiscItem(int comparatorValue, Supplier<SoundEvent> soundSupplier, Properties builder) {
		super(comparatorValue, soundSupplier, builder);
	}

	@Override
	public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> toFill) {
		return;
	}
}
