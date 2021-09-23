package lostworlds.library.item.armour;

import lostworlds.content.ModUtils;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;

public class MaskItem extends ArmorItem
{	
	public MaskItem(IArmorMaterial material) 
	{
		super(material, EquipmentSlotType.HEAD, new Properties().tab(ModUtils.ITEMS));
	}
}
