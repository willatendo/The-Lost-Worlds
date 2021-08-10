package lostworlds.library.enums;

import net.minecraft.util.IStringSerializable;

/*
 * Author: Willatendo
 * Date: July 2, 2021
 */

public enum QuintupleBlockHalfs implements IStringSerializable
{
	BOTTOM,
	MIDDLE_BOTTOM,
	MIDDLE_MIDDLE,
	MIDDLE_TOP,
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
		else if(this == MIDDLE_BOTTOM)
		{
			return "middle_bottom";
		}
		else if(this == MIDDLE_MIDDLE)
		{
			return "middle_middle";
		}
		else if(this == MIDDLE_TOP)
		{
			return "middle_top";
		}
		else 
		{
			return "top";
		}
	}
}
