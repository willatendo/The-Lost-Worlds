package lostworlds.server.item.armour;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.SoundEvents;

public class ModArmourMaterial {
	public static final ArmourTier CLOTH_MASK = new ArmourTier(LostWorldsUtils.rL("cloth_mask"), 0, new int[] { 0, 2, 3, 1 }, 0, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
		return Ingredient.of(ItemTags.WOOL);
	});
	public static final ArmourTier DECO = new ArmourTier(LostWorldsUtils.rL("deco"), 0, new int[] { 1, 1, 1, 1 }, 0, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
		return null;
	});
}
