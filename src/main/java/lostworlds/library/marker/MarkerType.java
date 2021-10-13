package lostworlds.library.marker;

import lostworlds.library.entity.DinoDiet;
import net.minecraftforge.common.IExtensibleEnum;

public enum MarkerType implements IExtensibleEnum
{
	;
	
	private DinoDiet diet;
	
	private MarkerType(DinoDiet diet) 
	{
		this.diet = diet;
	}
	
	public DinoDiet getDiet()
	{
		return this.diet;
	}
	
	public int getWidth()
	{
		return 32;
	}
	
	public int getHeight()
	{
		return 32;
	}
	
	public static MarkerType create(String name, DinoDiet diet)
	{
		throw new IllegalStateException("Enum not extended");
	}
}
