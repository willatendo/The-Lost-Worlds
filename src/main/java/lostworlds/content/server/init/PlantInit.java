package lostworlds.content.server.init;

import lostworlds.library.block.Plants;
import lostworlds.library.block.Trees;
import lostworlds.library.item.builder.ItemBuilder;
import lostworlds.library.tab.ModItemGroup;
import net.minecraft.item.Item;

public class PlantInit 
{	
	public static Item ALETHOPTERIS_FOSSIL;
	public static Item BRAZILEA_FOSSIL;
	public static Item CALAMITES_SUCKOWII_FOSSIL;
	public static Item CEPHALOTAXUS_FOSSIL;
	public static Item DILLHOFFIA_FOSSIL;
	public static Item DUISBERGIA_FOSSIL;
	public static Item OSMUNDA_FOSSIL;
	public static Item WILLIAMSONIA_FOSSIL;
	public static Item ZAMITES_FOSSIL;
	
	public static void init()
	{
		for(Plants plants : Plants.values())
		{
			Item fossil = ItemBuilder.create(plants.toString().toLowerCase() + "_fossil", ModItemGroup.PLANTS);
			if(plants == Plants.ALETHOPTERIS)
			{
				ALETHOPTERIS_FOSSIL = fossil;
			}
			if(plants == Plants.BRAZILEA)
			{
				BRAZILEA_FOSSIL = fossil;
			}
			if(plants == Plants.CALAMITES_SUCKOWII)
			{
				CALAMITES_SUCKOWII_FOSSIL = fossil;
			}
			if(plants == Plants.CEPHALOTAXUS)
			{
				CEPHALOTAXUS_FOSSIL = fossil;
			}
			if(plants == Plants.DILLHOFFIA)
			{
				DILLHOFFIA_FOSSIL = fossil;
			}
			if(plants == Plants.DUISBERGIA)
			{
				DUISBERGIA_FOSSIL = fossil;
			}
			if(plants == Plants.OSMUNDA)
			{
				OSMUNDA_FOSSIL = fossil;
			}
			if(plants == Plants.WILLIAMSONIA)
			{
				WILLIAMSONIA_FOSSIL = fossil;
			}
			if(plants == Plants.ZAMITES)
			{
				ZAMITES_FOSSIL = fossil;
			}
				
				
			ItemBuilder.create(plants.toString().toLowerCase() + "_soft_tissue", ModItemGroup.PLANTS);
			ItemBuilder.create(plants.toString().toLowerCase() + "_dna", ModItemGroup.PLANTS);
			ItemBuilder.create(plants.toString().toLowerCase() + "_dna_disc", ModItemGroup.PLANTS);
			//ItemBuilder.create(plants.toString().toLowerCase() + "_seeds");
		}
		
		for(Trees trees : Trees.values())
		{
			ItemBuilder.create(trees.toString().toLowerCase() + "_soft_tissue", ModItemGroup.PLANTS);
			ItemBuilder.create(trees.toString().toLowerCase() + "_dna", ModItemGroup.PLANTS);		
			ItemBuilder.create(trees.toString().toLowerCase() + "_dna_disc", ModItemGroup.PLANTS);
		}
	}
}
