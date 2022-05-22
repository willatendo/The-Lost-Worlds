package lostworlds.server.item.armour;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;

public class MaskItem extends ArmorItem {
	public MaskItem(ArmorMaterial material, Properties properties) {
		super(material, EquipmentSlot.HEAD, properties);
	}
}
