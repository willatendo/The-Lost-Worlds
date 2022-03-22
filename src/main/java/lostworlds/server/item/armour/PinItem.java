package lostworlds.server.item.armour;

import java.util.List;

import lostworlds.client.ClientUtils;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class PinItem extends ArmorItem {
	public PinItem() {
		super(ModArmourMaterial.DECO, EquipmentSlotType.CHEST, new Properties().tab(LostWorldsUtils.ITEMS));
	}

	@Override
	public void appendHoverText(ItemStack stack, World world, List<ITextComponent> text, ITooltipFlag flag) {
		if (ClientUtils.isHoldingLeftShift()) {
			text.add(LostWorldsUtils.gTC("toolTip", "item.collectible"));
		}
	}
}