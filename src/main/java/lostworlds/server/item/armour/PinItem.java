package lostworlds.server.item.armour;

import java.util.List;

import lostworlds.client.ClientUtils;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class PinItem extends ArmorItem {
	public PinItem(ArmorMaterial material, EquipmentSlot slot, Properties properties) {
		super(material, slot, properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, Level world, List<Component> text, TooltipFlag flag) {
		if (ClientUtils.isHoldingLeftShift()) {
			text.add(LostWorldsUtils.gTC("toolTip", "icon.collectible"));
		}
	}
}
