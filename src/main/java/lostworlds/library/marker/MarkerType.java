package lostworlds.library.marker;

import lostworlds.content.ModUtils;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class MarkerType extends ForgeRegistryEntry<MarkerType>
{
	public static final MarkerType ANKYLOSAURID_MARKER = register("ankylosaurid_marker", MarkerGroups.ANKYLOSAURID);
	public static final MarkerType CERATOPSID_MARKER = register("ceratopsid_marker", MarkerGroups.CERATOPSID);
	public static final MarkerType HADROSAURID_MARKER = register("hadrosaurid_marker", MarkerGroups.HADROSAURID);
	public static final MarkerType IGUANODONTID_MARKER = register("iguanodontid_marker", MarkerGroups.IGUANODONTID);
	public static final MarkerType ORNITHOMIMID_MARKER = register("ornithomimid_marker", MarkerGroups.ORNITHOMIMID);
	public static final MarkerType SAUROPODA_MARKER = register("sauropoda_marker", MarkerGroups.SAUROPODA);
	public static final MarkerType THEROPODA_MARKER = register("theropoda_marker", MarkerGroups.THEROPODA);
	
	private static MarkerType register(String id, MarkerGroups type)
	{
		MarkerType marker = new MarkerType(type);
		marker.setRegistryName(ModUtils.rL(id));
		return marker;
	}
	
	private final MarkerDiet diet;
	private final MarkerGroups type;
	
	public MarkerType(MarkerGroups type)
	{
		this.diet = type == MarkerGroups.THEROPODA ? MarkerDiet.CARNIVORE : MarkerDiet.HERBIVORE;
		this.type = type;
	}
	
	public MarkerDiet getDiet() 
	{
		return this.diet;
	}
	
	public MarkerGroups getType() 
	{
		return this.type;
	}
	
	public static void init() { }
}
