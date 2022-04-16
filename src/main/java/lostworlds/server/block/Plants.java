package lostworlds.server.block;

import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;

public enum Plants implements IStringSerializable {
	ALETHOPTERIS("alethopteris", () -> LostWorldsBlocks.ALETHOPTERIS.get()),
	ARCHAEFRUTUS("archaefrutus", () -> LostWorldsBlocks.ARCHAEFRUTUS.get()),
	BRAZILEA("brazilea", () -> LostWorldsBlocks.BRAZILEA.get()),
	CALAMITES_SUCKOWII("calamites_suckowii", () -> LostWorldsBlocks.CALAMITES_SUCKOWII.get()),
	CEPHALOTAXUS("cephalotaxus", () -> LostWorldsBlocks.CEPHALOTAXUS.get()),
	CYCAD("cycad", () -> LostWorldsBlocks.CYCAD.get()),
	DICKSONIA("dicksonia", () -> LostWorldsBlocks.DICKSONIA.get()),
	DILLHOFFIA("dillhoffia", () -> LostWorldsBlocks.DILLHOFFIA.get()),
	DUISBERGIA("duisbergia", () -> LostWorldsBlocks.DUISBERGIA.get()),
	EUDICOTS("eudicots", () -> LostWorldsBlocks.EUDICOTS.get()),
	MAGNOLIA("magnolia", () -> LostWorldsBlocks.MAGNOLIA.get()),
	OSMUNDA("osmunda", () -> LostWorldsBlocks.OSMUNDA.get()),
	WILLIAMSONIA("williamsonia", () -> LostWorldsBlocks.WILLIAMSONIA.get()),
	ZAMITES("zamites", () -> LostWorldsBlocks.ZAMITES.get());

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
