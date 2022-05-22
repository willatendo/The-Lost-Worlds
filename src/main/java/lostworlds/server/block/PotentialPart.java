package lostworlds.server.block;

import net.minecraft.util.StringRepresentable;

public enum PotentialPart implements StringRepresentable {
	SKULL("skull"),
	ARM("arm"),
	LEG("leg"),
	RIB_CAGE("rib_cage"),
	TAIL("tail"),
	NONE("none");

	private final String id;

	private PotentialPart(String id) {
		this.id = id;
	}

	@Override
	public String getSerializedName() {
		return this.id;
	}
}
