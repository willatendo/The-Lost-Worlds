package lostworlds.library.entity.utils.enums;

public enum CreatureTypes {
	BAT(Size.TINY, BirthType.GESTATION),
	CAT(Size.SMALL, BirthType.GESTATION),
	CHICKEN(Size.SMALL, BirthType.EGG),
	COW(Size.MEDIUM, BirthType.GESTATION),
	DOLPHIN(Size.MEDIUM, BirthType.GESTATION),
	DONKEY(Size.MEDIUM, BirthType.GESTATION),
	FOX(Size.SMALL, BirthType.GESTATION),
	HORSE(Size.MEDIUM, BirthType.GESTATION),
	LLAMA(Size.MEDIUM, BirthType.GESTATION),
	MULE(Size.MEDIUM, BirthType.GESTATION),
	OCELOT(Size.SMALL, BirthType.GESTATION),
	PANDA(Size.MEDIUM, BirthType.GESTATION),
	PARROT(Size.TINY, BirthType.EGG),
	PIG(Size.SMALL, BirthType.GESTATION),
	POLAR_BEAR(Size.MEDIUM, BirthType.GESTATION),
	RABBIT(Size.SMALL, BirthType.GESTATION),
	SHEEP(Size.MEDIUM, BirthType.GESTATION),
	TURTLE(Size.MEDIUM, BirthType.EGG),
	WOLF(Size.SMALL, BirthType.GESTATION);

	private final Size size;
	private final BirthType type;

	private CreatureTypes(Size size, BirthType type) {
		this.size = size;
		this.type = type;
	}

	public Size size() {
		return this.size;
	}

	public BirthType type() {
		return this.type;
	}

	public int getGestationPeriod(CreatureTypes creature) {
		if (creature.size() == Size.TINY) {
			return 10000;
		} else if (creature.size() == Size.SMALL) {
			return 20000;
		} else if (creature.size() == Size.MEDIUM) {
			return 30000;
		} else {
			return 40000;
		}
	}
}
