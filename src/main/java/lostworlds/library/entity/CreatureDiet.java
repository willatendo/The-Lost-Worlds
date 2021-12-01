package lostworlds.library.entity;

import net.minecraft.util.IStringSerializable;

public enum CreatureDiet implements IStringSerializable
{
	NONE("none"),
	CARNIVORE("carnivore"),
	HERBIVORE("herbivore"),
	INSECTIVORE("insectivore"),
	OMNIVORE("omnivore"),
	PISCIVORE("piscivore");

	private final String name;
	
	private CreatureDiet(String name) 
	{
		this.name = name;
	}
	
	@Override
	public String getSerializedName() 
	{
		return this.name;
	}
}
