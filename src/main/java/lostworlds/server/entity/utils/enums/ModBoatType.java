package lostworlds.server.entity.utils.enums;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.client.model.geom.ModelLayerLocation;

public enum ModBoatType {
	ARAUCARIA("araucaria"),
	CALAMITES("calamites"),
	CONIFER("conifer"),
	CYPRESS("cypress"),
	GINKGO("ginkgo"),
	SCORCHED("scorched"),
	SEQUOIA("seqouia");

	private final String name;
	private final ModelLayerLocation layer;

	private ModBoatType(String name) {
		this.name = name;
		this.layer = new ModelLayerLocation(LostWorldsUtils.rL(name), "main");
	}

	public ModelLayerLocation getLayer() {
		return layer;
	}

	public static ModBoatType byId(int id) {
		ModBoatType[] boatType = values();
		if (id < 0 || id >= boatType.length) {
			id = 0;
		}

		return boatType[id];
	}

	public static ModBoatType getTypeFromString(String nameIn) {
		ModBoatType[] boatType = values();

		for (ModBoatType ModBoatType : boatType) {
			if (ModBoatType.getName().equals(nameIn)) {
				return ModBoatType;
			}
		}

		return boatType[0];
	}

	public String getName() {
		return this.name;
	}

	public String toString() {
		return this.name;
	}
}
