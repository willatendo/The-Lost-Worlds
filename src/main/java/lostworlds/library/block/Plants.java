package lostworlds.library.block;

import net.minecraft.util.IStringSerializable;
import net.minecraftforge.common.IExtensibleEnum;

public enum Plants implements IStringSerializable, IExtensibleEnum
{
	NONE("none"),
	ALETHOPTERIS("alethopteris");
	
	private final String id;
	
	private Plants(String id) 
	{
		this.id = id;
	}

	//Used for addon creation. Use second one, first one is just because IExtensibleEnum is dumb.
	public static Plants create(String name, String id)
	{
		throw new IllegalStateException("Enum not extended");
	}
	
	public static Plants register(String id)
	{
		return create(id, id);
	}
	
	@Override
	public String getSerializedName() 
	{
		return this.id;
	}
}
