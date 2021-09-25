package lostworlds.library.block;

import lostworlds.content.server.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.common.IExtensibleEnum;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.common.util.NonNullSupplier;

public enum Plants implements IStringSerializable, IExtensibleEnum
{
	ALETHOPTERIS("alethopteris", () -> BlockInit.ALETHOPTERIS),
	BRAZILEA("brazilea", () ->  BlockInit.BRAZILEA),
	CALAMITES_SUCKOWII("calamites_suckowii", () ->  BlockInit.CALAMITES_SUCKOWII),
	CEPHALOTAXUS("cephalotaxus", () ->  BlockInit.CEPHALOTAXUS),
	DILLHOFFIA("dillhoffia", () ->  BlockInit.DILLHOFFIA),
	DUISBERGIA("duisbergia", () ->  BlockInit.DUISBERGIA),
	OSMUNDA("osmunda", () ->  BlockInit.OSMUNDA),
	WILLIAMSONIA("williamsonia", () ->  BlockInit.WILLIAMSONIA),
	ZAMITES("zamites", () ->  BlockInit.ZAMITES);
	
	private final String id;
	private final Lazy<? extends Block> block;
	private Item item;
	
	private Plants(String id, NonNullSupplier<? extends Block> block) 
	{
		this.id = id;
		this.block = Lazy.of(block::get);
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
	
	//Used for addon creation. Use second one, first one is just because IExtensibleEnum is dumb.
	public static Plants create(String name, String id, NonNullSupplier<? extends Block> block)
	{
		throw new IllegalStateException("Enum not extended");
	}
	
	public static Plants register(String id, NonNullSupplier<? extends Block> block)
	{
		return create(id, id, block);
	}
	
	@Override
	public String getSerializedName() 
	{
		return this.id;
	}
}
