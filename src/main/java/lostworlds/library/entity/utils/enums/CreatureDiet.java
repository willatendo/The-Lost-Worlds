package lostworlds.library.entity.utils.enums;

import net.minecraft.util.IStringSerializable;

public enum CreatureDiet implements IStringSerializable
{
	NONE("none"),
	CARNIVORE("carnivore"),
	HERBIVORE("herbivore"),
	INSECTIVORE("insectivore"),
	OMNIVORE("omnivore"),
	PISCIVORE("piscivore");
	
	public final String id;
	
	private CreatureDiet(String id) 
	{
		this.id = id;
	}
	
	@Override
	public String getSerializedName() 
	{
		return this.id;
	}
}
