package lostworlds.library.marker;

import net.minecraftforge.registries.ForgeRegistryEntry;

public class MarkerType extends ForgeRegistryEntry<MarkerType>
{
//	public static final MarkerType ANKYLOSAURID_MARKER = register("ankylosaurid_marker", MarkerTypes.ANKYLOSAURID);
//	public static final MarkerType CERATOPSID_MARKER = register("ceratopsid_marker", MarkerTypes.CERATOPSID);
//	public static final MarkerType HADROSAURID_MARKER = register("hadrosaurid_marker", MarkerTypes.HADROSAURID);
//	public static final MarkerType IGUANODONTID_MARKER = register("iguanodontid_marker", MarkerTypes.IGUANODONTID);
//	public static final MarkerType ORNITHOMIMID_MARKER = register("ornithomimid_marker", MarkerTypes.ORNITHOMIMID);
//	public static final MarkerType SAUROPODA_MARKER = register("sauropoda_marker", MarkerTypes.SAUROPODA);
//	public static final MarkerType THEROPODA_MARKER = register("theropoda_marker", MarkerTypes.THEROPODA);
	
//	private static MarkerType register(String id, MarkerTypes type)
//	{
//		MarkerType marker = new MarkerType(type);
//		marker.setRegistryName(ModUtils.rL(id));
//		ModForgeRegistries.MARKER_TYPE.register(marker);
//		return marker;
//	}
	
	private final MarkerDiet diet;
	private final MarkerTypes type;
	
	public MarkerType(MarkerTypes type)
	{
		this.diet = type == MarkerTypes.THEROPODA ? MarkerDiet.CARNIVORE : MarkerDiet.HERBIVORE;
		this.type = type;
	}
	
	public MarkerDiet getDiet() 
	{
		return this.diet;
	}
	
	public MarkerTypes getType() 
	{
		return this.type;
	}
}
