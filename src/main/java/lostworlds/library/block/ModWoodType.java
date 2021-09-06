package lostworlds.library.block;

import net.minecraft.block.WoodType;

public class ModWoodType extends WoodType
{
	public ModWoodType(String type) 
	{
		super(type);
	}
	
	public static final WoodType ARAUCARIA = register(new ModWoodType("araucaria"));
	public static final WoodType CONIFER = register(new ModWoodType("conifer"));
	public static final WoodType GINKGO = register(new ModWoodType("ginkgo"));
	public static final WoodType GLASS = register(new ModWoodType("glass"));
	public static final WoodType SCORCHED = register(new ModWoodType("scorched"));
}