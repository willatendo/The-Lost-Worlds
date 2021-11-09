package lostworlds.library.block;

import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;

public enum Trees implements IStringSerializable
{
	ARAUCARIA("araucaria"),
	CALAMITES("calamites"),
	CONIFER("conifer"),
	GINKGO("ginkgo"),
	SEQUOIA("sequoia");
	
	private final String id;
	private Item dna;
	
	private Trees(String id) 
	{
		this.id = id;
	}

	public Item setDNA(Item item)
	{
		return this.dna = item;
	}
	
	public Item getDNA()
	{
		return this.dna;
	}
	
	@Override
	public String getSerializedName() 
	{
		return this.id;
	}
}
