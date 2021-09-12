package lostworlds.library.entity;

import net.minecraft.util.IStringSerializable;

public enum TimeEras implements IStringSerializable
{
	PRECAMBRIAN_EON, 
	CAMBRIAN_PERIOD, 
	ORDOVICIAN_PERIOD, 
	SILURIAN_PERIOD, 
	DEVONIAN_PERIOD, 
	CARBONIFEROUS_PERIOD, 
	PERMIAN_PERIOD, 
	TRIASSIC_PERIOD, 
	JURASSIC_PERIOD, 
	CRETACEOUS_PERIOD, 
	PALEOCENE_EPOCH,
	EOCENE_EPOCH, 
	OLIGOCENE_EPOCH, 
	MIOCENE_EPOCH, 
	PLIOCENE_EPOCH, 
	PLEISTOCENE_EPOCH, 
	HOLOCENE_EPOCH, 
	MODERN_MINECRAFT;
	
	@Override
	public String getSerializedName() 
	{
		switch(this)
		{
			case PRECAMBRIAN_EON:
				return "precambrian_eon";
			case CAMBRIAN_PERIOD:
				return "cambrian_period";
			case ORDOVICIAN_PERIOD:
				return "ordovician_period";
			case SILURIAN_PERIOD:
				return "silurian_period";
			case DEVONIAN_PERIOD:
				return "devonian_period";
			case CARBONIFEROUS_PERIOD:
				return "carboniferous_period";
			case PERMIAN_PERIOD:
				return "permian_period";
			case TRIASSIC_PERIOD:
				return "triassic_period";
			case JURASSIC_PERIOD:
				return "jurassic_period";
			case CRETACEOUS_PERIOD:
				return "cretaceous_period";
			case PALEOCENE_EPOCH:
				return "paleocene_epoch";
			case EOCENE_EPOCH:
				return "eocene_epoch";
			case OLIGOCENE_EPOCH:
				return "oligocene_epoch";
			case MIOCENE_EPOCH:
				return "miocene_epoch";
			case PLIOCENE_EPOCH:
				return "pliocene_epoch";
			case PLEISTOCENE_EPOCH:
				return "pleistocene_epoch";
			case HOLOCENE_EPOCH:
				return "holocene_epoch";
			case MODERN_MINECRAFT:
			default:
				return "modern_minecraft";
		}
	}
}
