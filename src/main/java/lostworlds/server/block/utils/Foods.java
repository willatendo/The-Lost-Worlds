package lostworlds.server.block.utils;

import java.util.ArrayList;
import java.util.function.Supplier;

import com.google.common.collect.Lists;

import lostworlds.server.block.Plants;
import lostworlds.server.entity.utils.enums.DinoTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class Foods {
	public static final ArrayList<Supplier<Item>> HERBIVORE_FOODS = Lists.newArrayList(() -> Items.APPLE, () -> Items.WHEAT, () -> Items.WHEAT_SEEDS, () -> Items.MELON, () -> Items.MELON_SEEDS, () -> Items.PUMPKIN, () -> Items.PUMPKIN_SEEDS, () -> Items.CARROT, () -> Items.POTATO, () -> Items.BEETROOT, () -> Items.BEETROOT_SEEDS);
	public static final ArrayList<Supplier<Item>> CARNIVORE_FOODS = Lists.newArrayList(() -> Items.BEEF, () -> Items.PORKCHOP, () -> Items.CHICKEN, () -> Items.RABBIT, () -> Items.RABBIT_FOOT, () -> Items.PUMPKIN_SEEDS, () -> Items.MUTTON);
	public static final ArrayList<Supplier<Item>> PISCAVORE_FOODS = Lists.newArrayList(() -> Items.COD, () -> Items.SALMON, () -> Items.TROPICAL_FISH);
	public static final ArrayList<Supplier<Item>> INSECTIVORE_FOODS = Lists.newArrayList();

	static {
		for (DinoTypes dinos : DinoTypes.values()) {
			CARNIVORE_FOODS.add(dinos.getMeat());
		}

		for (Plants plants : Plants.values()) {
			HERBIVORE_FOODS.add(plants.getSeed());
		}
	}

	public static ArrayList<Item> getHerbivoreFoods() {
		ArrayList<Item> foods = new ArrayList<>();
		for (Supplier<Item> list : HERBIVORE_FOODS) {
			foods.add(list.get());
		}
		return foods;
	}

	public static ArrayList<Item> getCarnivoreFoods() {
		ArrayList<Item> foods = new ArrayList<>();
		for (Supplier<Item> list : CARNIVORE_FOODS) {
			foods.add(list.get());
		}
		return foods;
	}

	public static ArrayList<Item> getPiscavoreFoods() {
		ArrayList<Item> foods = new ArrayList<>();
		for (Supplier<Item> list : PISCAVORE_FOODS) {
			foods.add(list.get());
		}
		return foods;
	}

	public static ArrayList<Item> getInsectivoreFoods() {
		ArrayList<Item> foods = new ArrayList<>();
		for (Supplier<Item> list : INSECTIVORE_FOODS) {
			foods.add(list.get());
		}
		return foods;
	}

	public static void init() {
	}
}
