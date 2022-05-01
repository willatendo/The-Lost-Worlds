package lostworlds.server.util.registrate;

import java.util.ArrayList;

public enum WoodTypes {
	LOG,
	STRIPPED_LOG,
	WOOD,
	STRIPPED_WOOD,
	SAPLING,
	LEAVES,
	PLANKS,
	STAIRS,
	SLAB,
	FENCE,
	FENCE_GATE,
	PRESSURE_PLATE,
	BUTTON,
	TRAPDOOR,
	DOOR,
	SIGN,
	BOAT,
	PETRIFIED_LOG,
	STRIPPED_PETRIFIED_LOG;

	public static ArrayList<WoodTypes> get(boolean living, boolean petrifies) {
		ArrayList<WoodTypes> types = new ArrayList<>();

		types.add(LOG);
		types.add(STRIPPED_LOG);
		types.add(WOOD);
		types.add(STRIPPED_WOOD);

		if (living) {
			types.add(SAPLING);
			types.add(LEAVES);
		}

		types.add(PLANKS);
		types.add(STAIRS);
		types.add(SLAB);
		types.add(FENCE);
		types.add(FENCE_GATE);
		types.add(PRESSURE_PLATE);
		types.add(BUTTON);
		types.add(TRAPDOOR);
		types.add(DOOR);
		types.add(SIGN);
		types.add(BOAT);

		if (petrifies) {
			types.add(PETRIFIED_LOG);
			types.add(STRIPPED_PETRIFIED_LOG);
		}

		return types;
	}
}
