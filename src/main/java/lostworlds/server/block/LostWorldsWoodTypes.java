package lostworlds.server.block;

import java.util.ArrayList;

import org.apache.commons.compress.utils.Lists;

import net.minecraft.world.level.block.state.properties.WoodType;

public class LostWorldsWoodTypes {
	public static final ArrayList<WoodType> WOOD_TYPES = Lists.newArrayList();

	public static final WoodType ARAUCARIA = registerMod("araucaria");
	public static final WoodType CALAMITES = registerMod("calamites");
	public static final WoodType CONIFER = registerMod("conifer");
	public static final WoodType CYPRESS = registerMod("cypress");
	public static final WoodType GINKGO = registerMod("ginkgo");
	public static final WoodType GLASS = registerMod("glass");
	public static final WoodType SCORCHED = registerMod("scorched");
	public static final WoodType SEQUOIA = registerMod("sequoia");

	public static WoodType registerMod(String woodType) {
		WoodType type = WoodType.create("lostworlds:" + woodType);
		WOOD_TYPES.add(type);
		return type;
	}
}
