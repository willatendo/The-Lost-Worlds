package lostworlds.library.entity;

import net.minecraft.util.IStringSerializable;
import net.minecraftforge.common.IExtensibleEnum;

public enum TimeEras implements IStringSerializable, IExtensibleEnum
{
	PRECAMBRIAN_EON("precambrian_eon"), 
	CAMBRIAN_PERIOD("cambrian_period"), 
	ORDOVICIAN_PERIOD("ordovician_period"), 
	SILURIAN_PERIOD("silurian_period"), 
	DEVONIAN_PERIOD("devonian_period"), 
	CARBONIFEROUS_PERIOD("carboniferous_period"), 
	PERMIAN_PERIOD("permian_period"), 
	TRIASSIC_PERIOD("triassic_period"), 
	JURASSIC_PERIOD("jurassic_period"), 
	CRETACEOUS_PERIOD("cretaceous_period"), 
	PALEOCENE_EPOCH("paleocene_epoch"),
	EOCENE_EPOCH("eocene_epoch"), 
	OLIGOCENE_EPOCH("oligocene_epoch"), 
	MIOCENE_EPOCH("miocene_epoch"), 
	PLIOCENE_EPOCH("pliocene_epoch"),
	PLEISTOCENE_EPOCH("pleistocene_epoch"), 
	HOLOCENE_EPOCH("holocene_epoch"), 
	MODERN_MINECRAFT("modern_minecraft");
	
	private final String id;
	
	private TimeEras(String id) 
	{
		this.id = id;
	}
	
	//Used for addon creation. Use second one, first one is just because IExtensibleEnum is dumb.
	public static TimeEras create(String name, String id)
	{
		throw new IllegalStateException("Enum not extended");
	}
	
	public static TimeEras register(String id)
	{
		return create(id, id);
	}
	
	@Override
	public String getSerializedName() 
	{
		return id;
	}
}
