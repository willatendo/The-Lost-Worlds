package lostworlds.server.item.armour;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;

public class MaskItem extends ArmorItem {
	public MaskItem(IArmorMaterial material) {
		super(material, EquipmentSlotType.HEAD, new Properties().tab(LostWorldsUtils.ITEMS));
	}
}
