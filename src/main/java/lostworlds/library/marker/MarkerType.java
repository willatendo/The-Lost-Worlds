package lostworlds.library.marker;

import lostworlds.library.entity.CreatureDiet;
import net.minecraftforge.common.IExtensibleEnum;

public enum MarkerType implements IExtensibleEnum
{
	;
	
	private CreatureDiet diet;
	
	private MarkerType(CreatureDiet diet) 
	{
		this.diet = diet;
	}
	
	public CreatureDiet getDiet()
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
	
	public static MarkerType create(String name, CreatureDiet diet)
	{
		throw new IllegalStateException("Enum not extended");
	}
}
