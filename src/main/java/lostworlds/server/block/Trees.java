package lostworlds.server.block;

import java.util.function.Supplier;

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
	private Supplier<Item> dna;

	private Trees(String id) {
		this.id = id;
	}

	public Supplier<Item> setDNA(Supplier<Item> item) {
		return this.dna = item;
	}

	public Supplier<Item> getDNA() {
		return this.dna;
	}

	@Override
	public String getSerializedName() {
		return this.id;
	}
}
