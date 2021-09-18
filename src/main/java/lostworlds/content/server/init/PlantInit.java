package lostworlds.content.server.init;

import lostworlds.library.block.Plants;
import lostworlds.library.block.Trees;
import lostworlds.library.item.block.SeedItem;
import lostworlds.library.item.builder.ItemBuilder;
import lostworlds.library.tab.ModItemGroup;
import lostworlds.library.util.ModUtils;
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
		ModUtils.LOGGER.debug("Registering Mod Plant Items");
		
		for(Plants plants : Plants.values())
		{
			Item fossil = ItemBuilder.create(plants.toString().toLowerCase() + "_fossil", ModItemGroup.PLANTS);
			ItemBuilder.create(plants.toString().toLowerCase() + "_soft_tissue", ModItemGroup.PLANTS);
			ItemBuilder.create(plants.toString().toLowerCase() + "_dna", ModItemGroup.PLANTS);
			ItemBuilder.create(plants.toString().toLowerCase() + "_dna_disc", ModItemGroup.PLANTS);
			if(plants == Plants.ALETHOPTERIS)
			{
				ALETHOPTERIS_FOSSIL = fossil;
				SeedItem.create(plants.toString().toLowerCase(), BlockInit.ALETHOPTERIS);
			}
			if(plants == Plants.BRAZILEA)
			{
				BRAZILEA_FOSSIL = fossil;
				SeedItem.create(plants.toString().toLowerCase(), BlockInit.BRAZILEA);
			}
			if(plants == Plants.CALAMITES_SUCKOWII)
			{
				CALAMITES_SUCKOWII_FOSSIL = fossil;
				SeedItem.create(plants.toString().toLowerCase(), BlockInit.CALAMITES_SUCKOWII);
			}
			if(plants == Plants.CEPHALOTAXUS)
			{
				CEPHALOTAXUS_FOSSIL = fossil;
				SeedItem.create(plants.toString().toLowerCase(), BlockInit.CEPHALOTAXUS);
			}
			if(plants == Plants.DILLHOFFIA)
			{
				DILLHOFFIA_FOSSIL = fossil;
				SeedItem.create(plants.toString().toLowerCase(), BlockInit.DILLHOFFIA);
			}
			if(plants == Plants.DUISBERGIA)
			{
				DUISBERGIA_FOSSIL = fossil;
				SeedItem.create(plants.toString().toLowerCase(), BlockInit.DUISBERGIA);
			}
			if(plants == Plants.OSMUNDA)
			{
				OSMUNDA_FOSSIL = fossil;
				SeedItem.create(plants.toString().toLowerCase(), BlockInit.OSMUNDA);
			}
			if(plants == Plants.WILLIAMSONIA)
			{
				WILLIAMSONIA_FOSSIL = fossil;
				SeedItem.create(plants.toString().toLowerCase(), BlockInit.WILLIAMSONIA);
			}
			if(plants == Plants.ZAMITES)
			{
				ZAMITES_FOSSIL = fossil;
				SeedItem.create(plants.toString().toLowerCase(), BlockInit.ZAMITES);
			}
		}
		
		for(Trees trees : Trees.values())
		{
			ItemBuilder.create(trees.toString().toLowerCase() + "_soft_tissue", ModItemGroup.PLANTS);
			ItemBuilder.create(trees.toString().toLowerCase() + "_dna", ModItemGroup.PLANTS);		
			ItemBuilder.create(trees.toString().toLowerCase() + "_dna_disc", ModItemGroup.PLANTS);
		}
	}
}
