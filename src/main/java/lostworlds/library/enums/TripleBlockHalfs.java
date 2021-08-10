package lostworlds.library.enums;

import net.minecraft.util.IStringSerializable;

/*
 * Author: Willatendo
 * Date: July 2, 2021
 */

public enum TripleBlockHalfs implements IStringSerializable
{
	BOTTOM,
	MIDDLE,
	TOP;
	
	@Override
	public String toString()
	{
		return this.getSerializedName();
	}
	
	@Override
	public String getSerializedName() 
	{
		if(this == BOTTOM)
		{
			return "bottom";
		}
		else if(this == MIDDLE)
		{
			return "middle";
		}
		else 
		{
			return "top";
		}
	}
}
