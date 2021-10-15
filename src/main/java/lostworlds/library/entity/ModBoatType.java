package lostworlds.library.entity;

import net.minecraftforge.common.IExtensibleEnum;

public enum ModBoatType implements IExtensibleEnum
{
	ARAUCARIA("araucaria"),
	CALAMITES("calamites"),
	CONIFER("conifer"),
	GINKGO("ginkgo"),
	SCORCHED("scorched"),
	SEQUOIA("seqouia");

	private final String name;

	private ModBoatType(String string) 
	{
		this.name = string;
	}

	//Used for addon creation. Use second one, first one is just because IExtensibleEnum is dumb.
	public static ModBoatType create(String name, String id)
	{
		throw new IllegalStateException("Enum not extended");
	}
		
	public static ModBoatType register(String id)
	{
		return create(id, id);
	}
	
	public static ModBoatType byId(int id) 
	{
		ModBoatType[] boatType = values();
		if(id < 0 || id >= boatType.length) 
		{
			id = 0;
		}

		return boatType[id];
	}

	public static ModBoatType getTypeFromString(String nameIn) 
	{
		ModBoatType[] boatType = values();

		for(ModBoatType ModBoatType : boatType) 
		{
			if(ModBoatType.getName().equals(nameIn)) 
			{
				return ModBoatType;
			}
		}

		return boatType[0];
	}

	public String getName() 
	{
		return this.name;
	}

	public String toString() 
	{
		return this.name;
	}
}
