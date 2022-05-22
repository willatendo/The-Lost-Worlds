package lostworlds.server.block;

import net.minecraft.util.StringRepresentable;

public enum Egg implements StringRepresentable {
	TINY("tiny"),
	SMALL("small"),
	MEDIUM("medium"),
	LARGE("large");

	private final String id;

	private Egg(String id) {
		this.id = id;
	}

	@Override
	public String getSerializedName() {
		return this.id;
	}

}
