package lostworlds.library.item;

import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.util.NonNullSupplier;

public class SexSpawnEggItem extends ModSpawnEggItem {
	public SexSpawnEggItem(NonNullSupplier<? extends EntityType<?>> entityTypeSupplier, int primaryColour, int secondaryColour, ItemGroup group) {
		super(entityTypeSupplier, primaryColour, secondaryColour, group);
	}

}
