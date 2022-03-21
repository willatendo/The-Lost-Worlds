package lostworlds.server.item.armour;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.SoundEvents;
import tyrannotitanlib.library.base.item.TyrannoArmourTeir;

public class ModArmourMaterial {
	public static final TyrannoArmourTeir CLOTH_MASK = new TyrannoArmourTeir(LostWorldsUtils.rL("cloth_mask"), 0, new int[] { 0, 2, 3, 1 }, 0, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
		return Ingredient.of(ItemTags.WOOL);
	});
	public static final TyrannoArmourTeir DECO = new TyrannoArmourTeir(LostWorldsUtils.rL("deco"), 0, new int[] { 1, 1, 1, 1 }, 0, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
		return null;
	});
}
