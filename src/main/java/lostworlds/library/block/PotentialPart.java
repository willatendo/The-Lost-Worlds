package lostworlds.library.block;

import net.minecraft.util.IStringSerializable;
import net.minecraftforge.common.IExtensibleEnum;

public enum PotentialPart implements IStringSerializable, IExtensibleEnum
{
	SKULL("skull"),
	RIGHT_ARM("right_arm"),
	LEFT_ARM("left_arm"),
	RIGHT_LEG("right_leg"),
	LEFT_LEG("left_leg"),
	RIB_CAGE("rib_cage"),
	TAIL("tail"),
	NONE("none");

	private final String id;
	
	private PotentialPart(String id) 
	{
		this.id = id;
	}
	
	//Used for addon creation. Use second one, first one is just because IExtensibleEnum is dumb.
	public static PotentialPart create(String name, String id)
	{
		throw new IllegalStateException("Enum not extended");
	}
	
	public static PotentialPart register(String id)
	{
		return create(id, id);
	}
	
	@Override
	public String getSerializedName() 
	{
		return this.id;
	}
}
