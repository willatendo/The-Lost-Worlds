package lostworlds.server.entity.utils.enums;

import net.minecraft.util.StringRepresentable;

public enum CreatureDiet implements StringRepresentable {
	NONE("none"),
	CARNIVORE("carnivore"),
	HERBIVORE("herbivore"),
	INSECTIVORE("insectivore"),
	OMNIVORE("omnivore"),
	PISCIVORE("piscivore");

	public final String id;

	private CreatureDiet(String id) {
		this.id = id;
	}

	@Override
	public String getSerializedName() {
		return this.id;
	}
}
