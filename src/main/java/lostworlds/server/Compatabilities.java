package lostworlds.server;

import java.util.ArrayList;

import org.apache.commons.compress.utils.Lists;

import com.mojang.datafixers.util.Pair;

public class Compatabilities {
	public static final ArrayList<Pair<String, String>> COMPATABILITIES = Lists.newArrayList();

	public static void init() {
		COMPATABILITIES.add(Pair.of("Just Enough Items", "Full support for all custom recipes."));
		COMPATABILITIES.add(Pair.of("Craft Tweaker", "Full support for all custom recipes"));
		COMPATABILITIES.add(Pair.of("Enchantment Descriptions", "Full support for all custom enchantments"));
		COMPATABILITIES.add(Pair.of("Traveler's Titles", "Full support for all biomes and dimension"));
	}
}
