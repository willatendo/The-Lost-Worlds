package lostworlds.server.block;

import java.util.function.Supplier;

import lostworlds.server.item.LostWorldsItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;

public enum Trees implements IStringSerializable {
	ARAUCARIA("araucaria"),
	CALAMITES("calamites"),
	CONIFER("conifer"),
	CYPRESS("cypress"),
	GINKGO("ginkgo"),
	SEQUOIA("sequoia");

	private final String id;
	private Supplier<Item> softTissue;
	private Supplier<Item> dna;
	private Supplier<Item> dnaDisc;

	private Trees(String id) {
		this.id = id;
	}

	public Supplier<Item> getBarkSample() {
		switch (this) {
		default:
		case ARAUCARIA:
			return () -> LostWorldsItems.ARAUCARIA_BARK_SAMPLE.get();
		case CALAMITES:
			return () -> LostWorldsItems.CALAMITES_BARK_SAMPLE.get();
		case CONIFER:
			return () -> LostWorldsItems.CONIFER_BARK_SAMPLE.get();
		case CYPRESS:
			return () -> LostWorldsItems.CYPRESS_BARK_SAMPLE.get();
		case GINKGO:
			return () -> LostWorldsItems.GINKGO_BARK_SAMPLE.get();
		case SEQUOIA:
			return () -> LostWorldsItems.SEQUOIA_BARK_SAMPLE.get();
		}
	}

	public Supplier<Item> setSoftTissue(Supplier<Item> softTissue) {
		return this.softTissue = softTissue;
	}

	public Supplier<Item> getSoftTissue() {
		return this.softTissue;
	}

	public Supplier<Item> setDNA(Supplier<Item> dna) {
		return this.dna = dna;
	}

	public Supplier<Item> getDNA() {
		return this.dna;
	}

	public Supplier<Item> setDNADisc(Supplier<Item> dnaDisc) {
		return this.dnaDisc = dnaDisc;
	}

	public Supplier<Item> getDNADisc() {
		return this.dnaDisc;
	}

	public Supplier<Block> getSapling() {
		switch (this) {
		default:
		case ARAUCARIA:
			return () -> LostWorldsBlocks.ARAUCARIA.getBlock(4).get().get();
		case CALAMITES:
			return () -> LostWorldsBlocks.CALAMITES.getBlock(4).get().get();
		case CONIFER:
			return () -> LostWorldsBlocks.CONIFER.getBlock(4).get().get();
		case CYPRESS:
			return () -> LostWorldsBlocks.CYPRESS.getBlock(4).get().get();
		case GINKGO:
			return () -> LostWorldsBlocks.GINKGO.getBlock(4).get().get();
		case SEQUOIA:
			return () -> LostWorldsBlocks.SEQUOIA.getBlock(4).get().get();
		}
	}

	@Override
	public String getSerializedName() {
		return this.id;
	}
}
