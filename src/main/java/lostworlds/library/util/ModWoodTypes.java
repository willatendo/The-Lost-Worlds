package lostworlds.library.util;

import net.minecraft.block.WoodType;

public class ModWoodTypes extends WoodType
{
	protected ModWoodTypes(String type) 
	{
		super(type);
	}
	public static final WoodType CONIFER = WoodType.register(new ModWoodTypes("conifer"));
	public static final WoodType GINKGO = WoodType.register(new ModWoodTypes("ginkgo"));
	public static final WoodType ARAUCARIA = WoodType.register(new ModWoodTypes("araucaria"));
	public static final WoodType SCORCHED = WoodType.register(new ModWoodTypes("scorched"));
}
