package lostworlds.server.block;

import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;

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
	private final Supplier<Block> block;
	private Supplier<Item> item;
	private Supplier<Item> seed;
	private Supplier<Item> dna;

	private Plants(String id, Supplier<Block> block) {
		this.id = id;
		this.block = block;
	}

	public Supplier<Item> setDNA(Supplier<Item> item) {
		return this.dna = item;
	}

	public Supplier<Item> getDNA() {
		return this.dna;
	}

	public Supplier<Block> getPlant() {
		return this.block;
	}

	public Supplier<Item> setDrop(Supplier<Item> item) {
		return this.item = item;
	}

	public Supplier<Item> getDrop() {
		return this.item;
	}

	public Supplier<Item> setSeed(Supplier<Item> seed) {
		return this.seed = seed;
	}

	public Supplier<Item> getSeed() {
		return this.seed;
	}

	@Override
	public String getSerializedName() {
		return this.id;
	}
}
