package lostworlds.library.item.armour;

import lostworlds.library.tab.ModItemGroup;
import lostworlds.library.util.ModRegistry;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;

public class MaskItem extends ArmorItem
{	
	private MaskItem(IArmorMaterial material, Properties properties) 
	{
		super(material, EquipmentSlotType.HEAD, properties);
	}
	
	private static Item create(Type type)
	{
		return type == Type.CLOTH ? ModRegistry.register("cloth_mask", new MaskItem(ModArmourMaterial.CLOTH_MASK, new Properties().stacksTo(1).tab(ModItemGroup.ITEMS))) : ModRegistry.register("oxygen_mask", new MaskItem(ModArmourMaterial.OXYGEN_MASK, new Properties().stacksTo(1).tab(ModItemGroup.ITEMS)));
	}
	
	public static Item createCloth()
	{
		return create(Type.CLOTH);
	}
	
	public static Item createOxygen()
	{
		return create(Type.OXYGEN);
	}
	
	enum Type
	{
		CLOTH,
		OXYGEN
	}
}
