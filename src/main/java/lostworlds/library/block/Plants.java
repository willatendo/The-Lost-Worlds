package lostworlds.library.block;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.common.IExtensibleEnum;

public enum Plants implements IStringSerializable, IExtensibleEnum
{
	ALETHOPTERIS("alethopteris"),
	BRAZILEA("brazilea"),
	CALAMITES_SUCKOWII("calamites_suckowii"),
	CEPHALOTAXUS("cephalotaxus"),
	DILLHOFFIA("dillhoffia"),
	DUISBERGIA("duisbergia"),
	OSMUNDA("osmunda"),
	WILLIAMSONIA("williamsonia"),
	ZAMITES("zamites");
	
	private final String id;
	
	private Plants(String id) 
	{
		this.id = id;
	}
	
	public Item getDrop()
	{
//		switch(this)
//		{
//			case ALETHOPTERIS:
//				return PlantInit.ALETHOPTERIS_FOSSIL;
//			case BRAZILEA:
//				return PlantInit.BRAZILEA_FOSSIL;
//			case CALAMITES_SUCKOWII:
//				return PlantInit.CALAMITES_SUCKOWII_FOSSIL;
//			case CEPHALOTAXUS:
//				return PlantInit.CEPHALOTAXUS_FOSSIL;
//			case DILLHOFFIA:
//				return PlantInit.DILLHOFFIA_FOSSIL;
//			case DUISBERGIA:
//				return PlantInit.DUISBERGIA_FOSSIL;
//			case OSMUNDA:
//				return PlantInit.OSMUNDA_FOSSIL;
//			case WILLIAMSONIA:
//				return PlantInit.WILLIAMSONIA_FOSSIL;
//			case ZAMITES:
//			default:
//				return PlantInit.ZAMITES_FOSSIL;
//		}
		
		return Items.BONE;
	}
	
	//Used for addon creation. Use second one, first one is just because IExtensibleEnum is dumb.
	public static Plants create(String name, String id)
	{
		throw new IllegalStateException("Enum not extended");
	}
	
	public static Plants register(String id)
	{
		return create(id, id);
	}
	
	@Override
	public String getSerializedName() 
	{
		return this.id;
	}
}
