package lostworlds.library.entity;

public enum LivingCreatures 
{
	BAT(CreatureSize.TINY, BirthType.GESTATION),
	CAT(CreatureSize.SMALL, BirthType.GESTATION),
	CHICKEN(CreatureSize.SMALL, BirthType.EGG),
	COW(CreatureSize.MEDIUM, BirthType.GESTATION),
	DOLPHIN(CreatureSize.MEDIUM, BirthType.GESTATION),
	DONKEY(CreatureSize.MEDIUM, BirthType.GESTATION),
	FOX(CreatureSize.SMALL, BirthType.GESTATION),
	HORSE(CreatureSize.MEDIUM, BirthType.GESTATION),
	LLAMA(CreatureSize.MEDIUM, BirthType.GESTATION),
	MULE(CreatureSize.MEDIUM, BirthType.GESTATION),
	OCELOT(CreatureSize.SMALL, BirthType.GESTATION),
	PANDA(CreatureSize.MEDIUM, BirthType.GESTATION),
	PARROT(CreatureSize.TINY, BirthType.EGG),
	PIG(CreatureSize.SMALL, BirthType.GESTATION),
	POLAR_BEAR(CreatureSize.MEDIUM, BirthType.GESTATION),
	RABBIT(CreatureSize.SMALL, BirthType.GESTATION),
	SHEEP(CreatureSize.MEDIUM, BirthType.GESTATION),
	TURTLE(CreatureSize.MEDIUM, BirthType.EGG),
	WOLF(CreatureSize.SMALL, BirthType.GESTATION);
	
	private final CreatureSize size;
	private final BirthType type;
	
	private LivingCreatures(CreatureSize size, BirthType type) 
	{
		this.size = size;
		this.type = type;
	}
	
	public CreatureSize size()
	{
		return this.size;
	}
	
	public BirthType type()
	{
		return this.type;
	}
	
	public int getGestationPeriod()
	{
		if(this.size == CreatureSize.TINY)
		{
			return 10000;
		}
		else if(this.size == CreatureSize.SMALL)
		{
			return 20000;
		}
		else if(this.size == CreatureSize.MEDIUM)
		{
			return 30000;
		}
		else
		{
			return 40000;
		}
	}
}
