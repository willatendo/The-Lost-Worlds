package lostworlds.library.block;

import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.common.IExtensibleEnum;

public enum Trees implements IStringSerializable, IExtensibleEnum
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

	//Used for addon creation. Use second one, first one is just because IExtensibleEnum is dumb.
	public static Trees create(String name, String id)
	{
		throw new IllegalStateException("Enum not extended");
	}
	
	public static Trees register(String id)
	{
		return create(id, id);
	}
	
	@Override
	public String getSerializedName() 
	{
		return this.id;
	}
}
