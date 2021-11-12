package lostworlds.library.block;

import lostworlds.content.server.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.common.util.NonNullSupplier;

public enum Plants implements IStringSerializable
{
	ALETHOPTERIS("alethopteris", () -> BlockInit.ALETHOPTERIS),
	ARCHAEFRUTUS("archaefrutus", () -> BlockInit.ARCHAEFRUTUS),
	BRAZILEA("brazilea", () ->  BlockInit.BRAZILEA),
	CALAMITES_SUCKOWII("calamites_suckowii", () ->  BlockInit.CALAMITES_SUCKOWII),
	CEPHALOTAXUS("cephalotaxus", () ->  BlockInit.CEPHALOTAXUS),
	CYCAD("cycad", () ->  BlockInit.CYCAD),
	DICKSONIA("dicksonia", () ->  BlockInit.DICKSONIA),
	DILLHOFFIA("dillhoffia", () ->  BlockInit.DILLHOFFIA),
	DUISBERGIA("duisbergia", () ->  BlockInit.DUISBERGIA),
	EUDICOTS("eudicots", () ->  BlockInit.EUDICOTS),
	MAGNOLIA("magnolia", () ->  BlockInit.MAGNOLIA),
	OSMUNDA("osmunda", () ->  BlockInit.OSMUNDA),
	WILLIAMSONIA("williamsonia", () ->  BlockInit.WILLIAMSONIA),
	ZAMITES("zamites", () ->  BlockInit.ZAMITES);
	
	private final String id;
	private final Lazy<? extends Block> block;
	private Item item;
	private Item dna;
	
	private Plants(String id, NonNullSupplier<? extends Block> block) 
	{
		this.id = id;
		this.block = Lazy.of(block::get);
	}

	public Item setDNA(Item item)
	{
		return this.dna = item;
	}
	
	public Item getDNA()
	{
		return this.dna;
	}
	
	public Block getPlant()
	{
		return this.block.get();
	}
	
	public Item setDrop(Item item)
	{
		return this.item = item;
	}
	
	public Item getDrop()
	{
		return this.item;
	}
	
	@Override
	public String getSerializedName() 
	{
		return this.id;
	}
}
