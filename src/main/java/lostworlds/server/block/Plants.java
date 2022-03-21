package lostworlds.server.block;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.common.util.NonNullSupplier;

public enum Plants implements IStringSerializable {
	ALETHOPTERIS("alethopteris", () -> LostWorldsBlocks.ALETHOPTERIS),
	ARCHAEFRUTUS("archaefrutus", () -> LostWorldsBlocks.ARCHAEFRUTUS),
	BRAZILEA("brazilea", () -> LostWorldsBlocks.BRAZILEA),
	CALAMITES_SUCKOWII("calamites_suckowii", () -> LostWorldsBlocks.CALAMITES_SUCKOWII),
	CEPHALOTAXUS("cephalotaxus", () -> LostWorldsBlocks.CEPHALOTAXUS),
	CYCAD("cycad", () -> LostWorldsBlocks.CYCAD),
	DICKSONIA("dicksonia", () -> LostWorldsBlocks.DICKSONIA),
	DILLHOFFIA("dillhoffia", () -> LostWorldsBlocks.DILLHOFFIA),
	DUISBERGIA("duisbergia", () -> LostWorldsBlocks.DUISBERGIA),
	EUDICOTS("eudicots", () -> LostWorldsBlocks.EUDICOTS),
	MAGNOLIA("magnolia", () -> LostWorldsBlocks.MAGNOLIA),
	OSMUNDA("osmunda", () -> LostWorldsBlocks.OSMUNDA),
	WILLIAMSONIA("williamsonia", () -> LostWorldsBlocks.WILLIAMSONIA),
	ZAMITES("zamites", () -> LostWorldsBlocks.ZAMITES);

	private final String id;
	private final Lazy<? extends Block> block;
	private Item item;
	private Item seed;
	private Item dna;

	private Plants(String id, NonNullSupplier<? extends Block> block) {
		this.id = id;
		this.block = Lazy.of(block::get);
	}

	public Item setDNA(Item item) {
		return this.dna = item;
	}

	public Item getDNA() {
		return this.dna;
	}

	public Block getPlant() {
		return this.block.get();
	}

	public Item setDrop(Item item) {
		return this.item = item;
	}

	public Item getDrop() {
		return this.item;
	}

	public Item setSeed(Item seed) {
		return this.seed = seed;
	}

	public Item getSeed() {
		return this.seed;
	}

	@Override
	public String getSerializedName() {
		return this.id;
	}
}
