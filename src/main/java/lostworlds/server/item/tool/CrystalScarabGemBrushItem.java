package lostworlds.server.item.tool;

import net.minecraft.world.item.ItemStack;

public class CrystalScarabGemBrushItem extends BrushItem {
	public CrystalScarabGemBrushItem(Properties properties) {
		super(ModItemTier.CRYSTAL_SCARAB, properties);
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return true;
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return true;
	}
}
