package lostworlds.server.block;

import net.minecraft.util.IStringSerializable;

public enum Damage implements IStringSerializable {
	NONE,
	CHIPPED,
	SLIGHTLY,
	CRACKED,
	DAMAGED,
	COMPLETELY;

	@Override
	public String getSerializedName() {
		switch (this) {
		case CHIPPED:
			return "chipped";
		case SLIGHTLY:
			return "slightly";
		case CRACKED:
			return "cracked";
		case DAMAGED:
			return "damaged";
		case COMPLETELY:
			return "completely";
		case NONE:
		default:
			return "none";
		}
	}
}
