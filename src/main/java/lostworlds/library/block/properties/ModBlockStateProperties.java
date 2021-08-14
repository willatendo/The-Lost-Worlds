package lostworlds.library.block.properties;

import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;

public class ModBlockStateProperties 
{
	public static final BooleanProperty ON = BooleanProperty.create("on");
	public static final BooleanProperty LAVALOGGED = BooleanProperty.create("lavalogged");

	public static final IntegerProperty SMALL_EGGS = IntegerProperty.create("small_eggs", 1, 5);
	public static final IntegerProperty MEDIUM_EGGS = IntegerProperty.create("medium_eggs", 1, 3);
	public static final IntegerProperty LARGE_EGGS = IntegerProperty.create("large_eggs", 1, 2);
}
