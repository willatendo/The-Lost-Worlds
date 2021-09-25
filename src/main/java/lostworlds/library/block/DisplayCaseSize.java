package lostworlds.library.block;

import net.minecraft.util.IStringSerializable;

public enum DisplayCaseSize implements IStringSerializable
{
	SINGLE("single"),
	LEFT("left"),
	RIGHT("right"),
	BOTH("both");

	private final String id;
	
	private DisplayCaseSize(String id) 
	{
		this.id = id;
	}
	
	@Override
	public String getSerializedName() 
	{
		return this.id;
	}
}
