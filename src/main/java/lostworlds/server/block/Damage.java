package lostworlds.server.block;

import net.minecraft.util.StringRepresentable;

public enum Damage implements StringRepresentable {
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
