package lostworlds.library.block;

import net.minecraft.util.IStringSerializable;

public enum PotentialPart implements IStringSerializable {
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
