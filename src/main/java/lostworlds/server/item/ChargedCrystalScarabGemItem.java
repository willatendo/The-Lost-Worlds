package lostworlds.server.item;

import lostworlds.server.entity.LostWorldsEntities;
import net.minecraft.world.item.ItemStack;

public class ChargedCrystalScarabGemItem extends CustomEntityItem {
	public ChargedCrystalScarabGemItem(Properties properties) {
		super(() -> LostWorldsEntities.CHARGED_CRYSTAL_SCARAB_GEM_ITEM.get(), Integer.MAX_VALUE, properties);
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return true;
	}
}
