package lostworlds.server.item.tool;

import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import tyrannotitanlib.library.base.item.TyrannoItemTeir;

public class ModItemTier {
	public static final TyrannoItemTeir LEATHER = new TyrannoItemTeir(0, 59, 2.0F, 0.0F, 15, () -> {
		return Ingredient.of(Items.STRING);
	});
	public static final TyrannoItemTeir GOLD = new TyrannoItemTeir(0, 32, 2.0F, 0.0F, 22, () -> {
		return Ingredient.of(Items.STRING);
	});
	public static final TyrannoItemTeir IRON = new TyrannoItemTeir(2, 250, 2.0F, 2.0F, 14, () -> {
		return Ingredient.of(Items.STRING);
	});
	public static final TyrannoItemTeir DIAMOND = new TyrannoItemTeir(3, 1561, 2.0F, 3.0F, 10, () -> {
		return Ingredient.of(Items.STRING);
	});
	public static final TyrannoItemTeir NETHERITE = new TyrannoItemTeir(4, 2031, 2.0F, 4.0F, 15, () -> {
		return Ingredient.of(Items.STRING);
	});
	public static final TyrannoItemTeir CRYSTAL_SCARAB = new TyrannoItemTeir(5, 13616, 50.0F, 100.0F, 0, () -> {
		return null;
	});
}
