package lostworlds.server.item.armour;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;

public class MaskItem extends ArmorItem {
	public MaskItem(IArmorMaterial material, Properties properties) {
		super(material, EquipmentSlotType.HEAD, properties);
	}
}
