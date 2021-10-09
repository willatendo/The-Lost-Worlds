package lostworlds.library.block;

import net.minecraft.util.IStringSerializable;

public enum Egg implements IStringSerializable
{
	TINY("tiny"),
	SMALL("small"),
	MEDIUM("medium"),
	LARGE("large");
	
	private final String id;

	private Egg(String id) 
	{
		this.id = id;
	}
	
	@Override
	public String getSerializedName() 
	{
		return this.id;
	}
	
}
