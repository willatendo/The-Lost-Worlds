package lostworlds.server.item.tool;

import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

public class ModItemTier {
	public static final ItemTier LEATHER = new ItemTier(0, 59, 2.0F, 0.0F, 15, () -> {
		return Ingredient.of(Items.STRING);
	});
	public static final ItemTier GOLD = new ItemTier(0, 32, 2.0F, 0.0F, 22, () -> {
		return Ingredient.of(Items.STRING);
	});
	public static final ItemTier IRON = new ItemTier(2, 250, 2.0F, 2.0F, 14, () -> {
		return Ingredient.of(Items.STRING);
	});
	public static final ItemTier DIAMOND = new ItemTier(3, 1561, 2.0F, 3.0F, 10, () -> {
		return Ingredient.of(Items.STRING);
	});
	public static final ItemTier NETHERITE = new ItemTier(4, 2031, 2.0F, 4.0F, 15, () -> {
		return Ingredient.of(Items.STRING);
	});
	public static final ItemTier CRYSTAL_SCARAB = new ItemTier(5, 13616, 50.0F, 100.0F, 0, () -> {
		return null;
	});
}
