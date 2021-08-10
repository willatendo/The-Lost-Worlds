package lostworlds.library.enums;

import net.minecraft.util.IStringSerializable;

/*
 * Author: Willatendo
 * Date: July 4, 2021
 */

public enum RockSides implements IStringSerializable
{
	CENTRE,
	NORTH,
	SOUTH,
	EAST,
	WEST,
	NORTH_WEST,
	SOUTH_WEST;
	
	@Override
	public String toString()
	{
		return this.getSerializedName();
	}
	
	@Override
	public String getSerializedName() 
	{
		if(this == CENTRE)
		{
			return "centre";
		}
		else if(this == NORTH)
		{
			return "north";
		}
		else if(this == SOUTH)
		{
			return "south";
		}
		else if(this == EAST)
		{
			return "east";
		}
		else if(this == WEST)
		{
			return "west";
		}
		else if(this == NORTH_WEST)
		{
			return "north_west";
		}
		else
		{
			return "south_west";
		}
	}
}
