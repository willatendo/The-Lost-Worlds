package lostworlds.library.item.armour;

import java.util.List;

import lostworlds.content.ClientUtils;
import lostworlds.content.ModUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class PinItem extends ArmorItem {
	public PinItem() {
		super(ModArmourMaterial.DECO, EquipmentSlotType.CHEST, new Properties().tab(ModUtils.ITEMS));
	}

	@Override
	public void appendHoverText(ItemStack stack, World world, List<ITextComponent> text, ITooltipFlag flag) {
		if (ClientUtils.isHoldingLeftShift()) {
			text.add(ModUtils.gTC("toolTip", "item.collectible"));
		}
	}
}
