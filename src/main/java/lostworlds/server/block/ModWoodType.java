package lostworlds.server.block;

import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodType extends WoodType {
	public ModWoodType(String type) {
		super(type);
	}

	public static final WoodType ARAUCARIA = register(new ModWoodType("araucaria"));
	public static final WoodType CALAMITES = register(new ModWoodType("calamites"));
	public static final WoodType CONIFER = register(new ModWoodType("conifer"));
	public static final WoodType CYPRESS = register(new ModWoodType("cypress"));
	public static final WoodType GINKGO = register(new ModWoodType("ginkgo"));
	public static final WoodType GLASS = register(new ModWoodType("glass"));
	public static final WoodType SCORCHED = register(new ModWoodType("scorched"));
	public static final WoodType SEQUOIA = register(new ModWoodType("sequoia"));
}
